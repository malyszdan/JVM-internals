import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;

import java.nio.file.Files;
import java.nio.file.Paths;

import static spark.Spark.*;

public class ServiceExample {

    public final static String URL = "http://localhost:4567/";

    final static Logger logger = Logger.getLogger(ServiceExample.class);

    public static void main(String[] args) {
        Logger.getRootLogger().setLevel(Level.INFO);
        BasicConfigurator.configure();
        get("/", (request, response) -> {
            byte[] bytes = Files.readAllBytes(Paths.get("src/main/resources/Hello.class"));
//            byte[] bytes = Files.readAllBytes(Paths.get());
            HttpServletResponse raw = response.raw();
            raw.getOutputStream().write(bytes);
            logger.warn("Reading from service");
            raw.getOutputStream().flush();
            raw.getOutputStream().close();

            return response.raw();
        });
    }
}
