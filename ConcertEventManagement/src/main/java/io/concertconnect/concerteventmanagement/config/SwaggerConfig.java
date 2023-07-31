package io.concertconnect.concerteventmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //연락 할 수 있는 정보
    private static final Contact DEFAULT_CONTACT = new Contact(
            "Heesu Yeo","https://github.com/HeesuYeo/PortPolio", "dugmltn231@naver.com");

    //API의 정보 및 라이센스
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Awesome API Title", "My Event Manager REST API service", "1.0", "urn:tos",
            DEFAULT_CONTACT, "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());

    //API가 지원하는 타입
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
            Arrays.asList("application/json", "application/xml"));


    @Bean
    public Docket api () {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
}
/*
문서화해주는 swagger
http://localhost:8080/swagger-ui.html 웹페이지에 사용하는 컨트롤러와 모델을 보여줌
http://localhost:8080/v2/api-docs 사용하는 컨트롤러와 모델을 Json형태로 정보를 보여줌
 */