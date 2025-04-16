// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.presentation.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * {@link CallSidAttributeResponseDto}の子DTO．<br>
 * 空間IDのリストと、そこに紐づいた情報を格納する．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/09/20
 */
@Data
public class CallSidAttributeDto {

    /**
     * イベント属性．
     */
    private Map<String, String> eventAttribute;

    /**
     * イベント種別．
     */
    private String eventType;

    /**
     * イベントID．
     */
    private int eventId;

    /**
     * インフラ事業者ID．
     */
    private String infraCompanyId;

    /**
     * 空間IDのリスト．
     */
    private List<String> sidList;

}
