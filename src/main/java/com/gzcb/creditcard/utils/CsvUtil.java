package com.gzcb.creditcard.utils;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {
    private static final String charsetName = "UTF-8";

    /**
     * csv文件读取,小数据,大数据出现内存溢出
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static List<String[]> readerCsv(String file) {
        List<String[]> csvList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(file));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charsetName);
            CSVReader csvReader = new CSVReader(inputStreamReader);
            String[] nextLine;

            while ((nextLine = csvReader.readNext()) != null) {
                csvList.add(nextLine);
            }
            csvReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return csvList;
    }

    public static void readerLargeCsv(String file) {
        List<String> csvList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(file));
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream, charsetName);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 5 * 1024 * 1024);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                csvList.add(line);
            }
            bufferedReader.close();
            bufferedInputStream.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * csv写入
     *
     * @param file    文件路径
     * @param csvList 数据
     * @param append  true为在文件后插入，false文件开始处插入
     * @throws Exception
     */
    public static void writerCsv(String file, List<String[]> csvList, boolean append) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(file), append);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "GBK");
        CSVWriter csvWriter = new CSVWriter(outputStreamWriter, ',');

        csvWriter.writeAll(csvList);

        csvWriter.close();
        outputStreamWriter.close();
        fileOutputStream.close();

    }
}
