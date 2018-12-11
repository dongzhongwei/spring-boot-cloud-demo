package com.ddw.demo.poi;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordReplaceText {
    public static final String SOURCE_FILE = "/Users/dongdawei/demo.doc";
    public static final String OUTPUT_FILE = "/Users/dongdawei/new-demo.doc";

    public static void main(String[] args) throws Exception {




//        WordReplaceText instance = new WordReplaceText();
//        HWPFDocument doc = instance.openDocument(SOURCE_FILE);
//        if (doc != null) {
//            Map<String, String> params = new HashMap<>();
//            params.put("北京", "${北京展招}");
////            doc = instance.replaceText(doc, params);
//            doc = instance.loopText(doc, params);
//            instance.saveDocument(doc, OUTPUT_FILE);
//        }
    }

    private HWPFDocument loopText(HWPFDocument doc, Map<String, String> params) throws Exception {
        Range range = doc.getRange();
//        range.insertBefore("bbbbbbbb");

        for (int i = 0, l = range.numParagraphs(); i < l; i++) {
            final String text = range.getParagraph(i).text();
            if (text.contains("有限公司北京")){
                System.out.println("---------");
//                XWPFDocument document = new XWPFDocument();
//                document.setParagraph(((Paragraph)range.getParagraph(i).clone()),1);
//                range.getParagraph(i).clone();
            }
            System.out.println("====" + text);

        }
        return doc;
    }

    private HWPFDocument replaceText(HWPFDocument doc, Map<String, String> params) {
        Range range = doc.getRange();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            while (true) {
                String text = range.text();
                int startIndex = 0;
                if (text.contains(value)) {
                    startIndex = text.lastIndexOf(value) + value.length();
                    text = text.substring(startIndex);
                }
                int offset = text.indexOf(key);
                if (offset >= 0) {
                    range.replaceText(key, value, offset + startIndex);
                } else {
                    break;
                }
            }
        }
        return doc;
    }

    private HWPFDocument openDocument(String file) throws Exception {
//        URL res = getClass().getClassLoader().getResource(file);
//        HWPFDocument document = null;
//        if (res != null) {
//            document = new HWPFDocument(new POIFSFileSystem(
//                    new File(res.getPath())));
//        }

        HWPFDocument document = new HWPFDocument(new POIFSFileSystem(
                new File(file)));

        return document;
    }

    private void saveDocument(HWPFDocument doc, String file) {
        try (FileOutputStream out = new FileOutputStream(file)) {
            doc.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}