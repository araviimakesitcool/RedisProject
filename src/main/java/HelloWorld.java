
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class HelloWorld extends Application<HelloWorldConfiguration> {
    public Connection con;
    public static void main(String[] args)throws Exception {
         //Technical Doubt

        final String url = "jdbc:postgresql://localhost/apple";
        final String user = "apple";
        final String password = "";
        HelloWorld hw = new HelloWorld();

        try {
            hw.con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

          hw.run(args);


    }

    @Override
    public String getName() {
        return "Hello World";
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName(),
        this.con);
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);


    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {


    }
}
