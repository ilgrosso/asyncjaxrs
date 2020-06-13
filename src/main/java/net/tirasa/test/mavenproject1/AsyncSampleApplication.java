package net.tirasa.test.mavenproject1;

import java.util.Arrays;
import java.util.concurrent.Executor;
import net.tirasa.test.mavenproject1.api.HelloService;
import net.tirasa.test.mavenproject1.impl.HelloServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
public class AsyncSampleApplication {

    @Autowired
    private Bus bus;

    public static void main(String[] args) {
        SpringApplication.run(AsyncSampleApplication.class, args);
    }

    @Bean
    public Executor batchExecutor() {
        ThreadPoolTaskExecutor batchExecutor = new ThreadPoolTaskExecutor();
        batchExecutor.setCorePoolSize(10);
        batchExecutor.setThreadNamePrefix("Batch-");
        batchExecutor.initialize();
        return batchExecutor;
    }

    @Bean
    public HelloService helloService() {
        return new HelloServiceImpl();
    }

    @Bean
    public Server restServer() {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setAddress("/");
        endpoint.setServiceBeans(Arrays.asList(helloService()));
        endpoint.setFeatures(Arrays.asList(new LoggingFeature()));
        return endpoint.create();
    }
}
