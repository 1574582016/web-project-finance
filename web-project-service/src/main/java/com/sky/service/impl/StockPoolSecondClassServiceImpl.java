package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockPoolSecondClassMapper;
import com.sky.model.StockPoolSecondClass;
import com.sky.service.StockPoolSecondClassService;
import com.sky.vo.TreeNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2019/8/31.
 */
@Service
@Transactional
public class StockPoolSecondClassServiceImpl extends ServiceImpl<StockPoolSecondClassMapper,StockPoolSecondClass> implements StockPoolSecondClassService {
}
