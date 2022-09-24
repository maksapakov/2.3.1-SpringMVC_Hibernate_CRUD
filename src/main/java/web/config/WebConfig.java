package web.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

/*
Этот класс полностью заменяет applicationContext.xml
 */

@Configuration
@EnableWebMvc // помечаем что наше приложене поддерживает web функции
@ComponentScan("web") // сканировать будем по пакету web
public class  WebConfig implements WebMvcConfigurer { // этот интерфейс реализуют тогда, когда хотят настроить спринг под себя
    // например, мы будем использовать шаблонизатор Thymeleaf
    private final ApplicationContext applicationContext;

    // внедряем зависимость контекста через конструктор, нужна ли пометка @Autowired?
    public WebConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Bean // Используем ApplicationContext чтобы настроить таймлиф
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/pages/"); // задаем папку в которой будут лежать представления
        templateResolver.setSuffix(".html"); // создаем расширение для представлений
        return templateResolver;
    }

    @Bean // задаем конфигурацию для представлений
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override // создаем шаблонизатор Thymeleaf
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
}