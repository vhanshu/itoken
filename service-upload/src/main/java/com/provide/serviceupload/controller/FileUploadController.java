package com.provide.serviceupload.controller;

import com.provide.serviceupload.utils.FdfsClientWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author vhans
 */
@Controller
public class FileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FdfsClientWrapper fdfsClientWrapper;

    /**
     * 进入上传页面
     *
     * @return 路径
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String form() {
        return "test";
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件路径
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(MultipartFile file) {
        String filePath = null;
        try {
            filePath = fdfsClientWrapper.uploadFile(file);
        } catch (IOException e) {
            logger.error("上传文件异常:{}", e);
            return "上传文件失败";
        }
        return filePath;
    }

    /**
     * 下载文件
     *
     * @param filePath 文件路径
     * @return
     */
    @RequestMapping(value = "download")
    public void downloadFile(String filePath, HttpServletResponse response) {
        ServletOutputStream outputStream = null;
        try {
            byte[] bytes = fdfsClientWrapper.downloadFile(filePath);
            String fileName = "fdfs.jpg";
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setCharacterEncoding("UTF-8");
            if (bytes != null) {
                outputStream = response.getOutputStream();
                outputStream.write(bytes);
                outputStream.flush();
            }
        } catch (IOException e) {
            logger.debug("下载文件输出流异常:{}", e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                logger.debug("下载文件关闭流异常:{}", e);
            }
        }
    }

    /**
     * 删除文件
     *
     * @param filePath 文件路径
     * @return 删除结果
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public String deleteFile(String filePath) {
        fdfsClientWrapper.deleteFile(filePath);
        return "删除成功";
    }
}
