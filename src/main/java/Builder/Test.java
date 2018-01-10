package Builder;

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
        //System.out.println(html);
    }
}
