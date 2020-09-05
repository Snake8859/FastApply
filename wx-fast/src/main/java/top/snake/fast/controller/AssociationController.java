package top.snake.fast.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.gson.Gson;

import top.snake.fast.common.utils.JsonUtils;
import top.snake.fast.common.utils.WxResult;
import top.snake.fast.pojo.TAssociation;
import top.snake.fast.service.AssociationService;

/**
 * 协会Controller
 * 主要负责：
 * 		1.保存自定义模板
 * 		2.查看协会报名情况
 * 		3.导出协会报名表
 * @author snake8859
 *
 */
@Controller
@RequestMapping("/ass")
public class AssociationController {
	
	//注入协会服务
	@Autowired
	private AssociationService associationService;
	
	
	/**
	 * 申请成为协会管理员
	 * @return
	 */
	@RequestMapping(value="applyAssAdmin",method= RequestMethod.POST)
	@ResponseBody
	public WxResult applyAssAdmin(TAssociation tAssociation){
		if(tAssociation!=null){//判断协会信息是否为空
			WxResult wxResult = associationService.saveAssInfo(tAssociation);
			return wxResult;
		}
		return WxResult.build(500, "获取协会信息失败");
	}
	
	/**
	 * 修改协会管理员信息
	 * @param tAssociation
	 * @return
	 */
	@RequestMapping(value="editAssAdmin",method=RequestMethod.POST)
	@ResponseBody
	public WxResult editAssAdmin(TAssociation tAssociation){
		if(tAssociation!=null){
			WxResult wxResult = associationService.updateAssInfo(tAssociation);
			return wxResult;
		}
 		return WxResult.build(500, "获取协会更新基本信息失败");
	}
	
	/**
	 * 根据openid获取协会基本信息和审核情况
	 * @param openid
	 * @return
	 */
	@RequestMapping("getAssAuditStatus")
	@ResponseBody
	public WxResult getAssList(String openid){
		if(!openid.equals("")&&openid!=null){//openid不为空
			WxResult wxResult = associationService.queryAssListByOpenId(openid);
			return wxResult;
		}
		return WxResult.build(500, "获取openid失败");
	}
	
	/**
	 * 根据openid和协会审核状态获取协会列表
	 * @param openid
	 * @return
	 */
	@RequestMapping("getPassedAssList")
	@ResponseBody
	public WxResult getPassedAssList(String openid){
		if(!openid.equals("")&&openid!=null){
			WxResult wxResult = associationService.queryAssListByOendIdAndAuditstatus(openid);
			return wxResult;
		}
		return WxResult.build(500, "获取openid失败");
	}
	
	/**
	 * 根据协会id获取协会列表
	 * @param assid
	 * @return
	 */
	@RequestMapping("getAssInfo")
	@ResponseBody
	public WxResult getAssInfo(String assid){
		if(!assid.equals("")&&assid!=null){//assid不为空
			WxResult wxResult = associationService.queryAssListByAssid(assid);
			return wxResult;
		}
		return WxResult.build(500, "获取assid失败");
	}
	
	/**
	 * 保存报名表模板
	 * @param tAssociation
	 * @return
	 */
	@RequestMapping("createTemplate")
	@ResponseBody
	public WxResult saveTemplate(TAssociation tAssociation){
		if(tAssociation!=null){
			WxResult wxResult = associationService.saveTemplate(tAssociation);
			return wxResult;
		}
		return WxResult.build(500, "模板信息获取失败");
	}
	
	
	
	
}
