package oyunekrani;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.WHITE;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

class ammo{
    private int x;
    private int y;

    public ammo(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }    
}

class Sepet{
    private int x;
    private int y;
    public int solsag;
    public Sepet(int x, int y,int z) {
        this.x = x;
        this.y = y;
        this.solsag=z;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }    
}

class Ates{
    private int x;
    private int y;
    private int way;
    public Ates(int x, int y,int z) {
        this.x = x;
        this.y = y;
        this.way=z;
    }
    public int getWay() {
        return way;
    }
    public void setWay(int way) {
        this.way = way;
    }    
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }   
}

public class Oyun extends JPanel implements KeyListener,ActionListener{

    Timer timer=new Timer(10,this);
    Timer timer2=new Timer(10,this);
    boolean mainMenu = true;
    static Color tan = Color.decode("#F4EBC3");
    static Color darkGreen = Color.decode("#668284");
    static Color buttonColor = Color.decode("#A2896B");
    Rectangle header = new Rectangle(0, 0, 500, 100);
    Rectangle body = new Rectangle(0, 100, 500, 400);
    Rectangle start = new Rectangle(150, 150, 200, 40);
    private int zaman=0,gecen_sure=0,harcanan_ates=0,atesdirY=1,topX=0,score=0;
    private BufferedImage image,image2,egg,yuva,background,top,bullet,basketleft,basketright;        
    private ArrayList<Sepet> sepetler=new ArrayList<Sepet>();
    private ArrayList<Ates> atesler=new ArrayList<Ates>();
    private ArrayList<ammo> mermiler=new ArrayList<ammo>();
    private int topdirX=2,uzayGemisiX=200,uzayGemisiY=60,dirUzayX=20,simetri=1;
    private int level,left,right,at,ss,at2,tata,seviye;
    Random r =new Random();
    Random r2=new Random();
    Random s=new Random();
    Random s3=new Random();
    Random s4=new Random();
    static JFrame f;
    static JButton b,b1,b2,b3;
    static JLabel l;
    
    public void drawCenteredString(String s, int w, int h, Graphics g) {
    FontMetrics fm = g.getFontMetrics();
    int x = (w - fm.stringWidth(s)) / 2;
    int y = (fm.getAscent() + (h- (fm.getAscent() + fm.getDescent())) / 2);
    g.drawString(s, x, y);
}
    
    public boolean kontrolEt(){
        for(Ates ates: atesler)
            for(Sepet sepet:sepetler)
                if(new Rectangle(ates.getX(),ates.getY(),20,20).intersects(new Rectangle(sepet.getX()+10,sepet.getY()+15,20,30))){
                    sepetler.remove(sepet);
                    atesler.remove(ates);
                    score+=10;
                    return true;
                }
                                           
                
        return false;
    }    
    public boolean kontrolEt2(){
            //System.out.println(tata );
        for(ammo mermi:mermiler)
            if(new Rectangle(mermi.getX(),mermi.getY(),5,15).intersects(new Rectangle(uzayGemisiX+2,uzayGemisiY,88,20)))
               return true;                
        return false;
    }
    
    
    
    public Oyun() {
        
        try {
            
            image=ImageIO.read(new FileImageInputStream(new File("Eagle.png")));
            image2=ImageIO.read(new FileImageInputStream(new File("Eagle2.png")));
            egg=ImageIO.read(new FileImageInputStream(new File("egg.png")));
            bullet=ImageIO.read(new FileImageInputStream(new File("bullets.png")));
            background=ImageIO.read(new FileImageInputStream(new File("background.png")));
            //if(solsag==1)
                basketleft=ImageIO.read(new FileImageInputStream(new File("basketleft.png")));
            //if(solsag==2)
              basketright=ImageIO.read(new FileImageInputStream(new File("basketright.png")));
            
            } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        Dimension d=this.getSize();
        gecen_sure+=5;
        g.drawImage(background,0,0,null);
        tata=topX+800;
        if(simetri==1)
        g.drawImage(image,uzayGemisiX,uzayGemisiY,image.getWidth()/8,image.getHeight()/8,this);
        else
        g.drawImage(image2,uzayGemisiX,uzayGemisiY,image2.getWidth()/8,image2.getHeight()/8,this);
        zaman++;
        //g.setColor(Color.white);
        //g.fillRect(0,0,500,0);  
       // g.setFont(new Font("Eagle",Font.BOLD,24));
        //drawCenteredString("Black Eagle!", d.width, 100, g);
        g.setColor(darkGreen);
        g.fillRect(header.x, header.y, header.width, 30);
        //g.setFont(new Font("Courier", Font.BOLD, 24));
        g.setColor(Color.BLACK);
        drawCenteredString("Eagle In The Sky!\n", d.width, 25, g);
        
        g.setColor(tan);
        g.fillRect(0,30, 120, 30);
        g.setColor(Color.BLACK);
        drawCenteredString("Current Level :\n", 80, 80, g);
        
        if(seviye==1)drawCenteredString(" 1 ", 180, 80, g);
        if(seviye==2)drawCenteredString(" 2 ", 180, 80, g);
        if(seviye==3)drawCenteredString(" 3 ", 180, 80, g);
        if(seviye==4)drawCenteredString(" 4 ", 180, 80, g);
        
        for(Ates ates:atesler){
            if(ates.getY()<50)
                atesler.remove(ates);    
        }
        
        for(ammo mermi:mermiler){
            g.drawImage(bullet,mermi.getX(),mermi.getY(),bullet.getWidth()/10,bullet.getHeight()/10,this);
        
        }
        
        for(Sepet sepet:sepetler){
            if(sepet.solsag==1)
                g.drawImage(basketleft, sepet.getX(), sepet.getY(),basketleft.getWidth()/10,basketleft.getHeight()/10, this);
            else if(sepet.solsag==2)
                g.drawImage(basketright, sepet.getX(), sepet.getY(),basketright.getWidth()/10,basketright.getHeight()/10, this);
            
        } 
       
        for(Ates ates:atesler){
            g.drawImage(egg,ates.getX(),ates.getY(),egg.getWidth()/40, egg.getHeight()/40,this);            
        }        
        
        if(kontrolEt2()){
            timer.stop();
            String message="Yazık Kafana...\n"+ 
                           "Score=========>"+ score+"\n"+
                           "harcanan-ates==>"+ harcanan_ates +
                           "\nGeçen Süre :" + gecen_sure/1000.0;   
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
            
        }
        
        if(kontrolEt()){
            /*for(int y=0;y<1;y++){
            String message="Point increased +10\n";
            
            //JOptionPane.showMessageDialog(this,message);
            }*/
        } 
    }

    @Override
    public void repaint() {
        super.repaint();
    }                
    @Override
    public void keyTyped(KeyEvent e) {   
    } 
    @Override
    public void keyPressed(KeyEvent e) {
        int c=e.getKeyCode();
        
        if(c==KeyEvent.VK_A){
            if(uzayGemisiX<=-40){
                uzayGemisiX=-40;
            }
            else{
                uzayGemisiX-=dirUzayX;
            }
            simetri=1;
        }
        if(c==KeyEvent.VK_W){
            if(uzayGemisiY<=60){
                uzayGemisiY=60;
            }
            else{
                uzayGemisiY-=dirUzayX;
            }
            
        }
         if(c==KeyEvent.VK_S){
            if(uzayGemisiY>=600){
                uzayGemisiY=600;
            }
            else{
                uzayGemisiY+=dirUzayX;
            }
        }
        else if(c==KeyEvent.VK_D){
            
            if(uzayGemisiX>=430){
                uzayGemisiX=430;
            }
            else{
                uzayGemisiX+=dirUzayX;
            }
            simetri=0;
        }
            
        else if(c==KeyEvent.VK_SPACE){        
            atesler.add(new Ates(uzayGemisiX+40,uzayGemisiY+30,simetri));         
            harcanan_ates++;                                   
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       
        at=r.nextInt(450);
        at2=r2.nextInt(450);
        left=s3.nextInt(600)+60;
        right=s4.nextInt(550)+60;
        ss=s.nextInt(3);
        level=500;
        seviye=1;
        if(score>50){
            level=400;
            seviye=2;
        }
        if(score>200){
            level=350;
            seviye=3;
        }
        if(score>300){
            level=2900;
            seviye=4;
        }
        if(score>500){
            level=180;
            seviye=5;
        }
        if((gecen_sure%level)==0){
            mermiler.add(new ammo(at,800));
        }
        if((gecen_sure%(1000))==0){
            if(ss==1){
                sepetler.add(new Sepet(-10,left,1));
            }
            else if(ss==0){
                sepetler.add(new Sepet(440,right,2));
            }
        }
        for(ammo mermi:mermiler){
            mermi.setY(mermi.getY()-atesdirY);
        }
        
        /*for(Sepet sepet:sepetler){
            
            sepet.setY(sepet.getY()-atesdirY);
        }*/
        
        for(Ates ates:atesler){               
            if(ates.getWay()==1)
                ates.setX(ates.getX()-atesdirY); 
            else
                ates.setX(ates.getX()+atesdirY);
                    
        }
        
        topX-=topdirX;
        repaint();
    }
}