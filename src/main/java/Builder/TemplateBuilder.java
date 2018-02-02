package Builder;

public interface TemplateBuilder{
    void setConfFile(String fileName);
    HTMLBuilder setFileName();
    HTMLBuilder setBody();
    HTMLBuilder setTitle();
    String getTitle();
    String getBody();
    String getFileName();
    HTMLTemplate build();

}
