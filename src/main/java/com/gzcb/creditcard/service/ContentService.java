package com.gzcb.creditcard.service;

import com.github.pagehelper.Page;
import com.gzcb.creditcard.dao.entities.TContent;

import java.util.Date;
import java.util.List;

public interface ContentService {
    int pushMessage(TContent tContent);
    List<TContent> getMessage(Short type);
    int updateContent(TContent tContent);
    Page<TContent> getContentByPage(Page<TContent> page,Date createdAT,Short type);
    TContent getContent(Integer id);
    int deleteContent(Integer id);
}
