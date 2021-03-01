/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

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
@NoArgsConstructor
@AllArgsConstructor
public class InfoXUUserResponse {

    private long TotalBalance;

    private long PaymentBalance;

    private long PromoBalance;

}
