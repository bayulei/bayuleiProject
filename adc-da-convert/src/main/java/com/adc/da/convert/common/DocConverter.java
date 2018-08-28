package com.adc.da.convert.common;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.awt.*;
import java.io.*;

/**
 * @Author yangxuenan
 * @Description 文档转换
 * Date 2018/8/24 13:44
 * @Param 
 * @return 
 **/
public class DocConverter {
    // 环境 1：windows 2:linux
    private static final int environment = 1;
    // (只涉及pdf2swf路径问题)
    private String fileString;
    // 输入路径 ，如果不设置就输出在默认的位置
    private String outputPath = "";
    //转化文件名称
    private String fileName;
    //转换pdf文件
    private File pdfFile;
    //转换成swf文件
    private File swfFile;
    //待转换doc文件
    private File docFile;
    //为文件加水印后文件路径
    String waterPdfPath;

    public DocConverter(String fileString,String fileType) {
        ini(fileString,fileType);
    }

    /**
     * @Author yangxuenan
     * @Description 重新设置file
     * Date 2018/8/24 13:50
     * @Param [fileString, fileType]
     * @return void
     **/
    public void setFile(String fileString,String fileType) {
        ini(fileString,fileType);
    }

    /**
     * @Author yangxuenan
     * @Description 初始化
     * Date 2018/8/24 13:50
     * @Param [fileString, fileType]
     * @return void
     **/
    private void ini(String fileString,String fileType) {
        this.fileString = fileString;
        fileName = fileString.substring(0, fileString.lastIndexOf("."));
        //判断文件类型，进行不同操作
        if(".doc".equals(fileType) || ".docx".equals(fileType)){
            docFile = new File(fileString);
            pdfFile = new File(fileName + ".pdf");
            swfFile = new File(fileName + ".swf");
        } else if (".pdf".equals(fileType)){
            pdfFile = new File(fileName + ".pdf");
            swfFile = new File(fileName + ".swf");
        }
    }

    /**
     * @Author yangxuenan
     * @Description 转为PDF
     * Date 2018/8/24 13:50
     * @Param []
     * @return void
     **/
    private void doc2pdf() throws Exception {
        if (docFile.exists()) {
            if (!pdfFile.exists()) {
                //连接openOffice
                OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
                try {
                    connection.connect();
                    //使用openOffice将doc文件转换问pdf文件
                    DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
                    converter.convert(docFile, pdfFile);
                    //为转换完成的pdf加水印
                    addWaterToPdf(pdfFile);

                    // 关闭连接
                    connection.disconnect();
                    System.out.println("****pdf转换成功，PDF输出：" + pdfFile.getPath()+ "****");
                } catch (java.net.ConnectException e) {
                    e.printStackTrace();
                    System.out.println("****swf转换器异常，openoffice服务未启动！****");
                    throw e;
                } catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
                    e.printStackTrace();
                    System.out.println("****swf转换器异常，读取转换文件失败****");
                    throw e;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            } else {
                System.out.println("****已经转换为pdf，不需要再进行转化****");
            }
        } else {
            System.out.println("****swf转换器异常，需要转换的文档不存在，无法转换****");
        }
    }

    /**
     * @Author yangxuenan
     * @Description 转换成 swf
     * Date 2018/8/24 13:51
     * @Param [fileType]
     * @return void
     **/
    private void pdf2swf(String fileType) throws Exception {
        Runtime r = Runtime.getRuntime();
        if (!swfFile.exists()) {
            if (pdfFile.exists()) {
                // windows环境处理
                if (environment == 1) {
                    try {
                        //使用swfTools工具将pdf文件转换为swf文件
                        //String paperSwfFile = swfFile.getPath().replace(".swf","%.swf");
                        Process p = r.exec("G:/swftTools/pdf2swf.exe "+ waterPdfPath + " -o "+ swfFile.getPath() + " -f -T 9 -t -s languagedir=G:/xpdf/xpdf-chinese-simplified");
                        System.out.print(loadStream(p.getInputStream()));
                        System.err.print(loadStream(p.getErrorStream()));
                        System.out.print(loadStream(p.getInputStream()));
                        System.err.println("****swf转换成功，文件输出："
                                + swfFile.getPath() + "****");
                        //如果源文件为doc类文件，将过渡的pdf文件删除
                        if(".doc".equals(fileType) || ".docx".equals(fileType)){
                            if (pdfFile.exists()) {
                                pdfFile.delete();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw e;
                    }
                } else if (environment == 2) {// linux环境处理
                    try {
                        Process p = r.exec("pdf2swf " + pdfFile.getPath()
                                + " -o " + swfFile.getPath() + " -f -T 9 -t -s storeallcharacters");
                        System.out.print(loadStream(p.getInputStream()));
                        System.err.print(loadStream(p.getErrorStream()));
                        System.err.println("****swf转换成功，文件输出："
                                + swfFile.getPath() + "****");
                        if (pdfFile.exists()) {
                            pdfFile.delete();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw e;
                    }
                }
            } else {
                System.out.println("****pdf不存在,无法转换****");
            }
        } else {
            System.out.println("****swf已经存在不需要转换****");
        }
    }

    static String loadStream(InputStream in) throws IOException {

        int ptr = 0;
        in = new BufferedInputStream(in);
        StringBuffer buffer = new StringBuffer();

        while ((ptr = in.read()) != -1) {
            buffer.append((char) ptr);
        }

        return buffer.toString();
    }

    /**
     * @Author yangxuenan
     * @Description 转换主方法
     * Date 2018/8/24 13:51
     * @Param [fileType]
     * @return boolean
     **/
    public boolean conver(String fileType) {

        if (swfFile.exists()) {
            System.out.println("****swf转换器开始工作，该文件已经转换为swf****");
            return true;
        }

        if (environment == 1) {
            System.out.println("****swf转换器开始工作，当前设置运行环境windows****");
        } else {
            System.out.println("****swf转换器开始工作，当前设置运行环境linux****");
        }
        try {
            //判断待转换文件类型，如果为doc文件需要先转为pdf类型文件
            if(".doc".equals(fileType) || ".docx".equals(fileType)){
                doc2pdf();
                pdf2swf(fileType);
            } else if (".pdf".equals(fileType)){
                addWaterToPdf(pdfFile);
                pdf2swf(fileType);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (swfFile.exists()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Author yangxuenan
     * @Description 返回文件路径
     * Date 2018/8/24 13:52
     * @Param []
     * @return java.lang.String
     **/
    public String getswfPath() {
        if (swfFile.exists()) {
            //获取转换后的swf文件路径
            String tempString = swfFile.getPath();
            tempString = tempString.replaceAll("\\\\", "/");
            return tempString;
        } else {
            return "";
        }

    }

    /**
     * @Author yangxuenan
     * @Description 设置输出路径
     * Date 2018/8/24 13:52
     * @Param [outputPath]
     * @return void
     **/
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
        if (!outputPath.equals("")) {
            String realName = fileName.substring(fileName.lastIndexOf("/"),
                    fileName.lastIndexOf("."));
            if (outputPath.charAt(outputPath.length()) == '/') {
                swfFile = new File(outputPath + realName + ".swf");
            } else {
                swfFile = new File(outputPath + realName + ".swf");
            }
        }
    }

    /**
     * @Author yangxuenan
     * @Description 加水印
     * Date 2018/8/24 13:52
     * @Param [pdfFile]
     * @return void
     **/
    public void addWaterToPdf(File pdfFile) throws Exception{
        if (pdfFile.exists()) {
            try {
                //为PDF 加水印
                String pdfPath = pdfFile.getPath();
                waterPdfPath = pdfPath.replace(".pdf","_water.pdf");
                // 待加水印的文件
                PdfReader reader = new PdfReader(pdfPath);
                // 加完水印的文件
                PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(waterPdfPath));
                int total = reader.getNumberOfPages() + 1;

                PdfContentByte content;
                // 设置字体
                BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                                    BaseFont.EMBEDDED);
                // 水印文字
                String waterText = "我是水印！！！qwe12~";
                // 文字长度
                int j = waterText.length();
                char c = 0;
                // 高度
                int high = 0;
                // 循环对每页插入水印
                for (int i = 1; i < total; i++) {
                    // 水印的起始
                    high = 500;
                    content = stamper.getUnderContent(i);
                    // 开始
                    content.beginText();
                    // 设置颜色
                    content.setColorFill(BaseColor.GRAY);
                    // 设置字体及字号
                    content.setFontAndSize(base, 18);
                    // 设置起始位置
                    content.setTextMatrix(400, 780);
                    // 开始写入水印
                    for (int k = 0; k < j; k++) {
                        content.setTextRise(14);
                        c = waterText.charAt(k);
                        // 将char转成字符串
                        content.showText(c + "");
                        high -= 5;
                    }
                    content.endText();

                }
                stamper.close();
                reader.close();
                System.out.println("****pdf水印添加成功，PDF水印输出：" + waterPdfPath+ "****");
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        } else {
            System.out.println("****未找到需要转换的pdf****");
        }


    }
}
