/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 17, 2020
 */
@Getter
@Setter
@NoArgsConstructor
public class BasePhongMaChienResponse<T> {

    private int                                ret;

    private String                             msg;

    private List<T> content;

}
