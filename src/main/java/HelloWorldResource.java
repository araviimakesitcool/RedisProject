import com.codahale.metrics.annotation.Timed;
import redis.clients.jedis.Jedis;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;
   Jedis jed;

    public HelloWorldResource(String template, String defaultName , Jedis jed) {

        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
        this.jed = jed;
    }

    @GET
    @Timed
    @Path("/users/{id}")
    public Saying sayHello(@PathParam("id") String id) throws SQLException {

        Saying result = new Saying(Integer.parseInt(id),jed.get(id));

        return result;
    }

    @POST
    @Path("/users/insert")
    public void PostHello(@Valid Saying temp )throws SQLException
    {
        jed.set(Integer.toString(temp.id),temp.content);
    }


    @PATCH
    @Path("/users/insert/{id}")
    public void makeChange(@PathParam("id") int id, Saying temp )throws SQLException
    {
        jed.set(Integer.toString(id),temp.content);
    }


    @DELETE
    @Path("/users/delete/{id}")
    public void delete(@PathParam("id") String id) throws SQLException
    {
        jed.del(id);
    }
}


