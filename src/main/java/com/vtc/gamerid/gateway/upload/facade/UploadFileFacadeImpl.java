package com.vtc.gamerid.gateway.upload.facade;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.vtc.gamerid.gateway.annotations.AuditLogAnnotation;
import com.vtc.gamerid.gateway.annotations.PermissionService;
import com.vtc.gamerid.gateway.aws._interface.UploadFileService;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.EnvironmentKey;
import com.vtc.gamerid.gateway.common.dao.entity.TblImage;
import com.vtc.gamerid.gateway.common.dao.repository.ImageRepository;
import com.vtc.gamerid.gateway.common.dto.request.DeleteImageRequest;
import com.vtc.gamerid.gateway.common.dto.request.UploadImageRequest;
import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.common.ultils.StringUtils;
import com.vtc.gamerid.gateway.exception.FailedToExecuteException;
import com.vtc.gamerid.gateway.exception.ScoinInvalidDataRequestException;
import com.vtc.gamerid.gateway.exception.ScoinNotFoundEntityException;
import com.vtc.gamerid.gateway.exception.SplayBusinessException;
import com.vtc.gamerid.gateway.profile.service.AdminProfileService;
import com.vtc.gamerid.gateway.upload.bean.UploadFileBean;
import com.vtc.gamerid.gateway.upload.facade._interface.UploadFileFacade;

/**
 * Created by phucnguyen on 17/05/2017.
 */
@Component
public class UploadFileFacadeImpl extends AbstractService<TblImage, Long, ImageRepository>
        implements UploadFileFacade {
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private AdminProfileService adminProfileService;
    
    private String PROJECT_BASE_URL;
    
    public UploadFileFacadeImpl(Environment env) {
        PROJECT_BASE_URL = env.getProperty(EnvironmentKey.PROJECT_BASE_URL.getKey());
    }

    @Override
    @AuditLogAnnotation(functionName = "uploadFile")
    @PermissionService(functionName = "uploadFile")
    public BaseDataResponse uploadFile(UploadFileBean dataRequest) {
        try {
            adminProfileService.getSessionUserInfo();
            if (ObjectUtils.isEmpty(dataRequest)
                    || dataRequest.getFile().isEmpty()) {
                return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                        "Inavalid data request");
            }
            
            //Check dimension image
            if(dataRequest.getSize() > 1 && dataRequest.getSize() > dataRequest.getSize())
                return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                        "Dimension inavalid");

            String urlFile = uploadFileService.uploadFile(dataRequest.getFile());
            if (urlFile == null) {
                return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                        Constants.OBJECT_NOT_CREATE);
            }

            //Check size image
            if (dataRequest.getWidth() > 0 && dataRequest.getHeight() > 0) {
                URL url = new URL(urlFile);
                BufferedImage image = ImageIO.read(url);
                int height = image.getHeight();
                int width = image.getWidth();
                if (height != dataRequest.getHeight() ||
                        width != dataRequest.getWidth())
                    return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                            "Width or height image invalid");
            }


            return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                    Constants.SUCCESS, urlFile);
        } catch (Exception e) {

        }
        return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                Constants.MESS_UNKNOW);
    }

    @Override
    public String uploadImage(UploadImageRequest request) throws SplayBusinessException {
        verifyAdminToken();
        
        if (ObjectUtils.isEmpty(request)
                || ObjectUtils.isEmpty(request.getImage())) {
            throw new ScoinInvalidDataRequestException();
        }
        
        //Create folder if null
        String folderImage = "image/";
        File folderUpload = new File(Constants.PROJECT_RESOURCES + folderImage);
        if(!folderUpload.exists()){
            folderUpload.mkdir();
        }

        // Create file
        String imageName = request.getImage().getOriginalFilename().replace(" ", "_").replace("(", "_").replace(")", "_");
        File file = new File(folderUpload.getAbsolutePath() + "/" + new Date().getTime() + "-" + imageName);
        try {
            logger.info("Upload image name : {}", file.getName() + " to local project");
            request.getImage().transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            throw new FailedToExecuteException("Don't upload Image");
        }

        TblImage dataImage = new TblImage();
        dataImage.setNameImage(file.getName());
        dataImage.setUrlImage(PROJECT_BASE_URL + folderImage + file.getName());
        TblImage createImage = repo.save(dataImage);
        if (ObjectUtils.isEmpty(createImage)) {
            throw new FailedToExecuteException("Don't create Image");
        }
        
        return  createImage.getUrlImage();
    }

    @Override
    public String deleteImage(DeleteImageRequest request) {
        verifyAdminToken();
        
        if (ObjectUtils.isEmpty(request)
                || StringUtils.isEmpty(request.getUrlImage())) {
            throw new ScoinInvalidDataRequestException();
        }
        
        String folderImage = "image/";
        File folderUpload = new File(Constants.PROJECT_RESOURCES + folderImage);
        if(!folderUpload.exists()){
            throw new ScoinNotFoundEntityException("Not found folder image");
        }
        
        TblImage image = repo.findByUrlImage(request.getUrlImage());
        if (ObjectUtils.isEmpty(image))
            return "Not found Etity Image";

        logger.info("LOCAL IMAGE=============={}",
                folderUpload.getAbsoluteFile() + "/" + image.getNameImage());
        File file = new File(folderUpload.getAbsoluteFile() + "/" + image.getNameImage());
        if (!file.exists())
            return "Not found Image on local to delete";

        logger.info("Delete image name : {}", file.getName() + " to local project");
        boolean isDeleteFile = file.delete();
        if (!isDeleteFile) {
            logger.info("Delete image name : {}", file.getName() + " NON success");
            throw new FailedToExecuteException("Don't delete Image on local");
        }

        repo.delete(image);
        return "Delete Image Successfull";
    }
}
