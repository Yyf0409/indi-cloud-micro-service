package indi.cloud.api.utils;

import java.util.UUID;

public class UUIDUtil {

    /**
     * 生成32位UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }


}
