package logDemo;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(final String... args) throws Exception {
        System.out.println("Starting Roger Rabbit application context...");
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        ctx.start();
        System.out.println("Roger Rabbit running...");
    }
}
