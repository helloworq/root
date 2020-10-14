package com.transform.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class Tools {
    @Autowired
    MyIOUtil myIOUtil;

    public void getWindowsLockScreenWallPaper() throws IOException {

        String originFilePath = "C:\\Users\\12733\\AppData\\Local\\Packages\\Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\LocalState\\Assets";
        //小程序，从Windows聚焦的目录里提取所有文件到指定目录下，并且加上.jpg后缀
        String TargetfilePath = "C:\\Users\\12733\\Desktop\\Windows聚焦图片";
        File file = new File(originFilePath);
        //获取目录下所有文件的文件名
        String[] listOfFile = file.list();
        int order = 0;//完成序号
        for (String s : listOfFile) {
            //renameTo方法会把源文件夹文件删除，故不采用
            String Targetpath = originFilePath + "\\" + s;
            InputStream oringDataInputStream = new DataInputStream(new FileInputStream(Targetpath));
            //全部加上.jpg后缀
            File Targetfile = new File(TargetfilePath + "\\" + s + ".jpg");
            if (!Targetfile.exists()) {
                Targetfile.createNewFile();
            }
            OutputStream outputStream = new FileOutputStream(Targetfile);
            myIOUtil.inputStreamWriteToOutputStream(oringDataInputStream,outputStream);
            System.out.println("已完成第   " + ++order + "   个文件");
        }


    }

    List<String> getListNameFomePath(String filePath) {
        List<String> list = new ArrayList<>();
        File file = new File(filePath);
        String[] listOfFile = file.list();
        //全部加上.jpg后缀
        for (String s : listOfFile) {
            File subfile = new File(filePath + "\\" + s);
            subfile.renameTo(new File(filePath + "\\" + s + ".jpg"));
        }
        return list;
    }
}
