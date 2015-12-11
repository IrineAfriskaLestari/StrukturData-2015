import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.*;

public class Chat {    
    public void whois(String pesan) 
                throws UnknownHostException, IOException {
        // 0. Buka socket
        koneksi = new Socket("google.co.id", 80);//dibawah kominfo untuk registrasi utama diIndonesia protocol whois di port 43

        // Kirim perintah untuk informasi namaDomain
        kirimPerintah(pesan);
        
        // Baca balasannya
        bacaBalasan();

        // Tutup socket-nya => dengan sendirinya menutup semua stream
        koneksi.close();
    }
    
    public void kirimPerintah(String pesan) throws IOException {
        // 1 & 2. Minta socket untuk ditulis dan Langsung dibuka
        OutputStream keluaran = koneksi.getOutputStream();
        // Note: Karena protokol-nya berbasis teks pakai writer aja.
        Writer keluaranWriter = new OutputStreamWriter(keluaran); 
        BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
        keluaranBuff.write(pesan);
        keluaranBuff.newLine();
        // 3. Selagi ada data kirim
        keluaranBuff.flush(); // Paksa kirim data yang belum terkirim
    }
    
    public void bacaBalasan() throws IOException {
        // 1 & 2. Minta socket untuk baca -> Langsung dibuka
        InputStream masukan = koneksi.getInputStream();
        // Karena keluarannya panjang sehingga harus dibuffer
        BufferedInputStream masukanBuffer = new BufferedInputStream(masukan);
        // Selagi masih ada data baca
        System.out.print ("Orang :");
        int data = masukanBuffer.read();
        while (data != -1) {
            System.out.write((char) data);
            data = masukanBuffer.read();
        }
    }
    
    private Socket koneksi = null;
}
