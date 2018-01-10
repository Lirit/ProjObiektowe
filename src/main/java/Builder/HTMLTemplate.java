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
    BufferedReader in = new BufferedReader(new FileReader("template.html"));
    String str;
    while ((str = in.readLine()) != null) {

        if(str.matches(pattern_title)){
            str.replaceFirst(Pattern.quote(pattern_title), title);
        }
        if(str.matches(pattern_body)){
            str.replaceFirst(Pattern.quote(pattern_body), body);
        }
        tempHTML.append(str);
    }
    in.close();
    }
    catch(Exception e)
        {
            e.printStackTrace();
        }
        tempHTML.append("<html>");
        tempHTML.append("<head><title>"+ title + "</title></head>");
        tempHTML.append("<body>"+body+"</body>");
        tempHTML.append("</html>");
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(tempHTML.toString());
            writer.close();
        }
        catch(IOException e){
            System.out.println("There was a problem with saving content to the file");
        }

        return tempHTML.toString();
    }
}
