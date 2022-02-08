import com.fasterxml.jackson.annotation.JsonProperty;

public class Saying {

     int id;
    public String content;

    public Saying() {

    }

    public Saying(int id, String content) {
        this.id = id;
        this.content = content;
    }



    @JsonProperty
    public int getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    @JsonProperty
    public void setId(int id) {
        this.id = id ;
    }
    @JsonProperty
    public void setContent(String content) {
        this.content = content;
    }
}

            /* REPRESENTATION CLASSS */
