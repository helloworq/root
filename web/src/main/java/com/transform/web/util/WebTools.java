package com.transform.web.util;

import com.transform.base.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class WebTools implements ApplicationListener<WebServerInitializedEvent> {
    @Autowired
    MyIOUtil myIOUtil;

    private int serverPort;

    /**
     * 返回项目的ip+port，避免手动写死
     *
     * @return
     */
    public String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "http://" + address.getHostAddress() + ":" + this.serverPort;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }

    public String getCookie(Cookie[] cookies, String key) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

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
            FileUtil.inputStreamWriteToOutputStream(oringDataInputStream, outputStream);
            System.out.println("已完成第   " + ++order + "   个文件");
        }


    }

}