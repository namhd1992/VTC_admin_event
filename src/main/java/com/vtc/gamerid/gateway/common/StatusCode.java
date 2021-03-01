/***************************************************************************
 * Product 2018 by Quang Dat * 
 **************************************************************************/
package com.vtc.gamerid.gateway.common;

import lombok.Setter;

import lombok.Getter;

/**
 * Creator : Le Quang Dat
 * Email   : quangdat0993@gmail.com
 * Date    : Oct 11, 2018
 */
@Getter
@Setter
public class StatusCode {
    
    public static final String UNKNOWN_ERROR                    = "00";
    public static final String UNKNOWN_ERROR_DESCRIPTION        = "Unknown Error";

    public static final String SUCCESS                          = "01";
    public static final String SUCCESS_DESCRIPTION              = "Successful!";

    public static final String INVALID_DATA_REQUEST             = "02";
    public static final String INVALID_DATA_REQUEST_DESCRIPTION = "Invalid Input's request!";
    
    public static final String DUPLICATE_ENTITY                 = "04";
    public static final String DUPLICATE_ENTITY_DESCRIPTION     = "Duplicate Entity!";

    public static final String NOT_FOUND_ENTITY                 = "03";
    public static final String NOT_FOUND_ENTITY_DESCRIPTION     = "Not found entity!";

    public static final String UNAUTHORIZED                     = "06";
    public static final String UNAUTHORIZED_DESCRIPTION         = "No permission to do!";
    
    public static final String FAILED_TO_EXECUTE                = "08";
    public static final String FAILED_TO_EXECUTE_DESCRIPTION    = "Failed to execute!";
    
    public static final String EXPIRED_SESSION                  = "09";
    public static final String EXPIRED_SESSION_DESCRIPTION      = "The session has expired!";
    
    public static final String EXCEEDING_LIMITS_RECHARGE_XO              = "-1004";
    public static final String EXCEEDING_LIMITS_RECHARGE_XO_DESCRIPTION  = "Bạn đã vượt quá giới hạn nạp XO";

    public static final String EXCEEDING_LIMITS_WITHDRAW_XO              = "-1005";
    public static final String EXCEEDING_LIMITS_WITHDRAW_XO_DESCRIPTION  = "Bạn đã vượt quá giới hạn rút XO";
    
    public static final String NOT_ENOUGH_BALANCE_XO              = "-1001";
    public static final String NOT_ENOUGH_BALANCE_XO_DESCRIPTION  = "Số dư XO không đủ";
    
    public static final String NOT_ENOUGH_BALANCE_XU              = "-55";
    public static final String NOT_ENOUGH_BALANCE_XU_DESCRIPTION  = "Số dư XU không đủ";

}
