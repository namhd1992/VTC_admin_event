package com.vtc.gamerid.gateway.aws;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.vtc.gamerid.gateway.aws._interface.AmazonManagerService;
import com.vtc.gamerid.gateway.common.Constants;

/**
 * Created by phucnguyen on 04/04/2017.
 */
@Service
public class AmazonManagerServiceImpl implements AmazonManagerService {
    private Logger logger = LoggerFactory.getLogger(AmazonManagerServiceImpl.class);

    @Override
    public String createFolder(AmazonS3 s3client, Bucket bucket, String folderName) {
        try {
            // create meta-data for your folder and set content-length to 0
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(0);

            // create empty content
            InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

            // create a PutObjectRequest passing the folder name suffixed by /
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket.getName(),
                    folderName + Constants.AWS_SUFFIX, emptyContent, metadata);

            // send request to S3 to create folder
            s3client.putObject(putObjectRequest);

            return folderName;
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public Bucket createBucket(AmazonS3 s3client, String bucketName) {
        try {
            List<Bucket> buckets = getListBucket(s3client);
            if (buckets == null) {
                System.out.println("Can\'t get bucket list");
                return null;
            }

            //Get list bucket
            boolean notHaveDefaultBucket = true;
            System.out.println("==>List bucket: ");
            for (Bucket bucket : buckets) {
                System.out.println("    - "+bucket.getName());
                if (bucket.getName().equals(bucketName)){
                    notHaveDefaultBucket = false;
                    System.out.println("Bucket ["+bucketName+"] doesn\'t exist");
                    return bucket;
                }
            }

            //Create if not have
            if (notHaveDefaultBucket) {
                return s3client.createBucket(bucketName);
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public String uploadFile(AmazonS3 s3client, Bucket bucket, String folderName, File file) {
        try {
            String fileName = folderName + Constants.AWS_SUFFIX + file.getName();
            PutObjectResult result = s3client.putObject(
                    new PutObjectRequest(bucket.getName(), fileName, file)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            if(result == null) return null;

            return "http://"+bucket.getName()+".s3-ap-southeast-1.amazonaws.com/" +
                    folderName+"/"+file.getName();
            /*String aws_endpoint = VariableConstant.AWS_REGIONS_DEFAULT;
            return "http://"+bucket.getName() + "." + aws_endpoint + "/" +
                    folderName+"/"+file.getName();*/
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public List<Bucket> getListBucket(AmazonS3 s3client) {
        try {
            return s3client.listBuckets();
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public AmazonS3 loginAmazonS3() {
        try {
            AWSCredentials credentials = new BasicAWSCredentials(
                    Constants.AWS_ACCESS_KEY_ID, Constants.AWS_SECRET_ACCESS_KEY);
            AmazonS3 s3client = new AmazonS3Client(credentials);
            /*String aws_endpoint = VariableConstant.AWS_REGIONS_DEFAULT;
            try{
                aws_endpoint = sysConfig.get("aws_endpoint");
            }catch (Exception e){

            }*/
//            s3client.setEndpoint(aws_endpoint);
            return s3client;
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public Bucket getBucketByName(AmazonS3 s3client, String bucketName) {
        try{
            List<Bucket> buckets = getListBucket(s3client);
            for(Bucket instance: buckets){
                if(instance.getName().equals(bucketName)) return instance;
            }
            return null;
        }catch (Exception e){
            logger.error(e.toString());
        }
        return null;
    }
}
