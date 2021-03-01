package com.vtc.gamerid.gateway.luckyspin.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;

import com.vtc.gamerid.gateway.common.Constants;

import java.io.Serializable;

/**
 * Created by phucnguyen on 23/06/2017.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SpinItemBeanRequest implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private MultipartFile     giftCodeFile     = null;

    private String            name             = null;

    private long             value            = 0;

    private String            type;

    private int               quantity         = -1;

    private int               isDefault        = 0;

    private int               weight           = 0;

    private Long              itemId;

    private int               defaultPosition  = -1;

    private String            urlImage         = null;

    private String            winningTitle     = null;

    private String            wheelTitle       = null;

    private String            status           = Constants.STATUS_ACTIVE;

    private String            description      = null;

    private boolean           bigItem;

    private boolean           highLights;

}
