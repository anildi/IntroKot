package ttl.introkot.course._1Intro;

/**
 * @author whynot
 */
public class InitialzationInJava {
    private String name;


    public static void main(String[] args) {
        InitialzationInJava ic = new InitialzationInJava();

    }

    public void fun(Object obj) {
        if(obj instanceof String) {
            String s = (String)obj;
            s.length();
        }
    }
}
