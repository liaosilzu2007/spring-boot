package com.ddcx.springboot;

import com.ddcx.springboot.Area;
import java.util.List;

public interface AreaMapper {
    int insert(Area record);

    List<Area> selectAll();
}