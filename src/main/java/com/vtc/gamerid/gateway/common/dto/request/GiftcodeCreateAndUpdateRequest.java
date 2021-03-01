/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 27, 2020
 */
@Getter
@Setter
@NoArgsConstructor
public class GiftcodeCreateAndUpdateRequest {

    private MultipartFile codeFile;

    private String        eventType;

    private Long          mainEventId;

    private Long          subEventId;

}
