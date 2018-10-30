import config.springConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.QueryService;

public class springTest {
    public static void main(String[] args) throws Exception{

        //通过java配置方式实例化spring容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(springConfig.class);

        //在spring容器中获取对象
        QueryService queryService = context.getBean(QueryService.class);

        queryService.queryBusData();

        context.destroy();

    }
}
