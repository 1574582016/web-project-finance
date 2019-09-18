package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.MessagePriceStatic;
import com.sky.vo.MessageStatic_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/18.
 */
public interface MessagePriceStaticMapper extends BaseMapper<MessagePriceStatic> {

    List<MessageStatic_VO> getMessagePriceStaticData(@Param("messageType") String messageType,@Param("timeType") String timeType,@Param("directType") String directType);
}
