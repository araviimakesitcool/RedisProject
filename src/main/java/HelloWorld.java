import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class HelloWorld {
    public static void main(String[] args) {

        Student a = new Student();
       a = convertJSONN.getStudent(a);
        ObjectMapper obj = new ObjectMapper();

        try{
                System.out.println(obj.writeValueAsString(a));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


    }
}
