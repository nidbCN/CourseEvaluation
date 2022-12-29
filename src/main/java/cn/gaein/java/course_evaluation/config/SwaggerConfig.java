package cn.gaein.java.course_evaluation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "cn.gaein.java.course_evaluation.controller";
    public static final String VERSION = "v1.0.0";

    @Bean
    public Docket createSwagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：<a href="http://ip:port/swagger-ui">swagger-ui</a>
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Course Evaluation API reference")
                .description("""
                        <h3>本软件是自由软件 —— Free as in freedom</h3>
                        <p>更多关于自由软件的信息请查阅：
                            <a href='https://www.gnu.org/philosophy/free-sw.html'>
                            什么是自由软件？ - GNU 工程 - 自由软件基金会
                        </a><p>
                        <img alt='GPL logo' src='https://www.gnu.org/graphics/agplv3-155x51.png' />
                        <hr />
                        <p><a href='https://beian.miit.gov.cn/'>冀ICP备17015375-1号</a></p>
                        <p>作者：<a href='https://gaein.cn'>Gaein nidb | gaein.cn</a></p>
                        <p>另附：傻逼 Java 傻逼 SpringBoot 我草你妈，屎一样的库和乱七八糟的不兼容升级是不是脑子有病</p>"""
                )
                .license("GNU AFFERO GENERAL PUBLIC LICENSE v3.0")
                .licenseUrl("https://www.gnu.org/licenses/agpl-3.0.html")
                .version(VERSION)
                .build();
    }
}
