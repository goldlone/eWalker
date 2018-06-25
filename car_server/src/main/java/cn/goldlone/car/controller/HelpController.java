package cn.goldlone.car.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author Created by CN on 2018/6/26 0:15 .
 */
@RestController
public class HelpController extends BaseController {

    @Value("${videoFilePath}")
    private String VIDEO_FILE_PATH;
    @Value("${ServerIP}")
    private String SERVER_IP;


    @PostMapping("/help/video/upload")
    public String receiveVideo(HttpServletRequest request) {
        long currentTime = System.currentTimeMillis();
        File file = new File(VIDEO_FILE_PATH+"/"+currentTime+".mp4");
        String getUrl = SERVER_IP+"help?id="+currentTime;
        System.out.println(getUrl);
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = request.getInputStream();
            fileOutputStream = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int len;
            while((len=inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
                System.out.println("write...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return getUrl;
    }

    /**
     * 下载求救视频
     * @param response
     * @param id
     * @return
     */
    @GetMapping("/help")
    public String downloadVideoFile(HttpServletResponse response, Long id) {
        if(id == null)
            return "{\"code\":\"2001\", \"msg\":\"该视频不存在\", \"data\":null}";
        File file = new File(VIDEO_FILE_PATH+id+".mp4");
        if(!file.exists())
            return "{\"code\":\"2001\", \"msg\":\"该视频不存在\", \"data\":null}";
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + id+".mp4");
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
//            file = new File(Configs.VIDEO_FILE_PATH+id+".mp4");
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
