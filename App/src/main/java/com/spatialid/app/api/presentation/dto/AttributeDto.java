// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.presentation.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * イベント情報取得APIが返却するレスポンスを定義したDTOクラス.<br>
 * {@link EventInfoDto}をリスト形式で子DTOとして持つ.
 * 
 * @author ukai jun.
 * @version 1.1 2024/11/13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttributeDto {

    /**
     * イベント情報リスト.
     */
    List<EventInfoDto> eventList;
}