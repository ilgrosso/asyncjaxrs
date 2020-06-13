package net.tirasa.test.mavenproject1.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.annotation.Resource;
import net.tirasa.test.mavenproject1.api.HelloService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class HelloServiceImpl implements HelloService {

    @Resource(name = "batchExecutor")
    private ThreadPoolTaskExecutor executor;

    @Override
    public String sayHello(final String a) {
        return "Hello " + a;
    }

    @Override
    public CompletionStage<String> getBookDescriptionResumeFromFastThread() {
        CompletableFuture<String> future = new CompletableFuture<>();
        executor.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
                // ignore
            }
            future.complete("resumeFromFastThread");
        });
        return future;
    }
}
