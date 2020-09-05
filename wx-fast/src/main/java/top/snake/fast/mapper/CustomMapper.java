package top.snake.fast.mapper;

import java.util.List;

import top.snake.fast.pojo.TAssociation;

/**
 * 自定义查询Mapper
 * @author snake8859
 *
 */
public interface CustomMapper {

	/**
	 * 根据openid获取协会信息
	 * @param openid
	 * @return
	 */
	public List<TAssociation> queryApplyedAssInfoByAssIds(List<String> assids);
	
	public List<String> queryAssIdByOpenId(String openid);
}
