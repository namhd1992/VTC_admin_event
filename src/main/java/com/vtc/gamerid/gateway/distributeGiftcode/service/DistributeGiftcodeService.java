package com.vtc.gamerid.gateway.distributeGiftcode.service;

import javax.mail.MessagingException;
import javax.mail.Transport;

import com.vtc.gamerid.gateway.distributeGiftcode.bean.DistributeGiftcodeBean;

/**
 * Created by phucnguyen on 23/04/2018.
 */
public interface DistributeGiftcodeService {
    public void distributeGiftcode(DistributeGiftcodeBean dataRequest, Transport transport) throws MessagingException;
//    public ServiceResponse reportDistributeGiftcode(DistributeGiftcodeFilter dataRequest);
}
