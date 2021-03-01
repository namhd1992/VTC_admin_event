package com.vtc.gamerid.gateway.distributeGiftcode.facade;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vtc.gamerid.gateway.annotations.AuditLogAnnotation;
import com.vtc.gamerid.gateway.annotations.PermissionService;
import com.vtc.gamerid.gateway.aws._interface.UploadFileService;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;
import com.vtc.gamerid.gateway.distributeGiftcode.bean.DistributeGiftcodeBean;
import com.vtc.gamerid.gateway.distributeGiftcode.service.DistributeGiftcodeService;
import com.vtc.gamerid.gateway.exception.RegularExpression;
import com.vtc.gamerid.gateway.profile.service.AdminProfileService;

/**
 * Created by phucnguyen on 23/04/2018.
 */
@Component
public class DistributeGiftcodeFacadeImpl implements DistributeGiftcodeFacade {
    @Autowired
    private DistributeGiftcodeService distributeGiftcodeService;
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private AdminProfileService adminProfileService;
    private Logger logger = LoggerFactory.getLogger(DistributeGiftcodeFacadeImpl.class);
    private Transport transport;

//    static final String SMTP_USERNAME = "AKIAJYWXELVTO755GEAQ";
//    static final String SMTP_PASSWORD = "Ak3Y/1EfyXHeFzF7FvKqZeZBsH6400qPtCOp1so+oRKq";
//    static final String HOST = "email-smtp.us-east-1.amazonaws.com";
    static final String HOST = "smtp.gmail.com";
    static final String CONFIGSET = "ConfigSet";
    static final int PORT = 587;

    @Override
    @AuditLogAnnotation(functionName = "distributeGiftcode")
    @PermissionService(functionName = "toolGiftcodeManager")
    public BaseDataResponse distributeGiftcode(DistributeGiftcodeBean dataRequest) throws IOException {

        //Get user
        UserGameRID admin = adminProfileService.getSessionUserInfo();
        if(admin == null)
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                    Constants.NOT_PERMISSION);
        dataRequest.setCreateBy(admin.getId());

        //Validate data
//        if(!RegularExpression.validateStripXss(dataRequest.getContent())){
//            return new BaseDataResponse(VariableConstant.STATUS_CODE_FALSE,
//                    "Content invalid");
//        }
        if(!RegularExpression.validateStripXss(dataRequest.getTitle())){
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                    "Title invalid");
        }
        if(!RegularExpression.validateStripXss(dataRequest.getFromEmail())){
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                    "From email invalid");
        }
        if(!RegularExpression.validateStripXss(dataRequest.getPassword())){
          return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                  "Password from email invalid");
        }
        if(!RegularExpression.validateStripXss(dataRequest.getFromName())){
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                    "From name invalid");
        }



        //Upload file
        String fileGiftcode = uploadFileService.uploadFileLocal(
                dataRequest.getDistributeFile());
        if(fileGiftcode == null) {
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                    "Distribute giftcode file invalid");
        }

        //Check file
        if (!FilenameUtils.getExtension(fileGiftcode).equals("xlsx")) {
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                    "Distribute giftcode file invalid");
        }
        dataRequest.setGiftcodeFile(fileGiftcode);

        //Connect aws
        try{
            // Create a Properties object to contain connection configuration information.
            Properties props = System.getProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.port", PORT);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);
            transport = session.getTransport();
            transport.connect(HOST, dataRequest.getFromEmail(), dataRequest.getPassword());

//            Runnable myRunnable = new Runnable(){
//                public void run(){
                    //Call send mail
            distributeGiftcodeService.distributeGiftcode(dataRequest, transport);
//                }
//            };
//            Thread thread = new Thread(myRunnable);
//            thread.start();
        }catch (Exception e){
            logger.error("distributeGiftcode", e);
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE, 
                    "Không Đúng PASSWORD hoặc Email chưa được phân quyền");
        }

        return new BaseDataResponse(Constants.STATUS_CODE_TRUE, Constants.SUCCESS);
    }

//    @SuppressWarnings("unchecked")
//    @Override
//    @AuditLogAnnotation(functionName = "reportDistributeGiftcode")
//    @PermissionService(functionName = "toolGiftcodeManager")
//    public BaseDataResponse reportDistributeGiftcode(DistributeGiftcodeFilter dataRequest) {
//        //Get user
//        TblUserInfo userInfo = adminProfileService.getSessionUserInfo();
//        if(userInfo == null)
//            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
//                    Constants.NOT_PERMISSION);
//        if(userInfo.getGroupRole().getName().equals("admin")){
//            dataRequest.setCreateBy((long) -1);
//        }else{
//            dataRequest.setCreateBy(userInfo.getId());
//        }
//
//        //Get service response
//        ServiceResponse serviceResponse = distributeGiftcodeService.reportDistributeGiftcode(
//                dataRequest);
//        if(!serviceResponse.isSuccess())
//            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
//                    serviceResponse.getMessage());
//
//        //Response data
//        List<TblDistributeGiftcodeHistory> result = (List<TblDistributeGiftcodeHistory>)
//                serviceResponse.getDataResponse();
//        return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
//                Constants.SUCCESS, result);
//    }
}
