package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.MessagePriceStatic;
import com.sky.vo.MessageStatic_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/18.
 */
public interface MessagePriceStaticService extends IService<MessagePriceStatic> {

    List<MessageStatic_VO> getMessagePriceStaticData( String messageType, String timeType, String directType);
}
