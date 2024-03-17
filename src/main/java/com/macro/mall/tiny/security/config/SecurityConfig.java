package com.macro.mall.tiny.security.config;

import com.macro.mall.tiny.security.component.DynamicAuthorizationManager;
import com.macro.mall.tiny.security.component.JwtAuthenticationTokenFilter;
import com.macro.mall.tiny.security.component.RestAuthenticationEntryPoint;
import com.macro.mall.tiny.security.component.RestfulAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


/**
 * SpringSecurity 6.x以上新用法配置
 * 为避免循环依赖，仅用于配置HttpSecurity
 * Created by macro on 2019/11/5.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private DynamicAuthorizationManager dynamicAuthorizationManager;

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity, MvcRequestMatcher.Builder mvc) throws Exception {
        HttpSecurity security = httpSecurity.authorizeHttpRequests(registry -> {
//            registry.requestMatchers(mvc("/druid/**")).permitAll();
            ignoreUrlsConfig.getUrls().forEach(u -> registry.requestMatchers(mvc.pattern(u)).permitAll());
            registry.requestMatchers(HttpMethod.OPTIONS).permitAll()
                    // 任何请求需要身份认证
                    .anyRequest()
                    .access(dynamicAuthorizationManager);
        });
        // 关闭跨站请求防护
        security.csrf(AbstractHttpConfigurer::disable);
        // 不使用session
        security.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // 自定义权限拒绝处理类
        security.exceptionHandling(s -> {
            s.accessDeniedHandler(restfulAccessDeniedHandler)
                    .authenticationEntryPoint(restAuthenticationEntryPoint);
        });
        // 自定义权限拦截器JWT过滤器
        security.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return security.build();
//        AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry = httpSecurity
//                .authorizeHttpRequests();
//        //不需要保护的资源路径允许访问
//        for (String url : ignoreUrlsConfig.getUrls()) {
//            registry.requestMatchers(url).permitAll();
//        }
//        //允许跨域请求的OPTIONS请求
//        registry.requestMatchers(HttpMethod.OPTIONS)
//                .permitAll();
//        // 任何请求需要身份认证
//        registry.and()
//                .authorizeHttpRequests()
//                .anyRequest()
//                .access(dynamicAuthorizationManager)
//                // 关闭跨站请求防护及不使用session
//                .and()
//                .csrf()
//                .disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                // 自定义权限拒绝处理类
//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(restfulAccessDeniedHandler)
//                .authenticationEntryPoint(restAuthenticationEntryPoint)
//                // 自定义权限拦截器JWT过滤器
//                .and()
//                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//        return httpSecurity.build();
    }
}
