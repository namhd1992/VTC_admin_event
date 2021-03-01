package com.vtc.gamerid.gateway.game.bean;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.exception.RegularExpression;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phucnguyen on 05/12/2017.
 */
@Setter
@Getter
@NoArgsConstructor
public class AdminSplayGameBeanRequest implements Serializable {
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    private Long              id;

    private String            name;

    private String            defaultImage;

    private String            fanpageFB;

    private String            groupFB;

    private String            publisher;

    private String            description;

    private String            gameType;

    private String            webgameUrl;

    private String            subTitle;

    private String            urlDownloadAndroid;

    private String            urlDownloadIos;

    private String            urlDownloadPC;

    private String            screenShot;

    private String            bigImage;

    private String            youtubeChannelId;

    private String            youtubeDefaultSearch;

    private Long              createBy;

    private String            status           = Constants.STATUS_ACTIVE;

    private long              scoinGameId      = -1;

    private long              downloadTurns    = 0;

    private long              priorityTag      = -1;

    private List<Long>        tagList          = new ArrayList<>();

    public ValidateBean validate(){
        if(!RegularExpression.validateStripXss(this.name))
            return new ValidateBean(false, "Tên game không đúng");
        if(!RegularExpression.validateStripXss(this.defaultImage))
            return new ValidateBean(false, "Ảnh mặc định không đúng");
        if(this.groupFB != null && !RegularExpression.validateStripXss(this.groupFB))
            return new ValidateBean(false, "Group FB không đúng");
        if(this.fanpageFB != null && !RegularExpression.validateStripXss(this.fanpageFB))
            return new ValidateBean(false, "Fanpage không đúng");
        if(this.publisher != null && !RegularExpression.validateStripXss(this.publisher))
            return new ValidateBean(false, "Publisher không đúng");
        if(!RegularExpression.validateStripXss(this.status))
            return new ValidateBean(false, "Status không đúng");
        if((this.description != null && !RegularExpression.validateStripXss(this.description)))
            return new ValidateBean(false, "Description không đúng");
        if((this.youtubeChannelId != null && !RegularExpression.validateStripXss(this.youtubeChannelId)))
            return new ValidateBean(false, "Youtube channelId không đúng");
        if((this.youtubeDefaultSearch != null && !RegularExpression.validateStripXss(this.youtubeDefaultSearch)))
            return new ValidateBean(false, "Youtube default search không đúng");

        return new ValidateBean(true, Constants.SUCCESS);
    }

}
