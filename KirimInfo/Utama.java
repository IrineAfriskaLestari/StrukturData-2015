import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Write a description of class Utama here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Utama
{
    public static void main(String[] args) {
        try {
            KirimInfo tanya = new KirimInfo();
            tanya.whois("Irine Afriska Lestari (1408107010019)");
        }
        catch (UnknownHostException ex) {
            System.err.println(ex);
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }

}
