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
                <a href='https://beian.miit.gov.cn/'>冀ICP备17015375-1号</a>
                作者：<a href='https://gaein.cn'>Gaein nidb | gaein.cn</a>
                另附：傻逼 Java 傻逼 SpringBoot 我草你妈，屎一样的库和乱七八糟的不兼容升级是不是脑子有病"""
            )
            .license("GNU General Public License v3.0")
            .licenseUrl("https://www.gnu.org/licenses/gpl-3.0.en.html")
            .version(VERSION)
            .build();
    }
}
