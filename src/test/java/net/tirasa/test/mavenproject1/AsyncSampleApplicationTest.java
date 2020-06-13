package net.tirasa.test.mavenproject1;

import java.util.concurrent.ExecutionException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AsyncSampleApplicationTest {

    @LocalServerPort
    private int randomServerPort;

    @Test
    public void test() throws InterruptedException, ExecutionException {
        WebTarget target = ClientBuilder.newClient().target("http://localhost:" + randomServerPort + "/rest");
        target
                .path("resumeFromFastThread")
                .request()
                .async()
                .get(new InvocationCallback<String>() {

                    @Override
                    public void completed(final String s) {
                        // do something
                        System.out.println("EEEEEEEEEEEE " + s);
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        // process error
                        throwable.printStackTrace();
                    }
                }).get();
    }
}
