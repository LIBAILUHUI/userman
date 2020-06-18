package com.lh.userman;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import jdk.nashorn.internal.runtime.GlobalConstants;


public class MyPlusGenerate {

    public static void main(String[] args) {
        //自动生成代码的对象
        AutoGenerator mp = new AutoGenerator();
        //1.全局设置
        GlobalConfig gc = new GlobalConfig();
        //设置作者
        gc.setAuthor("lh");
        //设置输出的路径
        gc.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        //设置输出是否打开
        gc.setOpen(false);
        //设置生成返回的map结果集
        gc.setBaseResultMap(true);
        //设置通用查询结果列
        gc.setBaseColumnList(true);
        mp.setGlobalConfig(gc);

        //2.数据库设置
        DataSourceConfig ds =new DataSourceConfig();
        //数据库驱动
        ds.setDriverName("com.mysql.cj.jdbc.Driver");
        //数据库的连接地址
        ds.setUrl("jdbc:mysql://localhost:3306/mp?characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8");
        //数据库用户名
        ds.setUsername("root");
        //数据库密码
        ds.setPassword("root");
        mp.setDataSource(ds);

        //3.包的设置
        PackageConfig pg = new PackageConfig();
        //设置xml文件的名称
        pg.setXml("mapper.mappers");
        //系统当前包名
        pg.setParent(MyPlusGenerate.class.getPackage().getName());
        mp.setPackageInfo(pg);

        //4.生成策略
        StrategyConfig sc = new StrategyConfig();
        //包括哪些表
        sc.setInclude("user","dept");
        //排除哪些表
        //sc.setExclude();
        //开启lombok模式
        sc.setEntityLombokModel(true);
        //设置命名规范
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        //生成命名规范
        mp.setStrategy(sc);
        //执行生成
        mp.execute();
    }
}
