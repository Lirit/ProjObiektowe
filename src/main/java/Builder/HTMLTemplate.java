package Builder;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HTMLTemplate {
    private String fileName = "template.html";
    private String body = "Template Body";
    private String title = "Template Title";

    public HTMLTemplate(HTMLBuilder builder){
        this.fileName = builder.fileName;
        this.body = builder.body;
        this.title = builder.title;
    }

    String returnHTML(){

        StringBuilder tempHTML = new StringBuilder();
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
