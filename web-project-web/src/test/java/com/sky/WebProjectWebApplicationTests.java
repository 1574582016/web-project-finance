package com.sky;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.sky.core.utils.SaltUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebProjectWebApplicationTests {

	@Test
	public void contextLoads() {
//		System.out.println("====================" + SaltUtils.getMD5Password("liuxiaolong"));
//		System.out.println("====================" + SaltUtils.getMD5Password("liuxiaojing"));

		for(int i = 0 ; i < 20 ; i ++ ){
			System.out.println("==========="+ i +"==========" + IdWorker.getIdStr());
		}
	}

}
