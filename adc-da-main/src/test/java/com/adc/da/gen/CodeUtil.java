package com.adc.da.gen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.codemagic.DbCodeGenerateFactory;

public class CodeUtil {

    public CodeUtil() {}

    public static void main(String args[]) {

        String entityPackage;



        entityPackage = "lawss";


        /*DbCodeGenerateFactory.codeGenerate("MSG_BROWSE_INFO", entityPackage);
        DbCodeGenerateFactory.codeGenerate("MSG_DYNAMIC_INFO", entityPackage);
        DbCodeGenerateFactory.codeGenerate("MSG_FILE", entityPackage);
        DbCodeGenerateFactory.codeGenerate("MSG_MODULE", entityPackage);
        DbCodeGenerateFactory.codeGenerate("MSG_SAR_PUT_TIME", entityPackage);
        DbCodeGenerateFactory.codeGenerate("OT_CONVERT_MQ", entityPackage);
        DbCodeGenerateFactory.codeGenerate("OT_STAND_ITEM", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_BROWSE_INFO", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_BUS_SAR_COMPILE", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_BUSS_STAND_FILE", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_BUSS_STAND_ITEM_VAL", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_BUSS_STAND_ITEMS", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_BUSS_STAND_MENU", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_BUSS_STAND_PLAN", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_BUSS_STAND_RES", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_BUSS_STAND_VAL", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_BUSSIONESS_STAND", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_LAWS_FILE", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_LAWS_INFO", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_LAWS_ITEM_VAL", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_LAWS_MENU", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_LAWS_RES", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_LAWS_VAL", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_MENU", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_PRODUCT_INFO", entityPackage);*/
        DbCodeGenerateFactory.codeGenerate("SAR_PRODUCT_LAWS", entityPackage);
        /*DbCodeGenerateFactory.codeGenerate("SAR_PRODUCT_STAND", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_PRODUCT_TEST", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_PRODUCT_VAL", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_SAR_ACCESS", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_SAR_ACCESS_INFO", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_STAND_FILE", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_STAND_ITEM_VAL", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_STAND_ITEMS", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_STAND_MENU", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_STAND_RES", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_STAND_VAL", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_STANDARDS_INFO", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_TEST_ITEM", entityPackage);
        DbCodeGenerateFactory.codeGenerate("SAR_TEST_ITEM_VAL", entityPackage);
        DbCodeGenerateFactory.codeGenerate("UT_CAR_CCC", entityPackage);
        DbCodeGenerateFactory.codeGenerate("UT_CAR_ENVIRON", entityPackage);
        DbCodeGenerateFactory.codeGenerate("UT_CAR_EXEMPTION", entityPackage);
        DbCodeGenerateFactory.codeGenerate("UT_CAR_EXPAND", entityPackage);
        DbCodeGenerateFactory.codeGenerate("UT_CAR_NOTICE", entityPackage);*/












//        DbCodeGenerateFactory.codeGenerate("TS_ROLE", entityPackage);
//        DbCodeGenerateFactory.codeGenerate("TS_MENU", entityPackage);

//        testOracle();
    }


    public static void testOracle()
    {
        Connection con = null;// 创建一个数据库连接
        PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
        ResultSet result = null;// 创建一个结果集对象
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
            System.out.println("开始尝试连接数据库！");
            String url = "jdbc:oracle:" + "thin:@60.30.69.61:1521:ADC";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
            String user = "adcda01";// 用户名,系统默认的账户名
            String password = "adcda01";// 你安装时选设置的密码
            con = DriverManager.getConnection(url, user, password);// 获取连接
            System.out.println("连接成功！");
            String sql = "select * from TEST";// 预编译语句，“？”代表参数
            pre = con.prepareStatement(sql);// 实例化预编译语句
            result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
            while (result.next())
                // 当结果集不为空时
                System.out.println("学号:" + result.getInt("id") );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                // 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
                // 注意关闭的顺序，最后使用的最先关闭
                if (result != null)
                    result.close();
                if (pre != null)
                    pre.close();
                if (con != null)
                    con.close();
                System.out.println("数据库连接已关闭！");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
