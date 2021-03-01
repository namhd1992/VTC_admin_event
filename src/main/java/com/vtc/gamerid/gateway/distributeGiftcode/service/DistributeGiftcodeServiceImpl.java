package com.vtc.gamerid.gateway.distributeGiftcode.service;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtc.gamerid.gateway.common.dao.entity.TblDistributeGiftcodeHistory;
import com.vtc.gamerid.gateway.common.dao.repository.DistributeGiftcodeRepository;
import com.vtc.gamerid.gateway.common.ultils.StringUtils;
import com.vtc.gamerid.gateway.distributeGiftcode.bean.DistributeGiftcodeBean;

/**
 * Created by phucnguyen on 23/04/2018.
 */
@Service
public class DistributeGiftcodeServiceImpl implements DistributeGiftcodeService {
    private Logger logger = LoggerFactory.getLogger(DistributeGiftcodeServiceImpl.class);

    static final String CONFIGSET = "ConfigSet";
    static final int PORT = 587;

    @Autowired
    private DistributeGiftcodeRepository distributeGiftcodeDao;
    
    @Override
    public void distributeGiftcode(DistributeGiftcodeBean dataRequest, Transport transport) throws MessagingException {
        try{
            InputStream inputStream = new URL(dataRequest.getGiftcodeFile()).openStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            StringBuffer toEmail = new StringBuffer();
            StringBuffer giftcode = new StringBuffer();
            StringBuffer content = new StringBuffer();
            DataFormatter formatter = new DataFormatter();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                //Renew string
                toEmail.delete(0, toEmail.length());
                giftcode.delete(0, giftcode.length());
                content.delete(0, content.length());
                content.append(dataRequest.getContent());

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    int columnIndex = currentCell.getColumnIndex();

                    switch (columnIndex) {
                        case 0:
                            toEmail.append(currentCell.getStringCellValue().trim());
                            String validateEmail = toEmail.toString();
                            validateEmail = validateEmail.replaceAll(" ", "");
                            validateEmail = validateEmail.toLowerCase();
                            if(!StringUtils.isValidateFormatEmail(validateEmail)) {
                                distributeGiftcodeDao.save(new TblDistributeGiftcodeHistory(
                                        dataRequest.getFromEmail(), 
                                        dataRequest.getFromName(), dataRequest.getTitle(), 
                                        toEmail.toString(), "To Email Invalid format", new Date(),(long) 0));
                                continue;
                            }
                            
                            break;
                        case 1:
                            giftcode.append(formatter.formatCellValue(currentCell).trim());
                            if(content.indexOf(":tmp") > 0){
                                content.replace(content.indexOf(":tmp"), content.indexOf(":tmp")+4,
                                        giftcode.toString());
                            }else{
                                content.append(": ");
                                content.append(giftcode);
                            }

                            sendEmail(transport, dataRequest.getFromEmail(), dataRequest.getFromName(),
                                    dataRequest.getTitle(), content.toString(), toEmail.toString(),
                                    dataRequest.getCreateBy());
                            break;
                    }
                }
                workbook.close();
            }
        }catch (Exception e){
            logger.error("distributeGiftcode", e);
        }finally {
            transport.close();
        }
    }

//    @Override
//    public ServiceResponse reportDistributeGiftcode(DistributeGiftcodeFilter dataRequest) {
//        return new ServiceResponse(true, distributeGiftcodeDao.
//                getDistributeGiftcodeHistory(dataRequest));
//    }

    private boolean sendEmail(Transport transport, String fromEmail, String fromName, String title,
                              String content, String toEmail,
                              Long createBy) throws Exception {
        
        //fix email fomart
        toEmail = toEmail.replaceAll(" ", "");
        toEmail = toEmail.toLowerCase();
        if(!StringUtils.isValidateFormatEmail(toEmail)) {
            return false;
        }
            
        
//         Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        try{
            // Create a message with the specified information.
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail, fromName));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(title);
            msg.setHeader("Content-Type", "text/html; charset=UTF-8");
            msg.setContent(content, "text/html; charset=UTF-8");
            msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

            logger.info("Email sending  \'" + toEmail + "\' ...");
            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            logger.info("Send Mail to  : " + toEmail + " Success");
            
            return true;
        }catch (Exception e){
            logger.error("sendEmail", e);
            distributeGiftcodeDao.save(new TblDistributeGiftcodeHistory(
                    fromEmail, fromName, title, toEmail, e.toString(), new Date(), createBy));
        }
        return false;
    }
}
