package com.sky.api.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.sky.api.AbstractController;
import com.sky.model.LearnDiary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

/**
 * Created by ThinkPad on 2018/10/13.
 */
@RestController
@RequestMapping("/api/diary")
public class LearnDiaryApiController extends AbstractController {

    @PostMapping("/getLearnDiaryList")
    public Object getLearnDiaryList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                     @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                     String name ,
                                     String type ,
                                     String startDate ,
                                     String endDate ){
        Page selectedPage = learnDiaryService.getLearnDiaryList( page , size , name, type, startDate , endDate);
        return PageData(selectedPage);
    }

    @PostMapping("/editLearnDiary")
    public Object editLearnDiary(@RequestBody LearnDiary body){
        if(body.getId() == null){
            body.setDiaryCode(IdWorker.getIdStr());
        }

        String basePath = diaryTextPath + body.getType() +"/";

        File directory = new File(basePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        FileWriter fwriter = null;
        try {
            fwriter = new FileWriter(basePath + body.getDiaryCode()+".txt");
            fwriter.write(body.getContent());
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        body.setFilesPath(basePath + body.getDiaryCode()+".txt");
        learnDiaryService.insertOrUpdate(body);
        return ResponseEntity.ok(MapSuccess("保存成功"));
    }

    @PostMapping("/getLearnDiaryInfo")
    public Object getLearnDiaryInfo(String id){
        LearnDiary body = learnDiaryService.selectById(id);

        if(StringUtils.isNotEmpty(body.getFilesPath())){
            try {
                File file = new File(body.getFilesPath());//定义一个file对象，用来初始化FileReader
                FileReader reader = new FileReader(file);
                BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
                StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
                String s = "";
                while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
                    sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
                    System.out.println(s);
                }
                bReader.close();
                body.setContent(sb.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok(MapSuccess("查询成功！",body ));
    }

}
