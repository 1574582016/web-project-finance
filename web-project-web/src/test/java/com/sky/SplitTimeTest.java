package com.sky;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.DateUtils;
import com.sky.model.StockDealData;
import com.sky.service.StockDealDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ThinkPad on 2019/11/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SplitTimeTest {

    @Autowired
    private StockDealDataService stockDealDataService ;

    @Test
    public void test(){
       String startDay = "2015-01-01";
       for(int i = 0 ; i < 100 ; i ++){
           Date pointDate = DateUtils.addDays(DateUtils.parseDate(startDay , "yyyy-MM-dd") , i);
           String pointDay = DateUtils.formatDate( pointDate, "yyyy-MM-dd");
           int day = Integer.parseInt(DateUtils.getDay(pointDate));
           String weekStr = DateUtils.getWeek(pointDate);
//           System.out.println("=================" + DateUtils.getDay(pointDate));
           System.out.println("=================" + DateUtils.getWeek(pointDate));
           System.out.println("=================" + pointDay);
           Calendar calendar = Calendar.getInstance();
           calendar.setTime(pointDate);
           int week = calendar.get(Calendar.WEEK_OF_MONTH);
//           System.out.println("=================" + week);
           if(week == 1 && ((day == 1 && (weekStr.equals("星期四") || weekStr.equals("星期五") || weekStr.equals("星期六") || weekStr.equals("星期日")))||
                            (day == 2 && (weekStr.equals("星期四") || weekStr.equals("星期五") || weekStr.equals("星期六") || weekStr.equals("星期日"))) ||
                            (day == 3 && (weekStr.equals("星期四") || weekStr.equals("星期五") || weekStr.equals("星期六") || weekStr.equals("星期日"))))){
               System.out.println("================" + (week - 1));
           }else{
               System.out.println("================" + week);
           }
       }
    }
}
