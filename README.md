# data-exchange数据交易中心项目

使用spring-boot,spring,mybatis,thymeleaf,jQuery,bootstrap,sass,webjars框架搭建
数据库使用Mysql

## spring-boot
使用spring-boot框架搭建应用。spring-boot是基于spring4.0的快速开发部署框架，不需要配置即可搭建一个应用，用于简化配置和部署工作。

## mybatis
使用mybatis官方最新的mybatis-spring-boot-starter-1.1.1版本，用于集成mybatis

### mybatis分页
mybatis-PageHelper作者的官方地址[https://github.com/pagehelper/Mybatis-PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)。

本项目中使用了```PageHelper.startPage```静态方法调用启用分页查询，使用```com.github.pagehelper.PageInfo```保存分页信息。
在需要进行分页的Mybatis方法前调用```PageHelper.startPage```静态方法即可，紧跟在这个方法后的第一个Mybatis查询方法会被进行分页。

项目代码示例如下：
```java
    public PageResponseDTO<DemoDTO> queryDemoInfo(ReqDemoDTO baseInfo) {
        //分页信息赋值
        int page = baseInfo.getPageNo();
        int rows = baseInfo.getPageSize();
        //查询条件赋值
        String dataId = baseInfo.getDataId();
        String example = new DemoExample();
        example.setOrderByClause("id desc"); //排序字段放在前面设置
        example.createCriteria().andDataIdEqualTo(dataId);
        //开启分页查询，使用mybatis-PageHelper分页插件
        PageHelper.startPage(page, rows);
        //执行查询第一个mybatis查询方法，会被进行分页
        List<DemoInfo> lists = demoMapper.selectByDataId(example);
        //使用PageInfo对结果进行包装
        PageInfo pageInfo = new PageInfo(lists);
        logger.info("IDemoInfoSV查询完成，总数：" + pageInfo.getTotal() + "当前页内记录数：" + lists.size());
        //按照返回数据结构封装分页数据，本项目中分页统一返回PageResponseDTO。入参pageInfo，返回的数据传输对象DTO的class
        PageResponseDTO<DemoDTO> respDTO = PageResponseFactory.genPageResponse(pageInfo,DemoDTO.class);
        return respDTO;
    }
```

## RESTful API
对外接口统一使用RESTful风格的API提供服务。RESTful是一种架构原则，是基于HTTP的通信协议。选用RESTful的原因是它是目前微服务架构的流行实现方式，具有良好的扩展能力，对外部系统提供的服务不要求有任何依赖。

本项目使用jersey框架实现RESTful风格的API。

## thymeleaf模板
使用thymeleaf模板来替代velocity，thymeleaf支持模板预览，所见即所得，方便开发和美工使用同一份前端代码开发。本项目中使用了thymeleaf-3.0.2版本。

* fragments/layout.html是全站统一的模板布局
* fragments/header.html是网站head模板
* widget/fileupload.html是文件上传控件的模板，widget目录下存放本项目内使用的公共页面元素
* demo/thymeTest.html使用了布局，引用了模板的示例
* demo/imageTest.html是使用了图片和文件上传模板的示例

开发注意点：
1. 定义和使用thymeleaf模板时，html描述信息中要增加```xmlns:th="http://www.thymeleaf.org"```
2. 定义布局layout时，html描述信息中要增加```xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"```
3. 使用布局时，html描述信息中要增加```xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout" layout:decorate="~{fragments/layout}"```

代码示例：
```
// 模板的定义上，只需要在对应的html代码块上增加th:fragment="templetName"就可以了。
// 模板的引用上，只需要在要引用的地方写上th:replace="~{fragments/footer :: footer}"或者th:include="~{fragments/footer :: footer}"
// 布局的定义上，只需要在布局文件中定义好全部的文档结构，在预留的主页面内容部分写上layout:fragment="screen_content"，其中screen_content可任意起名，需要和使用布局的地方保持一致。
// 布局的使用上，只需要在页面写好对应html模板，在要替换布局的html代码块上添加layout:fragment="screen_content"即可。
// 模板引用和布局的区别：引用是将其他模板中的内容添加到当前页面，布局是将当前页面内容添加到layout定义页面中并最终返回layout定义的内容。

// 模板定义代码例子：
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<head>
    <!-- css 实际生成的页面不会使用 -->
    <link rel="stylesheet" href="../../static/component/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../static/css/iconfont/iconfont.css"/>
    <link rel="stylesheet" href="../../static/css/base/global.css"/>
</head>
<body>
    <!--底部信息-->
    <footer class="footer" th:fragment="footer">
        <div class="footerCon">
            <div class="copyright">
                Copyright 1999-2013 中国联合网络通信有限公司 版权所有
                <br/>
                桂ICP备05001086号 中华人民共和国增值电信业务 经营许可证编号：a2.b1.b2-2C090003
            </div>
        </div>
    </footer>
</body>
</html>

// 模板引用代码示例：
<!DOCTYPE html>
<html lang="zh" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head th:include="~{fragments/header :: head}">
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>工作流</title>
</head>
<body>
<div class="clearfix wrap">
    <div th:replace="~{fragments/menu :: menu}">
        <!--顶部菜单-->
    </div>
    <!-- 主内容 -->
    <div layout:fragment="screen_content">
        <p>这里是内容</p>
    </div>
</div>
<!--footer-->
<div th:replace="~{fragments/footer :: footer}" ></div>
</body>
</html>

// 布局定义代码示例：同上

// 布局使用代码示例：
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorate="~{fragments/layout}">
<head>
    <!-- css 实际生成的页面不会使用 -->
    <link rel="stylesheet" href="../../static/component/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../static/css/iconfont/iconfont.css"/>
    <link rel="stylesheet" href="../../static/css/base/global.css"/>
</head>
<body>
<!-- 主内容 -->
<div layout:fragment="screen_content">
    <span>congratulations!</span><br/>
    <!--/*@thymesVar id="msg" type="String"*/-->
    <span th:text="${msg}">工作流，这是一个测试页面</span>
</div>
</body>
</html>

```

## 系统配置
配置文件路径```src/main/resources/application.yml```

系统配置使用yaml标记语言编写，相比于*.properties，yaml提供了结构化的数据结构，且支持更灵活的配置。yaml和spring-boot整合可简化配置。

## 版本发布
本项目开发流程采用主干开发，分支发布的模式。具体工作步骤如下：
1. 本项目开发负责人制定版本发布计划，在最近一期上线计划的功能代码可直接提交到develop中。发布版本统一基于develop分支拉取。
2. 需求开发可在开发本地拉取分支提交，或者拉取特性分支协作提交，需求分支命名规范：需求编码-需求描述，如：910-工作流一期。
3. 需求进入联测阶段并确认在下一版本发布时，开发负责人将特性分支further合并到develop中。
4. 需求进入UAT测试阶段时，测试负责人从develop拉取release分支进行测试，并修改对应版本号。
5. 需求上线时，运维负责人基于release分支拉取对应版本分支，如发布1.1版本时，创建分支名为“V1.1”的分支，同时修改发布分支中pom文件版本号为正式版本，并对该分支进行写保护。
6. 如果遇到生产环境需要紧急更新以解决bug，开发基于发布分支拉取Hotfix分支完成修复工作，由开发负责人将修复内容合并到发布分支V1.1并更新版本号。
7. 持续集成会自动识别正式分支V1.1，执行对应的持续发布流程，完成生产环境的更新。
