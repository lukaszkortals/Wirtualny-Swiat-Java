package zwierzeta;

import java.util.Random;
import witualnyswiat.Organizm;
import witualnyswiat.Swiat;
import witualnyswiat.Zwierze;

public class Lis extends Zwierze{
    public Lis(){
        this.setCzyRobilRuch(true);
        this.setCzyZywy(true);
        this.setCzasZycia(0);
        this.setInicjatywa(5);
        this.setSila(9);
        this.setKolor(0xFF0000);
        this.setGatunek("Lis");
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
            this.getSwiat().setOrganizm(nowyX, nowyY, new Lis());
            this.getSwiat().getOrganizm(nowyX, nowyY).setSwiat(this.getSwiat());
            this.getSwiat().dodajZdarzenie("W [" + this.getPolozenieX() + "," + this.getPolozenieY() + "] pojawia się Lis!");
        }
    }
    @Override
    public void kolizja(Organizm atakowany){
        if (atakowany.getClass() == this.getClass()){
            this.rozmnazanie();
        }
        else {
            if (atakowany.getInicjatywa() == 0 && atakowany.getSila()==0){
                this.zjedz(atakowany);
                atakowany.reakcja(this);
                
            } else if (this.getSila() > atakowany.getSila()){
                this.idz(atakowany.getPolozenieX(), atakowany.getPolozenieY());
                this.zabij(atakowany);
                
            }else if (this.getSila() < atakowany.getSila()){
              this.getSwiat().dodajZdarzenie(this.getGatunek() + " odpuszcza atak na " + atakowany.getGatunek() + " w [" + this.getPolozenieX() + "," + this.getPolozenieY() + "] !");
                
            } else if(this.getSila() == atakowany.getSila()){
                
                if (this.getCzasZycia() > atakowany.getCzasZycia()){
                    this.idz(atakowany.getPolozenieX(), atakowany.getPolozenieY());
                    this.zabij(atakowany);
                 } else if(this.getCzasZycia() < atakowany.getCzasZycia()){
                     this.getSwiat().dodajZdarzenie(this.getGatunek() + " odpuszcza atak na " + atakowany.getGatunek() + " w [" + this.getPolozenieX() + "," + this.getPolozenieY() + "] !");
                 }
            }
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
                    swiat.setOrganizm(x, y, new Lis());
                    swiat.getOrganizm(x, y).setSwiat(swiat);
                    swiat.getOrganizm(x, y).setId(swiat.getLiczbaOrganizmow());
                    swiat.setLiczbaOrganizmowPlus1();
                    dodany = true;
                }
            }
        }
    }
}