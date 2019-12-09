package ccx.cloud.credit.risk.view.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtils {
    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static final String IGNORE_KEY = "流动资产：,非流动资产：,流动负债：,非流动负债：,所有者权益(或股东权益)：," +
            "每股收益,经营活动产生的现金流量：,投资活动产生的现金流量：,筹资活动产生的现金流量：";

    /**
     * 创建临时文件
     *
     * @param prefix 前缀
     * @param suffix 后缀
     * @return 返回文件
     * @throws IOException
     */
    public static File createTempFile(String prefix, String suffix) throws IOException {

        return File.createTempFile(prefix, suffix);
    }


    /**
     * 读取Excel
     *
     * @param filepath 文件路径
     * @return 文件流
     */
    public static InputStream CrmExcelReadHelper(String filepath) throws Exception {

        try {
            File file = new File(filepath);
            InputStream is = new FileInputStream(file);
            return is;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("读取流异常");
        }
    }


    /**
     * 读取临时文件
     *
     * @param filename 文件名
     * @return 返回文件
     * @throws IOException
     */
    public static File getTempFile(String filename) throws IOException {

        String folder = System.getProperty("java.io.tmpdir");

        if (folder.endsWith("/") || folder.endsWith("\\")) {
            return new File(folder + filename);
        } else {
            return new File(folder + "/" + filename);
        }
    }

    /**
     * 下载服务器已存在文件
     *
     * @param response
     * @param localFile      （文件路径）
     * @param attachmentName （下载后文件名带后缀）
     * @throws Exception
     */
    public static void downloadFile(HttpServletResponse response, String localFile, String attachmentName) throws Exception {
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(attachmentName.getBytes(), "iso-8859-1"));//下载文件的名称
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(localFile));
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }

    /**
     * 下载Excel Workbook
     *
     * @param request
     * @param response
     * @param wb
     * @param attachmentName
     * @throws Exception
     */
    public static void downloadExcel(HttpServletRequest request, HttpServletResponse response, Workbook wb, String attachmentName) throws Exception {
        OutputStream output = response.getOutputStream();
        response.reset();
        try {
            response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(attachmentName.getBytes(), "iso-8859-1") + ".xlsx\"");//下载文件的名称
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        wb.write(output);
        output.flush();
        output.close();
    }

    /**
     * 根据路径创建临时文件
     *
     * @param prefix 临时文件前缀
     * @param suffix 临时文件后缀
     * @param path   临时文件目录
     * @return
     */
    public static File createTempExcel(String prefix, String suffix, String path) throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return File.createTempFile(prefix, suffix, dir);
    }


    /**
     * 根据模板填充公开报表数据
     * @param tempPath
     * @param resultPath
     * @param model
     */
    public static void createByTemp(String tempPath,String resultPath,JSONObject model) {
        InputStream in = null;
        FileOutputStream out = null;
        try {
            //读取流获取对象
            in = CrmExcelReadHelper(tempPath);
            XSSFWorkbook excel = new XSSFWorkbook(in);
            //
            for(int i=0;i<excel.getNumberOfSheets();i++){
                //获取sheet
                XSSFSheet sheet = excel.getSheetAt(i);
                //循环row
                if(i==0){
                    for(int j=0;j<sheet.getLastRowNum();j++){

                        XSSFRow row = sheet.getRow(j);
                        String key = row.getCell(3).getStringCellValue().trim();
                        if(IGNORE_KEY.contains(key)){
                            continue;
                        }
                        if(StringUtils.isBlank(key)){
                            continue;
                        }
                        if(model.containsKey(key)){
                            row.getCell(4).setCellValue(NumberUtils.toDouble(model.getString(key)));
                        }
                    }

                }
                for(int j=0;j<sheet.getLastRowNum();j++){

                    XSSFRow row = sheet.getRow(j);
                    String key = row.getCell(1).getStringCellValue().trim();
                    if(IGNORE_KEY.contains(key)){
                        continue;
                    }
                    if(StringUtils.isBlank(key)){
                        continue;
                    }
                    if(model.containsKey(key)){
                        row.getCell(2).setCellValue(NumberUtils.toDouble(model.getString(key)));
                    }
                }

            }
            //输出流
            out = new FileOutputStream(resultPath);
            excel.write(out);

            logger.info("模板生成excel公开报表成功，参数---excel模板路径：{}，生成Html保存路径 ：{}", new Object[]{tempPath, resultPath});
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("模板生成excel公开报表失败，resultPath地址：{}，错误信息：{}", new Object[]{resultPath, e.getMessage()});
        } finally {
            if(null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
