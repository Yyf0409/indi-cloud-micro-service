package indi.cloud.oauth.center.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class PasswordUtils {

    private static final  String[]  symbol = {"!","@","#","$","%","&"};

    private static final int DEFAULT_PASSWORD_LENGTH = 8;

    /**
     * 生成随机密码
     * @return
     */
    public static String randomPassword() {
        return randomPassword(DEFAULT_PASSWORD_LENGTH, true, true, true, true);
    }

    /**
     * 生成随机密码
     * @param len 长度
     * @return
     */
    public static String randomPassword(int len) {
        return randomPassword(len, true, true, true, true);
    }

    /**
     * 生成随机密码
     * @param len  长度
     * @param useDigit 使用数字
     * @param useAlphaUpCase 使用大写字母
     * @param useAlphaLowCase 使用小写字母
     * @param useSymbol 使用符号
     * @return
     */
    public static String randomPassword(int len, boolean useDigit, boolean useAlphaUpCase, boolean useAlphaLowCase, boolean useSymbol){

        List<String> s = new ArrayList();

        // 使用数字
        if(useDigit){
            for(int i=0;i< 10;i++){
                s.add(String.valueOf(i));
            }
        }

        // 使用大写字母
        if(useAlphaUpCase) {
            for (int i = 0; i < 26; i++) {
                s.add(String.valueOf( (char) ('A' + i)));
            }
        }

        // 使用小写字母
        if(useAlphaLowCase) {
            for (int i = 0; i < 26; i++) {
                s.add(String.valueOf((char)('a' + i)));
            }
        }

        // 使用特殊符号
        if(useSymbol) {
            for (int i = 0; i < symbol.length; i++) {
                s.add(symbol[i]);
            }
        }

        StringBuilder sbPassword = new StringBuilder();
        Random rnd = new Random(new Date().getTime());

        // 生成随机密码
        while(sbPassword.length() < len){
            sbPassword.append(  s.get(rnd.nextInt(s.size())));
        }

        // 返回密码字符串
        return sbPassword.toString();
    }
}
