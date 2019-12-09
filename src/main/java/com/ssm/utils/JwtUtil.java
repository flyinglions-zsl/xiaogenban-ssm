package com.ssm.utils;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: xiaogenban
 * @description: jwt工具类
 *  *  使用场景:一种情况是webapi,另一种情况是多web服务器下实现无状态分布式身份验证。
 *  JWT是JSON Web Token的缩写，即JSON Web令牌。JSON Web令牌（JWT）是一种紧凑的、URL安全的方式，
 *  用来表示要在双方之间传递的“声明”。JWT中的声明被编码为JSON对象，
 *  用作JSON Web签名（JWS）结构的有效内容或JSON Web加密（JWE）结构的明文，
 *  使得声明能够被：数字签名、 或利用消息认证码（MAC）保护完整性、加密。
 *
 * 流程上是这样的：
 *
 * 1用户使用用户名和密码来请求服务器
 * 2服务器来进行验证用户的信息
 * 3服务器通过验证了，发送给用户一个token
 * 4客户端存储token，并在每次请求时附送这个token值
 * 5服务端验证token值，并返回数据
 * token必须要在每次请求时传递给服务端，它应该保存在请求头里，另外，服务端要支持CORS（跨来源资源共享）策略
 * @author: FlyingLion
 * @create: 2019-08-16 12:39
 **/

public class JwtUtil {

    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

    private static final String EXP = "exp";

    private static final String PAYLOAD = "payload";

    //加密，传入一个对象和有效期
    public static <T> String sign(T object, long maxAge) {
        try {
            final JWTSigner signer = new JWTSigner(SECRET);
            final Map<String, Object> claims = new HashMap<String, Object>();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(object);
            claims.put(PAYLOAD, jsonString);
            claims.put(EXP, System.currentTimeMillis() + maxAge);
            return signer.sign(claims);
        } catch(Exception e) {
            return null;
        }
    }

    //解密，传入一个加密后的token字符串和解密后的类型
    public static<T> T unsign(String jwt, Class<T> classT) {
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        try {
            final Map<String,Object> claims= verifier.verify(jwt);
            if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
                long exp = (Long)claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                //时间没过期
                if (exp > currentTimeMillis) {
                    String json = (String)claims.get(PAYLOAD);
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(json, classT);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}