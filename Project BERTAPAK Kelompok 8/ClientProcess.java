import java.net.Socket;
import java.net.InetAddress;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;

import java.util.Calendar;

public class ClientProcess implements Runnable {
    ArrayList<UserInformation> ID = new ArrayList<UserInformation>();

    public ClientProcess(Socket koneksi, ArrayList <UserInformation> ID ){
        this.koneksi = koneksi;
        this.ID=ID;
    }

    public void run() {        
        if (koneksi != null) {
            // Ambil IP dari koneksi
            ipStr = koneksi.getInetAddress().getHostAddress();

            try {
                // Ambil InputStream
                masukan = koneksi.getInputStream();
                masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
                // Ambil OutputStream
                keluaran = koneksi.getOutputStream();
                keluaranWriter = new BufferedWriter(new OutputStreamWriter(keluaran)); 

                // Selama masih terhubung dengan client tangani
                tangani();
            }
            catch(IOException salah) { 
                System.out.println(salah);
            }
            finally {
                try { 
                    // Tutup koneksi
                    koneksi.close();
                }
                catch(IOException salah) {
                    System.out.println(salah);
                }                
            }
        }
    }

    /*
     * 
     */
    private void tangani() throws IOException {
        String perintah = masukanReader.readLine();
        // Jika tidak ada perintah keluar saja
        if (perintah == null)
            return;            
        // Ada perintah, hilangkan spasi depan & belakang serta ubah ke huruf besar semua
        perintah = perintah.trim().toUpperCase();

        // Ambil parameter-nya
        String[] parameter = perintah.split(" ");

        if (parameter[0].compareTo("ID") == 0 && parameter[2].equals("MELANGKAH") && parameter.length== 6) {
            UserInformation simpan =null;
            String koordinat = null;

            for(UserInformation info : ID){
                if(parameter[1].equals(info.getID()))
                    simpan=info;
            }

            if(simpan !=null){
                koordinat = parameter[4] + " " + parameter[5];
                simpan.setKoordinat(koordinat);
            }else{
                simpan = new UserInformation();
                simpan.setUserId(parameter[1]);
                koordinat = parameter[4] + " " + parameter[5];
                simpan.setKoordinat(koordinat);
                ID.add(simpan);
            }

            // Hanya bertanya siapa yang mengirim
            keluaranWriter.write("YA !Data Tersimpan");
            System.out.println("ID "+ parameter[1]);
            keluaranWriter.newLine();
            keluaranWriter.flush();

        }
        else if(parameter[0].compareTo("ID") == 0 && parameter[2].equals("KOORDINAT") && parameter.length== 7){
            //ambil kkor, tampung
            UserInformation simpan =null;
            String keluaran=null;

            for(UserInformation info : ID){
                if(parameter[1].equals(info.getID()))
                    simpan=info;
            }

            if(simpan != null){
                keluaran=simpan.getData(parameter[5],parameter[6]);
            }

            if(keluaran == null){
                keluaran = "error";
            }
            // ambil jumlah, tampung
            keluaranWriter.write(keluaran);
            keluaranWriter.newLine();
            keluaranWriter.flush();
        }
        else {
            keluaranWriter.write(PERINTAH_TIDAK_DITEMUKAN, 0, PERINTAH_TIDAK_DITEMUKAN.length());
            keluaranWriter.newLine();
            keluaranWriter.flush();
        }

        // Tampilkan perintahnya
        System.out.println("Dari: " + ipStr);
        System.out.println("perintah: " + perintah);
        System.out.println();

    }

    // Koneksi ke Client
    private Socket koneksi; 
    // IP address dari client
    private String ipStr; 

    // InputStream untuk baca perintah
    private InputStream masukan = null;
    // Reader untuk InputStream, pakai yang buffer
    private BufferedReader masukanReader = null;
    // OutputStream untuk tulis balasan
    private OutputStream keluaran = null;
    // Writer untuk OutputStream, pakai yang buffer
    private BufferedWriter keluaranWriter = null;

    private final static String PERINTAH_TIDAK_DITEMUKAN = "TIDAK! Data tidak Tersimpan!";
}