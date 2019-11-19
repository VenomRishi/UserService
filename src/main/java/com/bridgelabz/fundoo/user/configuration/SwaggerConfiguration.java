package com.bridgelabz.fundoo.user.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	/**
	 * Purpose: this method is used to specify the swagger to which API(Application
	 * Programming Interface) to show on Swagger UI(User Interface) consoleF
	 * 
	 * @return returns {@link Docket} which has the information about
	 *         API(Application Programming Interface)
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.bridgelabz.fundoo.user.controller")).build()
				.apiInfo(metaData());
	}

	/**
	 * Purpose: this method is used to add extra data which will give user a proper
	 * idea about the API(Application Programming Interface) information in the
	 * swagger UI(User Interface) console
	 * 
	 * @return return the swagger API Info
	 */
	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Spring Boot REST API", "Spring Boot REST API for Online Store", "1.0",
				"Terms of service", new Contact("Rishikesh Mhatre", "", "mhatrerishikesh@gmail.com"),
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
		return apiInfo;
	}
}
