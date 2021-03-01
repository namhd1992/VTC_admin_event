package com.vtc.gamerid.gateway.luckyspin.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.TblLuckySpinItem;
import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.common.dao.repository.LuckySpinItemRepository;
import com.vtc.gamerid.gateway.common.dto.response.ServiceResponse;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.common.ultils.VNCharacterUtils;
import com.vtc.gamerid.gateway.exception.RegularExpression;
import com.vtc.gamerid.gateway.exception.RollBackTransaction;
import com.vtc.gamerid.gateway.luckyspin.bean.SpinItemBeanRequest;
import com.vtc.gamerid.gateway.luckyspin.service._interface.AdminItemSpinService;

/**
 * Created by phucnguyen on 03/07/2017.
 */
@Service
public class AdminItemSpinServiceImpl extends AbstractService<TblLuckySpinItem, Long, LuckySpinItemRepository> implements AdminItemSpinService {
    @Autowired
    private LuckySpinItemRepository spinItemDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ServiceResponse createItemSpin(SpinItemBeanRequest spinItemBeanRequest) {
        try{
            UserGameRID createBy = verifyTokenUser();
            int quantity = -1;
            //Add quantity for reality gift
            if(spinItemBeanRequest.getType().equals("REALITY")){
                quantity = spinItemBeanRequest.getQuantity();
            }

            //Check default
            if(spinItemBeanRequest.getIsDefault() > 0){
                if(!createBy.getGroupRole().getName().equals("admin"))
                    spinItemBeanRequest.setIsDefault(0);
            }

            //Add new item of spin
            TblLuckySpinItem itemOfSpin = new TblLuckySpinItem(spinItemBeanRequest.getName(),
                    VNCharacterUtils.removeAccent(spinItemBeanRequest.getName()),
                    spinItemBeanRequest.getValue(), spinItemBeanRequest.getType(),
                    quantity, createBy.getId(), spinItemBeanRequest.getStatus(), spinItemBeanRequest.getIsDefault(),
                    new Date(), spinItemBeanRequest.getWeight());
            itemOfSpin.setUrlImage(spinItemBeanRequest.getUrlImage());
            itemOfSpin.setDefaultPosition(spinItemBeanRequest.getDefaultPosition());
            itemOfSpin.setQuantity(spinItemBeanRequest.getQuantity());
            itemOfSpin.setWinningTitle(spinItemBeanRequest.getWinningTitle());
            itemOfSpin.setWheelTitle(spinItemBeanRequest.getWheelTitle());
            itemOfSpin.setBigItem(spinItemBeanRequest.isBigItem());;
            itemOfSpin.setDescription(spinItemBeanRequest.getDescription());
            itemOfSpin.setHighLights(spinItemBeanRequest.isHighLights());
            itemOfSpin = spinItemDao.save(itemOfSpin);

            if(itemOfSpin == null){
                RollBackTransaction.callRollback();
                return new ServiceResponse(false, "Can not create item of spin");
            }

            //Check if giftcode
//            if(spinItemBeanRequest.getType().equals("GIFTCODE")){
//                //Upload file giftcode
//                String fileGiftcode = uploadFileService.uploadFile(
//                        spinItemBeanRequest.getGiftCodeFile());
//                if(fileGiftcode == null) {
//                    RollBackTransaction.callRollback();
//                    return new ServiceResponse(false, "Can not upload file giftcode");
//                }
//
//                //Save giftcode to db
//                int numberGiftcode = giftcodeManagerService.
//                        saveGiftcodeInFile(fileGiftcode, null, itemOfSpin, -1);
//                if(numberGiftcode < 1){
//                    RollBackTransaction.callRollback();
//                    return new ServiceResponse(false, "Can not save giftcode");
//                }
//                itemOfSpin.setQuantity(numberGiftcode);
//                itemOfSpin = spinItemDao.save(itemOfSpin);
//            }
            return new ServiceResponse(true, itemOfSpin);
        }catch (Exception e){
            RollBackTransaction.callRollback();
            logger.error(e.toString());
        }
        return new ServiceResponse(false, Constants.MESS_UNKNOW);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ServiceResponse updateItemSpin(SpinItemBeanRequest spinItemBeanRequest) {
        try{
            UserGameRID createBy = verifyTokenUser();
            //Get item
            if(spinItemBeanRequest.getItemId() < 1)
                return new ServiceResponse(false, "ItemID invalid");
            TblLuckySpinItem itemOfSpin = spinItemDao.findOne(spinItemBeanRequest.getItemId());
            if(itemOfSpin == null)
                return new ServiceResponse(false, "Find not found Item");

            //Add new name
            if(RegularExpression.validateStripXss(spinItemBeanRequest.getName())){
                itemOfSpin.setName(spinItemBeanRequest.getName());
//                itemOfSpin.setKeyName(VNCharacterUtils.removeAccent(spinItemBeanRequest.getName()));
            }

            //Add new value
            if(spinItemBeanRequest.getValue() > 0){
                itemOfSpin.setValue(spinItemBeanRequest.getValue());
            }

            //Add new type
            if(RegularExpression.validateStripXss(spinItemBeanRequest.getType())){
                itemOfSpin.setType(spinItemBeanRequest.getType());
            }

            //Update status
            itemOfSpin.setStatus(spinItemBeanRequest.getStatus());

            //Add image
            if(spinItemBeanRequest.getUrlImage() == null ||
                    RegularExpression.validateStripXss(spinItemBeanRequest.getUrlImage())){
                itemOfSpin.setUrlImage(spinItemBeanRequest.getUrlImage());
            }
            if(spinItemBeanRequest.getIsDefault() == 1){
                if(spinItemBeanRequest.getDefaultPosition() > -1)
                    itemOfSpin.setDefaultPosition(spinItemBeanRequest.getDefaultPosition());
                if(spinItemBeanRequest.getQuantity() > -1)
                    itemOfSpin.setQuantity(spinItemBeanRequest.getQuantity());
            }

            if(!spinItemBeanRequest.getType().equals("GIFTCODE")){
                itemOfSpin.setQuantity(spinItemBeanRequest.getQuantity());
            }

            if(createBy.getGroupRole().getName().equals("admin")){
                itemOfSpin.setIsDefault(spinItemBeanRequest.getIsDefault());
            }
            itemOfSpin.setWeight(spinItemBeanRequest.getWeight());
            itemOfSpin.setWinningTitle(spinItemBeanRequest.getWinningTitle());
            itemOfSpin.setWheelTitle(spinItemBeanRequest.getWheelTitle());
            itemOfSpin.setBigItem(spinItemBeanRequest.isBigItem());
            itemOfSpin.setDescription(spinItemBeanRequest.getDescription());
            itemOfSpin.setHighLights(spinItemBeanRequest.isHighLights());
            itemOfSpin = spinItemDao.save(itemOfSpin);
            if(itemOfSpin != null)
                return new ServiceResponse(true, Constants.SUCCESS);

            return new ServiceResponse(false, "Can not update item");

        }catch (Exception e){
            RollBackTransaction.callRollback();
            logger.error(e.toString());
        }
        return new ServiceResponse(false, Constants.BAD_REQ);
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public ServiceResponse deleteItemSpin(Long spintItemId) {
        try{
            TblLuckySpinItem itemOfSpin = spinItemDao.findOne(spintItemId);
            if(itemOfSpin == null)
                return new ServiceResponse(false, "Find not found item spin");

            //Set status is deleted
            itemOfSpin.setStatus(Constants.STATUS_DELETED);
            itemOfSpin = spinItemDao.save(itemOfSpin);
            if(itemOfSpin != null)
                return new ServiceResponse(true, Constants.SUCCESS);

            return new ServiceResponse(false, "Can not delete item spin");
        }catch (Exception e){
            logger.error(e.toString());
        }
        return new ServiceResponse(false, Constants.MESS_UNKNOW);
    }
}
