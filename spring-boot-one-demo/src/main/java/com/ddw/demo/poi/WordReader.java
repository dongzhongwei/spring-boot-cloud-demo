package com.ddw.demo.poi;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordReader {
    public static void main(String[] args) {
//        try(FileInputStream fis = new FileInputStream("simple.docx")) {
//            XWPFDocument docx = new XWPFDocument(fis);
//            final List<XWPFParagraph> paragraphs = docx.getParagraphs();
//            for (XWPFParagraph paragraph: paragraphs) {
//                System.out.println(paragraph.getText());
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }

        Map<String, String> map = new HashMap<>();
        map.put("北京", "${北京展招}");
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
            paragraphs.forEach(p -> {
                if (p.getText().contains("13523456543@163.com")){
                     int length = p.getRuns().size();
                    if (length > 0) {
                        String text = StringUtils.join(p.getRuns().toArray());
                        if (text.indexOf("13523456543@163.com") < 0) {
                            return;
                        }
                        for (int i = (length - 1); i >= 0; i--) {
                            p.removeRun(i);
                        }
                        XWPFRun newRun = p.insertNewRun(0);
                        text = text.replaceAll("13523456543@163.com", "dongzhongwei@126.com");

                        final XWPFRun run = p.getRuns().get(p.getRuns().size() - 1);
                        newRun.setText(text, 0);
                        newRun.setBold(run.isBold());
                        newRun.setCapitalized(run.isCapitalized());
                        // newRun.setCharacterSpacing(run.getCharacterSpacing());
                        newRun.setColor(run.getColor());
                        newRun.setDoubleStrikethrough(run.isDoubleStrikeThrough());
                        newRun.setEmbossed(run.isEmbossed());
                        newRun.setFontFamily(run.getFontFamily());
                        newRun.setFontSize(run.getFontSize());
                        newRun.setImprinted(run.isImprinted());
                        newRun.setItalic(run.isItalic());
                        newRun.setKerning(run.getKerning());
                        newRun.setShadow(run.isShadowed());
                        newRun.setSmallCaps(run.isSmallCaps());
                        newRun.setStrikeThrough(run.isStrikeThrough());
                        newRun.setSubscript(run.getSubscript());
                        newRun.setUnderline(run.getUnderline());
                    }
//                    replace(p, "13523456543@163.com","dongzhongwei@126.com");
                }
            });
            FileOutputStream outStream = new FileOutputStream(destPath);
            document.write(outStream);
            outStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }





    private static Map<Integer, XWPFRun> getPosToRuns(XWPFParagraph paragraph) {
        int pos = 0;
        Map<Integer, XWPFRun> map = new HashMap<>(10);
        for (XWPFRun run : paragraph.getRuns()) {
            String runText = run.text();
            if (runText != null) {
                for (int i = 0; i < runText.length(); i++) {
                    map.put(pos + i, run);
                }
                pos += runText.length();
            }
        }
        return (map);
    }

    public static <V> void replace(XWPFDocument document, Map<String, V> map) {
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {
            replace(paragraph, map);
        }
    }

    public static <V> void replace(XWPFDocument document, String searchText, V replacement) {
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {
            replace(paragraph, searchText, replacement);
        }
    }

    private static <V> void replace(XWPFParagraph paragraph, Map<String, V> map) {
        for (Map.Entry<String, V> entry : map.entrySet()) {
            replace(paragraph, entry.getKey(), entry.getValue());
        }
    }

    public static <V> void replace(XWPFParagraph paragraph, String searchText, V replacement) {
        boolean found = true;
        while (found) {
            found = false;
            int pos = paragraph.getText().indexOf(searchText);
            if (pos >= 0) {
                found = true;
                Map<Integer, XWPFRun> posToRuns = getPosToRuns(paragraph);
                XWPFRun run = posToRuns.get(pos);
                XWPFRun lastRun = posToRuns.get(pos + searchText.length() - 1);
                int runNum = paragraph.getRuns().indexOf(run);
                int lastRunNum = paragraph.getRuns().indexOf(lastRun);
                String texts[] = replacement.toString().split("\n");
                run.setText(texts[0], 0);
                XWPFRun newRun = run;
                for (int i = 1; i < texts.length; i++) {
                    newRun.addCarriageReturn();
                    newRun = paragraph.insertNewRun(runNum + i);
                /*
                    We should copy all style attributes
                    to the newRun from run
                    also from background color, ...
                    Here we duplicate only the simple attributes...
                 */
                    newRun.setText(texts[i]);
                    newRun.setBold(run.isBold());
                    newRun.setCapitalized(run.isCapitalized());
                    // newRun.setCharacterSpacing(run.getCharacterSpacing());
                    newRun.setColor(run.getColor());
                    newRun.setDoubleStrikethrough(run.isDoubleStrikeThrough());
                    newRun.setEmbossed(run.isEmbossed());
                    newRun.setFontFamily(run.getFontFamily());
                    newRun.setFontSize(run.getFontSize());
                    newRun.setImprinted(run.isImprinted());
                    newRun.setItalic(run.isItalic());
                    newRun.setKerning(run.getKerning());
                    newRun.setShadow(run.isShadowed());
                    newRun.setSmallCaps(run.isSmallCaps());
                    newRun.setStrikeThrough(run.isStrikeThrough());
                    newRun.setSubscript(run.getSubscript());
                    newRun.setUnderline(run.getUnderline());
                }
                for (int i = lastRunNum + texts.length - 1; i > runNum + texts.length - 1; i--) {
                    paragraph.removeRun(i);
                }
            }
        }
    }



}
