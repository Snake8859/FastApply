package top.snake.fast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.snake.fast.common.utils.UuidUtil;
import top.snake.fast.common.utils.WxResult;
import top.snake.fast.mapper.CustomMapper;
import top.snake.fast.mapper.TMemberMapper;
import top.snake.fast.mapper.TUserMapper;
import top.snake.fast.pojo.TAssociation;
import top.snake.fast.pojo.TMember;
import top.snake.fast.pojo.TMemberExample;
import top.snake.fast.pojo.TUser;
import top.snake.fast.service.MemberService;

/**
 * 会员Service实现类
 * @author snake8859
 *
 */
@Service
public class MemberServiceImpl implements MemberService {

	//注入用户表mapper
	@Autowired
	private TUserMapper tUserMapper;
	
	//注入会员表mapper
	@Autowired
	private TMemberMapper tMemberMapper;
	
	//注入自定义表mapper
	@Autowired
	private CustomMapper customMapper;
	
	@Override
	public WxResult saveMemberInfo(String name, String tel, String gender, String openid, String assid,
			String jsonData) {
				//根据openid查询用户
				TUser user = tUserMapper.selectByPrimaryKey(openid);
				int i = 0;
				if(user!=null){//存在用户
					if(user.getName()==null){//未有真实姓名
						user.setName(name);
					}
					if(user.getTelephone()==null){//未有联系方式
						user.setTelephone(tel);
					}
					user.setGender(gender);
					//更新用户
					i = tUserMapper.updateByPrimaryKeySelective(user);
				}
				//创建会员对象
				TMember member = new TMember();
				member.setAid(UuidUtil.get32UUID());
				member.setAssid(assid);
				member.setOpenid(openid);
				member.setTemplatedata(jsonData);
				//修改状态
				member.setIsmember("1");
				int j = tMemberMapper.insertSelective(member);
				
				if(i>0&&j>0){
					return WxResult.build(200, "用户报名成功");
				}
				return WxResult.build(501, "用户报名失败");
	}

	@Override
	public WxResult queryApplyedAssInfoByOpenId(String openid) {
		//根据openid查询协会id列表
		List<String> assids = customMapper.queryAssIdByOpenId(openid);
		//根据协会id列表查询协会
		List<TAssociation> list = customMapper.queryApplyedAssInfoByAssIds(assids);
		if(list.size()>0){//查询到数据
			return WxResult.build(200, "成功获取已填写协会", list);
		}
		return WxResult.build(501, "未查询到任何报名表数据");
	}

	@Override
	public WxResult queryApplyFormDataByOpenIdAndAssId(String openid, String assid) {
		//设置条件
		TMemberExample example = new TMemberExample();
		example.createCriteria().andOpenidEqualTo(openid).andAssidEqualTo(assid);
		List<TMember> list = tMemberMapper.selectByExampleWithBLOBs(example);
		if(list.size()>0){
			TMember tMember = list.get(0);
			return WxResult.build(200, "成功获取报名表信息", tMember);
		}
		return WxResult.build(501, "未有任何报名表信息");
	}

}
