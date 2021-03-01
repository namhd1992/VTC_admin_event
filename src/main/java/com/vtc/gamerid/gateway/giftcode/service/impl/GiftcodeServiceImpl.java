/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.giftcode.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.vtc.gamerid.gateway.common.dao.entity.Giftcode;
import com.vtc.gamerid.gateway.common.dao.repository.GiftcodeRepository;
import com.vtc.gamerid.gateway.common.dto.request.GiftcodeCreateAndUpdateRequest;
import com.vtc.gamerid.gateway.common.dto.response.GiftcodeGetRequest;
import com.vtc.gamerid.gateway.common.dto.response.GiftcodeGetResponse;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.common.ultils.StringUtils;
import com.vtc.gamerid.gateway.exception.ScoinInvalidDataRequestException;
import com.vtc.gamerid.gateway.giftcode.service.GiftcodeServcie;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 27, 2020
 */
@Service("giftcodeServiceImpl")
public class GiftcodeServiceImpl extends AbstractService<Giftcode, Long, GiftcodeRepository>
    implements GiftcodeServcie {
    
    @Override
    public GiftcodeGetResponse getGiftcodeInfo(GiftcodeGetRequest request) {
        if (ObjectUtils.isEmpty(request)
                || ObjectUtils.isEmpty(request.getMainEventId()))
            throw new ScoinInvalidDataRequestException();
        
        return new GiftcodeGetResponse(
                repo.countByEventTypeAndMainEventIdAndSubEventId(request.getEventType(), request.getMainEventId(), request.getSubEventId()),
                repo.countByEventTypeAndMainEventIdAndSubEventIdAndUserInfoNotNull(request.getEventType(), request.getMainEventId(), request.getSubEventId()));
    }

    @SuppressWarnings("resource")
    @Override
    public String createGiftcode(GiftcodeCreateAndUpdateRequest request) throws IOException {
        if (ObjectUtils.isEmpty(request)
                || ObjectUtils.isEmpty(request.getCodeFile())
                || ObjectUtils.isEmpty(request.getEventType())
                || ObjectUtils.isEmpty(request.getMainEventId()))
            throw new ScoinInvalidDataRequestException();
        
        List<Giftcode> giftcodes = repo.
                findByEventTypeAndMainEventIdAndSubEventId(request.getEventType(), 
                        request.getMainEventId(), 
                        request.getSubEventId());
        
        if (!CollectionUtils.isEmpty(giftcodes)) {
            repo.delete(giftcodes);
        }
        
        XSSFSheet workSheet = new XSSFWorkbook(request.getCodeFile().getInputStream()).getSheetAt(0);
        for (int i = 0; i < workSheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = workSheet.getRow(i);
            if (StringUtils.isEmpty(row.getCell(0).getStringCellValue()))
                throw new ScoinInvalidDataRequestException("Not found giftcode in this file");
            Giftcode giftcode = new Giftcode();
            giftcode.setMainCode(row.getCell(0).getStringCellValue());
            logger.info("row.getCell(0).getStringCellValue() ===================== {}", row.getCell(0).getStringCellValue());
            
            if (!ObjectUtils.isEmpty(row.getCell(1)))
                giftcode.setSubCode(row.getCell(1).getStringCellValue());
            giftcode.setEventType(request.getEventType());
            giftcode.setMainEventId(request.getMainEventId());
            giftcode.setSubEventId(request.getSubEventId());
            repo.save(giftcode);
        }
        return "Successfull";
    }

}
