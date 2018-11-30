package com.atosenet.ga.company.core.auth.shiro.stateless;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.atosenet.ga.company.core.auth.shiro.adapter.AuthProvider;
import com.google.common.collect.Sets;

public class StatelessRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(StatelessRealm.class);

//	@Autowired
//	private UserRepository userRepository;
//	
	private AuthProvider authProvider;

	public AuthProvider getAuthProvider() {
		return authProvider;
	}

	public void setAuthProvider(AuthProvider authProvider) {
		this.authProvider = authProvider;
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		// 仅支持StatelessToken类型的Token
		return token instanceof StatelessAuthenticationToken;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 根据用户名查找角色，请根据需求实现
		String account = (String) principals.getPrimaryPrincipal();
		
		logger.debug("User [{}] authorization: doGetAuthorizationInfo", account);
		
//		User user = userRepository.findByAccount(account);
		
//		if(user == null) {
//			throw new AuthorizationException("user not found");
//		}
//		
//		Set<Role> roles = user.getRoles();
		Set<String> permissions = Sets.newHashSet();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//		for (Role role : roles) {
//			authorizationInfo.addRole(role.getRolename());
//			for (Resource resource : role.getResources()) {
//				if (StringUtils.isNotBlank(resource.getPermission())) {
//					permissions.add(resource.getPermission());
//				}
//			}
//		}
		if(logger.isDebugEnabled()) {
			String rolesStr = JSONObject.toJSONString(authorizationInfo.getRoles());
			String permissionsStr = JSONObject.toJSONString(authorizationInfo.getStringPermissions());
			logger.debug("User [{}] has authoriz those: Roles[{}], Permissions[{}]", account, rolesStr, permissionsStr);
		}
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		StatelessAuthenticationToken statelessToken = (StatelessAuthenticationToken) token;
		String userName = (String) statelessToken.getPrincipal();

		String serverDigest = authProvider.retriToken(userName);
		if(StringUtils.isBlank(serverDigest)) {
			throw new AuthenticationException("token invalid");
		}
		
		logger.debug("User [{}] authenticate: doGetAuthenticationInfo[serverDigest={}]", userName, serverDigest);

		return new SimpleAuthenticationInfo(userName, serverDigest, getName());
	}

}