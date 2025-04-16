// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.presentation.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 空間・属性情報参照(イベント情報)APIを内部で呼び出す際のリクエストを定義したDTOクラス.
 * 
 * @author ukai jun
 * @version 1.1 2024/11/18
 */
@Data
@Builder
@AllArgsConstructor
public class CallSidAttributeRequestDto {

    /**
     * 空間ID.
     */
    private List<String> sidList;

    /**
     * インフラ事業者IDのリスト.
     */
    private List<String> infraCompanyIdList;

    /**
     * 返却ズームレベル.
     */
    private BigDecimal returnZoomLevel;

}