// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spatialid.app.api.constatnts.EventInfoConstants;
import com.spatialid.app.api.presentation.dto.AttributeDto;
import com.spatialid.app.api.presentation.dto.CallSidAttributeDto;
import com.spatialid.app.api.presentation.dto.CallSidAttributeRequestDto;
import com.spatialid.app.api.presentation.dto.CallSidAttributeResponseDto;
import com.spatialid.app.api.presentation.dto.EventInfoDto;
import com.spatialid.app.api.presentation.dto.EventInfoRequestDto;
import com.spatialid.app.api.presentation.dto.EventInfoResponseDto;
import com.spatialid.app.api.presentation.dto.FlattenedRequestDto;
import com.spatialid.app.common.constant.RestApiConstants;
import com.spatialid.app.common.exception.subexception.DataRangeException;
import com.spatialid.app.common.exception.subexception.InternalApiCallingException;
import com.spatialid.app.common.resource.ErrorResponse;

/**
 * イベント情報取得APIの処理を担うサービスクラス.
 * 
 * @author ukai jun
 * @version 1.1 2024/11/13
 */
@Service
public class EventInfoServiceImpl implements IGetEventInfoService {

    /**
     * レストクライアント.
     */
    private final RestClient restClient;

    /**
     * モデルマッパー.
     */
    private final ModelMapper modelMapper;

    /**
     * オブジェクトマッパー.
     */
    private final ObjectMapper objectMapper;

    /**
     * コンストラクタインジェクション.
     * 
     * @param restClient
     * @param modelMapper
     * @param objectMapper
     */
    public EventInfoServiceImpl(@Qualifier("commonApiClient")RestClient restClient, ModelMapper modelMapper,
            ObjectMapper objectMapper) {

        this.restClient = restClient;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public FlattenedRequestDto flattenSid(EventInfoRequestDto eventInfoRequestDto) {
        
        // 空間IDのfインデックス（標高）を0に設定する.
        final List<String> flattensids = eventInfoRequestDto.getSids().stream().map(sid -> {

            String[] splittedSid = sid.split("/");

            splittedSid[1] = "0";

            return String.join("/", splittedSid);
        })
                // 重複を削除する.
                .distinct().collect(Collectors.toList());

        FlattenedRequestDto flattenedRequestDto = FlattenedRequestDto.builder()
                .sids(flattensids)
                .infraCompanyIds(eventInfoRequestDto.getInfraCompanyIds())
                .returnZoomLevel(eventInfoRequestDto.getReturnZoomLevel())
                .build();

        return flattenedRequestDto;
    }

    @Override
    public CallSidAttributeResponseDto callSidAttribute(FlattenedRequestDto flattenedRequestDto) throws Exception {
        
        // 空間・属性情報参照(イベント)API用リクエストDTOに値を設定.
        CallSidAttributeRequestDto callSidAttributeRequestDto = CallSidAttributeRequestDto.builder()
                .sidList(flattenedRequestDto.getSids())
                .infraCompanyIdList(flattenedRequestDto.getInfraCompanyIds())
                .returnZoomLevel(flattenedRequestDto.getReturnZoomLevel())
                .build();

        // 空間・属性情報参照(イベント)APIにリクエストを送り、レスポンスを得る.
        ResponseEntity<String> rawResponse;
        try {
            rawResponse = restClient.method(HttpMethod.POST)
                    .uri(EventInfoConstants.SID_ATTRIBUTE_URI)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(callSidAttributeRequestDto)
                    .retrieve()
                    .toEntity(String.class);

        } catch (Exception e) {
        
            // エラーが送出された場合、代わりに内部API呼び出し例外を送出する.
            throw new InternalApiCallingException(EventInfoConstants.SID_ATTRIBUTE_EVENT_NAME);
        }

        // ステータスコード2xx以外の場合、エラー処理を行う.
        if (rawResponse.getStatusCode().isError()) {

            ErrorResponse errorResponse = objectMapper.readValue(rawResponse.getBody(), ErrorResponse.class);

            String errMsg = errorResponse.getMessage();

            if (errMsg.matches(EventInfoConstants.REGEX_INVALID_DATA_RANGE)) {
                
                // データ整備範囲外例外を送出する.
                throw new DataRangeException();
            }

            // 内部API呼び出し例外を送出する.
            throw new InternalApiCallingException(EventInfoConstants.SID_ATTRIBUTE_EVENT_NAME);

        } else {

            // エラー無しの場合、ボディをマッピングし返却する.
            List<CallSidAttributeDto> sidAttributeList = objectMapper.readValue(
                    objectMapper.readTree(rawResponse.getBody()).path("sidAttributeList").toString(),
                    new TypeReference<>() {
                    });

            CallSidAttributeResponseDto callSidAttributeResponseDto = CallSidAttributeResponseDto.builder()
                    .sidAttributeList(sidAttributeList)
                    .build();

            return callSidAttributeResponseDto;
        }
    }

    @Override
    public EventInfoResponseDto setResponseData(CallSidAttributeResponseDto callSidAttributeResponseDto) {

        List<CallSidAttributeDto> callSidAttributeDtoList = callSidAttributeResponseDto.getSidAttributeList();
        
        // 空間・属性情報参照API(イベント情報)のレスポンスDTOからイベントリストを取得する.
        List<EventInfoDto> eventList = modelMapper.map(callSidAttributeDtoList, new TypeToken<>() {}.getType());

        // レスポンスDTOに設定し、返却する.
        AttributeDto attributeDto = AttributeDto.builder()
                .eventList(eventList)
                .build();

        EventInfoResponseDto eventInfoResponseDto = EventInfoResponseDto.builder()
                .dataModelType(RestApiConstants.TEST1)
                .attribute(attributeDto)
                .build();

        return eventInfoResponseDto;
    }
}