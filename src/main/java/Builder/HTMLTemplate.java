package Builder;
import java.io.*;
import java.util.regex.Pattern;

public class HTMLTemplate {
    private String fileName = "template.html";
    private String body = "Template Body";
    private String title = "Template Title";

    public HTMLTemplate(HTMLBuilder builder){
        this.fileName = builder.fileName;
        this.body = builder.body;
        this.title = builder.title;
    }

    public String returnHTML(){

        StringBuilder tempHTML = new StringBuilder();
        String pattern_title = "\\$\\{title\\}";
        String pattern_body = "\\$\\{body\\}";
try {
    BufferedReader in = new BufferedReader(new FileReader("htmltemp.html"));
    String str;
    while ((str = in.readLine()) != null) {

        if(str.matches(pattern_title)){
            str = str.replaceFirst(pattern_title, title);

        }
        if(str.matches(pattern_body)){
            str = str.replaceFirst(pattern_body, body);
        }
        tempHTML.append(str);
    }
    in.close();
    }
    catch(Exception e)
        {
            e.printStackTrace();
        }
        return tempHTML.toString();
    }
}
