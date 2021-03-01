package com.vtc.gamerid.gateway.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vtc.gamerid.gateway.common.dao.entity.TblFunction;
import com.vtc.gamerid.gateway.common.dao.repository.FunctionManagerRepository;
import com.vtc.gamerid.gateway.common.dao.repository.UserInfoRepository;
import com.vtc.gamerid.gateway.role.service._interface.FunctionManagerService;

/**
 * Created by phucnguyen on 03/05/2017.
 */
@Service
public class FunctionManagerServiceImpl implements FunctionManagerService {
    @Autowired
    private FunctionManagerRepository functionManagerDao;
    
    @Autowired
    private UserInfoRepository userInfoRepo;

    @Override
    public List<TblFunction> getListFunction(String status, int limit, int offset) {
        return functionManagerDao.findByStatus(status, new PageRequest(offset, limit));
    }

    @Override
    public int countUserInfo(String status, String searchValue) {
        return userInfoRepo.countUserInfo(status, searchValue, searchValue, searchValue);
    }

    @Override
    public int countFunction(String status) {
        return functionManagerDao.countByStatus(status);
    }
}
