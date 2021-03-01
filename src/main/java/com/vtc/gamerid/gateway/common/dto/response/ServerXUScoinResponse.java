/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Nov 13, 2018
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServerXUScoinResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean            status;

    private Long               error_code;

    private String             error_desc;

    private InfoXUUserResponse data;

}
