package me.kbiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication
@RestController
@EnableResourceServer
public class ResourceApplication {



	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}

	@RequestMapping("/")
	//@CrossOrigin(origins="*", maxAge=3600,allowedHeaders={"x-auth-token", "x-requested-with"})
	public Message home() {
		return new Message("Hello World");
	}

	class Message {
		private String id = UUID.randomUUID().toString();
		private String content;

		public Message() {
		}

		public Message(String content) {
			this.content = content;
		}
		// ... getters and setters and default constructor



		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}

	/*@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			*//** 使用了Api网关之后不再需要跨域支持
			http.cors().and().authorizeRequests()
					.anyRequest().authenticated();*//*
			http.httpBasic().disable();
			http.authorizeRequests().anyRequest().authenticated();

		}
	}*/
//	@Bean
//	HeaderHttpSessionStrategy sessionStrategy() {
//		return new HeaderHttpSessionStrategy();
//	}

}
