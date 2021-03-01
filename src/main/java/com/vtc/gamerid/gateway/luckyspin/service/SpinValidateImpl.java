package com.vtc.gamerid.gateway.luckyspin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.common.ultils.StringUtils;
import com.vtc.gamerid.gateway.exception.RegularExpression;
import com.vtc.gamerid.gateway.exception.RequestException;
import com.vtc.gamerid.gateway.luckyspin.bean.AdSEventManagerBean;
import com.vtc.gamerid.gateway.luckyspin.bean.ItemOfSpinEventBean;
import com.vtc.gamerid.gateway.luckyspin.bean.SpinBuyTurnBeanRequest;
import com.vtc.gamerid.gateway.luckyspin.bean.SpinItemBeanRequest;
import com.vtc.gamerid.gateway.luckyspin.service._interface.SpinValidate;

/**
 * Created by phucnguyen on 26/06/2017.
 */
@Service
public class SpinValidateImpl implements SpinValidate {
    private Logger logger = LoggerFactory.getLogger(SpinValidateImpl.class);

    @Override
    public void validateSpinBuyTurn(SpinBuyTurnBeanRequest spinBuyTurnBeanRequest)
            throws RequestException {
        if(spinBuyTurnBeanRequest.getSpinId() < 1)
            throw new RequestException(false, Constants.SPIN_INVALID);
        if(spinBuyTurnBeanRequest.getNumberTurns() < 1)
            throw new RequestException(false, Constants.NUMBER_TURNS_INVALID);
    }

    @Override
    public ValidateBean validateItemSpinRequest(SpinItemBeanRequest spinItemBeanRequest) {
        try{
            //Check validate name & type
            if(!RegularExpression.validateStripXss(spinItemBeanRequest.getName())){
                return new ValidateBean(false, "Item name invalid");
            }
            if(!RegularExpression.validateStripXss(spinItemBeanRequest.getStatus())){
                return new ValidateBean(false, "Status invalid");
            }
            if(!(RegularExpression.validateStripXss(spinItemBeanRequest.getType())
                    && (spinItemBeanRequest.getType().equals("XU")
                            || spinItemBeanRequest.getType().equals("SCOIN")
                            || spinItemBeanRequest.getType().equals("GIFTCODE")
                            || spinItemBeanRequest.getType().equals("SCOIN_CARD")
                            || spinItemBeanRequest.getType().equals("JACKPOT")
                            || spinItemBeanRequest.getType().equals("REALITY")
                            || spinItemBeanRequest.getType().equals("LUCKY_NUMBER")
                            || spinItemBeanRequest.getType().equals("ACTION")))) {
                return new ValidateBean(false, "Item type invalid");
            }
            if(!RegularExpression.validateStripXss(spinItemBeanRequest.getWinningTitle())){
                return new ValidateBean(false, "Winning title invalid");
            }
            if(!RegularExpression.validateStripXss(spinItemBeanRequest.getWheelTitle())){
                return new ValidateBean(false, "Wheel title invalid");
            }

            //Validate value
            if(spinItemBeanRequest.getValue() < 0){
                return new ValidateBean(false, "Value of item invalid");
            }

            //Check if giftcode
            /*if(spinItemBeanRequest.getType().equals("giftcode")){
                if(spinItemBeanRequest.getGiftCodeFile() == null){
                    return new ValidateBean(false, "Not found file giftcode");
                }
            }*/

            //Validate realitygift
            if(spinItemBeanRequest.getType().equals("REALITY")){
                if(spinItemBeanRequest.getValue() < 0)
                    return new ValidateBean(false, "Must have quantity");
            }

            //Validate description
            if(spinItemBeanRequest.getDescription() != null
                    && !RegularExpression.validateStripXss(spinItemBeanRequest.getDescription()))
                return new ValidateBean(false, "Description invalid");

            return new ValidateBean(true, Constants.SUCCESS);
        }catch (Exception e){
            logger.error("validateItemSpinRequest", e);
        }
        return new ValidateBean(false, Constants.BAD_REQ);
    }

    @Override
    public ValidateBean validateSpinEventRequest(AdSEventManagerBean spinEventManagerBean) {
        try{
            //Validate name
            if(!RegularExpression.validateStripXss(spinEventManagerBean.getName()))
                return new ValidateBean(false, "Name of luckyspin invalid");

            //Validate description
            if(StringUtils.isEmpty(spinEventManagerBean.getDescription()))
                return new ValidateBean(false, "Description of luckyspin invalid");

            //Validate freeSpinPerDay
            if(spinEventManagerBean.getFreeSpinPerDay() < 0)
                return new ValidateBean(false, "Free spin per day of luckyspin invalid");

            //Validate freeSpinOnStart
            if(spinEventManagerBean.getFreeSpinOnStart() < 0)
                return new ValidateBean(false, "Free spin on start of luckyspin invalid");

//            //Validate price of spin
//            if(StringUtils.isEmpty(spinEventManagerBean.getPackagePriceTurn()))
//                return new ValidateBean(false, "Price of luckyspin invalid");

            //Validate item of spin
            if(spinEventManagerBean == null ||
                    spinEventManagerBean.getItemOfSpin().size() < 2)
                return new ValidateBean(false, "Item of luckyspin invalid");
            for(ItemOfSpinEventBean instance: spinEventManagerBean.getItemOfSpin()){
                if(instance.getSpinItemId() < 0)
                    return new ValidateBean(false, "Item of luckyspin invalid");
            }

            return new ValidateBean(true, Constants.SUCCESS);
        }catch (Exception e){
            logger.error("validateSpinEventRequest", e);
        }
        return new ValidateBean(false, Constants.BAD_REQ);
    }
}