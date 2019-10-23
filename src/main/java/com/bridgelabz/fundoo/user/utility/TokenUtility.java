/******************************************************************************
 *  Purpose: This class is utility class which create new JWTS token and also
 *  		 parse the created JWTS token
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   23-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.utility;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtility {
	/**
	 * Purpose: this method is used to generate JWTS(JSON web token's) token's
	 * 
	 * @param email     the field which is then encode into the token
	 * @param tokenName name's the token by passing name
	 * @return return the build token in string format back
	 */
	public static String buildToken(String email, String tokenName) {
		return Jwts.builder().setSubject(email).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, tokenName)
				.compact();
	}

	/**
	 * Purpose: method will parse the Claims by using the given token
	 * 
	 * @param token it is the input which is coming from the program which is in the
	 *              form of token
	 * @param key   key is to specify the key name which is used while building
	 *              token
	 * @return
	 */
	public static Claims parseToken(String token, String key) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}
}
