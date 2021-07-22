package com.target.datasource.service;

import com.target.datasource.bean.TrainInfo;

import java.util.List;
import java.util.Map;

public interface Uservice {


    List<Map> selectBlog(int id);

    List<TrainInfo> selectBlog2(int id);
}
