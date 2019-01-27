package com.ip.intelligentPropertyService.dao;

import com.ip.intelligentPropertyService.entity.CompanyInfo;
import com.ip.intelligentPropertyService.entity.FileEntity;
import com.ip.intelligentPropertyService.entity.MenuPictureEntity;
import com.ip.intelligentPropertyService.entity.VillageNotice;
import com.ip.intelligentPropertyService.model.NoticeParamModel;

import java.util.List;
import java.util.Map;

public interface SourceMapper {

    List<CompanyInfo> selectComanyInfo();

    List<MenuPictureEntity> selectPicByPage(String menu);

    FileEntity selectFileByPrimaryKey(String id);

    List<VillageNotice> selectNoticeByMenu(NoticeParamModel paramModel);
}
