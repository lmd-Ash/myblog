package com.myblog.utils;

import com.csvreader.CsvReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.myblog.utils.MultipartFileToFile.delteTempFile;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2021/3/31
 */
public class CsvUtil {
    /**
     * 读取csv文件
     *
     * @param file 获取的文件
     * @return 读取后返回list集合
     */
    public static List<String[]> readCsv(File file) {
        List<String[]> lists = new ArrayList<>();
        // 获取文件的绝对路径
        String filePath = file.getAbsolutePath();
        try {
            // 创建CSV读对象
            CsvReader csvReader = new CsvReader(filePath, ',', StandardCharsets.UTF_8);
            // 跳过表头
//            csvReader.readHeaders();
            while (csvReader.readRecord()) {
                // 读一整行
                csvReader.getRawRecord();
                // 读这行的某一列
//                System.out.println(csvReader.get("Link"));
                lists.add(csvReader.getValues());
            }
            csvReader.close();
            return lists;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 删除项目中上传的excel
            delteTempFile(file);
        }
        return null;
    }
}
