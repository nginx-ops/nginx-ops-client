package io.github.nginx.ops.client.comm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @author lihao3
 * @date 2022/8/20
 */
@Configuration
public class I18nConfig implements WebMvcConfigurer {

  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver session = new SessionLocaleResolver();
    // 默认语言
    session.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
    return session;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor local = new LocaleChangeInterceptor();
    // 参数名
    local.setParamName("lang");
    return local;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }
}
