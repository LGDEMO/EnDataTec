package com.endata.springboot.controller;

import com.endata.springboot.model.FileData;
import com.endata.springboot.service.FileService;
import com.endata.springboot.util.NewDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.util.*;

/**
 * @author ligang
 * @create 2019-12-04 16:18
 */
@RestController
@CrossOrigin
public class FileController {

    @Autowired
    private FileService fileService;

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    /*   文件列表的获取*/
    @GetMapping("/file/getFileData")
    public Map getFileData(@RequestParam("type") String type) {
        Map resultMap = new HashMap<>();
        List<FileData> fileList = new ArrayList<>();
        fileList = fileService.getFileData(type);
        resultMap.put("return_code",200);
        resultMap.put("FileList",fileList);
        return resultMap;
    }

    /*   文件列表的删除*/
    @GetMapping("/file/deleteFileData")
    public  Map  deleteFileData(@RequestParam("id") Integer id){
        Map resultMap = new HashMap<>();
       int  number =  fileService.deleteByPrimaryKey(id);
        resultMap.put("return_code",200);
        resultMap.put("return_data","删除了："+number+"数据");
        return resultMap;
    }

    /*   文件的上传*/
    @RequestMapping(value = "/file/uploadFileAction", method = RequestMethod.POST)
    public Map upload(@RequestParam("file") MultipartFile file, @RequestParam("title") String title, @RequestParam("detail") String detail, @RequestParam("type") String type) throws ParseException {
        if (file.isEmpty()) {
            Map resultMap = new HashMap<>();
            resultMap.put("return_code",0);
            return resultMap;
        }
        FileData fileData = new FileData();

        if (title != null && title.length() != 0) {
            fileData.setTitle(title);
        }
        if (detail != null && detail.length() != 0) {
            fileData.setDetail(detail);
        }
        if (type != null && type.length() != 0) {
            fileData.setType(type);
        }
        Date date = fileData.getDate();//时间
        if (date != null) {
            fileData.setDate(date);
        } else {
            NewDate newDate = new NewDate();
            fileData.setDate(newDate.getNewDate());
        }
        try {
            /* // 获取文件名*/
            String fileName = file.getOriginalFilename();
            fileData.setUrl(fileName);
            int number = fileService.insertSelective(fileData);
            log.info("插入：" + number + "条数据");
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            /*// 设置文件存储路径*/
            String filePath = "D://Downloads/";
            String path = filePath + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();/*// 新建文件夹*/
            }
            file.transferTo(dest);/*// 文件写入*/

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map resultMap = new HashMap<>();
        resultMap.put("return_code",200);
        return  resultMap;
    }

    /*   文件的下载*/
    @GetMapping(value = "/file/downloadFileAction")
    public Map downloadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileName") String fileStringName) {
        if (fileStringName != null && fileStringName.length()!= 0) {
            String fileName = fileStringName;// 文件名
            //设置文件路径
            File file = new File("D://Downloads/" + fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    Map resultMap = new HashMap<>();
                    resultMap.put("return_code",200);
                    return resultMap;
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
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        Map resultMap = new HashMap<>();
        resultMap.put("return_code",0);
        return resultMap;
    }

}
