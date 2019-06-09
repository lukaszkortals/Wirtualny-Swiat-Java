package witualnyswiat;

public abstract class Zwierze extends Organizm {
 
    @Override
    public boolean reakcja(Organizm atakujacy){
        return false;
    }
    
    @Override
    public void kolizja(Organizm atakowany){
        if (atakowany.getClass() == this.getClass()){
            this.rozmnazanie();
        }
        else {
            if (atakowany.getInicjatywa() == 0 && atakowany.getSila()==0){
                this.idz(atakowany.getPolozenieX(), atakowany.getPolozenieY());
                this.zjedz(atakowany);
                
            } else if (this.getSila() > atakowany.getSila()){
                this.idz(atakowany.getPolozenieX(), atakowany.getPolozenieY());
                this.zabij(atakowany);
                
            } else if(this.getSila() < atakowany.getSila()){
                atakowany.zabij(this);
                
            } else {
                
                if (this.getCzasZycia() > atakowany.getCzasZycia()){
                    this.idz(atakowany.getPolozenieX(), atakowany.getPolozenieY());
                    this.zabij(atakowany);
                    
                 } else if(this.getCzasZycia() < atakowany.getCzasZycia()){
                     atakowany.zabij(this);
                 }
            }
        }
    }
    public void zjedz(Organizm roslinka){
        roslinka.setCzyRobilRuch(true);
        roslinka.setCzyZywy(false);
        this.idz(roslinka.getPolozenieX(), roslinka.getPolozenieY());
        this.getSwiat().dodajZdarzenie(this.getGatunek() + " zjada " + roslinka.getGatunek() + " w [" + this.getPolozenieX() + "," + this.getPolozenieY() + "] !");
        roslinka.reakcja(this);
    }
    public void idz(int x, int y){
        this.setPolozenieX(x);
        this.setPolozenieY(y);
    }
}
