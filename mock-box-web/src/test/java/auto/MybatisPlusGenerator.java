package auto;

import java.util.Map;
import java.util.ResourceBundle;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.zhtty.mock.box.model.BaseDO;

/**
 * <p>
 * 手脚架-代码生成器
 * </p>
 *
 * @author A8142
 */
public class MybatisPlusGenerator {
    public static void main(String[] args) {
        final ResourceBundle properties = ResourceBundle.getBundle("application");
        String url = properties.getString("spring.datasource.url");
        String userName = properties.getString("spring.datasource.username");
        String pwd = properties.getString("spring.datasource.password");
        AutoGenerator autoGenerator = new AutoGenerator();
        GlobalConfig globalConfig = new GlobalConfig();
        String mainProjectPath = "C:/vue-admin/mock-box";//根据路径需要修改
        String daoProjectPath = "/mock-box-dao";
        String serviceProjectPath = "/mock-box-web";//根据路径需要修改
        String serviceImplProjectPath = "/mock-box-web";//根据路径需要修改
        String controllerProjectPath = "/mock-box-web";
        globalConfig.setAuthor("AutoGenerator")//作者注解
                .setOpen(false)
                .setFileOverride(false)//第二次会把第一次的覆盖掉，建议备份后的时候再开
                .setServiceName("%sService")
                .setEntityName("%sDO")
                .setMapperName("%sMapper")
                .setXmlName("%sDao")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
                .setOutputDir(mainProjectPath + "/src/main/java")
                .setSwagger2(true)//Swagger的注解 对于每个属性
                .setDateType(DateType.ONLY_DATE);
        autoGenerator.setGlobalConfig(globalConfig);
        //数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(url)
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername(userName)
                .setPassword(pwd)
                .setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dataSourceConfig);
        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setRestControllerStyle(true)//Restful风格的Controller，注意页面跳转的不能是Restful风格的
                .setInclude("t_mock_upms_action", "t_mock_upms_permission_group", "t_mock_upms_role")
                .setNaming(NamingStrategy.underline_to_camel)//映射为类的时候去掉表名的前缀
                .setTablePrefix("t_mock_upms_")//可以通过t_工程_模块来修改配置
                .setColumnNaming(NamingStrategy.underline_to_camel)//列名驼峰
                .setEntityLombokModel(true)//是否在实体类标注Lombok注解
                .setSuperEntityClass(BaseDO.class)
                .setSuperEntityColumns("id", "gmt_create", "gmt_modified");
        //包配置
        PackageConfig packageConfig = new PackageConfig();
        ConfigBuilder configBuilder = new ConfigBuilder(packageConfig, dataSourceConfig, strategyConfig, null, globalConfig);
        Map<String, String> packageInfo = configBuilder.getPackageInfo();
        packageInfo.remove(ConstVal.ENTITY);
        packageInfo.remove(ConstVal.MAPPER);
        packageInfo.remove(ConstVal.SERVICE);
        packageInfo.remove(ConstVal.SERVICE_IMPL);
        packageInfo.remove(ConstVal.CONTROLLER);
        packageInfo.remove(ConstVal.MODULE_NAME);
        packageInfo.remove(ConstVal.XML);
        packageInfo.put(ConstVal.ENTITY_PATH, mainProjectPath + daoProjectPath + "/src/main/java/com/zhtty/mock/box/dao/model");
        packageInfo.put(ConstVal.XML_PATH, mainProjectPath + daoProjectPath + "/src/main/resources/mapper");
        packageInfo.put(ConstVal.MAPPER_PATH, mainProjectPath + daoProjectPath + "/src/main/java/com/zhtty/mock/box/dao/mapper");
        packageInfo.put(ConstVal.SERVICE_PATH, mainProjectPath + serviceProjectPath + "/src/main/java/com/zhtty/mock/box/service");
        packageInfo.put(ConstVal.SERVICE_IMPL_PATH, mainProjectPath + serviceImplProjectPath + "/src/main/java/com/zhtty/mock/box/service/impl");
        packageInfo.put(ConstVal.CONTROLLER_PATH, mainProjectPath + controllerProjectPath + "/src/main/java/com/zhtty/mock/box/controller");
        packageConfig.setParent("com.zhtty.mock")
                .setModuleName("box")
                .setMapper("dao.mapper")
                .setController("controller")
                .setService("service")
                .setServiceImpl("service.impl")
                .setPathInfo(packageInfo)
                .setEntity("dao.model");
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setStrategy(strategyConfig);
        //模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("/template/entity.java")
                .setService("/template/service.java")
                .setServiceImpl("/template/serviceImpl.java")
                .setMapper("/template/mapper.java")
                .setXml("/template/mapper.xml")
                .setController("/template/controller.java");
        autoGenerator.setTemplate(templateConfig);
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };

        autoGenerator.setCfg(injectionConfig);
        autoGenerator.execute();
    }

}





