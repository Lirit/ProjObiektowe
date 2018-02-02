package Builder;

public class HTMLBuilder implements TemplateBuilder{
    String fileName = "data.json";
    private String confFile;
    String body;
    String title;

    public HTMLBuilder(String filename){
        this.confFile = filename;
    }

    public HTMLBuilder setFileName(){
        String tmpName = "template.html";
        HTMLConfig c = new HTMLConfig();
        try{
            tmpName = c.readConfig(confFile, "fileName");
        }
        catch(Exception e){
            System.out.println("There was a problem with accessing the configuration file.");
        }
        if(tmpName != null){
            this.fileName = tmpName;
        }
        else{
            this.fileName = "template.html";
        }

        return this;
    }

    public String getFileName(){
        return this.fileName;
    }

    public void setConfFile(String fileName){
        this.confFile = fileName;
    }


    public HTMLBuilder setBody(){
        String tmpBody = "Template Body";
        HTMLConfig c = new HTMLConfig();
        try{
            tmpBody = c.readConfig(confFile, "body");
        }
        catch(Exception e){
            System.out.println("There was a problem with accessing the configuration file.");
        }
        if(tmpBody  != null){
            this.body = tmpBody;
        }
        else{
            this.body = "Template Body";
        }
        return this;
    }

    public HTMLBuilder setTitle(){
        String tmpTitle = "Template Title";
        HTMLConfig c = new HTMLConfig();
        try{
            tmpTitle = c.readConfig(this.confFile, "title");
        }
        catch(Exception e){
            System.out.println("There was a problem with accessing the configuration file.");
            e.printStackTrace();
        }
        if(tmpTitle != null) {
            this.title = tmpTitle;
        }
        else
        {
            this.title = "Template Title";
        }
        return this;
    }

    public String getTitle(){
        return this.title;
    }

    public String getBody(){
        return this.body;
    }

    public HTMLTemplate build(){
        if(this.fileName == null){
            this.fileName = "automatic_file.html";
        }
        return new HTMLTemplate(this);
    }
}
