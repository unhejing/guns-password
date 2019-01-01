package com.stylefeng.guns.modular.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Lists;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.common.model.JSONResult;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.util.JwtTokenUtil;
import com.stylefeng.guns.modular.billingDetail.service.IBillingDetailService;
import com.stylefeng.guns.modular.billingDetail.vo.BillingDetailVo;
import com.stylefeng.guns.modular.password.service.IBizPasswordService;
import com.stylefeng.guns.modular.system.dao.BizPasswordMapper;
import com.stylefeng.guns.modular.system.dao.CustomerMapper;
import com.stylefeng.guns.modular.system.dao.UserMapper;
import com.stylefeng.guns.modular.system.model.BillingDetail;
import com.stylefeng.guns.modular.system.model.BizPassword;
import com.stylefeng.guns.modular.system.model.Customer;
import com.stylefeng.guns.modular.system.model.User;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 接口控制器提供
 *
 * @author stylefeng
 * @Date 2018/7/20 23:39
 */
@RestController
@RequestMapping("/gunsApi")
public class ApiController extends BaseController {

    @Autowired
    private IBizPasswordService bizPasswordService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private BizPasswordMapper passwordMapper;

    @Autowired
    private IBillingDetailService billingDetailService;

    /**
     * api登录接口，通过账号密码获取token
     */
    @ApiOperation("登陆接口")
    @PostMapping("/auth")
    public JSONResult auth(@RequestParam("username") String username,
                       @RequestParam("password") String password) {

        //封装请求账号密码为shiro可验证的token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password.toCharArray());

        //获取数据库中的账号密码，准备比对
        User user = userMapper.getByAccount(username);

        String credentials = user.getPassword();
        String salt = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(salt);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                new ShiroUser(), credentials, credentialsSalt, "");

        //校验用户账号密码
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        boolean passwordTrueFlag = md5CredentialsMatcher.doCredentialsMatch(
                usernamePasswordToken, simpleAuthenticationInfo);
        Map<String,String> data = new HashedMap();
        data.put("token",JwtTokenUtil.generateToken(String.valueOf(user.getId())));
        if (passwordTrueFlag) {
            return new JSONResult(JSONResult.CODE_SUCCESS,"登陆成功",data);
        } else {
            return new JSONResult(JSONResult.CODE_FAIL,"账号密码错误");
        }
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

    /**
     * 测试接口是否走鉴权
     */
    @RequestMapping(value = "/test", method = RequestMethod.POST)

    public Object test() {

        return SUCCESS_TIP;
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

    @ApiOperation("分页获取密码列表")
    @RequestMapping(value = "/findPasswordByPage", method = RequestMethod.POST)
    public Object findPasswordByPage(String userId) {
        System.out.println(userId);
        Object passwordList = bizPasswordService.selectList(null);
        return passwordList;
    }

    @ApiOperation("获取账单明细")
    @PostMapping("/getBillingDetail")
    public JSONResult getBillingDetail(BillingDetailVo vo) {
        JSONResult result = new JSONResult();
        if(vo == null){
            return new JSONResult(JSONResult.CODE_FAIL,"参数错误，vo为空！");
        }
        List<BillingDetail> billingDetailList = Lists.newArrayList();
        if(Objects.nonNull(vo.getUserId())){
            // 查询关于该用户的所有账单
            Wrapper<BillingDetail> wrapper = new EntityWrapper<>();
            wrapper = wrapper.eq("user_id", vo.getUserId());
            billingDetailList = billingDetailService.selectList(wrapper);
            result = new JSONResult(JSONResult.CODE_SUCCESS,"获取账单明细成功！",billingDetailList);
        }else{
            result =  new JSONResult(JSONResult.CODE_FAIL,"参数错误，userId为空！");
        }
        return result;

    }

    @ApiOperation("添加账单明细")
    @PostMapping("/addBillingDetail")
    public JSONResult addBillingDetail(BillingDetailVo vo) {
        JSONResult result;
        if(vo == null){
            return new JSONResult(JSONResult.CODE_FAIL,"参数错误，vo为空！");
        }
        BillingDetail billingDetail = new BillingDetail();
        BeanUtils.copyProperties(vo,billingDetail);
        billingDetailService.insert(billingDetail);
        result = new JSONResult(JSONResult.CODE_SUCCESS,"添加成功！");
        return result;

    }

}

