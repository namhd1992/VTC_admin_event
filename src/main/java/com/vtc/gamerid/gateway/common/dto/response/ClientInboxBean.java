package com.vtc.gamerid.gateway.common.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 24/10/2017.
 */
@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientInboxBean implements Serializable {

    private String title;

    private String defaultImage;

    private String subTitle;

    private String description;

    private String textCoppy;

}
