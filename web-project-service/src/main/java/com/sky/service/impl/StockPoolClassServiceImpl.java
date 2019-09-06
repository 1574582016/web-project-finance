package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockPoolClassMapper;
import com.sky.model.StockPoolClass;
import com.sky.service.StockPoolClassService;
import com.sky.vo.TreeNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2019/8/31.
 */
@Service
@Transactional
public class StockPoolClassServiceImpl extends ServiceImpl<StockPoolClassMapper,StockPoolClass> implements StockPoolClassService {
}
