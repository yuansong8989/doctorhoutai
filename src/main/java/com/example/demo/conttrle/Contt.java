package com.example.demo.conttrle;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Dao.Dao;
import com.example.demo.Service.Service;
import com.example.demo.conttrle.conmon.WordToSql;
import com.example.demo.conttrle.entity.*;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Contt {
    @Resource
    private Service service;
    @Resource
    private Dao dao;
    Integer i;

    @RequestMapping(value = "/book10", method = RequestMethod.GET)
    public String bbb() {
        return "网易音乐II";
    }

    @GetMapping(value = "/book11")
    public String bbb1(Integer id) {
        if (id == 1) {
            System.out.println("调用11111");
            return "三星小学校";
        } else {
            return "系统错误";
        }
    }

    @GetMapping(value = "/student1")
    public String student1() {
        System.out.println("djkdmndkdl");
        return "dkldkldldldm,.dmd";
    }

    @PostMapping("book12")
    public User Login(@RequestBody User user) {
        if (user.getName().equals("ys") && user.getPassword().equals("123")) {
            user.setName("yuansong");
            System.out.println("dhjdhjdhjd");
            return user;
        } else {
            return null;
        }
    }

    @PostMapping("getyuwen")
    public List<XuanZhe> getYuWen() {
        System.out.println("走次数");
        List<XuanZhe> list = new ArrayList<>();
        List<String> answer = new ArrayList<>();
        answer.add("答案A");
        answer.add("答案B");
        answer.add("答案C");
        answer.add("答案D");
        XuanZhe xuanZhe1 = new XuanZhe();
        XuanZhe xuanZhe2 = new XuanZhe();
        XuanZhe xuanZhe3 = new XuanZhe();
        XuanZhe xuanZhe4 = new XuanZhe();
        XuanZhe xuanZhe5 = new XuanZhe();
        XuanZhe xuanZhe6 = new XuanZhe();
        XuanZhe xuanZhe7 = new XuanZhe();
        XuanZhe xuanZhe8 = new XuanZhe();
        XuanZhe xuanZhe9 = new XuanZhe();
        xuanZhe1.setId("一：");
        xuanZhe1.setTitle("李白的故乡在哪儿");
        xuanZhe1.setAnswer(answer);
        xuanZhe1.setJiexi(null);
        System.out.println("----------------");
        xuanZhe2.setId("二：");
        xuanZhe2.setTitle("我的家在哪儿");
        xuanZhe2.setAnswer(answer);
        xuanZhe2.setJiexi(null);
        System.out.println("----------------");
        xuanZhe3.setId("三：");
        xuanZhe3.setTitle("你妈妈叫什么名字");
        xuanZhe3.setAnswer(answer);
        xuanZhe3.setJiexi(null);
        System.out.println("----------------");
        xuanZhe4.setId("四：");
        xuanZhe4.setTitle("你希望国庆节放几天假");
        xuanZhe4.setAnswer(answer);
        xuanZhe4.setJiexi(null);
        System.out.println("----------------");
        xuanZhe5.setId("五：");
        xuanZhe5.setTitle("我觉得世界真的很小");
        xuanZhe5.setAnswer(answer);
        xuanZhe5.setJiexi(null);
        System.out.println("----------------");
        xuanZhe6.setId("六：");
        xuanZhe6.setTitle("我要回家");
        xuanZhe6.setAnswer(answer);
        xuanZhe6.setJiexi(null);
        System.out.println("----------------");
        xuanZhe7.setId("七：");
        xuanZhe7.setTitle("中秋节日哪种月饼最好吃");
        xuanZhe7.setAnswer(answer);
        xuanZhe7.setJiexi(null);
        System.out.println("----------------");
        xuanZhe8.setId("八：");
        xuanZhe8.setTitle("知道一个人，怎么找第二个人");
        xuanZhe8.setAnswer(answer);
        xuanZhe8.setJiexi(null);
        System.out.println("----------------");
        xuanZhe9.setId("九：");
        xuanZhe9.setTitle("你清楚了没有？");
        xuanZhe9.setAnswer(answer);
        xuanZhe9.setJiexi(null);
        System.out.println("----------------");
        list.add(xuanZhe1);
        list.add(xuanZhe2);
        list.add(xuanZhe3);
        list.add(xuanZhe4);
        list.add(xuanZhe5);
        list.add(xuanZhe6);
        list.add(xuanZhe7);
        list.add(xuanZhe8);
        list.add(xuanZhe9);
        return list;
    }

    @PostMapping("jiancha")
    public List<String> jianCha(@RequestBody User user) {
        System.out.println("请去哦");
        List<String> fin = new ArrayList<>();
        String[] answer = user.getAnswer();
        String[] daan_su = {"答案A", "答案D", "答案C", "答案D", "答案B", "答案D", "答案A", "答案B", "答案B"};
        for (int i = 0; i < 9; i++) {
            if (answer[i].equals(daan_su[i])) {
                fin.add("正确");
            } else {
                fin.add("错误");
            }
        }
//        Collections.reverse(fin);
        return fin;
    }

    @PostMapping("student")
    public User Student(@RequestBody User user) {
        System.out.println("注册注册");
        User user1 = new User();
        if (user.getName().equals("510623")) {
            user1.setName("hjdndk00");
            user1.setPassword("dhjdhnd");
        }
        return user1;
    }

    @PostMapping("check")
    public Result check(@RequestBody DaTiRqe daTiRqe) {
        System.out.println("掉要");
        List<Answer> answers = daTiRqe.getList();
        Integer grade = 0;
        List<JieGuo> jieGuos = new ArrayList<>();
        for (Answer answer : answers) {
            if (answer.getAnswer().equals("B")) {
                JieGuo jieGuo = new JieGuo();
                jieGuo.setId(answer.getId());
                jieGuo.setDaan("正确");
                jieGuos.add(jieGuo);
                grade = grade + 5;
            } else {
                JieGuo jieGuo = new JieGuo();
                jieGuo.setId(answer.getId());
                jieGuo.setDaan("错误");
                jieGuos.add(jieGuo);
            }
        }
        Result result = new Result();
        result.setGrade(grade);
        result.setList(jieGuos);
        return result;
    }

    //查询全部的学科
    @GetMapping(value = "/subject")
    public Subject getSubject() {
        return service.getSubject();
    }

    //查询某一学科全部章节
    @PostMapping("allzhangjie")
    public Subject getZhngjie(@RequestBody Request request) {
        return service.getzhangjie(request.getId());
    }

    //在查询每一个章节的全部题目
    @PostMapping("alltitle")
    public Subject getAllTitl(@RequestBody Request request) {
        return service.getAlltitle(request.getId());
    }

    //需要查询全部的东西
    @GetMapping(value = "/alldata")
    public AllData allData() {
        return service.getallData();
    }

    //给一个问题的id 查询quanbu 包括评论
    @PostMapping("shuati")
    public OneProblem getOneProblem(@RequestBody Request request) {
        return service.getOneProblem(request.getId());
    }

    @PostMapping("login")
    public Integer login(@RequestBody Request request) {
        return service.login(request.getAppUser());
    }

    @PostMapping("vedio")
    public Subject getVedio(@RequestBody Request request) {
        return service.getVedio(request.getId());
    }

    //导入excel
    @PostMapping("daoru")
    public String uploadExcel(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        InputStream inputStream = null;
        List<List<Object>> list = null;
        MultipartFile file = multipartRequest.getFile("file");
        if (file.isEmpty()) {
            return "文件不能为空";
        }
        inputStream = file.getInputStream();
        // List<List<Object>>
        list = service.getBankListByExcel(file);
        inputStream.close();
//连接数据库部分
        for (int i = 0; i < list.size(); i++) {
            List<Object> lo = list.get(i);
            Poice poice = new Poice();
            List<Choice> choices = new ArrayList<>();
            for (int o = 0; o < 4; o++) {
                Choice choice = new Choice();
                switch (o) {
                    case 0:
                        choice.setXuhao("A");
                        break;
                    case 1:
                        choice.setXuhao("B");
                        break;
                    case 2:
                        choice.setXuhao("C");
                        break;
                    case 3:
                        choice.setXuhao("D");
                        break;
                    default:
                        break;
                }
                choice.setXuanxiang(String.valueOf(lo.get(o+3)));
                choices.add(choice);
            }
            poice.setChoce(choices);
            String a = JSONObject.toJSONString(poice);
            System.out.println("这个" + a);
            AllZhangjie allZhangjie = dao.getoneZhangjie(String.valueOf(lo.get(7)));
            dao.insertsafeuser(String.valueOf(lo.get(0)), String.valueOf(lo.get(1)), String.valueOf(lo.get(2)), String.valueOf(lo.get(3)), String.valueOf(lo.get(4)), String.valueOf(lo.get(5)), String.valueOf(lo.get(6)), allZhangjie.getId(),a);
//            mainDao.insert(String.valueOf(lo.get(0)), String.valueOf(lo.get(1)), String.valueOf(lo.get(2)), (int) (lo.get(3)), String.valueOf(lo.get(4)), String.valueOf(lo.get(5)), (int) (lo.get(6)), (int) (lo.get(7)), String.valueOf(lo.get(8)), (int) (lo.get(9)), String.valueOf(lo.get(10)), (int) (lo.get(11)), String.valueOf(lo.get(12)));
            //调用mapper中的insert方法
        }
        return "上传成功";
    }
    //文件的上传
    @PostMapping("upload1")
    public String upload1(@RequestParam("file") MultipartFile[] multipartFile) {
        if (multipartFile.length == 0) {
            System.out.println("失败");
        } else {
            try {

                for (int i = 0; i < multipartFile.length; i++) {
//                    multipartFile[i].transferTo(new File("path"));
                    FileUtils.writeByteArrayToFile(new File("c:/upload/" + multipartFile[i].getOriginalFilename()), multipartFile[i].getBytes());//字节数组
                    service.readAndWriterTest4(new File("c:/upload/" + multipartFile[i].getOriginalFilename()));
//                    servicemain.InsertFileName(multipartFile[i].getOriginalFilename());
                    System.out.println(""+multipartFile[i].getOriginalFilename());
                }
                return "成功";
            } catch (IOException e) {
                e.printStackTrace();
                return "失败3";
            }
        }
        return "空文件";
    }
}
