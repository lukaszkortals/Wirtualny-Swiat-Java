package zwierzeta;

import java.util.Random;
import witualnyswiat.Organizm;
import witualnyswiat.Swiat;
import witualnyswiat.Zwierze;

public class Mysz extends Zwierze{
    public Mysz(){
        this.setCzyRobilRuch(true);
        this.setCzyZywy(true);
        this.setCzasZycia(0);
        this.setInicjatywa(6);
        this.setSila(1);
        this.setKolor(0x00FFFF);
        this.setGatunek("Mysz");
    }
    @Override
    public void akcja() {
        int nowyX=this.getPolozenieX();
        int nowyY=this.getPolozenieY();
        
        while (nowyX == this.getPolozenieX() && nowyY == this.getPolozenieY()){
            int tmpX = new Random().nextInt(3);
            int tmpY = new Random().nextInt(3);
            nowyX = this.getNowyX(tmpX);
            nowyY = this.getNowyY(tmpY);
        }
       if (this.getSwiat() == null){
           System.out.println("dalej swiat null");
       }
        for (int i =0 ; i< this.getSwiat().getOrganizmy().size(); i++){
            System.out.println(this.getSwiat().getOrganizmy().get(i));
        }
        
        Organizm temp =this.getSwiat().getOrganizm(nowyX, nowyY);
        if (temp==null){
            this.idz(nowyX, nowyY);
        }else{
            if ( this.getSwiat().getOrganizm(nowyX, nowyY).getId() != this.getId()){
                this.kolizja(this.getSwiat().getOrganizm(nowyX, nowyY));
            }
        }
    }
    
    @Override
    public boolean reakcja(Organizm atakujacy) {
         int nowyX=this.getPolozenieX();
        int nowyY=this.getPolozenieY();
        
        while (nowyX == this.getPolozenieX() && nowyY == this.getPolozenieY()){
            int tmpX = new Random().nextInt(3);
            int tmpY = new Random().nextInt(3);
            nowyX = this.getNowyX(tmpX);
            nowyY = this.getNowyY(tmpY);
        }
        
        if (this.getSwiat().getOrganizm(nowyX, nowyY)==null){
            this.idz(nowyX, nowyY);
            this.getSwiat().dodajZdarzenie(this.getGatunek() + " ucieka przed "+ atakujacy.getGatunek() + " na [" + this.getPolozenieX() + "," + this.getPolozenieY() + "] !");
        
            return true;
        } else return false;
    }
    
    @Override
    public void rozmnazanie() {
         int nowyX=this.getPolozenieX();
        int nowyY=this.getPolozenieY();
        
        while (nowyX == this.getPolozenieX() && nowyY == this.getPolozenieY()){
            int tmpX = new Random().nextInt(3);
            int tmpY = new Random().nextInt(3);
            nowyX = this.getNowyX(tmpX);
            nowyY = this.getNowyY(tmpY);
        }
        
        if (this.getSwiat().getOrganizm(nowyX, nowyY)==null){
            this.getSwiat().setOrganizm(nowyX, nowyY, new Mysz());
            this.getSwiat().getOrganizm(nowyX, nowyY).setSwiat(this.getSwiat());
            this.getSwiat().dodajZdarzenie("W [" + this.getPolozenieX() + "," + this.getPolozenieY() + "] pojawia się Mysz!");
        }
    }
    
    public static void dodajNowy(Swiat swiat){
        int max = swiat.getWysokosc() * swiat.getSzerokosc();
        int ilosc = new Random().nextInt((int) (max * 0.05));
        
        for(int i=0 ; i< ilosc ; i++){    
            boolean dodany = false;
            
            for (int j=0; j < max && !dodany ; j++)  {
                int x = new Random().nextInt((int) (swiat.getSzerokosc()));
                int y = new Random().nextInt((int) (swiat.getWysokosc()));
                
                if (swiat.getOrganizm(x, y) == null){
                    swiat.setOrganizm(x, y, new Mysz());
                    swiat.getOrganizm(x, y).setSwiat(swiat);
                    swiat.getOrganizm(x, y).setId(swiat.getLiczbaOrganizmow());
                    swiat.setLiczbaOrganizmowPlus1();
                    dodany = true;
                }
            }
        }
    }
}
