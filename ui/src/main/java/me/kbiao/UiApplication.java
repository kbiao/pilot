package me.kbiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
//@EnableOAuth2Sso
//@EnableZuulProxy
public class UiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}

	/*@RequestMapping("/resource")
	public Map<String,Object> home(){
		Map<String ,Object> model = new HashMap<>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content","hello world");
		return model;
	}*/

	/*@RequestMapping("/user")
	public Principal user(Principal user){
		return user ;
	}*/

	/*@RequestMapping("/token")
	public Map<String,String> token(HttpSession session) {
		return Collections.singletonMap("token", session.getId());
	}*/

	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter{
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			http
//					.httpBasic().and().logout().and()
					.authorizeRequests().antMatchers("/index.html","/home.html"/*,"/login.html"*/,"/").permitAll()
					.anyRequest().authenticated().and()
					.csrf()
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		}

		/*@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			*//***
			 * 使用内存用户进行授权*//*
			auth.inMemoryAuthentication().withUser("kbiao").password("biaobiao").roles("USER").and().
					withUser("admin").password("password").roles("USER","ADMIN");
			super.configure(auth);

		}*/

	}
}
