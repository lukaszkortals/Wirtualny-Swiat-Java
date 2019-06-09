package witualnyswiat;

 public abstract class Organizm implements Comparable<Organizm>{
    private int sila, inicjatywa, polozenieX, polozenieY, czasZycia, kolor, id;
    private boolean czyZywy, czyRobilRuch;
    private Swiat swiat;
    private String gatunek;
      public Organizm(){
        this.setCzyRobilRuch(true);
        this.setCzyZywy(true);
        this.setCzasZycia(0);
        
      }

    public abstract void akcja();
    public abstract void kolizja(Organizm atakowany);
    public abstract boolean reakcja(Organizm atakujacy);
    public abstract void rozmnazanie();
    
    
    @Override
    public int compareTo(Organizm drugi){
      if (this.getInicjatywa() == drugi.getInicjatywa()) {		
          if(this.getCzasZycia() == drugi.getCzasZycia() ){
              return 0;
          }else if(this.getCzasZycia() > drugi.getCzasZycia() ){
              return -1;
          }else{
              return 1;
          }
      }else if(this.getInicjatywa() > drugi.getInicjatywa()) {
          return -1;
      }else{
          return 1;
      }
    }
    public int getNowyX(int liczba){
        int nowy;
        if(liczba == 0){
            nowy = this.getPolozenieX() + 1;
        }else if(liczba == 2){
            nowy = this.getPolozenieX() - 1;
        }else{
            nowy = this.getPolozenieX();
        }
        if (nowy >= 0 && nowy < this.getSwiat().getSzerokosc() ) return nowy;
        else return this.getPolozenieX();
    }
    public int getNowyY(int liczba){
        int nowy;
        if(liczba == 0){
            nowy = this.getPolozenieY() + 1;
        }else if(liczba == 1){
            nowy = this.getPolozenieY() - 1;
        }else{
            nowy = this.getPolozenieY();
        }
        if (nowy >= 0 && nowy < this.getSwiat().getWysokosc() )return nowy;
        else return this.getPolozenieY();
    }
    public void zabij(Organizm ofiara){
        if (this.getGatunek() == "Wilcze Jagody"){
            ofiara.setCzyRobilRuch(true);
            ofiara.setCzyZywy(false);
            this.getSwiat().dodajZdarzenie(this.getGatunek() + " zabija " + ofiara.getGatunek() + " w [" + this.getPolozenieX() + "," + this.getPolozenieY() + "] !");
        }
        else {
            if (!ofiara.reakcja(this) && this.getGatunek() != "Wilcze Jagody" ){
                ofiara.setCzyRobilRuch(true);
                ofiara.setCzyZywy(false);
                this.getSwiat().dodajZdarzenie(this.getGatunek() + " zabija " + ofiara.getGatunek() + " w [" + this.getPolozenieX() + "," + this.getPolozenieY() + "] !");
          }
       }
    }
    // GETERY
    public int getSila(){
        return this.sila;
    }
    public String getGatunek(){
        return this.gatunek;
    }
    public int getInicjatywa(){
        return this.inicjatywa;
    }
    public int getPolozenieX(){
        return this.polozenieX;
    }
    public int getPolozenieY(){
        return this.polozenieY;
    }
    public int getCzasZycia(){
        return this.czasZycia;
    }
   public int getId(){
       return this.id;
   }
    public boolean getCzyZywy(){
        return this.czyZywy;
    }
    public boolean getCzyRobilRuch(){
        return this.czyRobilRuch;
    }
    public Swiat getSwiat(){
        return this.swiat;
    }
    public int getKolor(){
        return this.kolor;
    }
    //SETERY
    public void setSila(int sila){
        this.sila = sila;
    }
    public void setGatunek(String gatunek){
        this.gatunek = gatunek;
    }
    public void setInicjatywa(int inicjatywa){
        this.inicjatywa = inicjatywa;
    }
    public void setPolozenieX(int x){
        this.polozenieX = x;
    }
    public void setPolozenieY(int y){
        this.polozenieY = y;
    }
    public void setCzasZycia(int czasZycia){
        this.czasZycia = czasZycia;
    }
    public void setCzasZyciaPlus1(){
        this.czasZycia ++;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setCzyZywy(boolean czyZywy){
        this.czyZywy = czyZywy;
    }
    public void setCzyRobilRuch(boolean czyRobilRuch){
        this.czyRobilRuch = czyRobilRuch;
    }
    public void setSwiat(Swiat swiat){
        this.swiat = swiat;
    }
    public void setKolor(int kolor){
        this.kolor = kolor;
    }
    public void zmienSila(int zmiana){
        this.sila += zmiana;
    }
}
