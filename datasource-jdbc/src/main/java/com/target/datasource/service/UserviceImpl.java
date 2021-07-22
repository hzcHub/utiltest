package com.target.datasource.service;

import com.target.datasource.bean.TrainInfo;
import com.target.datasource.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@CacheConfig(cacheNames = "trainInfo")
public class UserviceImpl implements Uservice{

    @Autowired
    UserMapper mapper;

    @Cacheable(cacheNames = "string",key = "#id")
    @Override
    public List<Map> selectBlog(int id) {
        System.out.println("selectBlog 执行 。。。");
        return mapper.selectBlog(id);
    }

    @Override
    @Cacheable(cacheNames = "traininfo",key = "#id")
    public List<TrainInfo> selectBlog2(int id) {
        return mapper.selectBlog2(id);
    }
}
