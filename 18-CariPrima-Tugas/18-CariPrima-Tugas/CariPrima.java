import java.io.FileWriter;
import java.io.IOException;

public class CariPrima {
    public static void main() throws IOException {
        // Buat berkas untuk menulis hasil. Pakai WRITER karena yang ditulis 
        // berkas text.
        FileWriter berkas = new FileWriter(NAMA_BERKAS);
        
        // Buat array dari thread
        BenarPrima[] benarPrima = new BenarPrima[JUMLAH_THREAD];
        Thread [] thread = new Thread[JUMLAH_THREAD];
        // Mulai hitung dari angka 2, karena 1 otomatis bukan prima
        int angka = 2;
        // Loop sampai batas atas yang diminta
     while (angka<=ANGKA_TERBESAR) {
         
         
        for(int q=0; q<JUMLAH_THREAD; q++){
          
            benarPrima[q] = new BenarPrima(angka);
            thread[q] =new Thread(benarPrima[q]);
            angka++;
        }       
    
    
        for (int counterThread=0; counterThread<JUMLAH_THREAD; ++counterThread)
           thread[counterThread].start();
           
           //tunggu Thread siap
            for(int counterThread = 0; counterThread < JUMLAH_THREAD; ++counterThread)
                while (benarPrima[counterThread].selesai()==false){ }
                
            for(int q=0; q<JUMLAH_THREAD; q++){
                if(benarPrima[q].selesai()){
                    if(benarPrima[q].prima()){
                        
                    synchronized(berkas) {
                       try {
                          berkas.write(benarPrima[q].angka()+"\n");
                          //System.out.println("tes");
                       }
                       catch (IOException salah) {
                          System.out.printf("Sistem Error: %s", salah);
                       }
                    }
                }
            }
        }
    }
        berkas.close();
  }
    
    private final static String NAMA_BERKAS = "prima.log";
    private final static int JUMLAH_THREAD = 10;
    private final static int ANGKA_TERBESAR = 100000;
}