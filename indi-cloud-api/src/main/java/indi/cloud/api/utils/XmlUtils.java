package indi.cloud.api.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


/**
* @Author yangyifan
* @date 2019/4/2 13:31
* xml读取Util
*/
public class XmlUtils {
    private static final Logger logger = LoggerFactory.getLogger(XmlUtils.class);

    public static Element getXmlChapters(String path) throws Exception {
        Element xmlChapters = null; // 根节点对象
        try {
            InputStream xmlFile = CrmExcelReadHelper(path);
            logger.debug("XML Path: " + xmlFile);
            SAXReader reader = new SAXReader();//xml阅读器
            Document doc = null; // 加载xml文件
            doc = reader.read(xmlFile);
            xmlChapters = doc.getRootElement();
        }catch (FileNotFoundException e) {
            throw  new Exception("文件路径错误"+ path);
        }  catch (Exception e) {
            e.printStackTrace();
            logger.error("财务指标设定文件 xml格式不正确。 Chapters节点不存在。");
            throw  new Exception("XML文件格式有误");
        }
        return  xmlChapters;
    }

    /**
     * 读取文件流
     * @param filepath 文件路径
     * @return 文件流
     */
    public static InputStream CrmExcelReadHelper(String filepath) throws Exception {

        try {
            File file = new File(filepath);
            InputStream is = new FileInputStream(file);
            return is;
        } catch (Exception e) {
            throw new IOException("读取流异常");
        }
    }



    public static String spilt(String str) {
        StringBuffer sb = new StringBuffer();
        String[] temp = str.split(",");
        for (int i = 0; i < temp.length; i++) {
            if (!"".equals(temp[i]) && temp[i] != null) {
                sb.append("'" + temp[i] + "',");
            }
        }
        String result = sb.toString();
        String tp = result.substring(result.length() - 1, result.length());
        if (",".equals(tp)) {
            return result.substring(0, result.length() - 1);
        } else {
            return result;
        }
    }

}
