import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;

public class KopiBerkas
{
      public void kopi(String sumber,String sasaran) throws IOException{
      FileInputStream masukan = null;
      FileOutputStream keluaran = null;
      
      try{
          masukan = new FileInputStream(sumber);
          keluaran = new FileOutputStream(sasaran);
          
          int karakter = masukan.read();
           
            while (karakter != -1) {
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
