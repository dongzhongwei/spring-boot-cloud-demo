package com.ddw.demo.io;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Slf4j
public class AnalysisZipDemo {

    public static void main(String[] args) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //线程池维护线程的最少数量
        executor.setCorePoolSize(3);
        //线程池维护线程的最大数量
        executor.setMaxPoolSize(5);
        //线程池所使用的缓冲队列
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("analysisZip-");
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.afterPropertiesSet();

        String[] files = new String[]{"resume1.zip","resume2.zip","resume3.zip","resume4.zip","resume5.zip","resume6.zip","resume7.zip"};

        for (int i = 0; i < files.length; i++) {
//            long total = 0;
            try {
                Thread.sleep(i*2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            File zipFile = new File("/Users/dongdawei/Documents/test/"+files[i]);
            try (InputStream fi = new FileInputStream(zipFile);
                 ZipArchiveInputStream is = new ZipArchiveInputStream(fi)) {
                ZipArchiveEntry entry;
                while ((entry = is.getNextZipEntry()) != null) {
                    String entryName = entry.getName();
                    if (!entryName.startsWith("__MACOSX") && !entry.isDirectory() && !entryName.endsWith(".DS_Store")) {
//                        total++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
//            final long totalNum = total;
            log.info("begin: zipFile={}",zipFile.getName());
            executor.submit(() -> {
                long total = 0;
                try (InputStream fi = new FileInputStream(zipFile);
                     ZipArchiveInputStream is = new ZipArchiveInputStream(fi)) {
                    ZipArchiveEntry entry;
                    while ((entry = is.getNextZipEntry()) != null) {
                        String entryName = entry.getName();
                        if (!entryName.startsWith("__MACOSX") && !entry.isDirectory() && !entryName.endsWith(".DS_Store")) {
                            //上传文件
                            byte[] bytes = IOUtils.toByteArray(is);
                            total++;
                            Thread.sleep(RandomUtils.nextInt(1000,5000));
                            log.info("zipFile={},entryName={}",zipFile.getAbsolutePath(),entryName);
                        }
                    }
                    log.info("execute: zipFile={},total={}",zipFile.getName(),total);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            log.info("end: zipFile={}",zipFile.getName());
        }
//        executor.shutdown();
    }
}
