package indi.cloud.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {

//	@Bean
//	@LoadBalanced
//	RestTemplate restTemplate() {
//		return new RestTemplate();
//	}

//	@Bean
//	public Configuration configuration(){
//		Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
////		String templatePath = FreemarkerUtil.class.getResource("/").getPath();
////		cfg.setDirectoryForTemplateLoading(new File(templatePath));
//		cfg.setDefaultEncoding("UTF-8");
//		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//		cfg.setLogTemplateExceptions(false);
//		cfg.setWrapUncheckedExceptions(true);
//
//		return cfg;
//	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
