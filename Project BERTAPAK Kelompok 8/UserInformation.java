import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class UserInformation {

    private String userID;
    private String koordinat;
    ArrayList<Object[]> koor = new ArrayList<Object[]>();

    
    public void setUserId(String userID) {
        this.userID = userID;

    }

    
    public void setKoordinat(String koordinat) {
        //dapat menyimpan smua tipe data
        Object[] data = new Object[2];
        data[0]= koordinat;
        data[1]= Calendar.getInstance().getTime();
        koor.add(data);

    }

   
    public String getID(){
        return userID;
    }

   
    public String getKoordinat(){
        String hasil ="";
        for(int nilai=0; nilai<koor.size() ; nilai++){
            hasil+=(koor.get(nilai))[0]+" ";
        }
        return hasil;
    }

    public String getData(String awal, String akhir){
        try{
            String koordinat="";
            int jumlahMelangkah = 0;
            int jumlahKoordinat = 0;
            
            System.out.println("ID "+ userID);
            String[] satu=awal.split(":");
            String[] dua=akhir.split(":");

            Calendar result;
            Date pertama, terakhir , nampung;

            result = Calendar.getInstance();
            result.set(Calendar.HOUR_OF_DAY, Integer.parseInt(satu[0]));
            result.set(Calendar.MINUTE, Integer.parseInt(satu[1]));
            result.set(Calendar.SECOND, Integer.parseInt(satu[2]));
            pertama=result.getTime();

            result = Calendar.getInstance();
            result.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dua[0]));
            result.set(Calendar.MINUTE, Integer.parseInt(dua[1]));
            result.set(Calendar.SECOND, Integer.parseInt(dua[2]));
            terakhir=result.getTime();

            for(int x=0; x<koor.size(); x++){
                nampung=(Date)((koor.get(x))[1]);
                if(nampung.after(pertama)&&nampung.before(terakhir)){
                    koordinat+="["+(String)((koor.get(x))[0])+"]";
                    jumlahMelangkah++;
                    
                }
                else if(nampung.after(terakhir))
                    break;
            }

            if(jumlahMelangkah==0 && koordinat.length()==0){
                return "Data Tidak ditemukan";
            }
            return "JumlahMelangkah = "+ jumlahMelangkah + " Koordinat = " +koordinat;

        }catch(Exception o){
            return null;

        }

    }
}
