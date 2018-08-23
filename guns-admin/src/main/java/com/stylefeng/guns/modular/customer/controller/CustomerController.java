package com.stylefeng.guns.modular.customer.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Customer;
import com.stylefeng.guns.modular.customer.service.ICustomerService;

import java.util.Date;

/**
 * APP用户管理控制器
 *
 * @author fengshuonan
 * @Date 2018-08-18 23:33:53
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    private String PREFIX = "/customer/customer/";

    @Autowired
    private ICustomerService customerService;

    /**
     * 跳转到APP用户管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "customer.html";
    }

    /**
     * 跳转到添加APP用户管理
     */
    @RequestMapping("/customer_add")
    public String customerAdd() {
        return PREFIX + "customer_add.html";
    }

    /**
     * 跳转到修改APP用户管理
     */
    @RequestMapping("/customer_update/{customerId}")
    public String customerUpdate(@PathVariable Integer customerId, Model model) {
        Customer customer = customerService.selectById(customerId);
        model.addAttribute("item",customer);
        LogObjectHolder.me().set(customer);
        return PREFIX + "customer_edit.html";
    }

    /**
     * 获取APP用户管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return customerService.selectList(null);
    }

    /**
     * 新增APP用户管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Customer customer) {
        customer.setCreateTime(new Date());
        customer.setPassword(ShiroKit.md5(customer.getPassword(), "unhejing"));
        customerService.insert(customer);
        return SUCCESS_TIP;
    }

    /**
     * 删除APP用户管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer customerId) {
        customerService.deleteById(customerId);
        return SUCCESS_TIP;
    }

    /**
     * 修改APP用户管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Customer customer) {
        customerService.updateById(customer);
        return SUCCESS_TIP;
    }

    /**
     * APP用户管理详情
     */
    @RequestMapping(value = "/detail/{customerId}")
    @ResponseBody
    public Object detail(@PathVariable("customerId") Integer customerId) {
        return customerService.selectById(customerId);
    }
}
