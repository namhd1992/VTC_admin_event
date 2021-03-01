package com.vtc.gamerid.gateway.game.service.Impl;

import java.net.URL;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.Game;
import com.vtc.gamerid.gateway.common.dao.entity.TblSplayTag;
import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.common.dao.repository.GameRepository;
import com.vtc.gamerid.gateway.common.dao.repository.GameTagRepository;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.common.ultils.StringUtils;
import com.vtc.gamerid.gateway.common.ultils.VNCharacterUtils;
import com.vtc.gamerid.gateway.exception.FailedToExecuteException;
import com.vtc.gamerid.gateway.exception.ScoinInvalidDataRequestException;
import com.vtc.gamerid.gateway.exception.ScoinNotFoundEntityException;
import com.vtc.gamerid.gateway.game.bean.AdminSplayGameBeanRequest;
import com.vtc.gamerid.gateway.game.bean.AdminSplayGameFilter;
import com.vtc.gamerid.gateway.game.service.AdminSplayGameService;

/**
 * Created by phucnguyen on 05/12/2017.
 */
@Service
public class AdminSplayGameServiceImpl extends AbstractService<Game, Long, GameRepository> implements AdminSplayGameService {
    @Autowired
    private GameTagRepository adminSplayTagDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Game createSplayGame(AdminSplayGameBeanRequest dataRequest) {
          if (dataRequest.getGameType().equals(Constants.GAME_TYPE_WEBGAME)
                  && StringUtils.isEmpty(dataRequest.getWebgameUrl())) {
              throw new ScoinInvalidDataRequestException("webgame không có url");
          }
          //Create splay game
          Game splayGame = new Game(dataRequest.getName(),
                  VNCharacterUtils.removeAccent(dataRequest.getName()).toLowerCase(),
                  dataRequest.getDefaultImage(), dataRequest.getGroupFB(), dataRequest.getFanpageFB(),
                  dataRequest.getPublisher(), dataRequest.getStatus(), new Date());
          splayGame.setGameType(dataRequest.getGameType());
          if(!StringUtils.isEmpty(dataRequest.getWebgameUrl())) 
              splayGame.setWebgameUrl(dataRequest.getWebgameUrl());
          splayGame.setDescription(dataRequest.getDescription());
          splayGame.setScoinGameId(dataRequest.getScoinGameId());
          splayGame.setCreateBy(dataRequest.getCreateBy());
          splayGame.setPriorityTag(dataRequest.getPriorityTag());
          splayGame.setYoutubeChannelId(dataRequest.getYoutubeChannelId());
          splayGame.setYoutubeDefaultSearch(dataRequest.getYoutubeDefaultSearch());
          splayGame.setUrlDownloadAndroid(dataRequest.getUrlDownloadAndroid());
          splayGame.setUrlDownloadIos(dataRequest.getUrlDownloadIos());
          splayGame.setUrlDownloadPC(dataRequest.getUrlDownloadPC());

          if(dataRequest.getDownloadTurns() < 1){
              splayGame.setDownloadTurns((long) (getGoogleStoreDownload(
                                      dataRequest.getUrlDownloadAndroid())*1.5));
          }else {
              splayGame.setDownloadTurns(dataRequest.getDownloadTurns());
          }

          splayGame.setSubTitle(dataRequest.getSubTitle());
          splayGame.setScreenShot(dataRequest.getScreenShot());
          splayGame.setBigImage(dataRequest.getBigImage());

          if(dataRequest.getTagList() != null && dataRequest.getTagList().size() > 0){
              List<TblSplayTag> listTag = adminSplayTagDao.findAll(dataRequest.getTagList());
              if(!CollectionUtils.isEmpty(listTag))
                  splayGame.setTagsList(listTag);
          }

          splayGame = repo.save(splayGame);
          if (ObjectUtils.isEmpty(splayGame))
              throw new FailedToExecuteException();
              
          return splayGame;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Game updateSplayGame(AdminSplayGameBeanRequest dataRequest) {
          if (dataRequest.getGameType().equals(Constants.GAME_TYPE_WEBGAME)
                  && StringUtils.isEmpty(dataRequest.getWebgameUrl())) {
              throw new ScoinInvalidDataRequestException("webgame không có url");
          }
          //Get splay game
          logger.info("============================= OK ================== ");
          Game splayGame = repo.findByIdAndStatusNot(dataRequest.getId(), Constants.STATUS_DELETED);
          logger.info("============================= OK 1 ================== ");
          if (ObjectUtils.isEmpty(splayGame))
              throw new ScoinNotFoundEntityException("Not found Game by this ID");

          //Set data
          splayGame.setUpdateOn(new Date());
          splayGame.setName(dataRequest.getName());
          splayGame.setKeyName(VNCharacterUtils.removeAccent(dataRequest.getName()).toLowerCase());
          splayGame.setDefaultImage(dataRequest.getDefaultImage());
          splayGame.setFanpageFB(dataRequest.getFanpageFB());
          splayGame.setGroupFB(dataRequest.getGroupFB());
          splayGame.setPublisher(dataRequest.getPublisher());
          splayGame.setStatus(dataRequest.getStatus());
          splayGame.setDescription(dataRequest.getDescription());
          splayGame.setGameType(dataRequest.getGameType());
          if(!StringUtils.isEmpty(dataRequest.getWebgameUrl())) 
              splayGame.setWebgameUrl(dataRequest.getWebgameUrl());
          splayGame.setScoinGameId(dataRequest.getScoinGameId());
          splayGame.setPriorityTag(dataRequest.getPriorityTag());
          splayGame.setYoutubeChannelId(dataRequest.getYoutubeChannelId());
          splayGame.setYoutubeDefaultSearch(dataRequest.getYoutubeDefaultSearch());

          if(dataRequest.getDownloadTurns() < 1){
              splayGame.setDownloadTurns(getGoogleStoreDownload(dataRequest.getUrlDownloadAndroid()));
          }else {
              splayGame.setDownloadTurns(dataRequest.getDownloadTurns());
          }

          splayGame.setSubTitle(dataRequest.getSubTitle());
          splayGame.setUrlDownloadAndroid(dataRequest.getUrlDownloadAndroid());
          splayGame.setUrlDownloadIos(dataRequest.getUrlDownloadIos());
          splayGame.setUrlDownloadPC(dataRequest.getUrlDownloadPC());
          splayGame.setScreenShot(dataRequest.getScreenShot());
          splayGame.setBigImage(dataRequest.getBigImage());

          if(dataRequest.getTagList() != null && dataRequest.getTagList().size() > 0){
              List<TblSplayTag> listTag = adminSplayTagDao.findAll(dataRequest.getTagList());
              if(!CollectionUtils.isEmpty(listTag))
                  splayGame.setTagsList(listTag);
          }

          logger.info("============================= OK 3 ================== ");
          //Update splay game
          splayGame = repo.save(splayGame);
          if (ObjectUtils.isEmpty(splayGame))
              throw new FailedToExecuteException();
              
          logger.info("splayGame ===================== {}", splayGame.getName());
          return splayGame;
    }

//    @Override
//    public ServiceResponse updatePositionGame(List<AdminSplayGamePositionBean> dataRequest) {
//        try{
//            for(AdminSplayGamePositionBean instance: dataRequest)
//                adminSplayGameDao.updatePostionSplayGame(instance);
//            return new ServiceResponse(true, Constants.SUCCESS);
//        }catch (Exception e){
//            logger.error("updatePositionGame", e);
//        }
//        return new ServiceResponse(false, Constants.MESS_UNKNOW);
//    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String deleteSplayGame(Long splayGameId) {
            //Get splay game
            Game splayGame = repo.findByIdAndStatusNot(splayGameId, Constants.STATUS_DELETED);
            if (ObjectUtils.isEmpty(splayGame))
                throw new ScoinNotFoundEntityException("Not found Game by this ID");

            splayGame.setStatus(Constants.STATUS_DELETED);
            //Update splay game
            splayGame = repo.save(splayGame);
            if (ObjectUtils.isEmpty(splayGame)) 
                throw new FailedToExecuteException(Constants.OBJECT_NOT_DELETE);
            
            return Constants.SUCCESS;
    }

    @Override
    public List<Game> getListSplayGame(AdminSplayGameFilter dataRequest) {
        UserGameRID userInfo = verifyTokenUser();
        if(!userInfo.getGroupRole().getName().equals(Constants.ROLE_ADMIN)
                && !userInfo.getGroupRole().getName().equals(Constants.ROLE_ADMIN_EVENT_GAME))
            dataRequest.setCreateBy(userInfo.getId());
        
        ValidateBean validateBean = dataRequest.validate();
        if(!validateBean.isSuccess())
            throw new ScoinInvalidDataRequestException();
        return repo.getSplayGame(Constants.STATUS_DELETED, dataRequest.getSearchValue(), dataRequest.getCreateBy());
    }

    private long getGoogleStoreDownload(String url){
        try{
            if(url == null) return 0;
            Document docSource = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
            Elements eles = docSource.select("div.JHTxhe > div.xyOfqd > div.hAyfc");
            Element element = eles.get(2);
            String tmp = element.getElementsByClass("htlgb").last().text().replace("+","").replace(",","");
            return Long.parseLong(tmp);
        }catch (Exception e){
        }
        return 0;
    }
}
