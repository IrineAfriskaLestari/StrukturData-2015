public class Becak implements Kendaraan{
    private String plat;
    
    public Becak (String plat){
        this.plat=plat;
    }
    
    public String plat(){
        return plat;
    }
    
    public String tipe(){
        String tipe= new String ("Becak");
        return tipe;
    }
    
}
