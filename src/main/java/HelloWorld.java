
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class HelloWorld extends Application<HelloWorldConfiguration> {
    public Connection con;
    public Jedis jed;
    public static void main(String[] args)throws Exception {
         //Technical Doubt

        HelloWorld hw = new HelloWorld() ;
        String redisHost = "localhost";
         int redisPort = 6379;

        //the jedis connection pool..

      JedisPool pool = new JedisPool(redisHost, redisPort);
      hw.jed = pool.getResource();



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
        this.jed);
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);


    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {


    }
}
