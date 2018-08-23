package com.stylefeng.guns.modular.api;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.model.JSONResult;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.password.service.IBizPasswordService;
import com.stylefeng.guns.modular.system.dao.CustomerMapper;
import com.stylefeng.guns.modular.system.model.Customer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jingyujie
 * @create 2018-08-19 下午11:47
 **/

@RestController
@RequestMapping("/password")
public class RestPasswordController extends BaseController {

    @Autowired
    private IBizPasswordService bizPasswordService;

    @Autowired
    private CustomerMapper customerMapper;

    @RequestMapping("/login")
    public JSONResult login(@RequestParam("username") String username,
                            @RequestParam("password") String password) {

        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return new JSONResult(JSONResult.CODE_FAIL,"用户名或密码为空！");
        }
        JSONResult result = new JSONResult();
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


    @RequestMapping(value = "/findPasswordByPage", method = RequestMethod.POST)
    public Object findPasswordByPage(String userId) {
        System.out.println(userId);
        Object passwordList = bizPasswordService.selectList(null);
        return passwordList;
    }
}
