package com.ip.intelligentPropertyService.controller;

import com.ip.intelligentPropertyService.Service.SourceService;
import com.ip.intelligentPropertyService.common.CommonTools;
import com.ip.intelligentPropertyService.entity.CompanyInfo;
import com.ip.intelligentPropertyService.entity.FileEntity;
import com.ip.intelligentPropertyService.entity.MenuPictureEntity;
import com.ip.intelligentPropertyService.entity.VillageNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/source")
public class SourceController {

    @Autowired
    private SourceService sourceService;

    @RequestMapping("/selectComanyInfo")
    public String selectComanyInfo() {
        List<CompanyInfo> records = sourceService.selectComanyInfo();
        return CommonTools.objectToJson(records);
    }

    @RequestMapping("/selectPicByMenu")
    public String selectPicByMenu(HttpServletRequest request) {
        String menu = request.getParameter("menu");
        List<MenuPictureEntity> records = sourceService.selectPicByPage(menu);
        return CommonTools.objectToJson(records);
    }

    @RequestMapping("/loadPic")
    public void selectPic(HttpServletRequest request,  HttpServletResponse response) throws UnsupportedEncodingException {
        String id = request.getParameter("fileId");
        FileEntity file = sourceService.selectFileByPrimaryKey(id);

        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel");
        try {
            InputStream inputStream = null;
            inputStream = new FileInputStream(file.getPath());
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.flush();
            os.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/selectPdf")
    public void selectPdf(HttpServletRequest request,  HttpServletResponse response) throws UnsupportedEncodingException {

        String fileId = request.getParameter("fileId");
        FileEntity file = sourceService.selectFileByPrimaryKey(fileId);
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel");
        try {
            InputStream inputStream = new FileInputStream(file.getPath());
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.flush();
            os.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/selectNoticeByMenu")
    public String selectNoticeByMenu(HttpServletRequest request) {
        String menu = request.getParameter("menu");
        List<VillageNotice> records = sourceService.selectNoticeByMenu(menu);
        return CommonTools.objectToJson(records);
    }

}
