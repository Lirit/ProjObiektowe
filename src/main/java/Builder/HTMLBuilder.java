package Builder;

public class HTMLBuilder implements TemplateBuilder{
    private String fileName;

    public HTMLBuilder(String filename){
        this.fileName = filename;
    }
    public void setFileName(String name){
        this.fileName = name;
    }

    public String getFileName(){
        return this.fileName;
    }

    public HTMLTemplate build(){
        String tmpName = null;
        HTMLConfig c = new HTMLConfig();
        try{
            tmpName = c.readConfig("HTMLConfig.json");
        }
        catch(Exception e){
            System.out.println("There was a problem with accessing the configuration file.");
        }
        HTMLBuilder build = new HTMLBuilder(tmpName);
        return new HTMLTemplate(build);
    }
}
