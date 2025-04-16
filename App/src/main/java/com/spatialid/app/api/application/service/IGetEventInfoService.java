// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.application.service;

import org.springframework.stereotype.Service;

import com.spatialid.app.api.presentation.dto.CallSidAttributeResponseDto;
import com.spatialid.app.api.presentation.dto.EventInfoRequestDto;
import com.spatialid.app.api.presentation.dto.EventInfoResponseDto;
import com.spatialid.app.api.presentation.dto.FlattenedRequestDto;

/**
 * イベント情報取得APIの処理を担うサービスインターフェース.
 * 
 * @author ukai jun
 * @version 1.1 2024/11/13
 * 
 */
@Service
public interface IGetEventInfoService {

    /**
     * 空間IDの平面化処理を行うメソッド.
     * 
     * @param sid 空間ID
     * @return 平面化処理後の空間ID
     */
    public FlattenedRequestDto flattenSid(EventInfoRequestDto eventInfoRequestDto);
    
    /**
     * 空間・属性情報参照(イベント情報)APIの呼び出しを行うメソッド.
     * 
     * @param sidAttributeRequest 空間・属性情報参照(イベント情報)APIへ送るリクエスト内容
     * @return ResponseEntity<CallSidAttributeResponseDto> 空間・属性情報参照(イベント情報)APIから返却されるレスポンス
     * @throws Exception 
     */
    public CallSidAttributeResponseDto callSidAttribute(FlattenedRequestDto flattenedRequestDto) throws Exception;

    /**
     * クライアントに返却するレスポンスを設定するメソッド.
     * 
     * @return EventInfoResponseDto イベント情報取得APIが返却するレスポンス.
     */
    public EventInfoResponseDto setResponseData(CallSidAttributeResponseDto callSidAttributeResponseDto);

}