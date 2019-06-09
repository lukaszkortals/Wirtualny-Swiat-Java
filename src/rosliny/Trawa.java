package rosliny;

import java.util.Random;
import witualnyswiat.Roslina;
import witualnyswiat.Swiat;

public class Trawa extends Roslina {
    public Trawa(){
        this.setCzyRobilRuch(true);
        this.setCzyZywy(true);
        this.setCzasZycia(0);
        this.setInicjatywa(0);
        this.setSila(0);
        this.setKolor(0x00FF00);
        this.setGatunek("Trawa");
    };
    @Override
    public void akcja(){
        int nowyX=this.getPolozenieX();
        int nowyY=this.getPolozenieY();
        
        while (nowyX == this.getPolozenieX() && nowyY == this.getPolozenieY()){
            int tmpX = new Random().nextInt(3);
            int tmpY = new Random().nextInt(3);
            nowyX = this.getNowyX(tmpX);
            nowyY = this.getNowyY(tmpY);
        }
        
        if (this.getSwiat().getOrganizm(nowyX, nowyY)==null){
            this.getSwiat().setOrganizm(nowyX, nowyY, new Trawa());
            this.getSwiat().getOrganizm(nowyX, nowyY).setSwiat(this.getSwiat());
        }
    };
      public static void dodajNowy(Swiat swiat){
        int max = swiat.getWysokosc() * swiat.getSzerokosc();
        int ilosc = new Random().nextInt((int) (max * 0.05));
        
        for(int i=0 ; i< ilosc ; i++){    
            boolean dodany = false;
            
            for (int j=0; j < max && !dodany ; j++)  {
                int x = new Random().nextInt((int) (swiat.getSzerokosc()));
                int y = new Random().nextInt((int) (swiat.getWysokosc()));
                
                if (swiat.getOrganizm(x, y) == null){
                    swiat.setOrganizm(x, y, new Trawa());
                    swiat.getOrganizm(x, y).setSwiat(swiat);
                    swiat.getOrganizm(x, y).setId(swiat.getLiczbaOrganizmow());
                    swiat.setLiczbaOrganizmowPlus1();
                    swiat.dodajZdarzenie("W [" + x + "," + y + "] rosnie Trawa!");
                    dodany = true;
                }
            }
        }
    }
}
