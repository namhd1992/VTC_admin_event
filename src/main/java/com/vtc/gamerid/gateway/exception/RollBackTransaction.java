package com.vtc.gamerid.gateway.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * Created by phucnguyen on 19/04/2017.
 */
public class RollBackTransaction {
    private static Logger logger = LoggerFactory.getLogger(RollBackTransaction.class);

    public static void callRollback(){
        logger.info("Call rollback !!!");
        TransactionAspectSupport.currentTransactionStatus()
                .setRollbackOnly();
    }

}
