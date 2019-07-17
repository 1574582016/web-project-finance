package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.ForexIndexType;

/**
 * Created by ThinkPad on 2019/7/8.
 */
public interface ForexIndexTypeService extends IService<ForexIndexType> {

    void spiderForexIndexType(String contry ,String url);

    void spiderLastYearForexIndexType(String url);
}
