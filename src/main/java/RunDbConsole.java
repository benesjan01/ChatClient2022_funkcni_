import org.apache.derby.tools.ij;

public class RunDbConsole {
    public static void main(String[] arg){
        //connect 'jdbc:derby:ChatClientDb_skB;create=true';
        //connect 'jdbc:derby:ChatClientDb_skB';
        try {
            ij.main(arg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
