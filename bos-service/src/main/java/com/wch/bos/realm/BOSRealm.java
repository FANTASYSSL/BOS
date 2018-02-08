package com.wch.bos.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.util.finder.ClassFinder.Info;
import com.wch.bos.dao.IUserDao;
import com.wch.bos.domain.User;

public class BOSRealm extends AuthorizingRealm {
	
	@Autowired
	private IUserDao userDao;

	/** 
	 * 授权
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("授权");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermission("staff-list");
		info.addStringPermission("staff-delete");
		User user1 = (User) principalCollection.getPrimaryPrincipal();
		User user2 = (User) SecurityUtils.getSubject().getPrincipal();
		System.out.println((user1 == user2) + " :授权");
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
