package com.vtc.gamerid.gateway.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by phucnguyen on 02/03/2017.
 */
public class Constants {

    public static Map<String, String>   sysConfig                    = new HashMap<>();

    //Service game id
    public static final long            SERVICE_GAME_ID_PHONG_MA_CHIEN = 330333;
    
    public static final String          CLIENT_ID                    = "vtcmobile";
    public static final String          CLIENT_SECRET                = "vtc@2017";
    public static final String          RESOURCE_ID                  = "myresource";
    public static final int             TIME_ZONE                    = 0;
    public static final String          PROJECT_NAME                 = "GameRID";
    public static final String          PROJECT_RESOURCES            = "src/main/webapp/resources/";
    public static final String          PROJECT_BASE_URL             = "https://api.simba-app.com/resources/";
    public static final String          PROJECT_UPLOAD_FILE          = "src/main/webapp/upload/";
    public static final String          DATA_CRAWL                   = "src/main/webapp/resources/datacrawl/";
    public static final String          LANDING_PAGE_INDEX           = "/etc/nginx/sites-available/vtc_landingpage/index.html";
    public static final String          TYPE_GAME                    = "game";
    public static final String          TYPE_GIFTCODE                = "giftcode";
    public static final String          STORE_DATA_TYPE_DEFAULT      = "image";
    public static final String          URL_DIRECTIVE                = "http://bot.simba-app.com/resources/";
    public static final String          URL_UPDATE_RATIO             = "http://localhost:9006/server/betting";
    public static final String          URL_SOCKET_AUCTION           = "https://socket.splay.vn/server/auction";
    public static final String          ADB_GAMERID                  = "adb.simba-app.vn";
    public static final String          LEAGUE_CURRENCY              = "LP";
    public static final String          SERVER_INFO_GAME_MCD_URL     = "http://api.game.mongchinhdo.vn/vtc_get_info.php?action=getServers";
    public static final String          PLAYER_INFO_GAME_MCD_URL     = "http://api.game.mongchinhdo.vn/vtc_get_role.php";

    // Status game
    public static final String          STATUS_ACTIVE                = "active";
    public static final String          STATUS_SUCCESS               = "thành công";
    public static final String          STATUS_INACTIVE              = "inactive";
    public static final String          STATUS_PENDING               = "đang chờ";
    public static final String          STATUS_REJECT                = "hủy";
    public static final String          STATUS_ACCEPT                = "chấp nhận";
    public static final String          STATUS_FOLLOW                = "follow";
    public static final String          STATUS_UNFOLLOW              = "unfollow";
    public static final String          STATUS_SENT                  = "sent";
    public static final String          STATUS_ERROR                 = "error";
    public static final String          STATUS_RECIEVED              = "recieved";
    public static final String          STATUS_DELETED               = "deleted";
    public static final Integer         SEARCH_USERNAME              = 1;
    public static final String          ERROR_PATH                   = "/errors";
    public static final String          LOGIN_TYPE_GAMERID           = "gamerid";
    public static final String          LOGIN_TYPE_SSO               = "sso";
    public static final String          LOGIN_TYPE_VTC               = "vtc";
    public static final String          LOGIN_FROM_ADB               = "adb";
    public static final String          LOGIN_FROM_APP               = "app";

    // Status code
    public static final String          STATUS_CODE_FALSE            = "F";
    public static final String          STATUS_CODE_TRUE             = "T";

    // Message
    public static final String          MESS_UNKNOW                  = "Có lỗi xảy ra.";
    public static final String          BAD_REQ                      = "Invalid data request";
    public static final String          AMOUNT_NOT_ENOUGHT           = "Số tiền không đủ";
    public static final String          NO_SUPPORT                   = "Không hỗ trợ";
    public static final String          NO_DATA                      = "Không có dữ liệu";
    public static final String          SUCCESS                      = "thành công";
    public static final String          ERROR                        = "có lỗi xảy ra";
    public static final String          XSS_ERROR                    = "XSS_ERROR";
    public static final String          NAME_NULL                    = "Tên sai định dạng";
    public static final String          STATUS_NOT_AVAIABLE          = "Trạng thái sai.";
    public static final String          CAN_NOT_GET_PROFILE          = "Không thể lấy thông tin người dùng.";
    public static final String          CHANNEL_EXIST                = "Channel exist!!!";
    public static final String          OBJECT_EXIST                 = "Đối tượng đã tồn tại";
    public static final String          OBJECT_NOT_EXIST             = "Đối tượng không tồn tại";
    public static final String          NAME_DUPPLICATE              = "Tên trùng";
    public static final String          OBJECT_NOT_CREATE            = "Không tạo được";
    public static final String          OBJECT_NOT_UPDATE            = "Không thể cập nhật";
    public static final String          OBJECT_NOT_DELETE            = "Không thể xóa";
    public static final String          OBJECT_EMPTY                 = "Đối tượng trống";
    public static final String          SESSION_TIMEOUT              = "Hết phiên làm việc";
    public static final String          DEVICE_HAD_GIFTCODE          = "Thiết bị này đã nhận giftcode";
    public static final String          USER_HAD_GIFTCODE            = "Tài khoản này đã nhận giftcode";
    public static final String          GIFTCODE_EMPTY               = "Giftcode đã được phát hết.";
    public static final String          ROLLBACKED                   = "Rollbacked";

    // Wallet config
    public static final String          MAX_TRANSFER_PER_DAY         = "Max transfer per day";
    public static final String          NOT_FOUND_WALLET_CONFIG      = "Not found eWallet config";
    public static final String          MONEY_TRANSFER_DISABLE       = "Money transfer is close";
    public static final String          DEPOSIT_DISABLE              = "Deposit is close";
    public static final String          MAX_BALANCE_PER_DAY          = "Max balance per day";
    public static final String          MAX_BALANCE_PER_TRANSACTION  = "Max balance per transaction";
    public static final String          TIME_BETWEEN_TWO_TRANSACTION = "Time between two transaction";
    public static final String          NOT_FOUND_RECEIVER           = "Can not found receiver";
    public static final String          BALANCE_NOT_ENOUGHT          = "Số tiền không đủ";
    public static final String          QUANTITY_NOT_ENOUGHT         = "Số lượng không đủ";
    public static final String          DESCRIPTION_INVALID          = "Description invalid";
    public static final String          AMOUNT_INVALID               = "Amount invalid";
    public static final String          FEE_INVALID                  = "Can not calculate fee";
    public static final String          SPIN_INVALID                 = "Spin invalid";
    public static final String          SPIN_TURNS_NOT_ENOUGHT       = "Spin turn not enought";
    public static final String          NUMBER_TURNS_INVALID         = "Number of turn invalid";
    public static final String          NOT_CREATE_HISTORY           = "Can not create spin history";

    // Lucky spin
    public static final String          SPIN_NORMAL_WINNINGS         = "Bạn đã trúng thưởng ";
    public static final String          SPIN_BIG_WINNINGS            = "Tuyệt vời. Bạn đã trúng ";
    public static final String          SPIN_LOST                    = "Chúc bạn may mắn lần sau - ";
    public static final String          SCOIN_TYPE_SCOIN             = "SCOIN";
    public static final String          SCOIN_TYPE_CARD              = "CARD";
    public static final String          SCOIN_TYPE_CARD_CROSS        = "CARD";
    

    // Betting
    public static final String          BETTING_SERVICE_TYPE         = "spent";
    public static final String          BETTING_SOURCE_TYPE          = "betting";

    // Role
    public static final String          ROLE_ADMIN                   = "admin";
    public static final String          ROLE_PUBLISHER               = "PUBLISHER";
    public static final String          ROLE_ADMIN_EVENT_GAME        = "Event Game Manager";
    public static final String          ROLE_USER                    = "USER";
    public static final int             POINT_ROLE_ADMIN             = 99;
    public static final int             POINT_ROLE_PUBLISHER         = 10;
    public static final int             POINT_ROLE_MIDDER            = 9;
    public static final int             POINT_ROLE_USER              = 2;

    // Permission
    public static final String          NOT_PERMISSION               = "You don\'t have permission";
    public static final String          SIGNATURE_FAILED             = "Signature failed";

    // System config
    public static final String          ACCEPT_COMMENT               = "auto_accept_comment";
    public static final String          CONFIG_TIME_ARTICLE_HOT      = "time_article_hot";
    public static final String          CONFIG_TIME_DEPLAY_ARTICLE   = "time_deplay_article";

    // Upload file
    public static final String          FILE_NOT_UPLOAD              = "Can not upload file";
    public static final String          ARTICLE_TYPE_GIFTCODE        = "giftcode";
    public static final int             LENGHT_FILE_OF_FOLDER_EMPTY  = 0;
    public static final int             INDEX_FIRST_FILE             = 0;
    public static final String          MISSION_AWRAD_TYPE_GIFTCODE  = "giftcode";

    // File google drive
    public static final String          GOOGLE_IMAGE_FILE_TYPE       = "image/";
    public static final String          GOOGLE_FOLDER_TYPE           = "application/vnd.google-apps.folder";
    public static final String          GOOGLE_FOLDER_NAME_STORE     = "STORE_DATA";
    public static final String          GOOGLE_FOLDER_NAME_IMAGE     = "IMAGE";
    public static final String          GOOGLE_FOLDER_NAME_FILE      = "FILE";

    // AWS constance
    public static final String          AWS_USERNAME                 = "admingamerid";
    public static final String          AWS_ACCESS_KEY_ID            = "AKIAIOPQL2M4KIG52BEQ";
    public static final String          AWS_SECRET_ACCESS_KEY        = "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";
    public static final String          AWS_SUFFIX                   = "/";
    public static final String          AWS_BUCKET_DEFAULT           = "bucket-league-vn";
    public static final String          AWS_FOLDER_DEFAULT           = "folder-default";
    public static final String          AWS_REGIONS_DEFAULT          = "ap-southeast-1";

    // Splay
    public static final String          SPLAY_POINT_NOT_ENOUGHT      = "Không đủ thịt";
    
    //Type nạp thịt
    public static final String          SMS                          = "SMS";
    public static final String          CARD                         = "CARD";
    public static final String          ITUNES                       = "ITUNES";
    public static final String          BANK                         = "SCOIN";
    public static final String          GOOGLE_PLAY                  = "GOOGLE PLAY";
    
    // Type limit gift
    public static final String          DAY                          = "DAY";
    public static final String          EVENT                        = "EVENT";
    
    //Type Action Mission
    public static final int             ACTION_LUCKY_SPIN            = 1;
    public static final int             ACTION_CHECKIN_DAYLY         = 2;
    public static final int             ACTION_AUCTION               = 3;
    public static final int             ACTION_GIFCODE               = 4;
    public static final int             ACTION_LOGIN_GAME            = 5;
    public static final int             ACTION_LIKE_FACEBOOK         = 6;
    public static final int             ACTION_SHARE_FACEBOOK        = 7;
    public static final int             ACTION_LEVER_GAME            = 8;
    public static final int             ACTION_FIRST_LOGIN_GAME      = 9;
    public static final int             ACTION_LOGIN_GAME_CONTINUOUS = 10;
    public static final int             MISSION_SATISFYING_DEFAULT      = 1;
    public static final int             MISSION_PROGRESS_DEFAULT        = 0;
    public static final int             MISSION_PROGRESS_FINISH_DEFAULT = 1;
    
    //HighLight
    public static final boolean         ITEM_HIGHLIGHT_TRUE             = true;
    public static final boolean         AUCTION_HIGHLIGHT_TRUE          = true;
    
    //Type event
    public static final int             TYPE_EVENT_LUCKY_SPIN           = 1;
    public static final int             TYPE_EVENT_AUCTION              = 2;
    
    //Exchange Coin
    public static final long            SERVICE_ID_XU                   = 330287;
    public static final String          EXCHANGE_SCOIN_TO_GAME          = "SCOINTOGAME";
    public static final String          EXCHANGE_GAME_TO_SCOIN          = "GAMETOSCOIN";
    public static final String          ROLLBACK_XU                     = "ROLLBACK";
    public static final String          XU_TOPUP                        = "TOPUP";
    public static final String          XU_DEDUCT                       = "DEDUCT";
    public static final String          XO_TOPUP                        = "TOPUP";
    public static final String          XO_DEDUCT                       = "DEDUCT";
    public static final int             EXCHANGE_INDEX_RECHARGE         = 1;
    public static final int             EXCHANGE_INDEX_WITHDRAW         = 2;
    public static final String          HISTORY_STATUS_SUCCESS          = "success";
    public static final String          HISTORY_STATUS_FAILURE          = "failure";
    public static final int             BALANCE_XU_DEFAULT              = 0;
    
    // MISSION
    public static final int             NUMBER_MISSION_UNFINISH_DEFAULT = 0;
    
    //SERVICE ID
    public static final long            SERVICE_ID_SU_KIEN_TRUY_KICH    = 330069;
    public static final String          SERVICE_NAME_SU_KIEN_TRUY_KICH  = "Sự Kiện Truy Kích";
    
    public static final long            SERVICE_ID_SPLAY                = 330265;
    public static final String          SERVICE_NAME_SPLAY              = "Splay";
    
    public static final long            SERVICE_ID_SCOIN_PAY            = 330259;
    public static final String          SERVICE_NAME_SCOIN_PAY          = "ScoinPay-Chia sẻ kho khàng";
    
    public static final long            SERVICE_ID_SU_SCOIN             = 330035;
    public static final String          SERVICE_NAME_SCOIN              = "Dịch Vụ Scoin";
    
    public static final long            SERVICE_ID_GAME_TOPGAME         = 330281;
    public static final String          SERVICE_NAME_GAME_TOPGAME       = "TOP GAME";
    
    public static final String          SERVICE_NAME_UNKNOWN            = "Nguồn không xác định";
    
    // EVENT GAME
    public static final String          EVENT_GAME_POINT_DEDUCT         = "DEDUCT";
    public static final String          PROVIDER_FROM_EVEN_GAME         = "EVENTGAME";
    public static final String          GAME_TYPE_WEBGAME               = "WEBGAME";
    public static final String          GAME_TYPE_MOBILE                = "MOBILE";
    
    //CAROUSEL
    public static final String          TYPE_CAROUSEL_IMAGE             = "IMGAE";
    public static final String          TYPE_CAROUSEL_TEXT_HIGHLIGHTS   = "TEXT";
    
    // Game Ranking
    public static final String GAME_RANKING_ITEM_TYPE_GIFTCODE       = "GIFTCODE";
    public static final String GAME_RANKING_ITEM_TYPE_INGAME         = "INGAME";
    public static final String GAME_RANKING_ITEM_TYPE_REAL           = "REAL";
    public static final String GAME_RANKING_ITEM_TYPE_TURN_LUCKYSPIN = "TURN_LUCKYSPIN";
    public static final String GAME_RANKING_ITEM_TYPE_XU             = "XU";
    public static final String GAME_RANKING_ITEM_TYPE_SCOIN          = "SCOIN";
    public static final String GAME_RANKING_ITEM_TYPE_CARD           = "CARD";
    
}
