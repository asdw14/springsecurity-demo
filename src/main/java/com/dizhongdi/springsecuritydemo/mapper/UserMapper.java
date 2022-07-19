package com.dizhongdi.springsecuritydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dizhongdi.springsecuritydemo.enity.Users;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName:UserMapper
 * Package:com.dizhongdi.springsecuritydemo.mapper
 * Description:
 *
 * @Date: 2022/7/19 22:50
 * @Author:dizhongdi
 */
@Mapper
public interface UserMapper extends BaseMapper<Users> {

}
