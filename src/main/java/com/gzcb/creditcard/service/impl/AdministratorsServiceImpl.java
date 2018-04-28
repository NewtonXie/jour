package com.gzcb.creditcard.service.impl;

import com.gzcb.creditcard.dao.TContentMapper;
import com.gzcb.creditcard.dao.entities.TContent;
import com.gzcb.creditcard.service.AdministratorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdministratorsServiceImpl implements AdministratorsService {

}
