package com.company.electriccar.service;

import com.company.electriccar.domain.Account;
import com.company.modules.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 14-6-12.
 */
@Service
public class AccountService {
    @Autowired
    private BaseDao baseDao;
    public Map findEntity(Account account) {
       return  baseDao.queryForMap("select * from sys_user where userId ='"+account.getUserId()+"' and pwd='"+account.getPwd()+"'" );
    }
}
