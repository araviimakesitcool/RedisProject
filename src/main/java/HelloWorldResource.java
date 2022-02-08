import com.codahale.metrics.annotation.Timed;

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
    Connection con;

    public HelloWorldResource(String template, String defaultName , Connection con) {

        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
        this.con = con;
    }

    @GET
    @Timed
    @Path("/users")
    public Saying sayHello(@QueryParam("name") Optional<String> name) throws SQLException {
        Saying result = null;
        int k =0;
        String query = "Select * from test where id = 1";


         PreparedStatement preparedStatement = con.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery();
         while(resultSet.next())
         result = new Saying( resultSet.getInt("id"),resultSet.getString("name"));

        return result;
    }

    @POST
    @Path("/users/insert")
    public void PostHello(@Valid Saying temp )throws SQLException
    {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO test values (?, ?)");
        stmt.setInt(1, temp.id);
        stmt.setString(2, temp.content);
        stmt.executeUpdate();
    }

    @PATCH
    @Path("/users/insert/{id}")
    public void makeChange(@PathParam("id") int id, Saying temp )throws SQLException
    {
        PreparedStatement stmt = con.prepareStatement(" UPDATE test SET name = ? where id = ?");
        stmt.setString(1, temp.content);
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }

    @DELETE
    @Path("/users/delete/{id}")
    public void delete(@PathParam("id") int id) throws SQLException
    {
        PreparedStatement stmt = con.prepareStatement(" DELETE FROM test where id =?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}


