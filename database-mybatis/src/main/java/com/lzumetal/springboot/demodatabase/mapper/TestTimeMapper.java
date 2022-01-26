package com.lzumetal.springboot.demodatabase.mapper;

import com.lzumetal.springboot.demodatabase.entity.TestTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author liaosi
 * @date 2022-01-25
 */
@Mapper
public interface TestTimeMapper {

    TestTime getById(@Param("id") Integer id);

    void insert(TestTime entity);
}
