package com.example.demo.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Dao.Dao;
import com.example.demo.conttrle.entity.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Resource
    Dao dao;

    public Subject getSubject() {
        Subject subject = new Subject();
        List<Classify> list1 = dao.getAllClassify(1);
        List<Classify> list2 = dao.getAllClassify(2);
        List<Classify> list3 = dao.getAllClassify(3);
        List<Classify> list4 = dao.getAllClassify(4);
        subject.setSubject1(list1);
        subject.setSubject2(list2);
        subject.setSubject3(list3);
        subject.setSubject4(list4);
        return subject;
    }

    public Subject getzhangjie(int id) {
        Subject subject = new Subject();
        List<AllZhangjie> allZhangjies = new ArrayList<>();
        allZhangjies = dao.getzhangjie(id);
        subject.setAllZhangjies(allZhangjies);
        return subject;

    }

    public Subject getAlltitle(int id) {
        Subject subject = new Subject();
        List<Problem> problems = new ArrayList<>();
        problems = dao.getAllProblem(id);
        subject.setProblems(problems);
        return subject;
    }

    public AllData getallData() {//id为病理学id
        //一个id的
        AllData allData = new AllData();
        List<One> ones = new ArrayList<>();
        //查询全部的id
        List<Classify> list = dao.getAllClassify(1);
        List<Integer> list1 = new ArrayList<>();
        for (Classify classify : list) {
            list1.add(classify.getId());
        }
        for (Integer e : list1) {
            ones.add(getOne(e));
        }
        allData.setOnes(ones);
        return allData;
    }

    public One getOne(int id) {
        Classify classify = dao.getClassify(id);
        One one = new One();
        one.setAlltite(classify.getAlltitle());
        one.setClassifyname(classify.getClassifyname());
        one.setId(id);

        //先找出全部的id
        List<Two> twos = new ArrayList<>();
        List<AllZhangjie> allZhangjies = dao.getzhangjie(id);
        List<Integer> integers = new ArrayList<>();
        for (AllZhangjie e : allZhangjies) {
            Two two = new Two(e.getUnitname(), e.getUnitnum(), e.getId(), e.getBelong(), e.getAllgrade(), e.getSuccess());
            twos.add(two);
        }

        for (int i = 0; i < twos.size(); i++) {
            List<Problem> problems = dao.getAllProblem(twos.get(i).getId());
            twos.get(i).setProblems(problems);
        }
        one.setTwos(twos);
        return one;
    }

    public OneProblem getOneProblem(int id) {
        int discussnum;
        OneProblem oneProblem = new OneProblem();
        Problem problem = dao.getOneProblem(id);
        List<Discuss> discusses = dao.getDiscuss(id);
        discussnum = discusses.size();
        oneProblem.setDiscusses(discusses);
        oneProblem.setDiscussnum(discussnum);
        oneProblem.setProblem(problem);
        Poice poice = JSONObject.parseObject(problem.getChoice(), Poice.class);
        oneProblem.setPoice(poice);
        return oneProblem;
    }

    public Integer login(AppUser appUser) {
        if (dao.login(appUser) > 0) {
            return 1;
        }
        return 0;
    }

    public Subject getVedio(int id) {
        Subject subject = new Subject();
        List<Vedio> vedios = dao.getVedio(id);
        subject.setVedios(vedios);
        return subject;
    }

    public List<List<Object>> getBankListByExcel(MultipartFile file) throws Exception {
        List<List<Object>> list = null;
        //创建Excel工作薄
        Workbook work = WorkbookFactory.create(file.getInputStream());
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        list = new ArrayList<List<Object>>();
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(cell);
                }
                list.add(li);
            }
        }
        work.close();
        return list;
    }

    public void readAndWriterTest4(File file) throws IOException {
//        File file = new File("E:\\vvv.docx");
        String belong = "";
        int belongid = 0;
        String pian;
        String zhang;
        String str = "";
        String e="";
        List<String> jiexi=new ArrayList<>();
        List<String> daan=new ArrayList<>();
        String ans;
        try {
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument xdoc = new XWPFDocument(fis);
            List<XWPFParagraph> paras = xdoc.getParagraphs();
            List<Problem> problems = new ArrayList<>();
            for (XWPFParagraph p : paras)//遍历段落
            {
                //获取段落的级别
                String jibie = p.getStyleID();
                System.out.println("等级" + jibie);
                if (jibie != null) {
                    if (jibie.equals("8")) {
                        pian = p.getParagraphText();
                        System.out.println("我2" + pian);

                    } else if (jibie.equals("9")) {
                        zhang = p.getParagraphText();
                        System.out.println("我3" + zhang);

                    } else if (jibie.equals("10")) {       //判断的段落为标题
                        //System.out.println(bean.toString());
                        //写入数据库
                        String title = p.getParagraphText();
                        System.out.println("我5" + title);
                    } else {      //判断段落为正文
                        String content = p.getParagraphText();
                        if (content.contains("#")) {
                            belong = subString1(content, "#", "w");
                            AllZhangjie allZhangjie = dao.getoneZhangjie(belong);
                            belongid = allZhangjie.getId();
                        }
                        if(content.contains("参考答案")){
                            daan.add(subString1(content, "参考答案：", "得分"));
                            jiexi.add(subString1(content, "考生答案：", "参考答案"));
                        }
                        if (content.contains("A") && content.contains("B")) {
                            Problem problem = new Problem();
                            content = "w" + content;
                            problem.setProblemtitle(subString1(content, "w", "A"));
                            problem.setChoicea(subString1(content, "A.", "B"));
                            problem.setChoiceb(subString1(content, "B.", "C"));
                            problem.setChoicec(subString1(content, "C.", "D"));
                            //如果存在E
                            if (content.contains("E")) {
                                problem.setChoiced(subString1(content, "D.", "E"));
                                int index = content.indexOf("E.");
                                e = content.substring(index + 1).replace(".","");
                                System.out.println("E点" + e);
                            } else {
                                //不存在E
                                int index = content.indexOf("D.");
                                String test3after = content.substring(index + 1).replace(".","");
                                System.out.println("D点" + test3after);
                                problem.setChoiced(test3after);

                            }
                            problem.setBelong(belongid);
//                            problem.setAnalysis("dkdkd");
//                            problem.setSuccess("B");
                            problem.setChoice(JSONObject.toJSONString(getpoice(problem,e)));
                            e="";
                            System.out.println("我4" + content);
                            //接下来就是插入
                            problems.add(problem);
                            //插入一条problem数据到数据库 problemtitle,analysis,success,choicea,choiceb,choicec,choiced,belong,choice

                        }
                    }
                }
            }

            for (int i=0;i<problems.size();i++){
                problems.get(i).setAnalysis(jiexi.get(i).replaceAll("\r|\n",""));
                problems.get(i).setSuccess(daan.get(i).replaceAll("\r|\n",""));
                dao.insertsafeuser(problems.get(i).getProblemtitle(), problems.get(i).getAnalysis(), problems.get(i).getSuccess(), problems.get(i).getChoicea(),problems.get(i).getChoiceb(), problems.get(i).getChoicec(), problems.get(i).getChoiced(), problems.get(i).getBelong(),problems.get(i).getChoice());
            }
            System.out.println("长度" + problems.get(0).getChoicea());
            fis.close();
        } catch (Exception f) {
            f.printStackTrace();
        }
    }

    public String subString1(String str, String strStart, String strEnd) {

        /* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex = str.indexOf(strEnd);

        /* index 为负数 即表示该字符串中 没有该字符 */
        if (strStartIndex < 0) {
            return "字符串 :---->" + str + "<---- 中不存在 " + strStart + ", 无法截取目标字符串";
        }
        if (strEndIndex < 0) {
            return "字符串 :---->" + str + "<---- 中不存在 " + strEnd + ", 无法截取目标字符串";
        }
        /* 开始截取 */
        String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
        return result.replaceAll("\r|\n","");
    }

    public Poice getpoice(Problem problem, String daane) {
        Poice poice = new Poice();
        List<Choice> choices = new ArrayList<>();
        choices.add(new Choice("A",problem.getChoicea()));
        choices.add(new Choice("B",problem.getChoiceb()));
        choices.add(new Choice("C",problem.getChoicec()));
        choices.add(new Choice("D",problem.getChoiced()));
        if(!daane.equals("")){
            //存在e
            choices.add(new Choice("E",daane));
        }
        poice.setChoce(choices);
        return poice;
    }
}
