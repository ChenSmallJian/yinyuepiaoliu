package com.whut.yinyuepiaoliu.service.impl;

import com.whut.yinyuepiaoliu.common.ServerResponse;
import com.whut.yinyuepiaoliu.service.IMusicService;
import com.whut.yinyuepiaoliu.util.MusicUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class MusicServiceImpl implements IMusicService {

    @Autowired
    private MusicUtil musicUtil;

    public ServerResponse searchMusicFromKUWO(String key, int page, int pageCount){
        if(pageCount > 0){
           // return musicUtil.searchMusicFromKUWO(key,page,pageCount);
        }
        return null;
    }
}