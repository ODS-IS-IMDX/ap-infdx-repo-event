// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.presentation.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * イベント情報を格納するDTOクラス.<br>
 * {@link AttributeDto}の子DTO.
 * 
 * @author ukai jun
 * @version 1.1 2024/11/13
 */
@Data
public class EventInfoDto {

    /**
     * イベント属性情報.
     */
    private Map<String, String> eventAttribute;

    /**
     * イベント種別.
     */
    private String eventType;

    /**
     * イベントID.
     */
    private int eventId;

    /**
     * インフラ事業者ID.
     */
    private String infraCompanyId;

    /**
     * 空間IDリスト.
     */
    private List<String> sidList;

}