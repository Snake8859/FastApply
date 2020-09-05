package top.snake.fast.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import top.snake.fast.common.utils.DateUtil;
import top.snake.fast.common.utils.WxResult;
import top.snake.fast.mapper.TMemberMapper;
import top.snake.fast.mapper.TUserMapper;
import top.snake.fast.pojo.TMember;
import top.snake.fast.pojo.TMemberExample;
import top.snake.fast.pojo.TUser;
import top.snake.fast.service.UserService;
/**
 * 用户服务实现类
 * @author snake8859
 *
 */
@Service
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class UserServiceImpl implements UserService {

	//用户表mapper
	@Autowired
	private TUserMapper tUserMapper;
	
	//会员表mapper
	@Autowired
	private TMemberMapper tMemberMapper;
	
	public WxResult initUser(String openid) {
		TUser user = findUserByOpenId(openid);
		if(user!=null){
			return WxResult.build(201, "用户已初始化",openid);
		}
		//创建user对象
		user = new TUser();
		//设置其openid
		user.setOpenid(openid);
		//设置其身份，第一次进入默认为普通用户，即非系统管理员
		user.setIdentity("0");
		//设置其注册时间
		user.setCreatetime(DateUtil.getDay());
		//插入到数据库中
		int i = tUserMapper.insertSelective(user);
		//测试事务
		//int j =1/0;
		if(i>0){
			return WxResult.build(200, "用户首次初始化成功", openid);
		}
		return WxResult.build(501, "用户初始化失败");
	}


	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=true)
	public TUser findUserByOpenId(String openid) {
		TUser user = tUserMapper.selectByPrimaryKey(openid);
		return user;
	}


	@Override
	public WxResult updateUserWxInfoByOpenId(TUser user) {
		
		int i = tUserMapper.updateByPrimaryKeySelective(user);
		if(i>0){
			return WxResult.build(200, "用户更新微信信息成功","logined");
		}
		return WxResult.build(500, "用户更新微信信息失败");
	}


	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=true)
	public WxResult checkPermission(String openid) {
		//查看是否为系统管理员权限
		TUser user = tUserMapper.selectByPrimaryKey(openid);
		//获取其系统管理员权限标识
		String identity = user.getIdentity();
		//查看是否有协会管理员权限
		TMemberExample example = new TMemberExample();
		example.createCriteria().andOpenidEqualTo(openid);
		List<TMember> list = tMemberMapper.selectByExample(example);
		String isAssHead = "0"; //默认为无协会管理员权限
		//若在会员表中已有记录
		if(list.size()>0){
			//搜索所有记录中，判断是否有已经是协会管理员的标识
			for (TMember tMember : list) {
				String flag = tMember.getIsasshead();
				if(flag.equals("3")){//若已有协会管理权限，无论是哪个协会
					isAssHead = flag;
					break;
				}
			}
		}
		Map map = new HashMap();
		map.put("identity", identity);
		map.put("isAssHead", isAssHead);
		return WxResult.build(200, "权限查询成功",map);
	}

	

}
