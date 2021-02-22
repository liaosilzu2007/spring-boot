package com.lzumetal.springboot.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author liaosi
 * @date 2021-02-22
 */
@Slf4j
public class TokenUtils {


    /**
     * 签名秘钥
     */
    private static final String SECRET = "1234567890";


    public static void main(String[] args) {
        System.out.println(TokenUtils.createJwtToken(1002));
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NjQwMDAwMCIsImlhdCI6MTYxMzk2MzE1MiwiZXhwIjoxNjE0MDQ5NTUyfQ.ediObiXUgFl-cyDHTutFqIanqD22LtctJsl77QEv0xI";
        //System.out.println(parseJwtTokenToId(token));
    }


    /**
     * 生成token
     *
     * @param id 用户id
     * @return
     */
    public static String createJwtToken(long id) {
        long ttlMillis = 1000 * 60 * 60 * 24;
        return createJwtToken(String.valueOf(id), null, null, ttlMillis);
    }


    /**
     * 生成Token
     *
     * @param id        用户id
     * @param issuer    该JWT的签发者，是否使用是可选的
     * @param subject   该JWT所面向的用户，是否使用是可选的；
     * @param ttlMillis 过期时间
     * @return token String
     */
    private static String createJwtToken(String id, String issuer, String subject, long ttlMillis) {

        // 签名算法，将对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 通过秘钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // 生成签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        // Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //设置过期时间
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();

    }


    // Sample method to validate and read the JWT
    public static Claims parseJwtToken(String jwtToken) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                    .parseClaimsJws(jwtToken).getBody();
        } catch (ExpiredJwtException e) {
            log.error("token已过期|{}", jwtToken, e);
            throw new RuntimeException("token已过期");
        }
        return claims;
    }


    public static long parseJwtTokenToId(String jwtToken) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = parseJwtToken(jwtToken);
        String idStr = claims.getId();
        if (StringUtil.isNumber(idStr)) {
            return Long.parseLong(idStr);
        }
        return 0L;
    }


}
