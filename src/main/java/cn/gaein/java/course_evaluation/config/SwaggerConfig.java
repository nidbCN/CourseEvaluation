package cn.gaein.java.course_evaluation.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiResourceController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UrlPathHelper;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiResourceController;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "cn.gaein.java.course_evaluation.controller";
    private static final String DEFAULT_PATH = "/conference";
    public static final String VERSION = "v1.0.0";

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("is-admin-conference")
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
            .paths(PathSelectors.any())
            .build();
    }

//    /**
//     * SwaggerUI接口访问
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
//            .addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("swagger-ui.html")
//            .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//            .addResourceLocations("classpath:/META-INF/resource/webjars/");
//
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//    }

    //
//    @Controller
//    @ApiIgnore
//    @RequestMapping(DEFAULT_PATH)
//    public static class SwaggerResourceController implements InitializingBean {
//
//        @Autowired
//        private ApiResourceController apiResourceController;
//
//        @Autowired
//        private Environment environment;
//
//        @Autowired
//        private DocumentationCache documentationCache;
//
//        @Autowired
//        private ServiceModelToSwagger2Mapper mapper;
//
//        @Autowired
//        private JsonSerializer jsonSerializer;
//
//        private Swagger2Controller swagger2Controller;
//
//        @Override
//        public void afterPropertiesSet() {
//            swagger2Controller = new Swagger2Controller(environment, documentationCache, mapper, jsonSerializer);
//        }
//
//        /**
//         * 首页
//         */
//        @RequestMapping
//        public ModelAndView index() {
//            ModelAndView modelAndView = new ModelAndView("redirect:" + DEFAULT_PATH + "/swagger-ui.html");
//            return modelAndView;
//        }
//
//        @RequestMapping("/swagger-resources/configuration/security")
//        @ResponseBody
//        public ResponseEntity<SecurityConfiguration> securityConfiguration() {
//            return apiResourceController.securityConfiguration();
//        }
//
//        @RequestMapping("/swagger-resources/configuration/ui")
//        @ResponseBody
//        public ResponseEntity<UiConfiguration> uiConfiguration() {
//            return apiResourceController.uiConfiguration();
//        }
//
//        @RequestMapping("/swagger-resources")
//        @ResponseBody
//        public ResponseEntity<List<SwaggerResource>> swaggerResources() {
//            return apiResourceController.swaggerResources();
//        }
//
//        @RequestMapping(value = "/v2/api-docs", method = RequestMethod.GET, produces = {"application/json",
//            "application/hal+json"})
//        @ResponseBody
//        public ResponseEntity<Json> getDocumentation(
//            @RequestParam(value = "group", required = false) String swaggerGroup,
//            HttpServletRequest servletRequest) {
//            return swagger2Controller.getDocumentation(swaggerGroup, servletRequest);
//        }
//    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：<a href="http://ip:port/swagger-ui.html">...</a>
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Course Evaluation API reference")
            .description("傻逼 Java 傻逼 SpringBoot 我草你妈，屎一样的库和乱七八糟的不兼容升级")
            .version(VERSION)
            .build();
    }
}
