package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.ForexNewsStatictis;

/**
 * Created by ThinkPad on 2019/9/30.
 */
public interface ForexNewsStatictisService extends IService<ForexNewsStatictis> {

    boolean spiderForexNews(Integer page);

    Page<ForexNewsStatictis> getForexNewsStatisticsList(Integer page,
                                                            Integer size ,
                                                            String newsTitle ,
                                                            String newsType ,
                                                            String startDate ,
                                                            String endDate ,
                                                            String newsTopic ,
                                                            String newsHot);
}
