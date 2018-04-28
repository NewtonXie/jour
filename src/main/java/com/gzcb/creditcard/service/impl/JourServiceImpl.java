package com.gzcb.creditcard.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzcb.creditcard.dao.TJourMapper;
import com.gzcb.creditcard.dao.entities.TJour;
import com.gzcb.creditcard.service.JourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class JourServiceImpl implements JourService {
    @Autowired
    TJourMapper tJourMapper;
    @Override
    public Page<TJour> selectJour(Page<TJour> page,String mobile,Integer type) {
        PageHelper.startPage(page.getPageNum() <= 0 ? 1 : page.getPageNum(), page.getPageSize() <= 0 ? 15 : page.getPageSize());
        return tJourMapper.selectAllTJourByPage(mobile,type);
    }

    @Override
    public int addJour(TJour tJour) {
        return tJourMapper.insertSelective(tJour);
    }

    @Override
    public int deleterJour(Integer id) {
        return tJourMapper.deleteByPrimaryKey(id);
    }

    @Override
    public TJour selectJourById(Integer id) {
        return tJourMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteJour(Integer id) {
        return tJourMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateJour(TJour tJour) {
        return tJourMapper.updateByPrimaryKeySelective(tJour);
    }

    @Override
    public List<TJour> selectAllJour(Integer id,Integer type) {
        return tJourMapper.selectAllJour(id,type);
    }
}
