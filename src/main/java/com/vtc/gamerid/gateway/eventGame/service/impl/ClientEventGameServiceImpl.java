///***************************************************************************
//* Product made by Quang Dat *
// **************************************************************************/
//package com.vtc.gamerid.gateway.eventGame.service.impl;
//
//import java.lang.reflect.Type;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//
//import com.google.gson.reflect.TypeToken;
//import com.vtc.gamerid.gateway.common.EnvironmentKey;
//import com.vtc.gamerid.gateway.common.dao.entity.GameEvent;
//import com.vtc.gamerid.gateway.common.dao.entity.TblEventGiftHistory;
//import com.vtc.gamerid.gateway.common.dao.entity.TblLoginGameHistory;
//import com.vtc.gamerid.gateway.common.dao.entity.TblUserEventGame;
//import com.vtc.gamerid.gateway.common.dao.repository.GameEventRepository;
//import com.vtc.gamerid.gateway.common.dao.repository.TblEventGiftHistoryRepository;
//import com.vtc.gamerid.gateway.common.dao.repository.TblLoginGameHistoryRepository;
//import com.vtc.gamerid.gateway.common.dao.repository.TblUserEventGameRepository;
//import com.vtc.gamerid.gateway.common.dto.request.BuyItemShopEventGameRequest;
//import com.vtc.gamerid.gateway.common.dto.request.ExchangePointEventGameRequest;
//import com.vtc.gamerid.gateway.common.dto.request.GetEventGameRequest;
//import com.vtc.gamerid.gateway.common.dto.request.ServerInfoMongChinhDoResponse;
//import com.vtc.gamerid.gateway.common.dto.request.SplayExchangeGiftEventGameRequest;
//import com.vtc.gamerid.gateway.common.dto.request.TopupXURequest;
//import com.vtc.gamerid.gateway.common.dto.response.EventGameResponse;
//import com.vtc.gamerid.gateway.common.dto.response.EventGameResponseData;
//import com.vtc.gamerid.gateway.common.dto.response.ExchangeGiftEventGameResponse;
//import com.vtc.gamerid.gateway.common.dto.response.GetEventGameResponse;
//import com.vtc.gamerid.gateway.common.dto.response.ItemEventResponse;
//import com.vtc.gamerid.gateway.common.dto.response.JoinEventGameResponse;
//import com.vtc.gamerid.gateway.common.dto.response.PlayerInfoResponse;
//import com.vtc.gamerid.gateway.common.dto.response.ScoinBaseResponse;
//import com.vtc.gamerid.gateway.common.dto.response.ServerXUScoinResponse;
//import com.vtc.gamerid.gateway.common.dto.response.UserChildentResponse;
//import com.vtc.gamerid.gateway.common.service.AbstractService;
//import com.vtc.gamerid.gateway.common.ultils.DateUtils;
//import com.vtc.gamerid.gateway.common.ultils.StringUtils;
//import com.vtc.gamerid.gateway.component.VariableConstant;
//import com.vtc.gamerid.gateway.eventGame.service.ClientEventGameService;
//import com.vtc.gamerid.gateway.exception.FailedToExecuteException;
//import com.vtc.gamerid.gateway.exception.InvalidDataRequestException;
//import com.vtc.gamerid.gateway.exception.NotFoundEntityException;
//import com.vtc.gamerid.gateway.exception.SplayBusinessException;
//import com.vtc.gamerid.gateway.exception.UnknownErrorException;
//import com.vtc.gamerid.gateway.hibernate.TblShoping;
//import com.vtc.gamerid.gateway.hibernate.TblShopingItem;
//import com.vtc.gamerid.gateway.hibernate.Game;
//import com.vtc.gamerid.gateway.hibernate.TblUserInfo;
//import com.vtc.gamerid.gateway.hibernate.TblUserVTC;
//import com.vtc.gamerid.gateway.network.CallToServer;
//import com.vtc.gamerid.gateway.profile.service.AdminProfileService;
//import com.vtc.gamerid.gateway.shoping.bean.ClientShopingItemFilter;
//import com.vtc.gamerid.gateway.shoping.dao.AdminShopingDao;
//import com.vtc.gamerid.gateway.shoping.dao.ClientShopingItemDao;
//import com.vtc.gamerid.gateway.splayGame.dao.ClientSplayGameDao;
//
///**
// * Author : Dat Le Quang
// * Email: Quangdat0993@gmail.com
// * Dec 26, 2018
// */
//@Service("clientEventGameService")
//public class ClientEventGameServiceImpl extends AbstractService<TblUserEventGameRepository>
//        implements ClientEventGameService {
//    
//    @Autowired
//    GameEventRepository        eventGameRepo;
//
//    @Autowired
//    TblEventGiftHistoryRepository eventGiftHistoryRepository;
//
//    @Autowired
//    AdminProfileService           profileService;
//    
//    @Autowired
//    ClientSplayGameDao            splayGameRepo;
//
//    @Autowired
//    AdminShopingDao               shopingDao;
//
//    @Autowired
//    ClientShopingItemDao          shopingItemDao;
//
//    @Autowired
//    TblLoginGameHistoryRepository loginGameHistoryRepo;
//
//    private String HEADER_BASE_URL;
//    private String EVENTGAME_TRIEUHOI_CHINHDO_URL;
//    private String EVENTGAME_TRIEUHOI_CHINHDO_SECRET;
//    private String MCD_SERVER_INFO_URL;
//    private String MCD_PLAYER_INFO_URL;
//    private String MCD_PLAYER_INFO_KEY;
//    
//    ClientEventGameServiceImpl(Environment env){
//        EVENTGAME_TRIEUHOI_CHINHDO_URL = env.getProperty(EnvironmentKey.EVENTGAME_TRIEUHOI_CHINHDO_URL.getKey());
//        EVENTGAME_TRIEUHOI_CHINHDO_SECRET = env.getProperty(EnvironmentKey.EVENTGAME_TRIEUHOI_CHINHDO_SECRET.getKey());
//        MCD_SERVER_INFO_URL = env.getProperty(EnvironmentKey.MCD_SERVER_INFO_URL.getKey());
//        MCD_PLAYER_INFO_URL = env.getProperty(EnvironmentKey.MCD_PLAYER_INFO_URL.getKey());
//        MCD_PLAYER_INFO_KEY = env.getProperty(EnvironmentKey.MCD_PLAYER_INFO_KEY.getKey());
//        HEADER_BASE_URL = env.getProperty(EnvironmentKey.HEADER_BASE_URL.getKey());
//    }
//    
//    @Override
//    public GetEventGameResponse getEvent(GetEventGameRequest request) {
//        List<GameEvent> eventGames = new ArrayList<>();
//        GameEvent eventGame = new GameEvent();
//        if (ObjectUtils.isEmpty(request.getSearchValue())) {
//            eventGames = eventGameRepo.getAllAndById(null);
//            if (CollectionUtils.isEmpty(eventGames)) {
//                throw new NotFoundEntityException("All the event not active");
//            }
//            eventGame = eventGames.get(0);
//        }
//        
//        TblShoping shoping = shopingDao.findByScoinGameId(eventGame.getServiceId());
//        if(ObjectUtils.isEmpty(shoping)) {
//            throw new NotFoundEntityException("Not found Shop of this game");
//        }
//        
//        ClientShopingItemFilter clientShopingItemFilter = new ClientShopingItemFilter();
//        clientShopingItemFilter.setShopId(shoping.getId());
//        clientShopingItemFilter.setItemType(3);
//        clientShopingItemFilter.setLimit(50);
//        
//        List<TblShopingItem> shopingItems = shopingItemDao
//                .getListItem(clientShopingItemFilter);
//        if(CollectionUtils.isEmpty(shopingItems)) {
//            return new GetEventGameResponse(eventGame, new ArrayList<ItemEventResponse>());
//        }
//        
//        //sort list by price
//        Collections.sort(shopingItems, new Comparator<TblShopingItem>() {
//
//          @Override
//          public int compare(TblShopingItem o1, TblShopingItem o2) {
//            return (int) (o1.getPrice() - o2.getPrice());
//          }
//        });
//        
//        List<ItemEventResponse> itemResponses = new ArrayList<>();
//        shopingItems.forEach(shopingItem -> {
//            ItemEventResponse item = new ItemEventResponse(shopingItem, shopingItem.getPrice());
//            itemResponses.add(item);
//        });
//        
//        GetEventGameResponse response = new GetEventGameResponse(eventGame, itemResponses);
//        
//        return response;
//    }
//    
//    @Override
//    public int countAllRecordEvent() {
//        return eventGameRepo.countAllRecordEvent();
//    }
//
//    
//    public EventGameResponseData exchangePoint(String typeExchange, Long scoinId, Long exchangePoint, Long transId) {
//        if (ObjectUtils.isEmpty(scoinId)
//                || ObjectUtils.isEmpty(exchangePoint)
//                || ObjectUtils.isEmpty(transId)) {
//            throw new InvalidDataRequestException("Invalid data request exchange Point");
//        }
//        
//        String dateRequest = DateUtils.toStringFormDate(new Date(), DateUtils.DATE_TIME_CODE_SCOIN);
//        
//        ExchangePointEventGameRequest exchangeRequest = new ExchangePointEventGameRequest();
//        
//        exchangeRequest.setName("Đổi thưởng điểm triệu hồi - " 
//                                + "Scoin Id : " + scoinId
//                                + "Points : " + exchangePoint
//                                + "Phương thức : " + typeExchange
//                                + "Date : " + DateUtils.toStringFormDate(new Date(), DateUtils.DATE_TIME_MYSQL_FORMAT));
//        exchangeRequest.setAccountId(scoinId);
//        exchangeRequest.setPoint(exchangePoint);
//        exchangeRequest.setTransId(transId);
//        exchangeRequest.setTransDate(dateRequest);
//        exchangeRequest.setSignature(StringUtils.toMD5(scoinId.toString() 
//                                                          + transId.toString()
//                                                          + dateRequest
//                                                          + exchangePoint.toString()
//                                                          + EVENTGAME_TRIEUHOI_CHINHDO_SECRET).toLowerCase());
//        String responseJson = null;
//        try {
//            responseJson = CallToServer.callApiService(EVENTGAME_TRIEUHOI_CHINHDO_URL, gson.toJson(exchangeRequest), "POST");
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new UnknownErrorException("Call to server exchange point unsuccess");
//        }
//        
//        EventGameResponse response = gson.fromJson(responseJson, EventGameResponse.class);
//        if (response.getStatus() != 1) {
//            throw new UnknownErrorException(response.getStatus().toString() , response.getDescription());
//        }
//        
//        return response.getData();
//    }
//
//    @Override
//    public JoinEventGameResponse joinEventGame() throws SplayBusinessException {
//        TblUserInfo userInfo = verifyTokenUser();
//        
//        //check event game
//        List<GameEvent> eventGames = eventGameRepo.getAllAndById(null);
//        if (CollectionUtils.isEmpty(eventGames) || eventGames.size() != 1) {
//            throw new NotFoundEntityException("Not found Event game by this id");
//        }
//        GameEvent eventGame = eventGames.get(0);
//        Date conditionDate = DateUtils.toDateChangeFormatFromDate(eventGame.getFromDate(), DateUtils.DATE_TIME_MYSQL_FORMAT, DateUtils.DATE_MYSQL_FORMAT);
//        
//        //check user join event
//        String baseUrlEvent = HEADER_BASE_URL + eventGame.getUrlBaseEvent();
//        TblUserEventGame userEventGame = repo.getByUserInfoAndEventGame(userInfo.getId(), eventGame.getId());
//        if (!ObjectUtils.isEmpty(userEventGame)) {
//            if(!userEventGame.isFinishMission()) {
//                // played game
//                if(checkLoginGameServiceOfScoin(userInfo, eventGame.getServiceId(), conditionDate)) {
//                    userEventGame.setFinishMission(true);
//                    userEventGame.setEventPoint(userEventGame.getEventPoint() + eventGame.getOncePoint());
//                    TblUserEventGame saveUserEventGame = repo.update(userEventGame);
//                    if(ObjectUtils.isEmpty(saveUserEventGame)) {
//                        throw new FailedToExecuteException("Don't save User join event game");
//                    }
//                    
//                    //add point if finish condition while not comeback link share
//                    if (!userEventGame.isUsedLink()) {
//                      if (!ObjectUtils.isEmpty(userEventGame.getUserParent())) {
//                          TblUserVTC parentUser = profileService.getUserScoinByAccountId(userEventGame.getUserParent());
//                          if(ObjectUtils.isEmpty(parentUser)) {
//                              throw new NotFoundEntityException("This Account is Empty");
//                          }
//                          
//                          TblUserEventGame userEventParent = repo
//                                  .getByUserInfoAndEventGame(parentUser.getUserInfo().getId(), eventGame.getId());
//                          if (ObjectUtils.isEmpty(userEventParent)) {
//                              throw new NotFoundEntityException("Not found parent user join event");
//                          }
//                          userEventGame.setUsedLink(true);
//                          repo.update(userEventGame);
//                          userEventParent.setEventPoint(userEventParent.getEventPoint() + eventGame.getOncePoint());
//                          TblUserEventGame saveUserEventParent = repo.update(userEventParent);
//                          if(ObjectUtils.isEmpty(saveUserEventParent)) {
//                              throw new FailedToExecuteException("Don't save User used link");
//                          }
//                      }
//                    }
//                }
//            }
//            
//            List<UserChildentResponse> userChildentResponses = new ArrayList<>();
//            List<TblUserEventGame> userChildents = repo.getAllByUserParent(userEventGame.getUserInfo().getUserVTC().getScoinId());
//            if (!CollectionUtils.isEmpty(userChildents)) {
//                userChildents.forEach(userChildent -> {
//                    userChildentResponses.add(new UserChildentResponse(userChildent.getUserInfo().getUserVTC().getUsername(),
//                            userChildent.isFinishMission()));
//                });
//            }
//            
//            return new JoinEventGameResponse(eventGame.getId(),
//                            userEventGame.getFacebookId(),
//                            userEventGame.getEventPoint(),
//                            baseUrlEvent + "/" + userEventGame.getUserInfo().getUserVTC().getScoinId().toString(),
//                            userChildentResponses);
//        }
//        
//        userEventGame = new TblUserEventGame();
//        userEventGame.setUserInfo(userInfo);
//        userEventGame.setCreateOn(new Date());
//        userEventGame.setEventGame(eventGame);
//        userEventGame.setEventPoint(0);
//        userEventGame.setFinishMission(false);
//        userEventGame.setUsedLink(false);
//        if(checkLoginGameServiceOfScoin(userInfo, eventGame.getServiceId(), conditionDate)) {
//            userEventGame.setEventPoint(eventGame.getOncePoint());
//            userEventGame.setFinishMission(true);
//        }
//        
//        TblUserEventGame saveUserEventGame = repo.create(userEventGame);
//        if(ObjectUtils.isEmpty(saveUserEventGame)) {
//            throw new FailedToExecuteException("Don't create User join event game");
//        }
//        
//        return new JoinEventGameResponse(eventGame.getId(),
//                userEventGame.getFacebookId(),
//                userEventGame.getEventPoint(),
//                baseUrlEvent + "/" + userEventGame.getUserInfo().getUserVTC().getScoinId().toString(),
//                new ArrayList<UserChildentResponse>());
//    }
//
//    @Override
//    public String useLink(Long parentScoinId, String facebookId) throws SplayBusinessException {
//        TblUserInfo userInfo = verifyTokenUser();
//        if (ObjectUtils.isEmpty(parentScoinId)) {
//            throw new InvalidDataRequestException();
//        }
//        
//        //get event game
//        List<GameEvent> eventGames = eventGameRepo.getAllAndById(null);
//        if (CollectionUtils.isEmpty(eventGames) || eventGames.size() != 1) {
//            throw new NotFoundEntityException("Not found Event game by this id");
//        }
//        GameEvent eventGame = eventGames.get(0);
//        Date conditionDate = DateUtils.toDateChangeFormatFromDate(eventGame.getFromDate(), DateUtils.DATE_TIME_MYSQL_FORMAT, DateUtils.DATE_MYSQL_FORMAT);
//        
//        //check empty vtc user parent
//        TblUserVTC parentUser = profileService.getUserScoinByAccountId(parentScoinId);
//        if (ObjectUtils.isEmpty(parentUser)) {
//            throw new NotFoundEntityException("This Account is Empty");
//        }
//
//        //check parent join event
//        TblUserEventGame userEventParent = repo
//                .getByUserInfoAndEventGame(parentUser.getUserInfo().getId(), eventGame.getId());
//        if (ObjectUtils.isEmpty(userEventParent)) {
//            throw new NotFoundEntityException("Not found parent user join event");
//        }
//        
//        TblUserEventGame userUseLink = repo.getByUserInfoAndEventGame(userInfo.getId(),
//                eventGame.getId());
//        if (userUseLink.isUsedLink()) {
//            throw new UnknownErrorException("-03", "This account is already in use");
//        }
//
//        //check user used link or not
//        if (!ObjectUtils.isEmpty(userUseLink.getUserParent())) {
//            return "SUCCESSFUL";
//        }
//
//        if (userUseLink.getUserInfo().getId() == (userEventParent.getUserInfo().getId())) {
//            throw new UnknownErrorException("-04", "You don't use link your self");
//        }
//        
//        userUseLink.setUserParent(parentScoinId);
//        if (!checkLoginGameServiceOfScoin(userInfo, eventGame.getServiceId(), conditionDate)) {
//            repo.update(userUseLink);
//            throw new UnknownErrorException("-02", "You unfinished condition event");
//        }
//
//        userUseLink.setUsedLink(true);
//        repo.update(userUseLink);
//
//        userEventParent.setEventPoint(userEventParent.getEventPoint() + eventGame.getOncePoint());
//        if (!StringUtils.isEmpty(facebookId))
//            userEventParent.setFacebookId(facebookId);
//        repo.update(userEventParent);
//        return "Successfull";
//    }
//    
//    @SuppressWarnings("unchecked")
//    public boolean checkPlayGameMCD(TblUserInfo userInfo) {
//            String serverGameInfoResponse;
//            try {
//                serverGameInfoResponse = CallToServer
//                        .callApiService(MCD_SERVER_INFO_URL, null, "GET");
//            } catch (Exception e) {
//                throw new UnknownErrorException("-05", "failes to call service of Game");
//            }
//            
//            Type listType = new TypeToken<ArrayList<ServerInfoMongChinhDoResponse>>(){}.getType();
//            List<ServerInfoMongChinhDoResponse> serverInfoMongChinhDoResponses = gson
//                    .fromJson(serverGameInfoResponse, listType);
//            if (CollectionUtils.isEmpty(serverInfoMongChinhDoResponses)) {
//                throw new NotFoundEntityException("Game don't have any server");
//            }
//            
//            ServerInfoMongChinhDoResponse lastServer = serverInfoMongChinhDoResponses
//                    .get(serverInfoMongChinhDoResponses.size() - 1);
////            lastServer.setServerid((long) 1);
//            String sign = MCD_PLAYER_INFO_KEY 
//                            + userInfo.getUserVTC().getScoinId().toString() 
//                            + lastServer.getServerid();
//            
//            String urlCheckPlayGame = MCD_PLAYER_INFO_URL 
//                                        + "?accountid=" + userInfo.getUserVTC().getScoinId().toString()
//                                        + "&serverid=" + lastServer.getServerid()
//                                        + "&sign=" + StringUtils.toMD5(sign);
//            String playerInfoJson;
//            try {
//                playerInfoJson = CallToServer.callApiService(urlCheckPlayGame, null, "GET");
//            } catch (Exception e) {
//                throw new UnknownErrorException("-05", "failes to call service of Game");
//            }
//            ScoinBaseResponse<PlayerInfoResponse> playerInfo = 
//                    gson.fromJson(playerInfoJson, ScoinBaseResponse.class);
//            if (ObjectUtils.isEmpty(playerInfo.getData())) {
//                return false;
//            }
//            return true;
//            
//    }
//    
//    private boolean checkLoginGameServiceOfScoin(TblUserInfo userInfo, Long scoinGameId,
//                                                 Date conditionDate) {
//        if (ObjectUtils.isEmpty(scoinGameId)) {
//            throw new InvalidDataRequestException(
//                    "Can't check login with this service id from splay game");
//        }
//
//        Game game = splayGameRepo.getSplayGameByScoinId(scoinGameId);
//        if (ObjectUtils.isEmpty(game)) {
//            throw new NotFoundEntityException("Not fount the Game in splay");
//        }
//
//        List<TblLoginGameHistory> loginHistory = loginGameHistoryRepo
//                .findAllByServiceIdAndUserInfo(scoinGameId, userInfo);
//        if (CollectionUtils.isEmpty(loginHistory))
//            return false;
//        if (!ObjectUtils.isEmpty(conditionDate)) {
//            if (DateUtils.compareDayOfMonth(conditionDate, loginHistory.get(0).getFirstLogin()) > 0)
//                return false;
//        }
//        return true;
//    }
//
//    @Override
//    public ExchangeGiftEventGameResponse splayExchangeGift(SplayExchangeGiftEventGameRequest request) {
//        TblUserInfo userInfo = verifyTokenUser();
//        
//        if (ObjectUtils.isEmpty(request) 
//                || ObjectUtils.isEmpty(request.getEventGameId())
//                || ObjectUtils.isEmpty(request.getGiftValue())) {
//            throw new InvalidDataRequestException();
//        }
//        
//        //get event game 
//        List<GameEvent> eventGames = eventGameRepo.getAllAndById(request.getEventGameId());
//        if (CollectionUtils.isEmpty(eventGames) || eventGames.size() != 1) {
//            throw new NotFoundEntityException("Not found Event game by this id");
//        }
//        GameEvent eventGame = eventGames.get(0);
//        
//        //check packages gift can exchange
//        List<String> packageGifts = new ArrayList<>(Arrays.asList(eventGame.getPackageGift().split(",")));
//        boolean flag = false;
//        for (String packageGift : packageGifts) {
//            if (request.getGiftValue().equals(Long.parseLong(packageGift.trim()))) {
//                if (!ObjectUtils.isEmpty(flag))
//                    flag = true;
//            }
//        };
//        if (!flag)throw new UnknownErrorException("You have chosen the wrong package");
//      
//        //Get user info on event game 
//        TblUserEventGame userEventGame = repo.getByUserInfoAndEventGame(userInfo.getId(), eventGame.getId());
//        if (ObjectUtils.isEmpty(userEventGame)) {
//           throw new NotFoundEntityException("You don't join this event");
//        }
//        
//        //get point fee exchange gift
//        long pointToExchange = (long) (request.getGiftValue() / eventGame.getRatioGift());
//        if (userEventGame.getEventPoint() < pointToExchange) {
//            throw new UnknownErrorException("-04", "You don't have enough points to exchange");
//        }
//        
//        if (eventGame.getLimitGift() > 0
//                && (eventGame.getGaveGift() + request.getGiftValue()) > eventGame.getLimitGift()) {
//            throw new UnknownErrorException("-05", "The total number of event gifts is no more");
//        }
//        
//        //TOPUP XU
//        String descriptionName = "Đổi thưởng sự kiện Triệu Hồi game Chinh Đồ H5";
////        ServerXUScoinResponse exchangeXU = scoinExchangeCoinService.topupXU(new TopupXURequest(userInfo,
////                                                    request.getGiftValue(),
////                                                    VariableConstant.PROVIDER_FROM_EVEN_GAME,
////                                                    eventGame.getId() ,
////                                                    eventGame.getServiceId(),
////                                                    descriptionName,
////                                                    eventGame.getRatioGift()));
//        
//        userEventGame.setEventPoint(userEventGame.getEventPoint() - pointToExchange);
//        repo.update(userEventGame);
//        
//        //create history exchange
//        createExchagePointEventHistory(eventGame, userInfo, request.getGiftValue(), userEventGame, pointToExchange, null, 0);
//        
//        eventGame.setGaveGift(eventGame.getGaveGift() + request.getGiftValue());
//        eventGameRepo.update(eventGame);
//        
//        ExchangeGiftEventGameResponse response = new ExchangeGiftEventGameResponse();
//        response.setGiftValue(request.getGiftValue());
//        response.setGiftType(eventGame.getGiftEvent());
//        response.setPointExchange(pointToExchange);
//        response.setBalancePonit(userEventGame.getEventPoint());
////        response.setBalenceGift(exchangeXU.getData().getTotalBalance());
//
//        return response;
//    }
//    
//    private TblEventGiftHistory createExchagePointEventHistory(GameEvent eventGame, 
//                                                               TblUserInfo userInfo, 
//                                                               long giftValue,
//                                                               TblUserEventGame userEventGame,
//                                                               Long pointToExchange,
//                                                               String serverName,
//                                                               int itemGameId) {
//        Game game = splayGameRepo.getSplayGameByScoinId(eventGame.getServiceId());
//        if (ObjectUtils.isEmpty(game)) return null;
//        
//        TblEventGiftHistory eventGiftHistory = new TblEventGiftHistory();
//        eventGiftHistory.setEventGame(eventGame);
//        eventGiftHistory.setUserinfo(userInfo);
//        eventGiftHistory.setScoinGameId(eventGame.getServiceId());
//        eventGiftHistory.setGame(game.getName());
//        if(!StringUtils.isEmpty(serverName)) eventGiftHistory.setServerGame(serverName);
//        eventGiftHistory.setScoinId(userInfo.getUserVTC().getScoinId());
//        eventGiftHistory.setItemGameId(itemGameId);
//        eventGiftHistory.setGift(eventGame.getGiftEvent());
//        eventGiftHistory.setGiftValue(giftValue);
//        eventGiftHistory.setBeforePoint(userEventGame.getEventPoint() + pointToExchange);
//        eventGiftHistory.setCurrentPoint(userEventGame.getEventPoint());
//        eventGiftHistory.setStatus(VariableConstant.HISTORY_STATUS_SUCCESS);
//        return eventGiftHistory;
//    }
//
//    @Override
//    public String buyItemShopEventGame(BuyItemShopEventGameRequest request)
//            throws SplayBusinessException {
//        TblUserInfo userInfo = verifyTokenUser();
//        
//        if (ObjectUtils.isEmpty(request) 
//                || request.getItemId() < 1 
//                || request.getEventGameId() < 1
//                || (request.getServerGameId() != 1 && request.getServerGameId() != 2)) {
//            throw new InvalidDataRequestException();
//        }
//        
//        List<GameEvent> eventGames = eventGameRepo.getAllAndById(request.getEventGameId());
//        if (CollectionUtils.isEmpty(eventGames)
//                || eventGames.size() != 1) {
//            throw new NotFoundEntityException("Not fount event by this id");
//        }
//        
//        GameEvent eventGame = eventGames.get(0);
//        
//        TblShopingItem item = shopingItemDao.getItemById(request.getItemId());
//        if (ObjectUtils.isEmpty(item)) {
//            throw new NotFoundEntityException("Không Tồn Tại Item Này");
//        }
//
//        TblUserEventGame userEventGame = repo.getByUserInfoAndEventGame(userInfo.getId(),
//                request.getEventGameId());
//        if (ObjectUtils.isEmpty(userEventGame)) {
//            throw new UnknownErrorException("04", "User not join this event");
//        }
//        
//        Date conditionDate = DateUtils.toDateChangeFormatFromDate(eventGame.getFromDate(), DateUtils.DATE_TIME_MYSQL_FORMAT, DateUtils.DATE_MYSQL_FORMAT);
//        
//        if (!checkLoginGameServiceOfScoin(userInfo, eventGame.getServiceId(), conditionDate)) {
//          throw new UnknownErrorException("-02", "You unfinished condition event");
//        }
//
//        if (DateUtils.compareDayOfMonth(new Date(), eventGame.getToDate()) == 1) {
//            throw new UnknownErrorException("06", "expire Item buy");
//        }
//
//        if (item.getPrice() > userEventGame.getEventPoint()) {
//            throw new UnknownErrorException("05", "Point not enough");
//        }
//
//        String url = "http://171.244.11.217:8100/sendmail.php";
//        String key = "hxEXSKdsiwZ1cc6jb7TkC0bjoROpm5Kb";
//        Long time = System.currentTimeMillis();
//        String sign = StringUtils.toMD5(key
//                                        + userInfo.getUserVTC().getScoinId()
//                                        + item.getPackageId()
//                                        + request.getServerGameId()
//                                        + time.toString());
////        BuyItemShopEventRequest buyShopRequest = new BuyItemShopEventRequest();
////        buyShopRequest.setAccountid(userInfo.getUserVTC().getScoinId());
////        buyShopRequest.setPackid(item.getPackageId());
////        buyShopRequest.setServerid(request.getServerGameId());
////        buyShopRequest.setTime(new Timestamp(System.currentTimeMillis()));
////        buyShopRequest.setSign(sign.toUpperCase());
////        buyShopRequest.setTitle("Su Kien Truy Kich Bung No");
////        buyShopRequest.setContent("UserName : " + userInfo.getUserVTC().getUsername() 
////                                  + " ScoinId : " + userInfo.getUserVTC().getScoinId()
////                                  + " ItemId : " + item.getPackageId()
////                                  + " Time : " + time.toString());
//        
//        HttpHeaders headers = new HttpHeaders();
////        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
////        HttpEntity<BuyItemShopEventRequest> reqBody = new HttpEntity<>(buyShopRequest, headers);
//        
//        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
//        map.add("accountid", userInfo.getUserVTC().getScoinId().toString());
//        map.add("packid", item.getPackageId().toString());
//        map.add("serverid", request.getServerGameId().toString());
//        map.add("time", time.toString());
//        map.add("sign", sign.toUpperCase());
//        map.add("title", "Su Kien Truy Kich Bung No");
//        map.add("content", "UserName : " + userInfo.getUserVTC().getUsername() 
//                    + " ScoinId : " + userInfo.getUserVTC().getScoinId()
//                    + " ItemId : " + item.getPackageId()
//                    + " Time : " + time.toString());
//        
////        HttpEntity<MultiValueMap<String, String>> reqBody = new HttpEntity<MultiValueMap<String, String>>(map, headers);
//        
//        logger.info("BuyItemShopEventRequest ============== {}", gson.toJson(map));
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getMessageConverters()
//        .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
//        
//        String buyShopResponse = null;
//        try {
//            buyShopResponse = restTemplate.postForObject(url, map, String.class);
//        } catch (HttpClientErrorException exception) {
//            logger.info("" + exception.getStatusCode());
//            logger.info("" + exception.getResponseBodyAsString());
//        }
//        
//        String serverName = (request.getServerGameId() == 1) ? "HN" : "HCM";
//        TblEventGiftHistory history = createExchagePointEventHistory(eventGames.get(0),
//                                            userInfo, item.getPrice(), 
//                                            userEventGame, 
//                                            item.getPrice(), 
//                                            serverName,
//                                            item.getPackageId());
//        
//        if (StringUtils.isEmpty(buyShopResponse)
//                || !buyShopResponse.equals("1")) {
//            if (!StringUtils.isEmpty(buyShopResponse))
//            logger.info("Buy Shop Response =============== {}", buyShopResponse);
//            if (buyShopResponse.equals("2")) {
//                history.setStatus(VariableConstant.HISTORY_STATUS_FAILURE);
//                history.setStatusError(buyShopResponse);
//                history.setMessError("you not play this server");
//                eventGiftHistoryRepository.create(history);
//                throw new UnknownErrorException("06", "you not play this server");
//            }
//            
//            history.setStatus(VariableConstant.HISTORY_STATUS_FAILURE);
//            history.setStatusError(buyShopResponse);
//            history.setMessError("Server add item of game tech error");
//            eventGiftHistoryRepository.create(history);
//            throw new UnknownErrorException("Server add item of game tech error");
//        }
//        
//        userEventGame.setEventPoint(userEventGame.getEventPoint() - item.getPrice());
//        TblUserEventGame updateUserEventGame = repo.update(userEventGame);
//        if (ObjectUtils.isEmpty(updateUserEventGame)) {
//            throw new FailedToExecuteException();
//        }
//        
//       
//        eventGiftHistoryRepository.create(history);
//        return "Successful";
//    }
//    
//}
