package com.stylefeng.guns.modular.api;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.consumptionType.service.IConsumptionTypeService;
import com.stylefeng.guns.modular.system.model.ConsumptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 账单类型控制器
 *
 * @author fengshuonan
 * @Date 2018-12-21 11:06:50
 */
@Controller
@RequestMapping("/consumptionType")
public class RestConsumptionTypeController extends BaseController {

    private String PREFIX = "/consumptionType/consumptionType/";

    @Autowired
    private IConsumptionTypeService consumptionTypeService;

    /**
     * 跳转到账单类型首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "consumptionType.html";
    }

    /**
     * 跳转到添加账单类型
     */
    @RequestMapping("/consumptionType_add")
    public String consumptionTypeAdd() {
        return PREFIX + "consumptionType_add.html";
    }

    /**
     * 跳转到修改账单类型
     */
    @RequestMapping("/consumptionType_update/{consumptionTypeId}")
    public String consumptionTypeUpdate(@PathVariable Integer consumptionTypeId, Model model) {
        ConsumptionType consumptionType = consumptionTypeService.selectById(consumptionTypeId);
        model.addAttribute("item",consumptionType);
        LogObjectHolder.me().set(consumptionType);
        return PREFIX + "consumptionType_edit.html";
    }

    /**
     * 获取账单类型列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return consumptionTypeService.selectList(null);
    }

    /**
     * 新增账单类型
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ConsumptionType consumptionType) {
        consumptionTypeService.insert(consumptionType);
        return SUCCESS_TIP;
    }

    /**
     * 删除账单类型
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer consumptionTypeId) {
        consumptionTypeService.deleteById(consumptionTypeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改账单类型
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ConsumptionType consumptionType) {
        consumptionTypeService.updateById(consumptionType);
        return SUCCESS_TIP;
    }

    /**
     * 账单类型详情
     */
    @RequestMapping(value = "/detail/{consumptionTypeId}")
    @ResponseBody
    public Object detail(@PathVariable("consumptionTypeId") Integer consumptionTypeId) {
        return consumptionTypeService.selectById(consumptionTypeId);
    }
}
