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
 * Jul 24, 2019
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultReportSpinGetResponseData {

    private long   luckySpinId;

    private long   total;

    private String typeItem;

    private long   value;

}
