package com.wch.bos.realm;

import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.wch.bos.dao.IFunctionDao;
import com.wch.bos.dao.IUserDao;
import com.wch.bos.domain.Function;
import com.wch.bos.domain.Role;
import com.wch.bos.domain.User;

@SuppressWarnings("all")
public class BOSRealm extends AuthorizingRealm {
	
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IFunctionDao functionDao;

	/** 
	 * 授权
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		List<Function> list = null;
		
		if ("admin".equals(user.getUsername())) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Function.class);
			list = functionDao.findByCriteria(criteria );
		}else {
			list = functionDao.findFunctionListByUserId(user.getId());
		}
		
		for (Function function : list) {
			info.addStringPermission(function.getCode());
		}
		/*角色*/
		if (!user.getRoles().isEmpty()) {
			Set<Role> roles = user.getRoles();
			for (Role role : roles) {
				info.addRole(role.getCode());
			}
		}
		
		return info;
	}

	/** 
	 * 认证
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("认证");
		UsernamePasswordToken myToken = (UsernamePasswordToken)token;
		String username = myToken.getUsername();
		User user = userDao.findUserByUsername(username);
		if (user == null) {
			return null;
		}
		AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		return authenticationInfo;
	}

}
