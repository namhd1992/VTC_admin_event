/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.request;

import com.vtc.gamerid.gateway.common.dao.entity.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Feb 25, 2019
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TopupXURequest {

    private UserInfo userInfo;

    private Long        valueXuTopup;

    private String      provider;

    private Long        providerId;

    private Long        serviceId;

    private String      descriptionName;

    private double      ratioExchange;

}
