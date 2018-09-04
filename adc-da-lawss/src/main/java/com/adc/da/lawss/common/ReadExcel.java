package com.adc.da.lawss.common;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * @des :  excel信息读取
 * @author: duyunbao
 * @email: 1114808306@qq.com
 * @date 2017/10/27 17:06
 **/
public class ReadExcel {


    /**
     * 总行数
     */
    private int totalRows = 0;
    /**
     * 总条数
     */
    private int totalCells = 0;
    /**
     * 错误信息接收器
     */
    private String errorMsg;

    public ReadExcel() {
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public String getErrorInfo() {//获取错误信息
        return errorMsg;
    }

    /**
     * @method_name: validateExcel
     * @des :  验证excel格式
     * @author: duyunbao
     * @param: [filePath]
     * @return: boolean
     * @date: 2017/10/27 17:07
     **/
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    /**
     * @method_name: getExcelInfo
     * @des : 读EXCEL文件，获取信息集合
     * @author: duyunbao
     * @param: [fileName, Mfile]
     * @return: org.apache.poi.ss.usermodel.Workbook
     * @date: 2017/10/27 17:08
     **/
    public Workbook getExcelInfo(String fileName, MultipartFile Mfile) {
        Workbook wb = null;
        //把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
        CommonsMultipartFile cf = (CommonsMultipartFile) Mfile; //获取本地存储路径
        //初始化输入流
        InputStream is = null;
        try {
            //根据文件名判断文件是2003版本还是2007版本
            boolean isExcel2003 = true;
            if (WDWUtil.isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            is = cf.getInputStream();
            //根据excel里面的内容读取客户信息
            wb = getExcelInfo(is, isExcel2003, wb);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        return wb;
    }

    /***
     * @method_name: getExcelInfo
     * @des :  判断excel版本
     * @author: duyunbao
     * @param: [is, isExcel2003, wb]
     * @return: org.apache.poi.ss.usermodel.Workbook
     * @date: 2017/10/27 17:08
     **/
    private Workbook getExcelInfo(InputStream is, boolean isExcel2003, Workbook wb) {
        try {
            /** 根据版本选择创建Workbook的方式 */
            //当excel是2003时
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {//当excel是2007时
                wb = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * 获取sheet的名称
     * @MethodName:getSheetName
     * @author: 马晓晨
     * @email: 747052172@qq.com
     * @date 2017年11月24日 上午9:49:22
     * @version V1.0
     * @param filename
     * @param file
     * @param sheetIndex
     * @return
     */
	public String getSheetName(String filename, MultipartFile file, Integer sheetIndex) {
		Workbook wb = getExcelInfo(filename, file);
		return wb.getSheetName(sheetIndex);
	}

    /**
     *
     * @Title: encodeFileName
     * @Description: 导出文件转换文件名称编码
     * @param @param fileNames
     * @param @param request
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String encodeFileName(String fileNames ,HttpServletRequest request) {
        String codedFilename = null;
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident") || null != agent && -1 != agent.indexOf("Edge")) {// ie浏览器及Edge浏览器
                String name = java.net.URLEncoder.encode(fileNames, "UTF-8");
                codedFilename = name;
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,Chrome等浏览器
                codedFilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codedFilename ;
    }
}