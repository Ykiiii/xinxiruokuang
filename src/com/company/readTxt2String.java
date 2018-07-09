package com.company;
import java.io.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class readTxt2String {
    public static void main(String[] args) throws IOException, Exception, InterruptedException {
        File f = new File("D:/test.txt");

        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String sourceTXT = "";
        String data = "";

        while ((data = br.readLine()) != null) {
            sourceTXT += data;
        }

        //sourceTXT = sourceTXT.toLowerCase();//תСд
        //sourceTXT = sourceTXT.toUpperCase();//ת��д

        sourceTXT = sourceTXT.replaceAll(" ", "");//ȥ�����пո�
        sourceTXT = sourceTXT.replaceAll("\"", "");//ȥ����������

        int maxSplit = 200;//�ָ���Ľ���ַ�����������
        String[] sourceStrArray = sourceTXT.split(",", maxSplit);//�Զ��ŷָ�Ϊ�ַ���
        for (int i = 0; i < sourceStrArray.length; i++) {
            System.out.println(sourceStrArray[i] + "-----" + (i + 1));
        }

        br.close();
        fr.close();
        System.out.println("---read--Finish---");



    }
}
