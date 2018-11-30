package com.atosenet.ga.company.core.auth.shiro.token;

import java.util.Map;

/**
 * Token处理器
 * 1）Token生成
 * 2）将data和TokenParameter相互转换
 */
public interface ITokenProcessor {
	/**
	 * 返回Token处理器ID.
	 * 
	 * @return
	 */
	String getId();
	/**
	 * 返回Token过期时间
	 * @return
	 */
	Integer getExpr();

	/**
	 * 生成Token.
	 * 
	 * @param tp
	 * @return
	 */
	String generateToken(TokenParameter tp);

	/**
	 * 根据token参数生成Data.
	 *
	 * @param tp
	 * @return
	 */
	Map<String, ?> getDataFromTokenParameter(TokenParameter tp);

	/**
	 * 获取签名属性列表
	 * @return
	 */
	Map<String, ?> getLogoutData(String token, String userid);


	/**
	 * 从Data中还原Token参数.
	 *
	 * @param cookies
	 * @return
	 */
	TokenParameter getTokenParameterFromData(Map<String, ?> data);
}
