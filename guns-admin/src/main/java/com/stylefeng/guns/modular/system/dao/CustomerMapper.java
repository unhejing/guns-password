package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Customer;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * APP用户表 Mapper 接口
 * </p>
 *
 * @author unhejing
 * @since 2018-08-18
 */
public interface CustomerMapper extends BaseMapper<Customer> {

    Customer getByAccount(String username);
}
