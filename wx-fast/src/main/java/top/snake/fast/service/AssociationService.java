package top.snake.fast.service;

import top.snake.fast.common.utils.WxResult;
import top.snake.fast.pojo.TAssociation;

/**
 * 协会Service
 * 主要负责协会相关服务
 * @author snake8859
 *
 */
public interface AssociationService {

	/**
	 * 保存协会基本信息
	 * @param tAssociation
	 * @return
	 */
	public WxResult saveAssInfo(TAssociation tAssociation);
	
	/**
	 * 更新协会基本情况
	 * @param tAssociation
	 * @return
	 */
	public WxResult updateAssInfo(TAssociation tAssociation);
	
	
	/**
	 * 根据openid查询协会列表信息
	 * @param openid
	 * @return
	 */
	public WxResult queryAssListByOpenId(String openid);
	
	/**
	 * 根据openid和协会审核状态查询协会列表信息
	 * @param openid
	 * @return
	 */
	public WxResult queryAssListByOendIdAndAuditstatus(String openid);
	
	/**
	 * 根据assid查看协会列表信息
	 * @param assid
	 * @return
	 */
	public WxResult queryAssListByAssid(String assid);

	/**
	 * 协会自定义模板保存
	 * @param tAssociation
	 * @return
	 */
	public WxResult saveTemplate(TAssociation tAssociation);

	
}
