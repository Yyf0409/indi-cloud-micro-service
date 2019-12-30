package indi.cloud.api.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Map;
/**
* @Author yangyifan
* @date 2019/4/23 19:06
* @Description 
*/
public class PageInfoUtils {
    public static Page<Object> PageInfo(Map postData){
        int page = NumberUtils.toInt(String.valueOf(postData.get("page")));
        int size = NumberUtils.toInt(String.valueOf(postData.get("size")));
        return PageHelper.startPage(page, size);
    }
}
