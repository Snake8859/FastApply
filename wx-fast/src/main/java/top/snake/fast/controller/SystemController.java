package top.snake.fast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import top.snake.fast.common.utils.WxResult;
import top.snake.fast.pojo.TAssociation;
import top.snake.fast.service.SystemService;

/**
 * 系统Controller
 * 主要负责:
 * 		1.审核协会管理员
 * @author snake8859
 *
 */
@Controller
@RequestMapping("/sys")
public class SystemController {

	//注入系统服务
	@Autowired
	private SystemService systemService;
	
	/**
	 * 系统管理员查询协会管理员列表
	 * @return
	 */
	@RequestMapping("getAssInfoList")
	@ResponseBody
	public WxResult getAssInfoList(){
		WxResult wxResult = systemService.queryAssInfoList();
		return wxResult;
	}
	
	/**
	 * 系统管理员审核通过协会管理员信息
	 * @return
	 */
	@RequestMapping(value = "passAss",method = RequestMethod.POST)
	@ResponseBody
	public WxResult passAss(TAssociation tAssociation){
		if(tAssociation.getAssid()!=null&&tAssociation.getCreatorid()!=null){//非空判断
			String assid = tAssociation.getAssid();
			String openid = tAssociation.getCreatorid();
			if(!assid.equals("")&&!openid.equals("")){
				//assid和openid不能为空
				WxResult wxResult = systemService.passAss(tAssociation);
				return wxResult;
			}
		}
		return WxResult.build(500, "协会id和openid为空");
	}
	
	/**
	 * 系统管理员审核不通过协会管理员信息
	 * @param assid
	 * @return
	 */
	@RequestMapping(value = "rejectAss",method = RequestMethod.POST)
	@ResponseBody
	public WxResult rejectAss(TAssociation tAssociation){
		
		if(tAssociation.getAssid()!=null&&tAssociation.getCreatorid()!=null){//非空判断
			String assid = tAssociation.getAssid();
			String openid = tAssociation.getCreatorid();
			if(!assid.equals("")&&!openid.equals("")){
				//assid和openid不能为空
				WxResult wxResult = systemService.rejectAss(tAssociation);
				return wxResult;
			}
		}
		return WxResult.build(500, "协会id和openid为空");
	}
}
