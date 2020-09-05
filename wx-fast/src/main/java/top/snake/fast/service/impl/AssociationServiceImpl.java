package top.snake.fast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.snake.fast.common.utils.UuidUtil;
import top.snake.fast.common.utils.WxResult;
import top.snake.fast.mapper.TAssociationMapper;
import top.snake.fast.mapper.TMemberMapper;
import top.snake.fast.mapper.TUserMapper;
import top.snake.fast.pojo.TAssociation;
import top.snake.fast.pojo.TAssociationExample;
import top.snake.fast.pojo.TMember;
import top.snake.fast.pojo.TUser;
import top.snake.fast.service.AssociationService;
/**
 * 协会服务实现类
 * @author snake8859
 *
 */
@Service
public class AssociationServiceImpl implements AssociationService {

	//协会表mapper
	@Autowired
	private TAssociationMapper tAssociationMapper;
	
	//用户表mapper
	@Autowired
	private TUserMapper tUserMapper;
	
	//会员表mapper
	@Autowired
	private TMemberMapper tMemberMapper;
	
	@Override
	public WxResult saveAssInfo(TAssociation tAssociation) {
		//设置协会id
		tAssociation.setAssid(UuidUtil.get32UUID());
		//设置协会管理员审核状态	 0:待审核
		tAssociation.setAuditstatus("0");
		//保存协会基本信息
		int i = tAssociationMapper.insertSelective(tAssociation);
		if(i>0){
			return WxResult.build(200, "协会管理员申请成功");
		}
		return WxResult.build(501, "协会管理员申请失败");
	}

	@Override
	public WxResult queryAssListByOpenId(String openid) {
		//设置查询条件
		TAssociationExample example = new TAssociationExample();
		example.createCriteria().andCreatoridEqualTo(openid);
		List<TAssociation> list = tAssociationMapper.selectByExample(example);
		if(list.size()>0){//若有结果
			return WxResult.build(200, "协会管理员审核情况查询成功", list);
		}
		//若查询无结果
		return WxResult.build(501, "未查询到审核信息","notFind");
	}

	@Override
	public WxResult queryAssListByAssid(String assid) {
		//根据assid查询协会信息
		TAssociation tAssociation = tAssociationMapper.selectByPrimaryKey(assid);
		if(tAssociation!=null){//若有结果
			return WxResult.build(200, "协会基本信息查询成功", tAssociation);
		}
		return WxResult.build(501, "未查询到协会基本信息");
	}

	@Override
	public WxResult updateAssInfo(TAssociation tAssociation) {
		//根据assid更新协会信息
		int i = tAssociationMapper.updateByPrimaryKeySelective(tAssociation);
		if(i>0){
			return WxResult.build(200, "协会基本信息更新成功");
		}
		return WxResult.build(501, "协会基本信息更新失败");
	}

	@Override
	public WxResult queryAssListByOendIdAndAuditstatus(String openid) {
		//根据openid和审核状态为通过条件查询协会列表
		TAssociationExample example = new TAssociationExample();
		example.createCriteria().andCreatoridEqualTo(openid).andAuditstatusEqualTo("2");
		List<TAssociation> list = tAssociationMapper.selectByExampleWithBLOBs(example);
		if(list.size()>0){
			return WxResult.build(200, "协会基本信息查询成", list);
		}
		return WxResult.build(501, "未查询到协会基本信息");
	}

	@Override
	public WxResult saveTemplate(TAssociation tAssociation) {
		//根据主键更新自定义模板
		int i = tAssociationMapper.updateByPrimaryKeySelective(tAssociation);
		if(i>0){
			return WxResult.build(200, "协会自定义模板保存成功");
		}
		return WxResult.build(501, "协会自定义模板保存失败");
	}

	
	
	
}
