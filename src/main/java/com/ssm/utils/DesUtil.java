package com.ssm.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * des加密算法工具类
 * */
public class DesUtil {

    //加密密钥
    private static final String KEY = "DES";

    private static final String PASSWORD = "12345678";

    //转换格式
    private static final String TRANSFORMATION = "DES/ECB/PKCS5Padding";


    //加密
    public static byte[] encrypt(byte[] datasource, String password) {
        try{
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY);
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(datasource);
        }catch(Throwable e){
            e.printStackTrace();
        }
        return null;
    }

    //解密
    public static String decrypt(byte[] src, String password) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY);
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        src = cipher.doFinal(src);
        return new String(src,"utf-8");
    }

    /**
     * 使用base64解决乱码
     *
     * @param secretKey
     *            加密后的字节码
     */
    public static String jdkBase64String(byte[] secretKey) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(secretKey);
    }

    /**
     * 使用jdk的base64 解密字符串 返回为null表示解密失败
     *
     * @throws IOException
     */
    public static byte[] jdkBase64Decoder(String str) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(str);
    }


    public static void main(String[] args) {
        //待加密内容
        String str = "zsl0506ld0328";
        //密码，长度要是8的倍数
        String password = "12345678";
        String result = DesUtil.jdkBase64String(encrypt(str.getBytes(),password));
        System.out.println("加密后内容为："+ result);

        //直接将如上内容解密
        try {
            String decryResult = DesUtil.decrypt(jdkBase64Decoder(result), password);
            System.out.println("加密后内容为："+new String(decryResult));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
