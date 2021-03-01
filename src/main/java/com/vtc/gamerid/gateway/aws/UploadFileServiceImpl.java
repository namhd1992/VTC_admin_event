package com.vtc.gamerid.gateway.aws;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.vtc.gamerid.gateway.aws._interface.AmazonManagerService;
import com.vtc.gamerid.gateway.aws._interface.UploadFileService;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.EnvironmentKey;
import com.vtc.gamerid.gateway.common.ultils.DateUtils;
import com.vtc.gamerid.gateway.common.ultils.FileUtils;
import com.vtc.gamerid.gateway.common.ultils.GoogleDriveUltils;

/**
 * Created by phucnguyen on 17/04/2017.
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {
    protected Logger logger = LoggerFactory.getLogger(UploadFileServiceImpl.class);
    
    @Autowired
    protected Environment env;
    
    @Autowired
    private AmazonManagerService amazonManagerService;
    
    private String PROJECT_BASE_URL;
    
    public UploadFileServiceImpl(Environment env) {
        PROJECT_BASE_URL = env.getProperty(EnvironmentKey.PROJECT_BASE_URL.getKey());
    }
    
    @Override
    public String uploadFile(String urlFile, String fileName) {
        try{
            //Authenticaion Amazon
            AmazonS3 amazonS3 = amazonManagerService.loginAmazonS3();
            if(amazonS3 == null){
                logger.error("Can not loggin to amazon");
                return null;
            }

            //Get bucket by name
            Bucket bucket = amazonManagerService.getBucketByName(amazonS3,
                    Constants.AWS_BUCKET_DEFAULT);
            if(bucket == null){
                logger.error("Can not find bucket with name: "+Constants.AWS_BUCKET_DEFAULT);
                return null;
            }

            logger.info("Start upload file ["+fileName+"] ...");

            //Get extention
            String ext = FilenameUtils.getExtension(urlFile);
            if(ext.indexOf("?") > -1){
                ext = ext.substring(0,ext.indexOf("?"));
            }
            //Check create folder if no exits
            File folderSource = new File(Constants.DATA_CRAWL);
            if(!folderSource.exists()) folderSource.mkdir();
            File fileVideo = new File(Constants.DATA_CRAWL+fileName+"."+ext);

            //Download file
            URLConnection conn = new URL(urlFile).openConnection();
            InputStream is = conn.getInputStream();

            OutputStream outstream = new FileOutputStream(fileVideo);
            byte[] buffer = new byte[4096];
            int len;
            while ((len = is.read(buffer)) > 0) {
                outstream.write(buffer, 0, len);
            }
            outstream.close();

            //Upload file
            String urlFileUpload = amazonManagerService.uploadFile(
                    amazonS3, bucket, Constants.AWS_FOLDER_DEFAULT, fileVideo);
            if(urlFileUpload == null){
                logger.error("Can\'t upload file ["+fileVideo.getName()+"]");
                return null;
            }
            logger.info("Upload file success: "+urlFileUpload);
            deleteFile(fileVideo);
            return urlFileUpload;
        }catch (Exception e){
            logger.error(e.toString());
        }finally {
            logger.info("Finish upload file ["+fileName+"] !!!");
        }
        return null;
    }

    @Override
    public String uploadFile(File file, String fileName) {
        try{
            //Authenticaion Amazon
            AmazonS3 amazonS3 = amazonManagerService.loginAmazonS3();
            if(amazonS3 == null){
                logger.error("Can not loggin to amazon");
                return null;
            }

            //Get bucket by name
            Bucket bucket = amazonManagerService.getBucketByName(amazonS3,
                    Constants.AWS_BUCKET_DEFAULT);
            if(bucket == null){
                logger.error("Can not find bucket with name: "+Constants.AWS_BUCKET_DEFAULT);
                return null;
            }

            logger.info("Start upload file ["+fileName+"] ...");

            //Upload file
            String urlFileUpload = amazonManagerService.uploadFile(
                    amazonS3, bucket, Constants.AWS_FOLDER_DEFAULT, file);
            if(urlFileUpload == null){
                logger.error("Can\'t upload file ["+file.getName()+"]");
                return null;
            }
            logger.info("Upload file success: "+urlFileUpload);
            deleteFile(file);
            return urlFileUpload;
        }catch (Exception e){
            logger.error(e.toString());
        }finally {
            logger.info("Finish upload file ["+fileName+"] !!!");
        }
        return null;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            logger.info("Start upload file [" + file.getOriginalFilename() + "] ...");
            String dateName = DateUtils.toStringCurrentDateTime();

            //Create folder if null
            File folderUpload = new File(Constants.PROJECT_RESOURCES + dateName);
            if(!folderUpload.exists()){
                folderUpload.mkdir();
            }

            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                String fileName = file.getOriginalFilename().replaceAll("\\s+","-");
                // Create the file on server
                File serverFile = new File(folderUpload.getAbsolutePath() + "/"
                        + new Date().getTime() + "-" + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                //get id folder STORE_DATA (root) in google drive
                String rootFolderId = GoogleDriveUltils
                        .getFolderId(null, Constants.GOOGLE_FOLDER_NAME_STORE)
                        .get(Constants.INDEX_FIRST_FILE)
                        .getId();
                if (file.getContentType().startsWith(Constants.GOOGLE_IMAGE_FILE_TYPE)) {
                    //create fordel IMAGE if null
                    String folderIdImage = GoogleDriveUltils.createFolder(rootFolderId,
                            Constants.GOOGLE_FOLDER_NAME_IMAGE);
                    
                    com.google.api.services.drive.model.File imageGG = GoogleDriveUltils
                            .createFile(serverFile, serverFile.getName(), file.getContentType(), folderIdImage);

                    FileUtils.deleteFileServer(folderUpload);
                    return imageGG.getWebContentLink();
                }
                
                //create fordel FILE if null
                String folderIdFile = GoogleDriveUltils.createFolder(rootFolderId,
                        Constants.GOOGLE_FOLDER_NAME_FILE);
                //create fordel NAME DATE if null
                String folderIdDate = GoogleDriveUltils.createFolder(folderIdFile,
                        dateName.substring(0, dateName.length() - 1));
                //create file
                GoogleDriveUltils.createFile(
                        serverFile, serverFile.getName(), file.getContentType(), folderIdDate);

                return env.getProperty(EnvironmentKey.PROJECT_BASE_URL.getKey()) + dateName
                        + serverFile.getName();
            }
            logger.error("File upload empty");
        } catch (Exception e) {
            logger.error("Upload file: ["+file.getName()+"] have error: "+e.toString());
        }
        return null;
    }

    private void deleteFile(File file){
        try{
            Files.deleteIfExists(file.toPath());
        }catch (Exception e){

        }
    }

    @Override
    public String uploadFileLocal(MultipartFile file) throws IOException {
        String dateName = DateUtils.toStringCurrentDateTime();
        File folderUpload = new File(Constants.PROJECT_RESOURCES + dateName);
        if(!folderUpload.exists()){
            folderUpload.mkdir();
        }
        File fileProvisional = new File(folderUpload.getAbsolutePath() + "/" + new Date().getTime() + "-" + file.getOriginalFilename());
        if (!fileProvisional.exists()) fileProvisional.createNewFile();
        FileOutputStream fos = new FileOutputStream(fileProvisional);
        fos.write(file.getBytes());
        fos.close();
        return PROJECT_BASE_URL + dateName + fileProvisional.getName();
    }

}
