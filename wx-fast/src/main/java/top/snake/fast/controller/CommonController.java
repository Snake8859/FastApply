package top.snake.fast.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import top.snake.fast.common.utils.GetAccessTokenUtil;
import top.snake.fast.common.utils.GetQRCodeUtil;
import top.snake.fast.common.utils.JsonUtils;
import top.snake.fast.common.utils.UuidUtil;
import top.snake.fast.common.utils.WxResult;
import top.snake.fast.pojo.CustomInfo;
import top.snake.fast.pojo.QRInfo;
import top.snake.fast.pojo.TAssociation;
import top.snake.fast.service.AssociationService;

/**
 * 公共Controller 主要负责公共服务
 * 
 * @author snake8859
 *
 */
@Controller
public class CommonController {

	@Autowired
	private AssociationService associationService;
	
	//从配置文件中获取APPID
	@Value("${APPID}")
	private String APPID;

	//从配置文件中获取APPSECRET
	@Value("${APPSECRET}")
	private String APPSECRET;

	//从配置文件中获取HHTP_PIRCTURE
	@Value("${HHTP_PIRCTURE}")
	private String HHTP_PIRCTURE;

	//从配置文件中获取REALPATH_LOCAL
	@Value("${REALPATH_LOCAL}")
	private String REALPATH;

	/**
	 * 图片上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/common/uploadPic")
	@ResponseBody
	public WxResult uploadPic(HttpServletRequest request, HttpServletResponse response) {
		// 图片上传
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = req.getFile("logoPic");

		// 获得原始图片名称
		String originalFilename = multipartFile.getOriginalFilename();
		// 获得后缀名
		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
		// uuid生成图片新的名称
		String picName = UuidUtil.get32UUID() + suffix;
		// 保存路径 windows
		// String realPath = "E:/WeChat笔记/校外小程序项目/images";
		// 保存路径 liunx
		try {
			multipartFile.transferTo(new File(REALPATH, picName));
			return WxResult.build(200, "图片保存成功", HHTP_PIRCTURE + picName);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return WxResult.build(404, "图片保存失败");
		}
	}

	@RequestMapping("/common/createQR")
	@ResponseBody
	public WxResult createQR(String assid,String path, int width) {
		// 获得接口凭证
		String accessToken = GetAccessTokenUtil.getAccessToken(APPID, APPSECRET);
		CustomInfo customInfo = JsonUtils.jsonToPojo(accessToken, CustomInfo.class);
		String access_token = customInfo.getAccess_token();
		// 获取指定页面的二维码
		String qrName = GetQRCodeUtil.getQRCode(access_token, path, width, REALPATH);
		//二维码类
		QRInfo qrInfo = new QRInfo();
		//设置二维码路径
		qrInfo.setQRPath(HHTP_PIRCTURE+qrName);
		//根据assid查询协会
		WxResult assWxResult = associationService.queryAssListByAssid(assid);
		if(assWxResult.getStatus()==200){
		TAssociation tAssociation = (TAssociation) assWxResult.getData();
		//设置协会名称
		qrInfo.setAssName(tAssociation.getAssname());
		//设置协会id
		qrInfo.setLogo(tAssociation.getLogo());
		return WxResult.build(200, "二维码创建成功", qrInfo);
		}
		return WxResult.build(500, "二维码创建失败");
	}

}
