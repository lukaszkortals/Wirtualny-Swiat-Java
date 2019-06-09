package okno;

import rosliny.Trawa;
import rosliny.Guarana;
import rosliny.WilczeJagody;
import zwierzeta.Kret;
import zwierzeta.Lis;
import zwierzeta.Mysz;
import zwierzeta.Owca;
import zwierzeta.Wilk;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import witualnyswiat.Swiat;
@SuppressWarnings("serial")
public class Okno extends JFrame implements ActionListener, MouseListener {
   
    private int  wysokoscMapy, szerokoscMapy, srodekX, srodekY;
    private JTextField tWysokosc, tSzerokosc;
    private Swiat swiat;
    private Mapa mapa;
    
    
    private JTextArea tZdarzenia;
    
    public Okno(){
        
        Dimension ekran = Toolkit.getDefaultToolkit().getScreenSize();	
        this.setSrodekX((int) ekran.getWidth()/2);
        this.setSrodekY((int) ekran.getHeight()/2);
    
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLayout(null);
        this.setVisible(true);
        this.setVisible(true);
        this.setVisible(true);
        tWysokosc = new JTextField("");
        tSzerokosc = new JTextField("");
        tZdarzenia = new JTextArea("");
        this.pokazOknoPowitalne();        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
       Object zdarzenie = e.getActionCommand();
       if (zdarzenie == "Wyjdź"){
               dispose();
           
       } else if (zdarzenie == "Uruchom") {
           
            if (this.getTWysokosc().getText().equals("")) {
                this.setWysokoscMapy(20);
            }else{
                this.setWysokoscMapy(Integer.parseInt(this.getTWysokosc().getText()));
            }

            if (this.getTSzerokosc().getText().equals("")) {
                this.setSzerokoscMapy(20);
            }else{
                this.setSzerokoscMapy(Integer.parseInt(this.getTSzerokosc().getText()));
            }

            this.pokazOknoSwiata();
                  
       } else if (zdarzenie == "Nowa tura") {
           this.getSwiat().wykonajNastepnaTure();
           this.getMapa().odrysuj(this.getSwiat().rysuj());
         
          String info = "";
          
          for (int i =0 ; i < this.getSwiat().getZdarzenia().size(); i++){
              info += this.getSwiat().getZdarzenia().get(i) + "\n";
          }
          
          this.getZdarzenia().setText(info);
          
          this.repaint();
           
       }else if (zdarzenie == "Rozpocznij"){
                this.pobierzRozmiarMapy();
       } 
    }
    
    public void pokazOknoPowitalne(){
        this.setTitle("Start");
        this.setSize(205,150);
        
        this.setLocation(this.getSrodekX() - this.getWidth()/2, this.getSrodekY() - this.getHeight()/2);
        this.setLocation(this.getSrodekX() - this.getWidth()/2, this.getSrodekY() - this.getHeight()/2);
        
        JLabel lAutor = new JLabel("Łukasz Kortals");
        lAutor.setBounds(50, 1, 150, 25);
        this.getContentPane().add(lAutor);
        
        JLabel lNumerIndeksu = new JLabel("nr : 141452");
        lNumerIndeksu.setBounds(60, 20, 150, 25);
       this.getContentPane().add(lNumerIndeksu);        
        
        JButton bStworzenieSwiata = new JButton("Rozpocznij");
        bStworzenieSwiata.setBounds(20, 50, 150, 25);
        this.getContentPane().add(bStworzenieSwiata);
        bStworzenieSwiata.addActionListener(this);
        
        JButton bKoniec = new JButton("Wyjdź");
        bKoniec.setBounds(20,85, 150,25);  
        this.getContentPane().add(bKoniec);
        bKoniec.addActionListener(this);
       
    }
    
    public void pobierzRozmiarMapy(){
        this.getContentPane().removeAll();
        this.setTitle("Wirtualny Świat");
        this.setSize(205,230);
        
        this.setLocation(this.getSrodekX() - this.getWidth()/2, this.getSrodekY() - this.getHeight()/2);
        this.setLocation(this.getSrodekX() - this.getWidth()/2, this.getSrodekY() - this.getHeight()/2);
        
       JLabel lWysokosc = new JLabel("Podaj wysokość: ");
       lWysokosc.setBounds(20, 1, 150, 25);
       this.getContentPane().add(lWysokosc);
       
       JLabel lSzerokosc = new JLabel("Podaj szerokość: ");
       lSzerokosc.setBounds(20, 50 , 150, 25);
       this.getContentPane().add(lSzerokosc);
    
       this.getTWysokosc().setBounds(20, 26, 150, 25);
       this.getContentPane().add(this.getTWysokosc());
      
      
       this.getTSzerokosc().setBounds(20, 76 , 150, 25);
       this.getContentPane().add(this.getTSzerokosc());
       
        JButton bStworzenieSwiata = new JButton("Uruchom");
        bStworzenieSwiata.setBounds(20, 110, 150, 25);
        this.getContentPane().add(bStworzenieSwiata);
        bStworzenieSwiata.addActionListener(this);
        
        
        JButton bKoniec = new JButton("Wyjdź");
        bKoniec.setBounds(20,145, 150,25);  
        this.getContentPane().add(bKoniec);
        bKoniec.addActionListener(this);
     
    }
    
    public void pokazOknoSwiata(){
        this.getContentPane().removeAll();
        this.setTitle("Wirtualny Świat");
        this.setSize(700, 660);
        
        this.setLocation(this.getSrodekX() - this.getWidth()/2, this.getSrodekY() - this.getHeight()/2);
        this.setLocation(this.getSrodekX() - this.getWidth()/2, this.getSrodekY() - this.getHeight()/2);
        
        JButton bNowaTura = new JButton("Nowa tura");
        JButton  bKoniec = new JButton("Wyjdź");
        bNowaTura.setBounds(520,15, 150,50);
        bKoniec.setBounds(520,75, 150,25);
        add(bNowaTura);
        add(bKoniec);
        bNowaTura.addActionListener(this);
        bKoniec.addActionListener(this);
        
        this.setSwiat(new Swiat(this.getWysokoscMapy(), this.getSzerokoscMapy()));
        
       Guarana.dodajNowy(this.getSwiat());
       Trawa.dodajNowy(this.getSwiat());
       WilczeJagody.dodajNowy(this.getSwiat());
        
        Kret.dodajNowy(this.getSwiat());
        Lis.dodajNowy(this.getSwiat());
        Mysz.dodajNowy(this.getSwiat());
        Owca.dodajNowy(this.getSwiat());
        Wilk.dodajNowy(this.getSwiat());
        
        
        this.setMapa(new Mapa(this.getSwiat().rysuj()));
        
        this.setResizable(false);
        mapa.setBounds(1, 1, 519, 521);
        this.getContentPane().add(mapa);
       
        
     
        JPanel panelZdarzen = new JPanel(new GridLayout(1, 1));
		
        panelZdarzen.setBounds(1, 520, 680, 100);
        panelZdarzen.setBorder(BorderFactory.createTitledBorder("Zdarzenia"));
        this.getContentPane().add(panelZdarzen);
        
        
        
        JScrollPane scrollPanel = new JScrollPane(this.getZdarzenia(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
        panelZdarzen.add(scrollPanel);
    }
    //setters
    public void setWysokoscMapy(int wysokosc){
        this.wysokoscMapy = wysokosc;
    }
    public void setSzerokoscMapy(int szerokosc){
        this.szerokoscMapy = szerokosc;
    }
    public void setSrodekX(int x){
        this.srodekX = x;
    }
    public void setSrodekY(int y){
        this.srodekY = y;
    }
    public void setSwiat(Swiat swiat){
        this.swiat = swiat;
    }
    public void setMapa(Mapa mapa){
        this.mapa = mapa;
    }
    public void setTWysokosc(JTextField wysokosc){
        this.tWysokosc = wysokosc;
    }
    public void setTSzerokosc(JTextField szerokosc){
        this.tSzerokosc = szerokosc;
    }
    public void setZdarzenia(JTextArea zdarzenia){
        this.tZdarzenia = zdarzenia;
    }
    //Getters
    public int getSzerokoscMapy(){
        return this.szerokoscMapy;
    }
    public int getWysokoscMapy(){
        return this.wysokoscMapy;
    }
    public int getSrodekX(){
        return this.srodekX;
    }
    public int getSrodekY(){
        return this.srodekY;
    }
    public Mapa getMapa(){
        return this.mapa;
    }
    public Swiat getSwiat(){
        return this.swiat;
    }
    public JTextField getTWysokosc(){
        return this.tWysokosc;
    }
    public JTextField getTSzerokosc(){
        return this.tSzerokosc;
    }
    public JTextArea getZdarzenia(){
        return this.tZdarzenia;
    }
    @Override
    public void mouseClicked(MouseEvent event) {
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void mousePressed(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {		
    }
}
