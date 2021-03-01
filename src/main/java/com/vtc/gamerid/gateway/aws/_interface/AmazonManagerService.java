package com.vtc.gamerid.gateway.aws._interface;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;

import java.io.File;
import java.util.List;

/**
 * Created by phucnguyen on 04/04/2017.
 */
public interface AmazonManagerService {
    public String createFolder(AmazonS3 s3client, Bucket bucket, String folderName);

    public Bucket createBucket(AmazonS3 s3client, String bucketName);

    public String uploadFile(AmazonS3 s3client, Bucket bucket, String folderName, File file);

    public List<Bucket> getListBucket(AmazonS3 s3client);

    public AmazonS3 loginAmazonS3();

    public Bucket getBucketByName(AmazonS3 s3client, String bucketName);
}
