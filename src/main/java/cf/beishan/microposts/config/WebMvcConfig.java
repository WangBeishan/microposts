package cf.beishan.microposts.config;

import cf.beishan.microposts.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login", "/", "/index", "/about",
//                        "/contact", "/signupStatus",
//                        "/loginStatus", "/login", "/signup",
//                        "/help", "/css/**", "/images/**");
//    }
}
