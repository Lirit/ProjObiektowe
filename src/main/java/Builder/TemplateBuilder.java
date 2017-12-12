package Builder;

interface TemplateBuilder{

    void setFileName(String name);
    String getFileName();
    HTMLTemplate build();

}
