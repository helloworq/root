package com.transform.web.util;

import com.transform.base.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PicUtil {
    /**
     * "a52f2f"
     */
    private static final String PIC_FILE_PATH = "D:\\picBed\\Sync\\Pic\\";

    private ExecutorService executors = Executors.newCachedThreadPool();

    private Map<String, Map<String, String>> cachedPool = new HashMap<>();


    public static void main(String[] args) throws IOException {

        new PicUtil().getPicUrl();

//        Files.walk(Paths.get("D:\\picBed\\Sync\\Pic"))
//                .filter(e->Files.isRegularFile(e))
//                .map(e->e.getFileName().toString().split("\\.")[0])
//                .forEach(System.out::println);

//        Files.walk(Paths.get(PIC_FILE_PATH_Ori))
//                .filter(e -> Files.isRegularFile(e))
//                .forEach(e -> {
//                    try {
//                        String md5 = SyncFile.getFileMd5HexString(e.toString());
//                        new PicServerImpl().savePic(md5, "jpg", Files.readAllBytes(e));
//                    } catch (IOException ex) {
//                        System.out.println(e);
//                        ex.printStackTrace();
//                    }
//                });
    }

    public void getPicUrl() throws IOException {
        List<String> list = Files.readAllLines(Paths.get("C:\\Users\\12733\\Desktop\\s.txt"));

        long start = System.currentTimeMillis();
        list.forEach(e -> getPicRelativePath(e, 1000));
        System.out.println(System.currentTimeMillis() - start);


        long start1 = System.currentTimeMillis();
        list.forEach(e -> getPicRelativePathWithoutCache(e));
        System.out.println(System.currentTimeMillis() - start1);


        //System.out.println(JSON.toJSONString(cachedPool));
    }

    public String getPicRelativePathWithoutCache(String md5) {

        Integer saveLevel = saveLevel(md5);
        if (saveLevel != -1) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i <= saveLevel; i++) {
                stringBuilder.append(md5, 0, i).append("/");
            }
            String res = stringBuilder.append(md5).append(".jpg").toString();
            if (!new File(PIC_FILE_PATH + res).exists()) {
                return "";
            }
            return res;
        }
        return "";
    }

    /**
     * 根据层级获取路径
     *
     * @param md5
     * @return
     */
    public String getPicRelativePath(String md5, int poolSize) {

        //System.out.println("池内数据量: " + cachedPool.size());
        //检测缓存池里是否有数据
        Map<String, String> map = cachedPool.get(md5);
        if (Objects.nonNull(map)) {
            //缓存内的times自增
            executors.execute(() -> {
                Integer times = Integer.parseInt(map.get("times"));
                times++;
                map.put("times", String.valueOf(times));
                cachedPool.put(md5, map);
                checkPoolSize(poolSize);
            });
            return map.get("url");
        }

        Integer saveLevel = saveLevel(md5);
        if (saveLevel != -1) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i <= saveLevel; i++) {
                stringBuilder.append(md5, 0, i).append("/");
            }
            String res = stringBuilder.append(md5).append(".jpg").toString();
            if (!new File(PIC_FILE_PATH + res).exists()) {
                return "";
            }
            //结果写入缓存
            executors.execute(() -> {
                Map<String, String> mapPrev = new HashMap<>();
                mapPrev.put("url", res);
                mapPrev.put("times", "1");
                cachedPool.put(md5, mapPrev);
            });
            return res;
        }
        return "";
    }

    /**
     * 检测缓存池大小
     */
    public void checkPoolSize(int poolSize) {
        //当缓存池大于规定大小，则去除部分元素
        if (cachedPool.size() > poolSize) {
            cachedPool = cachedPool.entrySet().stream()
                    .sorted(Comparator.comparing(e -> e.getValue().get("times")))
                    .skip((long) (poolSize * 0.3))
                    .collect(Collectors.toConcurrentMap(Map.Entry::getKey, Map.Entry::getValue));
            System.out.println("当前池内数据量超过限制,剔除部分数据,当前池内数据量: " + cachedPool.size());
        }
    }

    /**
     * 保存图片
     *
     * @param file
     * @throws IOException
     */
    public String save(File file) throws IOException {
        //获取后缀
        String suffix = getFileSuffix(file.getName());
        //获取字节
//        Path path = Files.createTempFile(Paths.get("C:\\Users\\12733\\Desktop\\新建文件夹"), "temp", suffix);
//        byte[] fileBytes = Files.readAllBytes(path);
//        Files.write(path, fileBytes, StandardOpenOption.WRITE);
        //获取存储时的必要数据
        //String contentType = getContentType(path);
        FileInputStream fileInputStream=new FileInputStream(file);
        String md5 = DigestUtils.md5Hex(fileInputStream);

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        FileUtil.inputStreamWriteToOutputStream(fileInputStream,outputStream);

        savePic(md5, suffix, outputStream.toByteArray());

        outputStream.close();
        fileInputStream.close();
        return md5;
    }

    public String save(MultipartFile file) throws IOException {
        //获取后缀
//        String suffix = getFileSuffix(file.getOriginalFilename());
        //获取字节
//        Path path = Files.createTempFile(Paths.get("C:\\Users\\12733\\Desktop\\新建文件夹"), "temp", suffix);
//        byte[] fileBytes = Files.readAllBytes(path);
//        Files.write(path, fileBytes, StandardOpenOption.WRITE);
        //获取存储时的必要数据
        String contentType = getFileSuffix(file.getOriginalFilename());
        String md5 = DigestUtils.md5Hex(file.getBytes());

        savePic(md5, contentType, file.getBytes());
        return md5;
    }

    /**
     * 根据文件md5前缀将文件分类整理
     *
     * @param md5         文件md5值
     * @param contentType 文件类型
     * @param bytes       数据
     * @throws IOException
     */
    public void savePic(String md5, String contentType, byte[] bytes) throws IOException {
        if (!isCurrentImgSupport(contentType)) {
            return;
        }
        //获取存储层级
        int level = saveLevel(md5);
        //例如md5是123f2t422，level是3，则需要生成的路径是 1/12/123/
        //获取到level之后先检测，当前level目录是否有文件夹，有的话则创建文件夹将此文件放入，没有的话
        //监测此层级数据量，超过限制则触发创建文件夹机制，没有则将数据放入
        String prefix = md5.substring(0, level);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= level; i++) {
            stringBuilder.append(prefix, 0, i).append("/");
        }
        Path path = Paths.get(PIC_FILE_PATH + stringBuilder.toString());
        //检测是否有文件夹
        int directorySize = (int) Files.list(path.getParent()).filter(e -> Files.isDirectory(e)).count();
        if (directorySize == 0) {
            //没有文件夹情况
            //先将文件直接写入
            Files.write(Paths.get(path.getParent() + File.separator + md5 + "." + contentType), bytes, StandardOpenOption.CREATE);
            int fileSize = (int) Files.list(path.getParent()).filter(e -> Files.isRegularFile(e)).count();
            //检测当前层级的文件数量是否超过限制
            if (fileSize > 2) {
                List<String> list = new ArrayList<>();//储存已创建目录
                //文件数量超过限制情况,超过限制则创建文件夹
                Files.list(path.getParent()).forEach(e -> {
                    //根据已存在文件按需创建文件夹
                    String dirPathName = e.getFileName().toString().substring(0, level);
                    if (!list.contains(dirPathName)) {
                        try {
                            Files.createDirectories(Paths.get(path.getParent() + File.separator + dirPathName));
                            list.add(dirPathName);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    try {
                        Files.move(e, Paths.get(path.getParent() + File.separator + dirPathName).resolve(e.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
            }
        }
        //有文件夹情况
        //检测是否有此level文件夹
        else {
            int specficSize = (int) Files.list(path.getParent())
                    .filter(e -> e.toFile().isDirectory() && e.startsWith(md5.substring(0, level)))
                    .count();
            if (specficSize != 0) {
                Files.write(Paths.get(path.getParent() + File.separator + md5 + "." + contentType), bytes);
            } else {
                Files.createDirectories(path);
                Files.write(Paths.get(path + File.separator + md5 + "." + contentType), bytes);
            }
        }
    }

    //监测当前文件md5在第几个层级
    private Integer saveLevel(String md5) {

        StringBuilder prefix = new StringBuilder();

        for (int i = 1; i <= md5.length(); i++) {
            prefix.append(md5, 0, i).append("\\");
            if (!new File(PIC_FILE_PATH + prefix).exists()) {
                return i - 1;
            }
        }
        return -1;
    }

    private String getFileSuffix(String path) {
        int splitPosition = path.lastIndexOf(".");
        return path.substring(splitPosition + 1);
    }

    private boolean isCurrentImgSupport(String contentType) {
        List<String> list = Arrays.asList("jpg", "jpeg", "gif", "png", "bmp");
        return list.stream().anyMatch(contentType::contains);
    }

    private String getContentType(Path origin) throws IOException {
        String path = Files.probeContentType(origin);
        int splitPosition = path.lastIndexOf("/");
        return "." + path.substring(splitPosition + 1);
    }

    /**
     * 获取文件md5
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    private String getFileMd5HexString(String filePath) throws IOException {

        if (Objects.isNull(filePath) || filePath.equals("") || Files.isDirectory(Paths.get(filePath))) {
            return null;
        }

        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));

        if (fileBytes.length == 0) {
            return null;
        }

        return DigestUtils.md5Hex(fileBytes);
    }
}
