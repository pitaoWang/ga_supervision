package com.atosenet.ga.company.core.auth.shiro.token;

import org.owasp.esapi.ESAPI;

import com.atosenet.ga.company.core.auth.shiro.common.esapi.EncryptException;
import com.atosenet.ga.company.core.auth.shiro.common.esapi.IYCPESAPI;


/**
 * token生成器
 *
 */
public class TokenGenerator {
	private static String localSeedValue = null;
	
	/**
	 * 获得当前系统的 token seed
	 */
	public static String findSeed() throws EncryptException {
		if (localSeedValue != null) {
			return localSeedValue;
		} else {
			String seed = genSeed();
			localSeedValue = seed;
			return seed;
		}
	}

    /**
     * 生成用户登陆token
     *
     * @param uname
     * @param ts
     * @param seed
     * @return
     * @throws EncryptException
     */
    public static String genToken(String uname, long ts, String seed) throws EncryptException {
        return IYCPESAPI.encryptor().hash(uname + ts, seed);
    }

    /**
     * 生成系统的加密seed
     *
     * @return
     * @throws EncryptException
     */
    public static String genSeed() throws EncryptException {
        return IYCPESAPI.encryptor().hash(new String(ESAPI.securityConfiguration().getMasterKey()), new String(ESAPI.securityConfiguration().getMasterSalt()));
    }
}
