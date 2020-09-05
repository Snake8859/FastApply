package top.snake.fast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.tools.internal.ws.wsdl.document.http.HTTPAddress;

import top.snake.fast.common.utils.GetAccessTokenUtil;
import top.snake.fast.common.utils.GetQRCodeUtil;
import top.snake.fast.common.utils.JsonUtils;
import top.snake.fast.pojo.CustomInfo;
import top.snake.fast.service.UserService;

/**
 * 测试Controller
 * @author snake8859
 *
 */
@Controller
public class TestController {

	@Autowired
	private UserService userService;
	
	@Value("${HHTP_PIRCTURE}")
	private String HHTP_PIRCTURE;
	
	@Value("${REALPATH_LOCAL}")
	private String REALPATH;
	
	/**
	 * 测试环境搭建
	 * @return
	 */
	@RequestMapping("/test")
	@ResponseBody
	public String testInitProject(String tempData){
		//userService.initUser("123");
		//获得调用接口凭证
		//CustomInfo customInfo = JsonUtils.jsonToPojo(accessToken, CustomInfo.class);
		//String access_token = customInfo.getAccess_token();
		//String fileName = GetQRCodeUtil.getQRCode(access_token, "pages/HelpPage/HelpPage", 430,REALPATH);
		System.out.println(tempData);
		return "success";
	}
	
	
	
}
