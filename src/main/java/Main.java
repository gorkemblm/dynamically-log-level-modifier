import com.gorkem.parentpath.firstchildrenpath.FirstChildrenClass;
import com.gorkem.parentpath.httpserver.BasicHttpServer;
import com.gorkem.parentpath.secondchildrenpath.SecondChildrenClass;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int poolSize = 3;

    public static void main(String[] args) {
        BasicHttpServer server = new BasicHttpServer();
        FirstChildrenClass firstChildrenClass = new FirstChildrenClass();
        SecondChildrenClass secondChildrenClass = new SecondChildrenClass();

        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);

        executorService.execute(server);
        executorService.execute(firstChildrenClass);
        executorService.execute(secondChildrenClass);
    }
}
