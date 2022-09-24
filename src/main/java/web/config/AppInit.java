package web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*  этот класс считывается автоматически и работает как web.xml
 *  т.к. по цепочке наследования главный интерфейс у него WebApplicationInitializer
 *  в нем мы описываем создание диспетчер сервлета, его настройку и т.д.
 */

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Метод, указывающий на класс конфигурации
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    } // использовать не будем


    // Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class // теперь класс знает где находится cпринг конфигурация
        };
    }


    /* Данный метод указывает url, на котором будет базироваться приложение */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; // /-означает что мы все запросы отправляем на дипетчер сервлет
    }
}
