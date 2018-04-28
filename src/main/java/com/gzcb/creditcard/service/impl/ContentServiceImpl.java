package com.gzcb.creditcard.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzcb.creditcard.dao.TContentMapper;
import com.gzcb.creditcard.dao.entities.TContent;
import com.gzcb.creditcard.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ContentServiceImpl implements ContentService {
    @Autowired
    TContentMapper tContentMapper;
    @Override
    public int pushMessage(TContent tContent) {
        return tContentMapper.insertSelective(tContent);
    }

    @Override
    public List<TContent> getMessage(Short type) {
        return tContentMapper.selectMessage(type);
    }

    @Override
    public int updateContent(TContent tContent) {
        return tContentMapper.updateByPrimaryKeySelective(tContent);
    }

    @Override
    public Page<TContent> getContentByPage(Page<TContent> page,Date createdAT,Short type) {
        PageHelper.startPage(page.getPageNum() <= 0 ? 1 : page.getPageNum(), page.getPageSize() <= 0 ? 15 : page.getPageSize());
        return tContentMapper.selectAllContentByPage(createdAT,type);
    }

    @Override
    public TContent getContent(Integer id) {
        return tContentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteContent(Integer id) {
        return tContentMapper.deleteByPrimaryKey(id);
    }
}
