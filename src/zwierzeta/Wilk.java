package zwierzeta;

import java.util.Random;
import witualnyswiat.Swiat;
import witualnyswiat.Zwierze;

public class Wilk extends Zwierze{
    public Wilk(){
        this.setInicjatywa(5);
        this.setSila(9);
        this.setKolor(0x000000);
        this.setGatunek("Wilk");
    };
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
        
        if (this.getSwiat().getOrganizm(nowyX, nowyY)==null){
            this.idz(nowyX, nowyY);
        }else{
            if ( this.getSwiat().getOrganizm(nowyX, nowyY).getId() != this.getId()){
                this.kolizja(this.getSwiat().getOrganizm(nowyX, nowyY));
            }
        }
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
            this.getSwiat().setOrganizm(nowyX, nowyY, new Wilk());
            this.getSwiat().getOrganizm(nowyX, nowyY).setSwiat(this.getSwiat());
            this.getSwiat().dodajZdarzenie("W [" + this.getPolozenieX() + "," + this.getPolozenieY() + "] pojawia się Wilk!");
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
                    swiat.setOrganizm(x, y, new Wilk());
                    swiat.getOrganizm(x, y).setSwiat(swiat);
                    swiat.getOrganizm(x, y).setId(swiat.getLiczbaOrganizmow());
                    swiat.setLiczbaOrganizmowPlus1();
                    dodany = true;
                }
            }
        }
    }
}
