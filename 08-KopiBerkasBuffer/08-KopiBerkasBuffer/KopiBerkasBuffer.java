import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;

/**
 * 
 */

public class KopiBerkasBuffer
{
    public static void main (String[] args){
        try{
            KopiBerkasBuffer x = new KopiBerkasBuffer();
            x.kopi("Ujisumber.txt","kopi.txt");
        }
        
        catch(IOException kesalahan){
            System.out.printf("UjiSasaran : %s",kesalahan);
        }
    }
    
      public void kopi(String sumber,String sasaran) throws IOException {
      FileInputStream masukandefault = null;
      BufferedInputStream masukan = null;
      
      BufferedOutputStream keluaran = null;
      FileOutputStream keluarandefault = null;
      
      try{
          masukandefault = new FileInputStream(sumber);
          masukan = new BufferedInputStream(masukandefault);
         
          keluarandefault = new FileOutputStream(sasaran);
          keluaran = new BufferedOutputStream(keluarandefault);
          
          
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