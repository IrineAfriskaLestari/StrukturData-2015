import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CaesarCipher
{
    private int shift;
    
    
    
      public CaesarCipher(int shift)  {
          this.shift=shift;
        
      
    }
    
    public static void main (String[] args){
        try{
            CaesarCipher x = new CaesarCipher(4);
            x.dekripsi("Ujisumber.txt","dekripsi.txt");
            x.enkripsi("Ujisumber.txt","enkripsi.txt");
        }
        
        catch(IOException kesalahan){
            System.out.printf("UjiSasaran : %s",kesalahan);
        }
    }
    
    public void enkripsi(String sumber,String sasaran)throws IOException{
        FileInputStream sumbershift = null;
      FileOutputStream sasaranshift = null;
      
      try{
          sumbershift = new FileInputStream(sumber);
          sasaranshift = new FileOutputStream(sasaran);
          
          int karakter = sumbershift.read();
           
            while (karakter != -1) {
                
                karakter=karakter+shift;
                 sasaranshift.write(karakter);
                karakter = sumbershift.read();
            }
            sasaranshift.flush();
        }
        
        finally {
            if (sumbershift != null)
                sumbershift.close();
            if (sasaranshift != null)
                sasaranshift.close();
        }
    }
        
    public void dekripsi(String sumber,String sasaran)throws IOException{
             FileInputStream sumbershift = null;
      FileOutputStream sasaranshift = null;
      
      try{
          sumbershift = new FileInputStream(sumber);
          sasaranshift = new FileOutputStream(sasaran);
          
          int karakter = sumbershift.read();
           
            while (karakter != -1) {
                
                karakter=karakter-shift;
                 sasaranshift.write(karakter);
                karakter = sumbershift.read();
            }
            sasaranshift.flush();
        }
        
        finally {
            if (sumbershift != null)
                sumbershift.close();
            if (sasaranshift != null)
                sasaranshift.close();
        }
        }
  }
     
