package com.ddcx.springboot;

import com.ddcx.springboot.Guokao2017;
import java.util.List;

public interface Guokao2017Mapper {
    int insert(Guokao2017 record);

    List<Guokao2017> selectAll();
}