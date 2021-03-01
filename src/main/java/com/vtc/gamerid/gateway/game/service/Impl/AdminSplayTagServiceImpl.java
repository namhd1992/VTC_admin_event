package com.vtc.gamerid.gateway.game.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.TblSplayTag;
import com.vtc.gamerid.gateway.common.dao.repository.GameTagRepository;
import com.vtc.gamerid.gateway.common.dto.response.ServiceResponse;
import com.vtc.gamerid.gateway.exception.RollBackTransaction;
import com.vtc.gamerid.gateway.game.bean.AdminSplayTagBean;
import com.vtc.gamerid.gateway.game.service.AdminSplayTagService;

/**
 * Created by phucnguyen on 03/04/2018.
 */
@Service
public class AdminSplayTagServiceImpl implements AdminSplayTagService {
    @Autowired
    private GameTagRepository adminSplayTagDao;
    private Logger logger = LoggerFactory.getLogger(AdminSplayTagServiceImpl.class);

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ServiceResponse createSplayTag(AdminSplayTagBean dataRequest) {
        List<TblSplayTag> result = new ArrayList<TblSplayTag>();
        if(dataRequest.getListName().size() > 0){
            for(String instance: dataRequest.getListName()){
                //Check tag exits
                TblSplayTag checkSplayTag = adminSplayTagDao.findByName(dataRequest.getName());
                if(checkSplayTag != null)
                    return new ServiceResponse(false, Constants.OBJECT_EXIST);

                //Create tag
                TblSplayTag splayTag = new TblSplayTag(instance);
                splayTag.setBackgroundColor(dataRequest.getBackgroundColor());
                splayTag = adminSplayTagDao.save(splayTag);
                if(splayTag == null){
                    RollBackTransaction.callRollback();
                    return new ServiceResponse(false, Constants.OBJECT_NOT_CREATE);
                }
                result.add(splayTag);
            }
        }

        if(dataRequest.getName() != null){
            //Create splay tag
            TblSplayTag splayTag = new TblSplayTag(dataRequest.getName());
            splayTag.setTypeName(dataRequest.getTypeName());
            splayTag.setBackgroundColor(dataRequest.getBackgroundColor());
            splayTag = adminSplayTagDao.save(splayTag);
            if(splayTag == null){
                RollBackTransaction.callRollback();
                return new ServiceResponse(false, Constants.OBJECT_NOT_CREATE);
            }
            result.add(splayTag);
        }

        return new ServiceResponse(true, result);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ServiceResponse updateSplayTag(AdminSplayTagBean dataRequest) {
        try{
            //Get tag
            TblSplayTag splayTag = adminSplayTagDao.findOne(dataRequest.getId());
            if(splayTag == null)
                return new ServiceResponse(false, Constants.OBJECT_NOT_EXIST);

            //Update tag
            splayTag.setName(dataRequest.getName());
            splayTag.setTypeName(dataRequest.getTypeName());
            splayTag.setBackgroundColor(dataRequest.getBackgroundColor());

            splayTag = adminSplayTagDao.save(splayTag);
            if(splayTag == null){
                RollBackTransaction.callRollback();
                logger.error("Can not update splay tag");
                return new ServiceResponse(false, Constants.OBJECT_NOT_UPDATE);
            }
            return new ServiceResponse(true, splayTag);
        }catch (Exception e){
            RollBackTransaction.callRollback();
            logger.error("updateSplayTag", e);
        }
        return new ServiceResponse(false, Constants.MESS_UNKNOW);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ServiceResponse deleteSplayTag(long id) {
        try{
            //Get tag
            TblSplayTag splayTag = adminSplayTagDao.findOne(id);
            if(splayTag == null)
                return new ServiceResponse(false, Constants.OBJECT_NOT_EXIST);

            adminSplayTagDao.delete(splayTag);
            return new ServiceResponse(true, Constants.OBJECT_NOT_DELETE);
        }catch (Exception e){
            RollBackTransaction.callRollback();
            logger.error("deleteSplayTag", e);
        }
        return new ServiceResponse(false, Constants.MESS_UNKNOW);
    }
}
