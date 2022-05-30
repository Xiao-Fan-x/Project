package com.example.demo.service.claszInfor;

import com.example.demo.dao.master.ClaszDao;
import com.example.demo.entity.Clasz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaszInforServiceImpl implements ClaszInforService{

    @Autowired
    private ClaszDao claszDao;

    @Override
    public Object getClaszInforAll(){
        List<Clasz> list = claszDao.getAll();

        return list;
    }
}
