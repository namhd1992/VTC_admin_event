/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Feb 20, 2019
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JoinEventGameResponse {

    private Long                       eventGameId;

    private String                     facebookId;

    private Long                       eventPoint;

    private String                     linkUserEvent;

    private List<UserChildentResponse> userChildents;

}
