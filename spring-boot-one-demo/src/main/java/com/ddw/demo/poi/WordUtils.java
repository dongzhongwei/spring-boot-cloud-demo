package com.ddw.demo.poi;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.springframework.util.StringUtils;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordUtils {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("北京", "${北京展招}");
        String srcPath = "/Users/dongdawei/猎头公司-广州方承-推荐报告模板.docx";
        String destPath = "/Users/dongdawei/new-demo.doc";
//        WordUtils.searchAndReplace(srcPath, destPath, map);
        WordUtils.getTemplate(srcPath);
    }




    private static void insertReplacementRuns(XWPFParagraph paragraph, String replacedText) {
        String[] replacementTextSplitOnCarriageReturn = StringUtils.split(replacedText, "\n");

        for (int j = 0; j < replacementTextSplitOnCarriageReturn.length; j++) {
            String part = replacementTextSplitOnCarriageReturn[j];

            XWPFRun newRun = paragraph.insertNewRun(j);
            newRun.setText(part);

            if (j+1 < replacementTextSplitOnCarriageReturn.length) {
                newRun.addCarriageReturn();
            }
        }
    }

    private static void removeAllRuns(XWPFParagraph paragraph) {
        int size = paragraph.getRuns().size();
        for (int i = 0; i < size; i++) {
            paragraph.removeRun(0);
        }
    }



    public static void getTemplate(String srcPath){
        try (XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath))){
            List<XWPFParagraph> paragraphs = new ArrayList<>();
            //段落
            document.getParagraphs().forEach(p -> paragraphs.add(p));
            //表格
            document.getTables().forEach(tbl -> tbl.getRows().forEach(row -> {
                row.getTableCells().forEach(cell -> cell.getParagraphs().forEach(p -> {
                    paragraphs.add(p);

                }));
            }));
            paragraphs.forEach(p -> {

                if (p.getText().contains("13523456543@163.com")) {
                    String replacedText = StringUtils.replace(p.getText(), "13523456543@163.com", "dongzhongwei@126.com");

                    removeAllRuns(p);

                    insertReplacementRuns(p, replacedText);
                }

//                for (XWPFRun r : p.getRuns()) {
//                    String text = r.getText(0);
//                    System.out.println("----"+text);
//                    if (text != null && text.contains("needle")) {
//                        text = text.replace("needle", "haystack");
//                        r.setText(text, 0);
//                    }
//                }
            });
        } catch (Exception e){

        }
    }

    private static void replaceText(XWPFParagraph p, String findText, String replaceText){
        List<XWPFRun> runs = p.getRuns();
        if (runs != null) {
            for (XWPFRun r : runs) {
                String text = r.getText(0);
                if (text != null && text.contains(findText)) {
                    text = text.replace(findText, replaceText);
                    r.setText(text, 0);
                }
            }
        }
    }

    public static void searchAndReplace(String srcPath, String destPath, Map<String, String> map) {
        try (XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath))){
            final List<XWPFParagraph> paragraphs = document.getParagraphs();

            List<XWPFParagraph> projectParagraphs = new ArrayList<>();
            XWPFParagraph firstParagraph = null;
            int projectBeginPos = -1;
            int projectEndPos = Integer.MAX_VALUE;
            for (int i = 0, l = paragraphs.size(); i < l; i++) {
                final XWPFParagraph p = paragraphs.get(i);
                if (p.getText().contains("${project.begin}")) {
                    projectBeginPos = i;
                    firstParagraph = p;
                }
                if (p.getText().contains("${project.end}")){
                    projectEndPos = i;
                }
                if (projectBeginPos > -1 && i > projectBeginPos && i < projectEndPos) {
                    projectParagraphs.add(p);
                }
            }
            //项目经历
            if (projectBeginPos > -1) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0, l = projectParagraphs.size(); j < l; j++) {
                        XWPFParagraph p = projectParagraphs.get(j);
                        final XmlCursor xmlCursor = firstParagraph.getCTP().newCursor();
                        document.insertNewParagraph(xmlCursor);
                        document.setParagraph(p, projectBeginPos + i * l + j);
                    }
                }
            }

//            projectParagraphs.forEach(p -> document.removeBodyElement(document.getPosOfParagraph(p)));

            for (XWPFParagraph p : document.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        if (text != null && text.contains("京")) {
                            text = text.replace("京", "${北京展招}");
                            r.setText(text, 0);
                        }
                    }
                }
            }

            for (XWPFTable tbl : document.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            for (XWPFRun r : p.getRuns()) {
                                String text = r.getText(0);
                                if (text != null && text.contains("needle")) {
                                    text = text.replace("needle", "haystack");
                                    r.setText(text, 0);
                                }
                            }
                        }
                    }
                }
            }
            FileOutputStream outStream = new FileOutputStream(destPath);
            document.write(outStream);
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}