package com.ip.intelligentPropertyService.dao;

import com.ip.intelligentPropertyService.entity.CompanyInfo;
import com.ip.intelligentPropertyService.entity.FileEntity;
import com.ip.intelligentPropertyService.entity.MenuPictureEntity;
import com.ip.intelligentPropertyService.entity.VillageNotice;

import java.util.List;

public interface SourceMapper {

    List<CompanyInfo> selectComanyInfo();

    List<MenuPictureEntity> selectPicByPage(String menu);

    FileEntity selectFileByPrimaryKey(String id);

    List<VillageNotice> selectVillageInfo();
}
