package com.stylefeng.guns.modular.billingDetail.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.BillingDetail;
import com.stylefeng.guns.modular.billingDetail.service.IBillingDetailService;

/**
 * 账单明细控制器
 *
 * @author fengshuonan
 * @Date 2018-12-21 11:01:19
 */
@Controller
@RequestMapping("/billingDetail")
public class BillingDetailController extends BaseController {

    private String PREFIX = "/billingDetail/billingDetail/";

    @Autowired
    private IBillingDetailService billingDetailService;

    /**
     * 跳转到账单明细首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "billingDetail.html";
    }

    /**
     * 跳转到添加账单明细
     */
    @RequestMapping("/billingDetail_add")
    public String billingDetailAdd() {
        return PREFIX + "billingDetail_add.html";
    }

    /**
     * 跳转到修改账单明细
     */
    @RequestMapping("/billingDetail_update/{billingDetailId}")
    public String billingDetailUpdate(@PathVariable Integer billingDetailId, Model model) {
        BillingDetail billingDetail = billingDetailService.selectById(billingDetailId);
        model.addAttribute("item",billingDetail);
        LogObjectHolder.me().set(billingDetail);
        return PREFIX + "billingDetail_edit.html";
    }

    /**
     * 获取账单明细列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return billingDetailService.selectList(null);
    }

    /**
     * 新增账单明细
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BillingDetail billingDetail) {
        billingDetailService.insert(billingDetail);
        return SUCCESS_TIP;
    }

    /**
     * 删除账单明细
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer billingDetailId) {
        billingDetailService.deleteById(billingDetailId);
        return SUCCESS_TIP;
    }

    /**
     * 修改账单明细
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BillingDetail billingDetail) {
        billingDetailService.updateById(billingDetail);
        return SUCCESS_TIP;
    }

    /**
     * 账单明细详情
     */
    @RequestMapping(value = "/detail/{billingDetailId}")
    @ResponseBody
    public Object detail(@PathVariable("billingDetailId") Integer billingDetailId) {
        return billingDetailService.selectById(billingDetailId);
    }
}
