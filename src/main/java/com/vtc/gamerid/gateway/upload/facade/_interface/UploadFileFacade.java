package com.vtc.gamerid.gateway.upload.facade._interface;

import com.vtc.gamerid.gateway.common.dto.request.DeleteImageRequest;
import com.vtc.gamerid.gateway.common.dto.request.UploadImageRequest;
import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;
import com.vtc.gamerid.gateway.upload.bean.UploadFileBean;

/**
 * Created by phucnguyen on 17/05/2017.
 */
public interface UploadFileFacade {
    public BaseDataResponse uploadFile(UploadFileBean dataRequest);
    
    public String uploadImage(UploadImageRequest request);
    
    public String deleteImage(DeleteImageRequest request);
}
