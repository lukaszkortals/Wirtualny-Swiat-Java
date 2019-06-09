package witualnyswiat;

import java.awt.image.BufferedImage;
import okno.Okno;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Swiat {
    private int wysokosc, szerokosc, tura, licznikOrganizmow;
    
     private List<Organizm> organizmy;
     private List<String> zdarzenia;
    
   public Swiat(){
       setWysokosc(20);
       setSzerokosc(20);
       setTura0();
       this.organizmy = new ArrayList<>();
       this.zdarzenia = new ArrayList<>();
   }
    
    public Swiat(int wysokosc, int szerokosc){
       setWysokosc(wysokosc);
       setSzerokosc(szerokosc);
       setTura0();
       this.organizmy = new ArrayList<>();
       this.zdarzenia = new ArrayList<>();
    }
    
     public void wykonajNastepnaTure(){
         this.sortujOrganizmy();
         for (int i =0; i< this.getOrganizmy().size( ) ; i++){
             this.getOrganizmy().get(i).setCzyRobilRuch(false);
        }
        this.setTuraPlus1();
        for (int i =0; i< this.getOrganizmy().size( ) ; i++){
            Organizm organizm = this.getOrganizmy().get(i);
            if(!organizm.getCzyRobilRuch() && organizm.getCzyZywy()) {
                organizm.akcja();
                organizm.setCzasZyciaPlus1();
                organizm.setCzyRobilRuch(true);
                
            }
        }
        for (int i =0; i< this.getOrganizmy().size( ) ; i++){
             Organizm organizm = this.getOrganizmy().get(i);
            if (!organizm.getCzyZywy()){
                 this.getOrganizmy().remove(organizm);
            }
        }
     }
    
    public void sortujOrganizmy(){
        Collections.sort(this.getOrganizmy());
    }
    public BufferedImage rysuj(){

        BufferedImage mapka = new BufferedImage(this.getSzerokosc(), this.getWysokosc(), BufferedImage.TYPE_INT_RGB);
        for(int x = 0; x < this.getSzerokosc(); x++){
            for(int y = 0; y < this.getWysokosc(); y++){
                mapka.setRGB(x, y, 0xFFFFFF);
            }
        }

        for(int i = 0; i < this.getOrganizmy().size(); i++){
            Organizm organizm = this.getOrganizmy().get(i);
            mapka.setRGB(organizm.getPolozenieX(), organizm.getPolozenieY(), organizm.getKolor());
        } 

        return mapka;
    }
    public void dodajZdarzenie(String zdarzenie){
        this.zdarzenia.add(zdarzenie);
    }
    public void setWysokosc(int wysokosc){
        this.wysokosc = wysokosc;
    }
    public void setSzerokosc(int szerokosc){
        this.szerokosc = szerokosc;
    }
    public void setOrganizm(int x, int y, Organizm organizm){
        organizm.setPolozenieX(x);
        organizm.setPolozenieY(y);
        this.organizmy.add(organizm);
    }
    public void setTuraPlus1(){
        this.tura++;
    }
    public void setTura0(){
        this.tura = 0;
    }
    public void setLicznikOrganizmow(int liczba){
        this.licznikOrganizmow = liczba;
    }
    public void setLiczbaOrganizmowPlus1(){
        this.licznikOrganizmow ++ ;
    }
    public int getWysokosc(){
        return this.wysokosc;
    }
    public int getSzerokosc(){
        return this.szerokosc;
    }
    public List<Organizm> getOrganizmy() {
        return organizmy;
    }
    public List<String> getZdarzenia() {
        return this.zdarzenia;
    }
    public int getLiczbaOrganizmow(){
        return this.licznikOrganizmow;
    }
    public Organizm getOrganizm(int x, int y){
        for(int i = 0; i < this.getOrganizmy().size(); i++){
            if(this.getOrganizmy().get(i).getPolozenieX() == x && this.getOrganizmy().get(i).getPolozenieY() == y){
	return this.getOrganizmy().get(i);
            }
        }
        return null;
    }

    public int getTura(){
        return this.tura;
    }
   
     public static void main(String[] args) {
        Okno okno = new Okno();    
    }
}
