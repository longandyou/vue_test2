package com.lhw.vue_test2;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lhw.vue_test2.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.Mapping;

/**
 * @author lhw
 * @date 2023/3/13 11:23
 */
@Mapper
public interface TestMapper extends BaseMapper<User> {
}
