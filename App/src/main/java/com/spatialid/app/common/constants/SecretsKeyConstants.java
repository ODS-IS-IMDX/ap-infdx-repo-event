// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.constants;

/**
 * AWS Secrets Managerのキー名を定義した定数クラス．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/18
 */
public class SecretsKeyConstants {
        
    /**
     * API利用権限コンテナのドメインに対応したキー名．
     */
    public static final String AUTHORITY_DOMAIN = "ECS-AUTHORITY-DOMAIN";
    
    /**
     * 業務共通コンテナのドメインに対応したキー名．
     */
    public static final String COMMON_DOMAIN = "ECS-COMMON-DOMAIN";

    /**
     * アクセス履歴コンテナのドメインに対応したキー名．
     */
    public static final String AC_HISTORY_DOMAIN = "ECS-ACCESS-HISTORY-DOMAIN";

}