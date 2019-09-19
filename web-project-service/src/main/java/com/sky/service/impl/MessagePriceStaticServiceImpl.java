package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.MessagePriceStaticMapper;
import com.sky.model.MessagePriceStatic;
import com.sky.service.MessagePriceStaticService;
import com.sky.vo.MessageStatic_VO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/18.
 */
@Service
@Transactional
public class MessagePriceStaticServiceImpl extends ServiceImpl<MessagePriceStaticMapper,MessagePriceStatic> implements MessagePriceStaticService {

    @Override
    public List<MessageStatic_VO> getMessagePriceStaticData(String messageType, String timeType, String directType) {
        return baseMapper.getMessagePriceStaticData(messageType ,timeType ,directType);
    }

    @Override
    public List<MessageStatic_VO> getMessageStaticCount(String messageType) {
        return baseMapper.getMessageStaticCount(messageType);
    }
}
