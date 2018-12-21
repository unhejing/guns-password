package com.stylefeng.guns.modular.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.model.JSONResult;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.password.service.IBizPasswordService;
import com.stylefeng.guns.modular.system.dao.BizPasswordMapper;
import com.stylefeng.guns.modular.system.dao.CustomerMapper;
import com.stylefeng.guns.modular.system.model.BizPassword;
import com.stylefeng.guns.modular.system.model.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author jingyujie
 * @create 2018-08-19 下午11:47
 **/
@Api(description = "用户APP接口")
@RestController
@RequestMapping("/restPassword")
public class RestPasswordController extends BaseController {

    @Autowired
    private IBizPasswordService bizPasswordService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private BizPasswordMapper passwordMapper;

    @ApiOperation("APP登陆")
    @PostMapping("/login")
    public JSONResult login(@RequestParam("username") String username,
                            @RequestParam("password") String password) {

        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return new JSONResult(JSONResult.CODE_FAIL,"用户名或密码为空！");
        }
        JSONResult result;
        //获取数据库中的账号密码，准备比对
        Customer customer = customerMapper.getByAccount(username);
        if(customer == null){
            return new JSONResult(JSONResult.CODE_FAIL,"该账号不存在！");
        }

        String password1 = customer.getPassword();
        if(!password1.equals(ShiroKit.md5(password, "unhejing"))) {
            return new JSONResult(JSONResult.CODE_FAIL,"密码不正确！");
        }
        customer.setPassword("");
        result = new JSONResult(JSONResult.CODE_SUCCESS,"登陆成功",customer);

        //校验用户账号密码
        return result;

    }

    @ApiOperation("APP注册")
    @PostMapping("/register")
    public JSONResult register(@RequestParam("username") String username,
                            @RequestParam("password") String password) {

        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return new JSONResult(JSONResult.CODE_FAIL,"用户名或密码为空！");
        }
        JSONResult result;
        //获取数据库中的账号密码，准备比对
        Customer customer = customerMapper.getByAccount(username);
        if(customer != null){
            return new JSONResult(JSONResult.CODE_FAIL,"该账号已存在！");
        }
        customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(ShiroKit.md5(password, "unhejing"));
        customer.setCreateTime(new Date());
        customerMapper.insert(customer);
        customer.setPassword("");
        result = new JSONResult(JSONResult.CODE_SUCCESS,"注册成功",customer);

        //校验用户账号密码
        return result;

    }

    @ApiOperation("获取密码列表")
    @PostMapping("getPasswordList")
    public JSONResult getPasswordList(@RequestParam("userId") Integer userId){
        JSONResult result = new JSONResult();
        if(Objects.isNull(userId)){
            return new JSONResult(JSONResult.CODE_FAIL,"用户ueserId为空！");
        }
        // 通过usrId查询出password列表
        Wrapper<BizPassword> wrapper = new EntityWrapper<>();
        wrapper = wrapper.eq("user_id", userId);
        List<BizPassword> passwordList = passwordMapper.selectList(wrapper);
        result = new JSONResult(JSONResult.CODE_SUCCESS,"获取密码列表成功",passwordList);
        return result;
    }

    @ApiOperation("添加密码")
    @PostMapping("addPassword")
    public JSONResult addPassword(@RequestParam("userId") Integer userId,@RequestParam("username") String username,@RequestParam("password") String password){
        JSONResult result;
        if(Objects.isNull(userId)){
            return new JSONResult(JSONResult.CODE_FAIL,"用户ueserId为空！");
        }
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return new JSONResult(JSONResult.CODE_FAIL,"用户名或密码为空！");
        }
        // 通过usrId查询出password列表
        BizPassword bizPassword = new BizPassword();
        bizPassword.setUsername(username);
        bizPassword.setPassword(password);
        bizPassword.setUserId(userId);
        bizPassword.setCreateTime(new Date());

        passwordMapper.insert(bizPassword);
        result = new JSONResult(JSONResult.CODE_SUCCESS,"添加成功！",bizPassword);
        return result;
    }


    @RequestMapping(value = "/findPasswordByPage", method = RequestMethod.POST)
    public Object findPasswordByPage(String userId) {
        System.out.println(userId);
        Object passwordList = bizPasswordService.selectList(null);
        return passwordList;
    }
}
