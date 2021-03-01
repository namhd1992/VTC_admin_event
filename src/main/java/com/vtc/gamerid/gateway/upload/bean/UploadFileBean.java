package com.vtc.gamerid.gateway.upload.bean;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileBean implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private MultipartFile     file;

    private int               width            = -1;

    private int               height           = -1;

    private long              size             = 0;

}
