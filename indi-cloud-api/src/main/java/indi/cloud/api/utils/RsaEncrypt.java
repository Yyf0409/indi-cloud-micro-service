package indi.cloud.api.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * AES加解密类
 * @author xupengfei@chinasofti.com
 * @date 2017/3/31 14:20
 */
public class RsaEncrypt {


    private static Logger log = LoggerFactory.getLogger(RsaEncrypt.class);


    /**
     * 将私钥从字符串转换为PrivateKey对象
     * @param privateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws IOException
     */
    private static PrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        BASE64Decoder base64decoder = new BASE64Decoder();
        byte[] b = base64decoder.decodeBuffer(privateKey);
        //生成私匙
        KeyFactory kf = KeyFactory.getInstance("RSA");
        //X509EncodedKeySpec keySpec = new X509EncodedKeySpec(b);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(b);
        //PublicKey privateKey = kf.generatePublic(keySpec);
        return kf.generatePrivate(keySpec);
    }

    /**
     * 使用私钥，对数据进行签名
     */
    public static String encrypt(String beSign, String privateKey) throws Exception {

        if (StringUtils.isEmpty(privateKey) || StringUtils.isEmpty(beSign)) {
            return null;
        }
        PrivateKey key = getPrivateKey(privateKey);
        //获取Signature实例，指定签名算法（本例使用SHA1WithRSA）
        Signature signature = Signature.getInstance("SHA1WithRSA");
        //加载私钥
        signature.initSign(key);
        //更新待签名的数据
        signature.update(beSign.getBytes("UTF-8"));
        //进行签名
        byte[] signed = signature.sign();
        //将加密后的字节数组，转换成BASE64编码的字符串，作为最终的签名数据
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(signed);
    }

    /**
     * 讲字符创格式的公钥转换为PublicKey对象
     * @param publicKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws InvalidKeySpecException
     */
    private static PublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        //获取KeyFactory，指定RSA算法
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //将BASE64编码的公钥字符串进行解码
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] encodeByte = decoder.decodeBuffer(publicKey);
        //将BASE64解码后的字节数组，构造成X509EncodedKeySpec对象，生成公钥对象
        return keyFactory.generatePublic(new X509EncodedKeySpec(encodeByte));

    }


    public static boolean decrypt(String needBeSign, String sign, String publicKey) throws Exception {

        //获取Signature实例，指定签名算法(与之前一致)
        Signature signature = Signature.getInstance("SHA1WithRSA");
        //加载公钥
        PublicKey key = getPublicKey(publicKey);
        signature.initVerify(key);
        //更新原数据
        signature.update(needBeSign.getBytes("UTF-8"));
        //公钥验签（true-验签通过；false-验签失败）
        BASE64Decoder decoder = new BASE64Decoder();
        return signature.verify(decoder.decodeBuffer(sign));

    }

    /**
     * 读取密钥信息
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static String readKey(InputStream in) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String readLine = null;
        StringBuilder sb = new StringBuilder();
        while ((readLine = br.readLine()) != null)
        {
            if (readLine.charAt(0) == '-')
            {
                continue;
            } else
            {
                sb.append(readLine);
                sb.append('\r');
            }
        }

        return sb.toString();
    }


}

