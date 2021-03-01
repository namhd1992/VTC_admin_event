/***************************************************************************
 * Product 2018 by Quang Dat * 
 **************************************************************************/
package com.vtc.gamerid.gateway.common.ultils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.vtc.gamerid.gateway.common.Constants;

/**
 * Creator : Le Quang Dat
 * Email   : quangdat0993@gmail.com
 * Date    : Aug 28, 2018
 */
public class GoogleDriveUltils {
    
    private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
    
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
 
    // Directory to store user credentials for this application.
 
    private static final String CLIENT_SECRET_FILE_NAME = "/credentials.json";
    
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    
    private static Drive driveService;
 
    private static final java.util.Collection<String> SCOPES = DriveScopes.all();
 
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
      // Load client secrets.
      InputStream in = new FileInputStream("src/main/resources" + CLIENT_SECRET_FILE_NAME);
      GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

      // Build flow and trigger user authorization request.
      GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
              HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
              .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
              .setAccessType("offline")
              .build();
      return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
  }

 
    public static Drive getDriveService() throws IOException, GeneralSecurityException {
        if (driveService != null) {
            return driveService;
        }
        
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        driveService = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY,
                getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
        
        return driveService;
    }
    
    public static List<File> getFolderId(String folderIdParent, String folderName) throws IOException, GeneralSecurityException {
        String pageToken = null;
        List<File> list = new ArrayList<File>();
        
        String query = null;
        if(ObjectUtils.isEmpty(folderIdParent)) {
            query = " name = '" + folderName + "' " 
                    + " and mimeType = 'application/vnd.google-apps.folder' " 
                    + " and 'root' in parents";
            
            System.out.println("Folder is root : " + query);
        }else {
            query = " name = '" + folderName + "' "
                    + " and mimeType = 'application/vnd.google-apps.folder' "
                    + " and '" + folderIdParent + "' in parents";
            System.out.println("Folder has parent folder : " + query);
        }
        
        getDriveService();
        do {
            FileList result = driveService.files().list().setQ(query).setSpaces("drive")
                    .setFields("nextPageToken, files(id, name, createdTime)")
                    .setPageToken(pageToken).execute();
            for (File file : result.getFiles()) {
                list.add(file);
            }
            pageToken = result.getNextPageToken();
        } while (pageToken != null);
        
        return list;
    }
    
    public static String createFolder(String folderIdParent, String folderName)
            throws IOException, GeneralSecurityException {
        List<File> listFile = getFolderId(folderIdParent, folderName);
        if (!CollectionUtils.isEmpty(listFile)) {
            return listFile.get(0).getId();
        }
        
        File folder = new File();
        folder.setName(folderName);
        folder.setMimeType(Constants.GOOGLE_FOLDER_TYPE);

        if (!ObjectUtils.isEmpty(folderIdParent)) {
            List<String> parents = Arrays.asList(folderIdParent);
            folder.setParents(parents);
        }

        getDriveService();
        File newFolder = driveService.files().create(folder).setFields("id, name").execute();
        return newFolder.getId();
    }
    
    public static File createFile(java.io.File file, String fileName, String contentType, String folderIdParent)
            throws IOException, GeneralSecurityException {

        FileContent uploadStreamContent = new FileContent(contentType, file);
        
        File fileMetadata = new com.google.api.services.drive.model.File();
        if (!ObjectUtils.isEmpty(folderIdParent)) {
            List<String> parents = Arrays.asList(folderIdParent);
            fileMetadata.setParents(parents);
        }
        
        fileMetadata.setName(fileName);
        getDriveService();
        
        File fileGG = driveService.files()
                .create(fileMetadata, uploadStreamContent)
                .setFields("id, webContentLink, webViewLink, parents").execute();
        return fileGG;
    }
    
}
