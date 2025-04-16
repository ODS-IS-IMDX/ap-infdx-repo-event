// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.presentation.dto;

import java.util.List;

import com.spatialid.app.common.resource.ErrorResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 空間・属性情報参照(イベント情報)APIを内部で呼び出した際に返却されるレスポンスを定義したDTOクラス．<br>
 * {@link SidAttributeDto}を子DTOとして保持する．
 * 
 * @author ukai jun
 * @version 1.1 2024/08/29
 */
@Data
@Builder
@AllArgsConstructor
public class CallSidAttributeResponseDto {

    /**
     * 空間・属性情報リスト
     */
    private List<CallSidAttributeDto> sidAttributeList;

    /**
     * エラーレスポンス
     */
    private ErrorResponse errorResponse;

}