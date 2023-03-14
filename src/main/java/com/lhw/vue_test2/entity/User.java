package com.lhw.vue_test2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lhw
 * @date 2023/3/9 16:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "t_user")
public class User {
    private Integer id;
//    private String account;
    private String username;
    private String password;

}
