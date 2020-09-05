package top.snake.fast.service;

import org.springframework.stereotype.Service;

import top.snake.fast.common.utils.WxResult;
import top.snake.fast.pojo.TUser;

/**
 * 用户Service
 * 主要负责用户相关服务
 * @author snake8859
 *
 */
public interface UserService {

	/**
	 * 初始化用户
	 * @param openid
	 * @return
	 */
	public WxResult initUser(String openid);
	
	/**
	 * 根据openid查询用户
	 */
	public TUser findUserByOpenId(String openid);
	
	/**
	 * 根据openid更新用户微信信息
	 * @param user
	 * @return
	 */
	public WxResult updateUserWxInfoByOpenId(TUser user);

	/**
	 * 根据openid查看用户权限
	 * @param openid
	 * @return
	 */
	public WxResult checkPermission(String openid);
	
	
}
