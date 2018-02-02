package Builder;

import Decorator.Editor;

public class Test {

public HTMLTemplate getTemp(){
    return new HTMLBuilder("data.json")
            .setFileName()
            .setBody()
            .setTitle()
            .build();
}
    public static void main(String[] args){
        Test t = new Test();
        String html = t.getTemp().returnHTML();
        System.out.println("To: "+html);
        Editor ed = new Editor(new HTMLConfig());
        try{
        String s =  ed.readConfig("special_config.json","comments");
        System.out.println("I to: "+s);
        }
        catch(Exception e){
e.printStackTrace();
        }
    }
}
