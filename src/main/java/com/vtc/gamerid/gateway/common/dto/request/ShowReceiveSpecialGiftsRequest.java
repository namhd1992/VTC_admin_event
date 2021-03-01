/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.request;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Nov 12, 2018
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShowReceiveSpecialGiftsRequest implements Serializable  {
    
    private static final long serialVersionUID = 1L;

    private int               typeService;

    private int               serviceId;

    private List<Integer>     itemIds;

}
