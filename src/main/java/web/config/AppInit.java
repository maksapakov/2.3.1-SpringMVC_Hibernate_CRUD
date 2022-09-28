package web.config;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
// Это ДИСПЕТЧЕР
// с таймлиф это спринг конфигурация
// db config (он же hibernate)- это DriverManagerDataSource, LocalContainerEntityManagerFactoryBean
/*  этот класс считывается автоматически и работает как web.xml
 *  т.к. по цепочке наследования главный интерфейс у него WebApplicationInitializer
 *  в нем мы описываем создание диспетчер сервлета, его настройку и т.д.
 */

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Метод, указывающий на класс конфигурации
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    // Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                SpringConfig.class // теперь метод знает где искать спринг
        };
    }

    /* Данный метод указывает url, на котором будет базироваться приложение */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; // /-означает что мы все запросы отправляем на дипетчер сервлет
    }

    // что это????
    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException {
        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }

    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }
}