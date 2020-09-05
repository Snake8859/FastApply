package top.snake.fast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import top.snake.fast.common.utils.GetOpenIdUtil;
import top.snake.fast.common.utils.JsonUtils;
import top.snake.fast.common.utils.WxResult;
import top.snake.fast.pojo.CustomInfo;
import top.snake.fast.pojo.TUser;
import top.snake.fast.service.UserService;

/**
 * 用户Controller
 * 主要负责：
 *  	1.小程序初始化登录
 *  	2.申请协会管理员
 *  	3.查看已填写的报名表
 *  		3.1查看个人报名列表
 *  		3.2查看个人报名情况详情
 * @author snake8859
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	//从配置文件中获取APPID
	@Value("${APPID}")
	private String APPID;
	
	//从配置文件中获取APPSECRET
	@Value("${APPSECRET}")
	private String APPSECRET;
	
	//注入用户服务Service
	@Autowired
	private UserService userService;
	
	/**
	 * 微信登录 - 初始化用户
	 * @param code
	 * @return
	 */
	@RequestMapping("initUser")
	@ResponseBody
	public WxResult wxInitLogin(String code){
		//根据Appid和AppSecret获得openid和session_key
		String msg = GetOpenIdUtil.getopenid(APPID, code, APPSECRET);
		//json转为自定义信息类
		CustomInfo customInfo = JsonUtils.jsonToPojo(msg, CustomInfo.class);
		//System.out.println(msg);
		
		//判断是否获取成功
		if(!customInfo.getOpenid().equals("")){ //获取成功
			WxResult wxResult = userService.initUser(customInfo.getOpenid());
			return wxResult;
		}
		//获取失败
		return WxResult.build(500, "获取openid失败");
	}
	
	/**
	 * 微信登录 - 更新用户微信基本信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="updateUserWxInfo",method= RequestMethod.POST)
	@ResponseBody
	public WxResult wxUpdateLogin(TUser user){
		//判断用户是否为空
		if(user!=null){
			WxResult wxResult = userService.updateUserWxInfoByOpenId(user);
			return wxResult;
		}
		return WxResult.build(500, "用户微信基本信息获取失败");
	}
	
	/**
	 * 根据openid查看用户权限
	 * @param openid
	 * @return
	 */
	@RequestMapping(value="checkPermissons",method= RequestMethod.POST)
	@ResponseBody
	public WxResult checkPermissions(String openid){
		if(!openid.equals("")&&openid!=null){//若openid不为空
			WxResult wxResult =  userService.checkPermission(openid);
			return wxResult;
		}
		return WxResult.build(500, "获取openid失败");
	}

	/**
	 * 根据openid查询用户信息
	 * @param openid
	 * @return
	 */
	@RequestMapping("getUserInfo")
	@ResponseBody
	public WxResult queryUserInfoByOpenId(String openid){
		if(!openid.equals("")&&openid!=null){
			TUser user = userService.findUserByOpenId(openid);
			return WxResult.build(200, "用户信息查询成功", user);
		}
		return WxResult.build(500, "openid获取失败");
	}

	
}
