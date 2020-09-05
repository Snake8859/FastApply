package top.snake.fast.service;

import top.snake.fast.common.utils.WxResult;
import top.snake.fast.pojo.TAssociation;

/**
 * 系统Service
 * @author snake8859
 *
 */
public interface SystemService {

	/**
	 * 系统管理员获取协会基本信息列表
	 * @return
	 */
	public WxResult queryAssInfoList();

	/**
	 * 协会管理员通过审核
	 * @param tAssociation
	 * @return
	 */
	public WxResult passAss(TAssociation tAssociation);

	/**
	 * 协会管理员未通过审核
	 * @param tAssociation
	 * @return
	 */
	public WxResult rejectAss(TAssociation tAssociation);

}
