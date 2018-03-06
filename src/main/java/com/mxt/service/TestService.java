package com.mxt.service;

import com.mxt.dao.TestMapper;
import com.mxt.dao.extend.MyMapper;
import com.mxt.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mxt on 18-3-6.
 */
@Service
public class TestService {
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private MyMapper myMapper;

    public List<Test> list(){
        return testMapper.selectAll();
    }

    public List<Test> list1() {
        return myMapper.selectAll();
    }
}
