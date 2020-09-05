package top.snake.fast.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import top.snake.fast.common.utils.WxResult;
import top.snake.fast.service.MemberService;

/**
 * 会员Controller
 * @author snake8859
 *
 */
@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	/**
	 * 普通用户协会报名
	 * @param tempData
	 * @return
	 */
	@RequestMapping("applyAss")
	@ResponseBody
	public WxResult applyAss(String tempData,String openid,String assid){
		if((!tempData.equals("")&&tempData!=null)&&(!openid.equals("")&&openid!=null)
			&&(!assid.equals("")&&assid!=null)){
			//使用Gson,将json转成gson
			Map<String, String> map = new HashMap<String,String>();
			Gson gson = new Gson();
			Map jsonMap = gson.fromJson(tempData, map.getClass());
			//处理json,将姓名，性别，联系方式提取
			String name = (String) jsonMap.remove("name");
			String tel = (String) jsonMap.remove("tel");
			String gender = (String) jsonMap.remove("gender");
			//再次利用Gson，将提取后的数据转为json
			String jsonData = gson.toJson(jsonMap);
			//会员信息保存
			WxResult wxResult = memberService.saveMemberInfo(name,tel,gender,openid,assid,jsonData);
			return wxResult;
		}
		return WxResult.build(500, "模板数据，openid，协会id获取失败");
	}
	
	/**
	 * 根据openid获取已填写报名表的协会信息
	 * @param openid
	 * @return
	 */
	@RequestMapping("getApplyedAssInfo")
	@ResponseBody
	public WxResult queryApplyedAssInfoByOpenId(String openid){
		
		if(!openid.equals("")&&openid!=null){
			WxResult wxResult =  memberService.queryApplyedAssInfoByOpenId(openid);
			return wxResult;
		}
		return WxResult.build(500, "获取openid失败");
	}
	
	
	/**
	 * 根据assid和openid查询报名表信息
	 */
	@RequestMapping("getApplyFormData")
	@ResponseBody
	public WxResult queryApplyFormDataByOpenIdAndAssId(String openid,String assid){
		
		if((!openid.equals("")&&openid!=null)&&(!assid.equals("")&&assid!=null)){
			WxResult wxResult = memberService.queryApplyFormDataByOpenIdAndAssId(openid,assid);
			return wxResult;
		}
		
		return WxResult.build(500, "获取openid和协会id失败");
	}
	
}
