package com.vtc.gamerid.gateway.distributeGiftcode.bean;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 23/04/2018.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DistributeGiftcodeBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8139690041720820137L;

    private MultipartFile distributeFile = null;

    private String        content        = null;

    private String        title          = null;

    private String        fromEmail      = null;

    private String        password       = null;

    private String        fromName       = null;

    private String        giftcodeFile   = null;

    private Long          createBy;

}
