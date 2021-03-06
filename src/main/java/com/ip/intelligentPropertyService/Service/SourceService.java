package com.ip.intelligentPropertyService.Service;

import com.ip.intelligentPropertyService.dao.SourceMapper;
import com.ip.intelligentPropertyService.entity.CompanyInfo;
import com.ip.intelligentPropertyService.entity.FileEntity;
import com.ip.intelligentPropertyService.entity.MenuPictureEntity;
import com.ip.intelligentPropertyService.entity.VillageNotice;
import com.ip.intelligentPropertyService.model.NoticeParamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SourceService {

    @Autowired
    private SourceMapper sourceMapper;

    public List<CompanyInfo> selectComanyInfo() {
        return sourceMapper.selectComanyInfo();
    }

    public List<MenuPictureEntity> selectPicByPage(String menu) {
        return sourceMapper.selectPicByPage(menu);
    }

    public FileEntity selectFileByPrimaryKey(String id) {
        return sourceMapper.selectFileByPrimaryKey(id);
    }

    public List<VillageNotice> selectNoticeByMenu(NoticeParamModel paramModel) {
        return sourceMapper.selectNoticeByMenu(paramModel);
    }

}
