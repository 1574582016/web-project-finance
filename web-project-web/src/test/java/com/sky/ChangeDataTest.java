package com.sky;

import com.sky.model.StockCompanyNotice;
import com.sky.service.StockCompanyNoticeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChangeDataTest {

    @Autowired
    private StockCompanyNoticeService stockCompanyNoticeService ;

    @Test
    public void test(){
        List<StockCompanyNotice> list = stockCompanyNoticeService.selectList(null);
        for(StockCompanyNotice notice : list){

        }
    }
}
