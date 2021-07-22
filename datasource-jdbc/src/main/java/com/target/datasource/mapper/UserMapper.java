package com.target.datasource.mapper;

import com.target.datasource.bean.TrainInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface UserMapper {

    List<Map> selectBlog(int id);

    List<TrainInfo> selectBlog2(@Param("id") int id);
}
