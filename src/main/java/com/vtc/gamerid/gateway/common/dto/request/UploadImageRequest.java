/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Mar 21, 2019
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UploadImageRequest {
    
    private MultipartFile image;

    private int           width;

    private int           height;

    private long          size;

}
