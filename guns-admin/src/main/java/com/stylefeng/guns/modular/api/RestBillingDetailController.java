//package com.stylefeng.guns.modular.api;
//
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.baomidou.mybatisplus.mapper.Wrapper;
//import com.google.common.collect.Lists;
//import com.stylefeng.guns.core.base.controller.BaseController;
//import com.stylefeng.guns.core.common.model.JSONResult;
//import com.stylefeng.guns.core.log.LogObjectHolder;
//import com.stylefeng.guns.core.shiro.ShiroKit;
//import com.stylefeng.guns.modular.billingDetail.service.IBillingDetailService;
//import com.stylefeng.guns.modular.billingDetail.vo.BillingDetailVo;
//import com.stylefeng.guns.modular.system.model.BillingDetail;
//import com.stylefeng.guns.modular.system.model.Customer;
//import com.stylefeng.guns.modular.system.model.Dept;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//
///**
// * 账单明细控制器
// *
// * @author fengshuonan
// * @Date 2018-12-21 11:01:19
// */
//@Api(description = "账单明细接口")
//@RestController
//@RequestMapping("/restBillingDetail")
//public class RestBillingDetailController extends BaseController {
//
//    @Autowired
//    private IBillingDetailService billingDetailService;
//
//    @ApiOperation("获取账单明细")
//    @PostMapping("/getBillingDetail")
//    public JSONResult getBillingDetail(BillingDetailVo vo) {
//        JSONResult result = new JSONResult();
//        if(vo == null){
//            return new JSONResult(JSONResult.CODE_FAIL,"参数错误，vo为空！");
//        }
//        List<BillingDetail> billingDetailList = Lists.newArrayList();
//        if(Objects.nonNull(vo.getUserId())){
//            // 查询关于该用户的所有账单
//            Wrapper<BillingDetail> wrapper = new EntityWrapper<>();
//            wrapper = wrapper.eq("user_id", vo.getUserId());
//            billingDetailList = billingDetailService.selectList(wrapper);
//            result = new JSONResult(JSONResult.CODE_SUCCESS,"获取账单明细成功！",billingDetailList);
//        }else{
//            result =  new JSONResult(JSONResult.CODE_FAIL,"参数错误，userId为空！");
//        }
//        return result;
//
//    }
//
//    @ApiOperation("添加账单明细")
//    @PostMapping("/addBillingDetail")
//    public JSONResult addBillingDetail(BillingDetailVo vo) {
//        JSONResult result;
//        if(vo == null){
//            return new JSONResult(JSONResult.CODE_FAIL,"参数错误，vo为空！");
//        }
//        BillingDetail billingDetail = new BillingDetail();
//        BeanUtils.copyProperties(vo,billingDetail);
//        billingDetailService.insert(billingDetail);
//        result = new JSONResult(JSONResult.CODE_SUCCESS,"添加成功！");
//        return result;
//
//    }
//
//}
