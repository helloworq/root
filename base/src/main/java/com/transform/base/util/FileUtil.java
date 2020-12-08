package com.transform.base.util;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class FileUtil {

    /**
     * 删除指定目录下的文件，或者是指定文件的同级目录目录文件，但是不删除文件夹
     * @param filePath
     */
    public static void deleteFileUnderFolder(String filePath){
        File file=new File(filePath);
        if (file.isFile())
            file=file.getParentFile();
        if (file.listFiles().length>0) {
            for (File fileTemp : file.listFiles()) {
                if (fileTemp.isDirectory()){
                    continue;
                }
                if (fileTemp.isFile())
                    fileTemp.delete();
            }
        }
    }
    /**
     * 根据传入的路径以及后缀生成随机名字的文件,然后返回文件完整路径
     * @param filepath
     * @param fileSuffix
     * @return
     * @throws IOException
     */
    public static String creatRandomNameFile(String filepath,String fileSuffix) throws IOException {
        //String path="C:\\Users\\12733\\Desktop\\Windows聚焦图片\\k\\";
        String pathid= UUID.randomUUID().toString().replace("-","");
        String path=filepath+pathid+"."+fileSuffix;
        //System.out.println(path);
        File file=new File(path);
        File pathParent=file.getParentFile();
        if (!pathParent.exists())
            pathParent.mkdirs();
        if (!file.exists()) {
            file.createNewFile();
            return path;
        }
        else {
            System.out.println("创建文件失败！");
            return "fail";
        }
    }

    /**
     * 此方法用来实现将输入流中的数据写入到输出流中
     * @param input
     * @param output
     * @throws IOException
     */
    public static void inputStreamWriteToOutputStream(InputStream input, OutputStream output) throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        while ((index = input.read(bytes)) != -1) {
            output.write(bytes, 0, index);
            output.flush();
        }
        input.close();
        output.close();
    }

    /**
     * 文件大小字节转MB
     * @param bytes
     * @return
     */
    public static String byteToMb(Long bytes){
        double dbytes=Double.valueOf(bytes);
        double rate=1d/1024d/1024d;
        double Mb=dbytes*rate;
        return String.valueOf(Mb);
    }

    /**
     * 获取文件后缀，url一般为aaa.jpg
     * 可能会是a.b.c.x/sq.jpg
     * 增加对无后缀文件的判断例如文件名为：aaa  bbb
     *
     * @param filePath
     * @return
     */
    public static String getFileSuffix(String filePath) {
        //剔除文件的路径，无路径则不处理
        String fileCompletePath = filePath.substring(filePath.lastIndexOf("/") + 1);
        //判断文件名是否含有.号
        Integer dotIndex = fileCompletePath.lastIndexOf(".");
        return dotIndex == -1 ? null : fileCompletePath.substring(dotIndex + 1);
    }

    /**
     * 获取文件后缀，url一般为aaa.jpg
     * 可能会是a.b.c.x/sq.jpg
     * 增加对无后缀文件的支持例如文件名为：aaa  bbb
     *
     * @param filePath
     * @return
     */
    public static String getFilename(String filePath) {
        //剔除文件的路径，无路径则不处理
        String fileCompletePath = filePath.substring(filePath.lastIndexOf("/") + 1);
        //判断文件名是否含有.号
        Integer dotIndex = fileCompletePath.lastIndexOf(".");
        return dotIndex == -1 ? fileCompletePath : fileCompletePath.substring(0, dotIndex);
    }

}
