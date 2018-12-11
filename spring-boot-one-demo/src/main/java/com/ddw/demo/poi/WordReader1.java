package com.ddw.demo.poi;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordReader1 {

    private static final Map<String, String> params = new HashMap<>(200);

    static {
        params.put("推荐标题", "recommend_title");
        params.put("推荐内容", "recommend_content");
        params.put("推荐时间", "recommend_time(format:yyyy年M月d)");
        params.put("推荐次数", "recommend_count");

        //顾问
        params.put("推荐人", "recommend_name");
        params.put("顾问", "recommend_name");

        params.put("姓名", "name");
        params.put("年龄", "age");

        //学校
        params.put("学校", "highest_School");


        //专业
        params.put("专业", "highest_Profession");


        //学历
        params.put("学历", "highest_Education");

        //最近一份工作
        params.put("目前企业", "lastest_company");


        //最近岗位
        params.put("岗位", "lastest_position");


        params.put("生日(年)", "birth_year");
        params.put("生日(月)", "birth_month");
        params.put("生日(日)", "birth_date");
        params.put("性别", "sex");
        params.put("电话", "mobile");
        params.put("邮箱", "email");
        params.put("其他联系方式", "other_contacts");
        params.put("头像", "avatar_word");

        //婚姻状况
        params.put("婚姻状况", "marital_status");

        params.put("最高学历", "highest_Education");
        params.put("定居地", "city");
        params.put("语言能力", "languages");
        params.put("生育", "child_status");
        params.put("是否有子女", "child");
        params.put("子女年龄", "child_age");
        params.put("当前所在地", "city");

        //户口所在地
        params.put("户口所在地", "hkadr");

        params.put("籍贯", "native_place");
        params.put("工作年限", "working_life");
        params.put("所在公司", "company");
        params.put("担任职位", "position");
        params.put("当前职位类型", "now_job_type");
        params.put("当前行业", "now_industry");
        params.put("期望行业", "expect_job_types");
        params.put("期望地点", "expect_citys");
        params.put("期望职位", "expect_position");

        //目前状态
        params.put("目前状态", "work_status");

        params.put("当前状态备注", "work_status_remark");
        params.put("目前年薪", "now_salary");
        params.put("期望年薪备注", "expect_remark");
        params.put("期望年薪是否显示为面议", "negotiable");
//        params.put("","");
//        params.put("","");
//        params.put("","");
//        params.put("","");
//        params.put("","");
//        params.put("","");
//        params.put("","");
//        params.put("","");
//        params.put("","");
//        params.put("","");
    }

    public static void main(String[] args) {
        String srcPath = "/Users/dongdawei/猎头公司-广州方承-推荐报告模板.docx";
        String destPath = "/Users/dongdawei/new-demo.doc";
        try (XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath))) {
            List<XWPFParagraph> paragraphs = new ArrayList<>();
            //段落
            document.getParagraphs().forEach(p -> paragraphs.add(p));
            //表格
            document.getTables().forEach(tbl -> tbl.getRows().forEach(row -> {
                row.getTableCells().forEach(cell -> cell.getParagraphs().forEach(p -> {
                    paragraphs.add(p);
                }));
            }));

            replaceInAllParagraphs(paragraphs, params);

            FileOutputStream outStream = new FileOutputStream(destPath);
            document.write(outStream);
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 替换所有段落中的标记
     *
     * @param paragraphList
     * @param params
     */
    public static void replaceInAllParagraphs(List<XWPFParagraph> paragraphList, Map<String, String> params) {
        for (XWPFParagraph paragraph : paragraphList) {
            if (StringUtils.isEmpty(paragraph.getText())) {
                continue;
            }
            for (String key : params.keySet()) {
                if (StringUtils.replaceEach(paragraph.getText(), new String[]{"　", " "}, new String[]{"", ""}).contains(key)) {
                    replaceInParagraph(paragraph, key, "${" + params.get(key) + "}");
                }
            }
        }
    }

    /**
     * 替换段落中的字符串
     *
     * @param paragraph
     * @param oldString
     * @param newString
     */
    public static void replaceInParagraph(XWPFParagraph paragraph, String oldString, String newString) {
        Map<String, Integer> pos_map = findSubRunPosInParagraph(paragraph, oldString, newString);
        if (pos_map != null) {
            System.out.println("start_pos:" + pos_map.get("start_pos"));
            System.out.println("end_pos:" + pos_map.get("end_pos"));

            List<XWPFRun> runs = paragraph.getRuns();
            XWPFRun modelRun = runs.get(pos_map.get("end_pos"));
            XWPFRun xwpfRun = paragraph.insertNewRun(pos_map.get("end_pos") + 1);
            xwpfRun.setText(newString);
            System.out.println("字体大小：" + modelRun.getFontSize());
            if (modelRun.getFontSize() != -1) {
                xwpfRun.setFontSize(modelRun.getFontSize());//默认值是五号字体，但五号字体getFontSize()时，返回-1
            }
            xwpfRun.setFontFamily(modelRun.getFontFamily());
            //颜色
            xwpfRun.setColor(modelRun.getColor());
            for (int i = pos_map.get("end_pos"); i >= pos_map.get("start_pos"); i--) {
                System.out.println("remove run pos in :" + i);
                paragraph.removeRun(i);
            }
        }
    }


    /**
     * 找到段落中子串的起始XWPFRun下标和终止XWPFRun的下标
     *
     * @param paragraph
     * @param substring
     * @return
     */
    public static Map<String, Integer> findSubRunPosInParagraph(XWPFParagraph paragraph, String substring, String newString) {
        String text = StringUtils.replaceEach(paragraph.getText(), new String[]{"　", " "}, new String[]{"", ""});
        if (StringUtils.contains(text, substring)) {
            if (StringUtils.contains(text, ":") || StringUtils.contains(text, "：")) {
                substring = StringUtils.replaceEachRepeatedly(text, new String[]{substring, ":", "：", " "}, new String[]{"", "", "", ""});
            }
        }
//        System.out.println(substring);
        List<XWPFRun> runs = paragraph.getRuns();
        int start_pos = 0;
        int end_pos = 0;
        String subTemp = "";
        int l = runs.size();
        if (l == 1 && text.contains("："+substring) || text.contains(":"+substring)) {
            runs.get(0).setText(runs.get(0).getText(0).replace(substring, newString),0);
        } else {
            for (int i = 0; i < l; i++) {
                subTemp = "";
                start_pos = i;
                for (int j = i; j < l; j++) {
                    if (runs.get(j).getText(runs.get(j).getTextPosition()) == null) {
                        continue;
                    }
                    subTemp += runs.get(j).getText(runs.get(j).getTextPosition());
                    if (subTemp.equals(substring)) {
                        end_pos = j;
                        Map<String, Integer> map = new HashMap<>();
                        map.put("start_pos", start_pos);
                        map.put("end_pos", end_pos);
                        return map;
                    }
                }
            }
        }
        return null;
    }
}
