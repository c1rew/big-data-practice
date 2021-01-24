import com.viper.service.MessageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.InputStream;

/**
 * @author c1rew
 * @date 2021-01-13 15:51
 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");

        MessageService messageService = context.getBean(MessageService.class);

        System.out.println(messageService.getMessage());
    }
}
