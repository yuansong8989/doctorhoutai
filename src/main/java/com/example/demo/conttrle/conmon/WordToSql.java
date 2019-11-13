package com.example.demo.conttrle.conmon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.conttrle.entity.Problem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class WordToSql {

    public static String pian;
    public static String zhang;

    public static void readAndWriterTest4( File file) throws IOException {
//        File file = new File("E:\\vvv.docx");
        String str = "";
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
                                String test3after = content.substring(index + 1);
                                System.out.println("E点" + test3after);
                            }else {
                                int index = content.indexOf("D.");
                                String test3after = content.substring(index + 1);
                                System.out.println("D点" + test3after);
                            }
                            problem.setAnalysis(content);
                            problem.setAnalysis(subString1(content, "考生答案：", "参考答案"));
                            problem.setSuccess(subString1(content, "参考答案：", "得分"));
                            System.out.println("我4" + content);
                            problems.add(problem);
                        }
                    }
                }
            }
            System.out.println("长度" + problems.get(0).getChoicea());
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String subString1(String str, String strStart, String strEnd) {

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
        return result;
    }



}