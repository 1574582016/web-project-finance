package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.LearnEnglishWord;

import java.util.List;


/**
 * Created by ThinkPad on 2018/10/11.
 */
public interface LearnEnglishWordService extends IService<LearnEnglishWord>{

    List<LearnEnglishWord> getCommonRootEnglishList(String root);
}
