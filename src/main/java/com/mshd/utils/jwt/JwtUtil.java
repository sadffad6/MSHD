package com.mshd.utils.jwt;



import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.mshd.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private static final long EXPIRE_TIME = 30 * 60 * 1000; // 30分钟过期时间
    private static final String SECRET = "buptnovel"; //密钥字符串

    public String sign(String username, String password) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withClaim("username", username)
                    .withClaim("password", password)

                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkSign(String token, UserService userService) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            String username = claims.get("username").asString();
            String password = claims.get("password").asString();

            return userService.userExists(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getTokenClaims(String token, String name) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim(name).asString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}