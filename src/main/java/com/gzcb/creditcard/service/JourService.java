package com.gzcb.creditcard.service;

import com.github.pagehelper.Page;
import com.gzcb.creditcard.dao.entities.TJour;
import io.swagger.models.auth.In;

import java.util.List;

public interface JourService {
    Page<TJour> selectJour(Page<TJour> page,String mobile,Integer type);
    int addJour(TJour tJour);
    int deleterJour(Integer id);
    TJour selectJourById(Integer id);
    int deleteJour(Integer id);
    int updateJour(TJour tJour);
    List<TJour> selectAllJour(Integer id,Integer type);
}
