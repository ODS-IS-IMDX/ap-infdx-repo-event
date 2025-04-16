// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.presentation.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 空間・属性情報参照(イベント情報)APIを呼び出す際に使用するリクエストDTOクラス．
 * 
 * @author ukai jun
 * @version 1.1 2024/11/13
 */
@Data
@Builder
@AllArgsConstructor
public class FlattenedRequestDto {

    /**
     * 空間IDのリスト．
     */
    private List<String> sids;

    /**
     * インフラ事業者リスト．
     */
    private List<String> infraCompanyIds;

    /**
     * 返却ズームレベル．
     */
    private BigDecimal returnZoomLevel;
}