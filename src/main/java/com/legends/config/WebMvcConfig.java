package com.legends.config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);
	
	private static List<String> AUTH_WHITELIST=Arrays.asList(
			// -- swagger ui
			"/v2/api-docs", 
			"/swagger-resources", 
			"/swagger-resources/**", 
			"/configuration/ui",
			"/configuration/security", 
			"/swagger-ui.html", 
			"/webjars/**",
			"/error",
			"/actuator/health",
			"/actuator/info",
			"/actuator",
			"/webjars/springfox-swagger-ui",
			".css",
			".js"
	);
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HandlerInterceptor() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {

				String requestURI = request.getRequestURI();

				logger.debug(">>>>>>>>>>>>>请求路径>>>>>>>>>>>>>>>>" + requestURI);
				for (String singleAuth : AUTH_WHITELIST) {
					if (requestURI.contains(singleAuth)) {
						return true;
					}
				}

				return true;
			}

			@Override
			public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
					@Nullable Exception ex) throws Exception {

			}
		});
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter());
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		//添加此配置
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		converter.setObjectMapper(objectMapper);
		return converter;
	}

}
