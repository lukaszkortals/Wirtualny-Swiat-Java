package witualnyswiat;

public abstract class Roslina extends Organizm {
    
    @Override
    public void rozmnazanie(){
    };
    
    @Override
    public boolean reakcja(Organizm atakujacy){
        return false;
    };
    
    @Override
    public void kolizja(Organizm atakowany){
    };
    
}
