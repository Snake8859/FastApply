package top.snake.fast.service;

import top.snake.fast.common.utils.WxResult;

/**
 * 会员Service
 * @author snake8859
 *
 */
public interface MemberService {

	
	/**
	 * 保存会员信息
	 * @param name
	 * @param tel
	 * @param gender
	 * @param openid
	 * @param assid
	 * @param jsonData
	 * @return
	 */
	public WxResult saveMemberInfo(String name, String tel, String gender, String openid, String assid, String jsonData);

	/**
	 * 根据openid查询已填写的报名表
	 * @param openid
	 * @return
	 */
	public WxResult queryApplyedAssInfoByOpenId(String openid);

	/**
	 * 根据openid和协会id查询报名表信息
	 * @param openid
	 * @param assid
	 * @return
	 */
	public WxResult queryApplyFormDataByOpenIdAndAssId(String openid, String assid);

	
	
	
}
