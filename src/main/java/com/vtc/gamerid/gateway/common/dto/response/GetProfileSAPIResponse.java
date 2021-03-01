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
 * Dec 13, 2018
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetProfileSAPIResponse {
    
    private Long   accountId;

    private String accountName;

    private String fullName;

    private String address;

    private String phoneNumber;

    private String email;

    private String   facebookId;

    private String facebookName;

    private long rewardPoints;

}
