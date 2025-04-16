// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.api.constatnts;

/**
 * イベント情報取得APIの定数を管理するクラス.
 * 
 * @author ukai jun
 * @version 1.1 2024/11/13
 */
public class EventInfoConstants {

    /**
     * イベント情報取得APIのエンドポイント．
     */
    public static final String EVENT_INFO_URI = "/event-information";

    /**
     * 空間・属性情報参照（イベント情報）APIのエンドポイント．
     */
    public static final String SID_ATTRIBUTE_URI = "/gen/api/generic/v1/select-event";

    /**
     * 空間・属性情報参照(イベント情報)APIのAPI名
     */
    public static final String SID_ATTRIBUTE_EVENT_NAME = "空間・属性情報参照(イベント情報)";

    /**
     * イベント情報取得APIにおけるズームレベルの上限．
     */
    public static final int ZL_UPPER_LIMIT = 20;

    /**
     * イベント情報取得APIにおけるズームレベルの下限．
     */
    public static final int ZL_LOWER_LIMIT = 12;

    /**
     * データモデルタイプ.
     */
    public static final String DATA_MODEL_TYPE = "test1";

    /**
     * データ整備範囲外を識別する正規表現．
     */
    public static final String REGEX_INVALID_DATA_RANGE = ".*InvalidDataRange.*";
}