package com.sinolease.base.util;
import com.sinolease.base.exception.UploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by AlbertLy on 2016/11/22.
 */
public class FileUploadUtil {
    private static final Logger log = LoggerFactory.getLogger(FileUploadUtil.class);
    public static void largeFileIO(MultipartFile mf,  File uploadFile) {
        try {
            BufferedInputStream in = new BufferedInputStream(mf.getInputStream());

            FileOutputStream fw = new FileOutputStream(uploadFile);
            int n=1024;
            byte[] buffer=new byte[n];
            while ((in.read(buffer, 0, n) != -1) && (n > 0)) {
                fw.write(buffer);
            }
            in.close();
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * @param request
     * @param filePath 文件存储的路径
     * @param suffix   用来放在文件名末尾。原始文件名：text.txt, suffix=1234, 最终文件名为text1234.txt, 可以为空
     * @return 返回成功上传的文件名
     * @throws Exception
     */
    public static String uploadFile(HttpServletRequest request, String filePath, String suffix) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String fileName = null;
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();
            fileName = mf.getOriginalFilename();
            String wholeFileName = filePath + File.separatorChar + makeFileName(fileName, suffix);
            File dest = new File(filePath);
            if (!dest.exists()) {
                dest.mkdirs();
            }
            File uploadFile = new File(wholeFileName);
            if (uploadFile.exists()) {
                uploadFile.delete();
            }
            try {
                log.info("start upload file: " + fileName);
                //FileCopyUtils.copy(mf.getBytes(), uploadFile);
                largeFileIO(mf,uploadFile);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                log.info("upload failed. filename: " + fileName + e.getMessage());
                throw new UploadException(e.getMessage());
            }
        }
        return fileName;
    }

    /**
     * 根据suffix生成文件名
     *
     * @param fileName
     * @param suffix
     * @return
     */
    public static String makeFileName(String fileName, String suffix) {
        if (StringUtils.isEmpty(suffix)) {
            return fileName;
        } else {
            String fileNamePre = fileName.substring(0, fileName.lastIndexOf("."));
            String extension = fileName.substring(fileName.lastIndexOf("."));
            fileName = fileNamePre + suffix + extension;
            return fileName;
        }
    }

    /**
     * 生成下载的文件名
     *
     * @param filePath
     * @return
     */
    public static String makeDownloadFileName(String filePath) {
        try {
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            return URLEncoder.encode(fileName, "utf-8");
        } catch (Exception e) {
            return filePath;
        }
    }
    private static String ffmpeg_path="C:/ffmpeg.exe";
    public static boolean processImg(String veido_path) {
        File file = new File(veido_path);
        String img_path=veido_path.substring(0, veido_path.lastIndexOf(".")).replaceFirst("vedio", "file") + ".jpg";
        if (!file.exists()) {
            System.err.println("路径[" + veido_path + "]对应的视频文件不存在!");
            return false;
        }
        List<String> commands = new java.util.ArrayList<String>();
        commands.add(ffmpeg_path);
        commands.add("-i");
        commands.add(veido_path);
        commands.add("-y");
        commands.add("-f");
        commands.add("image2");
        commands.add("-ss");
        Random rand=new Random();
        int min=rand.nextInt()%20;
        commands.add(""+min);//这个参数是设置截取视频多少秒时的画面
        //commands.add("-t");
        //commands.add("0.001");
        commands.add("-s");
        commands.add("300x200");
        commands.add(img_path);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            builder.start();
            System.out.println("截取成功");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}