package com.vtc.gamerid.gateway.profile.facade;

import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.UserInfo;
import com.vtc.gamerid.gateway.common.dao.repository.UserInfoRepository;
import com.vtc.gamerid.gateway.common.service.AbstractService;

/**
 * Created by phucnguyen on 15/03/2017.
 */
@Repository
public class AdminProfileFacadeImpl extends AbstractService<UserInfo, Long, UserInfoRepository>
        implements AdminProfileFacade {

//    @Autowired
//    private AdminProfileService adminProfileService;
//    
//    @Autowired
//    private SplayService         splayService;
//
//    @Autowired
//    private TblExchangeHistoryRepository exchangeHistoryRepo;
//    
//    private String               PROVIDER_NAME_CUONGPHAN;
//    
//    private String               PROVIDER_ID_CUONGPHAN;
//
//    private String               PROVIDER_SECRET_CUONGPHAN;

//    AdminProfileFacadeImpl(Environment env) {
//        PROVIDER_NAME_CUONGPHAN = env
//                .getProperty(EnvironmentKey.MINI_GAME_CUONGPHAN_NAME.getKey());
//        PROVIDER_ID_CUONGPHAN = env
//                .getProperty(EnvironmentKey.MINI_GAME_CUONGPHAN_PROVIDER_ID.getKey());
//        PROVIDER_SECRET_CUONGPHAN = env
//                .getProperty(EnvironmentKey.MINI_GAME_CUONGPHAN_PROVIDER_SERCRET.getKey());
//    }

//    @Override
//    @AuditLogAnnotation(functionName = "getProfile")
//    public ProfileBeanResponse getMyProfile(String scoinToken) throws SplayBusinessException {
//        TblUserInfo userInfo = verifyTokenUser();
//
//        if (StringUtils.isEmpty(scoinToken))
//            throw new InvalidDataRequestException();
//        // Get scoin
//        int scoinBalance = callToScoin.getCurrentBalance(scoinToken);
//        userInfo.getUserVTC().setBalance(scoinBalance);
//
//        ProfileBeanResponse profileBeanResponse = new ProfileBeanResponse();
//        profileBeanResponse.setUserId(userInfo.getId());
//        if (!StringUtils.isEmpty(userInfo.getFullName())) {
//            profileBeanResponse.setFullName(userInfo.getFullName());
//        } else {
//            profileBeanResponse.setFullName(userInfo.getUserVTC().getUsername());
//        }
//
//        profileBeanResponse.setUrlAvatar(userInfo.getUrlAvatar());
//        profileBeanResponse.setScoinBalance(userInfo.getUserVTC().getBalance());
//        profileBeanResponse.setAccountNumber(userInfo.getUserVTC().getScoinId());
//        profileBeanResponse.setVipLevel(userInfo.getVipLevel());
//        if (!StringUtils.isEmpty(userInfo.getPhoneNumber()))
//            profileBeanResponse.setPhoneNumber(StringUtils.encodePhone(userInfo.getPhoneNumber(), 3));
//
//        if (!StringUtils.isEmpty(userInfo.getEmail()))
//            profileBeanResponse.setEmail(StringUtils.encodeEmail(userInfo.getEmail()));
//
//        // Get checkin today
//        profileBeanResponse.setCheckinToday(false);
////        TblCheckinHistory lastCheckinHistory = clientCheckinDao.getLastCheckinHistory(userInfo);
////        if (!ObjectUtils.isEmpty(lastCheckinHistory)) {
////            if (lastCheckinHistory.getCreateOn().after(startDate)) {
////                profileBeanResponse.setCheckinToday(true);
////            }
//
//            // Get number history shop
////            profileBeanResponse.setNumberInboxShop(
////                    clientBuyShopItemDao.countNumberHistory(
////                                            lastCheckinHistory.getCreateOn(),
////                                            new Date(), 
////                                            userInfo.getId(), 
////                                            userInfo.getUserVTC().getScoinId()));
////        }
//
//        // Get number mission
////        profileBeanResponse.setNumberMissionUnFinish(clientMissionDao.countRecordMissionActive()
////                - clientMissionDao.countMissionFinishActive(userInfo));
////        if (profileBeanResponse.getNumberMissionUnFinish() < 0)
////            profileBeanResponse
////                    .setNumberMissionUnFinish(VariableConstant.NUMBER_MISSION_UNFINISH_DEFAULT);
//
//        // Get balance XU
//        ServerXUScoinResponse balanceXU = callToScoin
//                .getXUBalance(userInfo.getUserVTC().getScoinId());
//        if (balanceXU.getError_code() == 1) {
//            profileBeanResponse.setBalanceXU(balanceXU.getData().getTotalBalance());
//        } else {
//            profileBeanResponse.setBalanceXU(VariableConstant.BALANCE_XU_DEFAULT);
//        }
//
//        // Get card loader extra thit
//        getRewardCardLoader(userInfo.getId(), scoinToken);
//        SplayBaseResponse splayBaseResponse = splayService
//                .getProfileFromSplay(new SplayProfileRequest(scoinToken, userInfo.getId()));
//        if (!splayBaseResponse.isStatus())
//            throw new NotFoundEntityException(MessageConstant.DO_NOT_GET_PROFILE_FROM_SAPI);
//
//        String dataJson = gson.toJson(splayBaseResponse.getData());
//        GetProfileSAPIResponse data = gson.fromJson(dataJson, GetProfileSAPIResponse.class);
//        userInfo.setSplayPoint(data.getRewardPoints());
//        profileBeanResponse.setSplayPoint(userInfo.getSplayPoint());
//        repo.updateInfo(userInfo);
//
//        return profileBeanResponse;
//    }

//    @Override
//    public BaseDataResponse searchUserMoneyTransfer(Map<String, Object> dataRequest) {
//        try{
//            long accountNumber = Long.parseLong(dataRequest.get("accountNumber")+"");
//            SearchUserBeanResponse searchUser = repo.getUserInfoByAccountNumber(accountNumber);
//            if(searchUser == null){
//                logger.error("Can not find user by accountNumber: "+accountNumber);
//                return new BaseDataResponse(STATUS_CODE_FALSE, OBJECT_NOT_EXIST);
//            }
//            return new BaseDataResponse(STATUS_CODE_TRUE, SUCCESS, searchUser);
//        }catch (Exception e){
//            logger.error(e.toString());
//        }
//        return new BaseDataResponse(STATUS_CODE_FALSE, MESS_UNKNOW);
//    }
    
    
//    public boolean getRewardCardLoader(int userInfoId, String scoinToken) {
//        TblUserInfo userInfo = adminProfileService.getUserInfoById(userInfoId);
//        if(ObjectUtils.isEmpty(userInfo)) {
//            RollBackTransaction.callRollback();
//            return false;
//        }
//        CardLoaderResponse cardLoaderResponse = new CardLoaderResponse();
//        try {
//            cardLoaderResponse = callToScoin.getSplayPoint(userInfo, scoinToken);
//        } catch (RequestException e) {
//            logger.error(e.toString());
//            e.printStackTrace();
//        }
//        
//        if (cardLoaderResponse.getAmount() != 0 
//                && cardLoaderResponse.getTransactionId() != 0) {
//            SplayBaseResponse reportSapi = splayService.splayTopup(new SplayTopupBean(null, null,
//                                                            userInfo.getUserVTC().getScoinId(), 
//                                                            cardLoaderResponse.getAmount(),
//                                                            "cardloaded In game", 
//                                                            cardLoaderResponse.getTransactionId(), 
//                                                            5));
//            if (!reportSapi.isStatus()) {
//                RollBackTransaction.callRollback();
//                return false;
//                // return new BaseDataResponse( STATUS_CODE_FALSE, "không thể công thịt khi người dùng nạp thẻ");
//            }
//            logger.info("you has card loader and get reward success");
//            return true;
//        }
//        
//        logger.info("You don't have transaction about card loader");
//        return true;
//    }

//    @Override
//    public ServerXUScoinResponse getBalanceXu(Long scoinId) {
//      ServerXUScoinResponse response = new ServerXUScoinResponse();
//      try {
//        response = callToScoin.getXUBalance(scoinId);
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//      
//      return response;
//    }

//    @Override
//    public ProviderExchangeXUReponse providerExchangeXU(ExchangeXuRequest request) {
//        TblUserInfo userInfo = verifyTokenUser();
//        if (ObjectUtils.isEmpty(request) 
//                || StringUtils.isEmpty(request.getClientId())
//                || StringUtils.isEmpty(request.getClientSecret())
//                || StringUtils.isEmpty(request.getTypeExchange())
//                || (!request.getTypeExchange().equals(VariableConstant.XU_TOPUP)
//                        && !request.getTypeExchange().equals(VariableConstant.XU_DEDUCT))
//                || ObjectUtils.isEmpty(request.getUserId())
//                || request.getAmount() <= 0)
//            throw new InvalidDataRequestException();
//            
//        if (!request.getClientId().equals(PROVIDER_ID_CUONGPHAN) 
//                || !request.getClientSecret().equals(PROVIDER_SECRET_CUONGPHAN)
//                || userInfo.getId() != request.getUserId()) {
//            throw new UnAuthorizationException("Service Un Authorization");
//        }
//        
////        if (request.getUnixTime() + 30000 < new Date().getTime()
//        if(
//                 request.getUnixTime() > new Date().getTime()) {
//            throw new UnknownErrorException(StatusCode.EXPIRED_SESSION,
//                    StatusCode.EXPIRED_SESSION_DESCRIPTION);
//        }
//        
//        ServerXUScoinResponse balanceXU = new ServerXUScoinResponse();
//        try {
//            balanceXU = callToScoin.getXUBalance(userInfo.getUserVTC().getScoinId());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//            
//        TblExchangeHistory exchangeHistory = new TblExchangeHistory();
//        exchangeHistory.setUserInfo(userInfo);
//        exchangeHistory.setProvider(PROVIDER_NAME_CUONGPHAN);
//        exchangeHistory.setProviderId((long) 1);
//        exchangeHistory.setBeforeBalance(balanceXU.getData().getTotalBalance());
//        TblExchangeHistory saveExchangeHistory = exchangeHistoryRepo.create(exchangeHistory);
//        
//        ServerXUScoinRequest serverRequest = new ServerXUScoinRequest();
//        serverRequest.setScoin_id(userInfo.getUserVTC().getScoinId());
//        serverRequest.setAmount(request.getAmount());
//        serverRequest.setContent(request.getContent());
//        serverRequest.setTransid(saveExchangeHistory.getId());
//        ServerXUScoinResponse exchangeXU = new ServerXUScoinResponse();
//        try {
//            exchangeXU = callToScoin.exchangeXU(serverRequest, request.getTypeExchange(), 0);
//        } catch (Exception e) {
//            exchangeHistoryRepo.delete(saveExchangeHistory);
//            e.printStackTrace();
//        }
//        
//        if (ObjectUtils.isEmpty(exchangeXU) 
//                || exchangeXU.getError_code() < 0) {
//            exchangeHistoryRepo.delete(saveExchangeHistory);
//            throw new UnknownErrorException(exchangeXU.getError_code().toString(),
//                    exchangeXU.getError_desc());
//        }
//        
//        saveExchangeHistory.setExchangeType(request.getTypeExchange());
//        saveExchangeHistory.setAmount(request.getAmount());
//        saveExchangeHistory.setCurrentBalance(exchangeXU.getData().getTotalBalance());
//        saveExchangeHistory.setExchangeId(exchangeXU.getError_code());
//        exchangeHistory.setStatus(VariableConstant.HISTORY_STATUS_SUCCESS);
//        exchangeHistoryRepo.update(saveExchangeHistory);
//        
//        return new ProviderExchangeXUReponse(exchangeXU.getData().getTotalBalance(),
//                request.getAmount());
//    }
}
