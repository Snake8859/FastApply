package top.snake.fast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.snake.fast.common.utils.UuidUtil;
import top.snake.fast.common.utils.WxResult;
import top.snake.fast.mapper.TAssociationMapper;
import top.snake.fast.mapper.TMemberMapper;
import top.snake.fast.pojo.TAssociation;
import top.snake.fast.pojo.TAssociationExample;
import top.snake.fast.pojo.TMember;
import top.snake.fast.pojo.TMemberExample;
import top.snake.fast.service.SystemService;
/**
 * 系统服务实现类
 * @author snake8859
 *
 */
@Service
public class SystemServiceImpl implements SystemService {

	//协会表mapper
	@Autowired
	private TAssociationMapper tAssociationMapper;
	
	//会员表mapper
	@Autowired
	private TMemberMapper tMemberMapper;
	
	@Override
	public WxResult queryAssInfoList() {
		//查询全部
		TAssociationExample example = new TAssociationExample();
		List<TAssociation> list = tAssociationMapper.selectByExample(example);
		if(list.size()>0){
			return WxResult.build(200, "协会基本信息列表查询成功", list);
		}
		return WxResult.build(500,"未查询到协会基本信息列表");
	}

	@Override
	public WxResult passAss(TAssociation tAssociation) {
		String openid = tAssociation.getCreatorid();
		String assid = tAssociation.getAssid();
		//改变审核状态为审核通过
		tAssociation.setAuditstatus("2");
		int i = tAssociationMapper.updateByPrimaryKeySelective(tAssociation);
		//判断是初次审核通过或再次审核通过
		TMemberExample example = new TMemberExample();
		//通过协会id和openid查询
		example.createCriteria().andAssidEqualTo(assid).andOpenidEqualTo(openid);
		List<TMember> list = tMemberMapper.selectByExample(example);
		int j = 0;
		if(list.size()>0){
			//再次审核通过
			TMember tMember = list.get(0);
			tMember.setIsmember("3");
			tMember.setIsasshead("3");
			j = tMemberMapper.updateByPrimaryKey(tMember);
		}else{
			//初次审核通过
			//协会管理员默认成为会员，创建一个会员对象
			TMember tMember = new TMember();
			//设置会员id
			tMember.setAid(UuidUtil.get32UUID());
			//默认成为协会会员
			tMember.setIsmember("3");
			//默认成为协会管理员
			tMember.setIsasshead("3");
			//关联协会id
			tMember.setAssid(assid);
			//关联用户id
			tMember.setOpenid(openid);
			//插入会员表
			j = tMemberMapper.insertSelective(tMember);
		}
		//修改成功
		if(i>0&&j>0){
			return WxResult.build(200, "系统管理员审核通过成功");
		}
		return WxResult.build(501, "系统管理员审核通过失败");
	}

	@Override
	public WxResult rejectAss(TAssociation tAssociation) {
		String openid = tAssociation.getCreatorid();
		String assid = tAssociation.getAssid();
		tAssociation.setAuditstatus("1");
		//暂时写死，后期引入
		tAssociation.setReason("资料不合格，拒绝");
		int i = tAssociationMapper.updateByPrimaryKeySelective(tAssociation);
		//判断是初次审核拒绝或再次审核拒绝
		TMemberExample example = new TMemberExample();
		//根据openid和协会id查询
		example.createCriteria().andAssidEqualTo(assid).andOpenidEqualTo(openid);
		List<TMember> list = tMemberMapper.selectByExample(example);
		int j = 0;
		if(list.size()>0){
			//若不为第一次拒绝
			TMember tMember = list.get(0);
			tMember.setIsasshead("2");
			j = tMemberMapper.updateByPrimaryKeySelective(tMember);
		}else{
			//若为第一次拒绝
			j = 1;
		}
		if(i>0&&j>0){
			return WxResult.build(200, "系统管理员审核未通过");
		}
		return WxResult.build(501, "系统管理员审核未通过失败");
	}

}
