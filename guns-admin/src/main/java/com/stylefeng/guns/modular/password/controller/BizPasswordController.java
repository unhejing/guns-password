package com.stylefeng.guns.modular.password.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.BizPassword;
import com.stylefeng.guns.modular.password.service.IBizPasswordService;

import java.util.Date;

/**
 * 密码管理控制器
 *
 * @author fengshuonan
 * @Date 2018-08-16 16:04:05
 */
@Controller
@RequestMapping("/bizPassword")
public class BizPasswordController extends BaseController {

    private String PREFIX = "/password/bizPassword/";

    @Autowired
    private IBizPasswordService bizPasswordService;

    /**
     * 跳转到密码管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizPassword.html";
    }

    /**
     * 跳转到添加密码管理
     */
    @RequestMapping("/bizPassword_add")
    public String bizPasswordAdd() {
        return PREFIX + "bizPassword_add.html";
    }

    /**
     * 跳转到修改密码管理
     */
    @RequestMapping("/bizPassword_update/{bizPasswordId}")
    public String bizPasswordUpdate(@PathVariable Integer bizPasswordId, Model model) {
        BizPassword bizPassword = bizPasswordService.selectById(bizPasswordId);
        model.addAttribute("item",bizPassword);
        LogObjectHolder.me().set(bizPassword);
        return PREFIX + "bizPassword_edit.html";
    }

    /**
     * 获取密码管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bizPasswordService.selectList(null);
    }

    /**
     * 新增密码管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizPassword bizPassword) {
        bizPassword.setCreateTime(new Date());
//        bizPassword.setCreateName();
        bizPasswordService.insert(bizPassword);
        return SUCCESS_TIP;
    }

    /**
     * 删除密码管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizPasswordId) {
        bizPasswordService.deleteById(bizPasswordId);
        return SUCCESS_TIP;
    }

    /**
     * 修改密码管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizPassword bizPassword) {
        bizPasswordService.updateById(bizPassword);
        return SUCCESS_TIP;
    }

    /**
     * 密码管理详情
     */
    @RequestMapping(value = "/detail/{bizPasswordId}")
    @ResponseBody
    public Object detail(@PathVariable("bizPasswordId") Integer bizPasswordId) {
        return bizPasswordService.selectById(bizPasswordId);
    }
}
