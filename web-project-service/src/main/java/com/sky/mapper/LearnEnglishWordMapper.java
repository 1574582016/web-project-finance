package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.LearnEnglishWord;
import com.sky.vo.EnglishWorld_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by ThinkPad on 2018/10/10.
 */
public interface LearnEnglishWordMapper extends BaseMapper<LearnEnglishWord> {

    List<LearnEnglishWord> getCommonRootEnglishList(@Param("root") String root);

    List<EnglishWorld_VO> getEnglishWorldByRoot(@Param("root") String root);
}
