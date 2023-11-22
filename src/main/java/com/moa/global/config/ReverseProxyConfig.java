package com.moa.global.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;


@Slf4j
@Configuration
public class ReverseProxyConfig {

	@Bean
	ForwardedHeaderFilter forwardedHeaderFilter() {
		log.debug("ReverseProxyConfig - set ForwardedHeaderFilter");
		return new ForwardedHeaderFilter();
	}

}
