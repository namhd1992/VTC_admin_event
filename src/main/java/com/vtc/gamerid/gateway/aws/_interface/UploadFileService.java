package com.vtc.gamerid.gateway.aws._interface;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by phucnguyen on 17/04/2017.
 */
public interface UploadFileService {
    public String uploadFile(String urlFile, String fileName);
    public String uploadFile(File file, String fileName);
    public String uploadFile(MultipartFile file);
    public String uploadFileLocal(MultipartFile file) throws IOException ;
}
