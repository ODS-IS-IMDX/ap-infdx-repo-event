// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.presentation.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.spatialid.app.api.application.service.IGetEventInfoService;
import com.spatialid.app.api.constatnts.EventInfoConstants;
import com.spatialid.app.api.presentation.dto.CallSidAttributeResponseDto;
import com.spatialid.app.api.presentation.dto.EventInfoRequestDto;
import com.spatialid.app.api.presentation.dto.EventInfoResponseDto;
import com.spatialid.app.api.presentation.dto.FlattenedRequestDto;
import com.spatialid.app.common.exception.subexception.ParamErrorException;
import com.spatialid.app.common.validation.Sequence;

/**
 * イベント情報取得APIを管理するControllerクラス．
 * 
 * @auther ukai jun
 * @version 1.1 2024/11/13
 */
@RestController
public class EventInfoController {

    /**
     * サービスインターフェース.
     */
    private final IGetEventInfoService eventInfoService;

    /**
     * コンストラクタインジェクション.
     * 
     * @param eventInfoService
     */
    public EventInfoController(IGetEventInfoService eventInfoService) {

        this.eventInfoService = eventInfoService;
    }
    
    /**
     * イベント情報を取得するメソッド.
     * 
     * @param eventInfoRequestDto 送られてきたリクエストDTO.
     * @return eventInfoResponseDto 返却するレスポンスDTO.
     * @throws Exception 
     */
    @GetMapping(EventInfoConstants.EVENT_INFO_URI)
    public EventInfoResponseDto getEventInfo(
            @Validated(Sequence.class) @ModelAttribute EventInfoRequestDto eventInfoRequestDto, BindingResult br) throws Exception {

        // 1.パラメータチェックを行う.
        if (br.hasErrors()) {

            throw new ParamErrorException(br);
        }

        // 2.空間ID平面化を行う.
        FlattenedRequestDto flattenedRequestDto = eventInfoService.flattenSid(eventInfoRequestDto);

        // 3.空間・属性情報参照(イベント情報)を呼び出す.
        CallSidAttributeResponseDto callSidAttributeResponse = eventInfoService.callSidAttribute(flattenedRequestDto);

        // 4.レスポンスデータ項目を設定する.
        EventInfoResponseDto eventInfoResponseDto = eventInfoService.setResponseData(callSidAttributeResponse);

        return eventInfoResponseDto;
    }

}