// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.presentation.dto;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.validator.constraints.Range;

import com.spatialid.app.api.constatnts.EventInfoConstants;
import com.spatialid.app.common.constant.RestApiConstants;
import com.spatialid.app.common.validation.CheckSid;
import com.spatialid.app.common.validation.CheckSidArea;
import com.spatialid.app.common.validation.CheckSidZoomLevel;
import com.spatialid.app.common.validation.Groups.First;
import com.spatialid.app.common.validation.Groups.Forth;
import com.spatialid.app.common.validation.Groups.Second;
import com.spatialid.app.common.validation.Groups.Third;
import com.spatialid.app.common.validation.ValidDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.groups.Default;
import lombok.Data;

/**
 * イベント情報取得APIが受け取るリクエストDtoを定義したクラス.
 * 
 * @author ukai jun
 * @version 1.1 2024/11/13
 */
@Data
public class EventInfoRequestDto {
    
    /** 空間ID. */
    @NotEmpty(groups = {Third.class})
    @CheckSidArea(lowerLimit = EventInfoConstants.ZL_LOWER_LIMIT,
                  upperLimit = EventInfoConstants.ZL_UPPER_LIMIT,
                  groups = {Forth.class})
    private List<
    @NotEmpty(groups = {Default.class})
    @CheckSid(groups = {First.class})
    @CheckSidZoomLevel(lowerLimit = EventInfoConstants.ZL_LOWER_LIMIT,
                       upperLimit = EventInfoConstants.ZL_UPPER_LIMIT,
                       groups = {Second.class})
                String> sids;

    /** インフラ事業者IDのリスト. */
    private List<String> infraCompanyIds;
        
    /** 返却ズームレベル. */
    @DecimalMin(value = "1", groups = {First.class})
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, groups = {Second.class})
    @Range(min=EventInfoConstants.ZL_LOWER_LIMIT, max=EventInfoConstants.ZL_UPPER_LIMIT)
    private BigDecimal returnZoomLevel;
    
    /** 更新日時 */
    @ValidDate(pattern = RestApiConstants.STRICT_DATE_FORMAT)
    private String updateDate;
    
}