package com.adc.da;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

//import com.codahale.metrics.CsvReporter;

@MapperScan("com.adc.da.**.dao")
@SpringBootApplication
@ServletComponentScan
public class AdcDaApplication {

	@Value("${project.port}")
	private int port;


	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(AdcDaApplication.class, args);

		// 启动Metrics 性能监控报表
//		CsvReporter reporter = applicationContext.getBean(CsvReporter.class);
//		reporter.start(1, TimeUnit.MINUTES);
	}

	/**
	 * 设定容器的session失效时间，默认30分
	 */
/*	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer(){
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.setSessionTimeout(18000);//单位为S
				container.setPort(port);
			}
		};
	}*/
}
