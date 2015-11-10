import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;

/**
 *
 */
public class Upper
{
    // instance variables - replace the example below with your own
   public static void main (String[] args){
        try{
            Upper x = new Upper();
            x.upper("Ujisumber.txt","kopi.txt");
        }
        
        catch(IOException kesalahan){
            System.out.printf("UjiSasaran : %s",kesalahan);
        }
    }
    
      public void upper(String sumber,String sasaran) throws IOException {
      FileInputStream masukan = null;
      FileOutputStream keluaran = null;
      
      try{
          masukan = new FileInputStream(sumber);
          keluaran = new FileOutputStream(sasaran);
          
          int karakter = masukan.read();
           
            while (karakter != -1) {
                karakter=Character.toUpperCase(karakter); //untuk mmbesarkan karakter
                 keluaran.write(karakter);
                karakter = masukan.read();
            }
            keluaran.flush();
        }
        
        finally {
            if (masukan != null)
                masukan.close();
            if (keluaran != null)
                keluaran.close();
        }
    }
}
