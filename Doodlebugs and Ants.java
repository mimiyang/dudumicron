import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Yhao5Proj6
{
    
    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    
    
    public static void main(String[] args){
        
        int sleeptime=1000;
        
        if(args.length == 2 && args[0].equals("-d") && isNumeric(args[1])){
            sleeptime = Integer.parseInt(args[1]);
            
        }else if(args.length == 0){
            sleeptime = 1000;
        }else
            System.exit(0);
        
        
        Picture game = new Picture();
        Mylist bugList = new Mylist();
        Mylist antList = new Mylist();
        for(int i=0; i<100; i++){
            Ant ant  = new Ant(game, Color.BLACK, "Ant");
            antList.insert(ant);
            
            
        }
        for(int i=0; i<5; i++){
            Doodlebug bug = new Doodlebug(game, Color.RED, "Doodlebug");
            bugList.insert(bug);
            
        }
        
        
        for(int i=0; i<20; i++){
            game.nextDay();
            game.sleep(sleeptime);}
        /*
         while((bugList.top() )!= null){
         Doodlebug b = (Doodlebug) bugList.top();
         b.setDayLastMoved(1);
         b.spawn(game, Color.PINK, "Doodlebug");
         b.hunt(game);
         game.sleep(1000);
         b.starve(game);
         
         game.sleep(1000);
         b.setDayLastMoved(2);
         b.hunt(game);
         
         System.out.println("-----------------------");
         b.starve(game);
         
         System.out.println(b.getnumEat(0)+"----------&&&&&"+b.getnumEat(1)+" "+b.getnumEat(2));
         game.sleep(1000);
         b.setDayLastMoved(3);
         b.hunt(game);
         b.starve(game);
         
         bugList.pop();
         }
         
         */
    }
    
    
    
}

class Picture
{
    private GridDisplay disp;
    private Creature[][] grid;
    private int dayCount;
    private int rows;
    private int cols;
    
    public Picture()
    {
        rows = 20;
        cols = 20;
        disp = new GridDisplay(rows, cols);
        grid = new Creature[rows][cols];
        
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
                grid[i][j]=null;
        dayCount = 0 ;
    }
    
    public Creature getGrid(int n, int m){
        return grid[n][m];
        
    }
    public void delGrid(int n, int m){
        grid[n][m]= null;
        disp.setColor(n,m,Color.GREEN);
    }
    
    public void delGrid(int n, int m, Color c){
        grid[n][m]= null;
        disp.setColor(n,m,c);
        
    }
    
    public void sleep(int x){
        disp.mySleep(x);
    }
    
    public void nextDay()
    {
        
        Mylist bugList = new Mylist();
        Mylist antList = new Mylist();
        /*
         Mylist antList1 = new Mylist();
         Mylist antList2 = new Mylist();
         Mylist antList3 = new Mylist();
         Mylist antList4 = new Mylist();
         */
        dayCount++;
        System.out.println("DayCount: " + dayCount);
        for(int x=0;x<20;x++)
            for(int y=0; y<20; y++){
                if(grid[x][y]!=null){
                    Creature b = grid[x][y];
                    int bbd= b.getDayLastMoved();
                    bbd++;
                    b.setDayLastMoved(bbd);
                    if(b.getType() == "Doodlebug"){
                        bugList.insert(b);
                    }
                    
                }
            }
        while((bugList.top() )!= null){
            Doodlebug d = (Doodlebug) bugList.top();
            Creature baby = new Creature("Doodlebug");
            
            d.hunt(this);
            d.starve(this);
            
            
            if(d.getDayLastMoved() >=8){
                if(d.getDayLastbaby()== 0){
                    baby=d.spawn(this, Color.RED, "Doodlebug");
                    if(baby == null){
                        d.setDayLastBaby(0) ;
                    }else
                        d.setDayLastBaby(1);
                }else{
                    int bbd= d.getDayLastbaby();
                    bbd++;
                    d.setDayLastBaby(bbd);
                }
            }
            bugList.pop();
            
        }
        
        for(int x=0;x<20;x++)
            for(int y=0; y<20; y++){
                if(grid[x][y]!=null){
                    Creature b = grid[x][y];
                    if(b.getType() == "Ant"){
                        antList.insert(b);
                    }
                    
                }
            }
        
        
        /*      for(int x=0;x<rows;x++)
         for(int y=0; y<cols; y++){
         if(this.getGrid(x,y)!=null){
         Creature e = grid[x][y];
         if(e.getType()=="Ant"){
         
         e.move(this,Color.BLACK);
         if((e.getDayLastMoved() >=7 )&& (e.getDayLastMoved()%7 == 0)){
         Creature babyant = new Creature("Ant");
         
         babyant=e.spawn(this,Color.BLACK,"Ant");}
         System.out.println("ant daymoved "+ e.getDayLastMoved());
         }
         }
         }
         */
        
        
        while((antList.top())!=null){
            Ant a=(Ant)(antList.top());
            Ant babyant = new Ant("Ant");
            
            a.move(this,Color.BLACK);
            
            if((a.getDayLastMoved() >=3 )&& (a.getDayLastMoved()%3 == 0)){
                babyant=a.spawn(this,Color.BLACK,"Ant");}
            antList.pop();
        }
        /*
         
         while((antList2.top())!=null){
         Ant a=(Ant)(antList2.top());
         Creature babyant = new Creature("Ant");
         
         a.move(this,Color.BLACK);
         
         if((a.getDayLastMoved() >=3 )&& (a.getDayLastMoved()%3 == 0)){
         babyant=a.spawn(this,Color.BLACK,"Ant");}
         antList2.pop();
         }
         while((antList3.top())!=null){
         Ant a=(Ant)(antList3.top());
         Creature babyant = new Creature("Ant");
         
         a.move(this,Color.BLACK);
         
         if((a.getDayLastMoved() >=3 )&& (a.getDayLastMoved()%3 == 0)){
         babyant=a.spawn(this,Color.BLACK,"Ant");}
         antList3.pop();
         }
         while((antList4.top())!=null){
         Ant a=(Ant)(antList4.top());
         Creature babyant = new Creature("Ant");
         
         a.move(this,Color.BLACK);
         
         if((a.getDayLastMoved() >=3 )&& (a.getDayLastMoved()%3 == 0)){
         babyant=a.spawn(this,Color.BLACK,"Ant");}
         antList4.pop();
         }
         
         
         */
    }
    
    
    public void addelement(Creature a, int x, int y, Color c )
    {
        if(x<0||x>=rows||y<0||y>=cols)
            return ;
        else if(grid[x][y]!=null)
            return ;
        else{
            grid[x][y]= a;
            disp.setColor(x,y,c );}
    }
    public void addelement(Doodlebug a, int x, int y, Color c )
    {
        if(x<0||x>=rows||y<0||y>=cols)
            return ;
        else if(grid[x][y]!=null)
            return ;
        else{
            grid[x][y]= a;
            disp.setColor(x,y,c );}
    }
    public void addelement(Ant a, int x, int y, Color c )
    {
        if(x<0||x>=rows||y<0||y>=cols)
            return ;
        else if(grid[x][y]!=null)
            return ;
        else{
            grid[x][y]= a;
            disp.setColor(x,y,c );}
    }
    
    
}
class Creature
{
    private String type;
    private Picture pic;
    private int x;
    private int y;
    private int dayLastMoved;
    private int dayLastEat;
    private int dayLastBaby;
    private int numEat[] = new int[21];
    
    
    public Creature(Picture picture, Color c,String name  )
    {
        type = name;
        pic = picture;
        x = (int)(Math.random()*20);
        y = (int)(Math.random()*20);
        while(pic.getGrid(x,y)!=null){
            x = (int)(Math.random()*20);
            y = (int)(Math.random()*20);
            dayLastMoved = 0;
            numEat[0]=0;
        }
        pic.addelement(this,x,y, c);
    }
    
    public void setDayLastBaby(int v){
        dayLastBaby = v;
    }
    
    public Creature(String name)
    {
        type = name;
    }
    
    public int getnumEat(int day){
        return numEat[day];
    }
    
    public void setnumEat(int n, int d){
        
        numEat[d]= n;
    }
    
    public Picture getPic(){
        return pic;
    }
    
    public void getPic(Picture m){
        pic = m;
    }
    
    public String getType(){
        return type;
    }
    
    public void placeit(int n, int m){
        x = n;
        y = m;
    }
    
    public int getDayLastbaby(){
        return dayLastBaby;
    }
    
    public void setDayLastMoved(int dlm)
    {
        dayLastMoved = dlm;
    }
    
    public int getDayLastMoved()
    {
        return dayLastMoved;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void move(Picture picture, Color c)
    {
        pic = picture;
        int j=(int)(Math.random()*4+1);
        int i=j;
        int k=0;
    loop: while(k<=4){
        k++;
        i++;
        i=i%4;
        switch(i){
            case 0:
                if(y+1 <= 19){
                    if(pic.getGrid(x,y+1) ==null ){
                        
                        pic.addelement(this,x,y+1, c);
                        pic.delGrid(x,y);
                        placeit(x,y+1);
                        break loop;
                    }
                }
                break;
            case 1:
                if(x+1<=19){
                    if(pic.getGrid(x+1,y) == null ){
                        
                        pic.addelement(this,x+1,y,c);
                        pic.delGrid(x,y);
                        placeit(x+1,y);
                        break loop;
                    }
                }
                break;
            case 2:
                if(y-1>=0){
                    if(pic.getGrid(x,y-1) == null ){
                        
                        pic.addelement(this,x,y-1,c);
                        pic.delGrid(x,y);
                        placeit(x,y-1);
                        break loop;
                    }
                }
                break;
            case 3:
                if(x-1>=0){
                    if(pic.getGrid(x-1,y) == null){
                        
                        pic.addelement(this,x-1,y,c);
                        pic.delGrid(x,y);
                        placeit(x-1,y);
                        break loop;
                    }
                }
                break;
        }
    }
        
        
    }
    
    public Creature spawn(Picture picture, Color c, String name)
    {
        pic = picture;
        Creature baby = new Creature(name);
        int j=0;
        
    loop: for(int i=1; i<=4; i++){
        
        switch(i){
            case 1:
                if(y+1 <= 19){
                    if(pic.getGrid(x,y+1) ==null ){
                        baby.placeit(x,y+1);
                        pic.addelement(baby,x,y+1, c);
                        break loop;
                    }else
                        j++;
                }else
                    j++;
                break;
            case 2:
                if(x+1<=19){
                    if(pic.getGrid(x+1,y) == null ){
                        baby.placeit(x+1,y);
                        pic.addelement(baby,x+1,y,c);
                        break loop;
                    }else
                        j++;
                }else
                    j++;
                break;
            case 3:
                if(y-1>=0){
                    if(pic.getGrid(x,y-1) == null ){
                        baby.placeit(x,y-1);
                        pic.addelement(baby,x,y-1,c);
                        break loop;
                    }else
                        j++;
                }else
                    j++;
                break;
            case 4:
                if(x-1>=0){
                    if(pic.getGrid(x-1,y) == null){
                        baby.placeit(x-1,y);
                        pic.addelement(baby,x-1,y,c);
                        break loop;
                    }else
                        j++;
                }else
                    j++;
                break;
        }
        
    }
        if(j==4){
            
            return null;
        }else{
            
            return baby;
        }
    }
    
    
    
    
}
class Doodlebug extends Creature
{
    
    public Doodlebug(Picture picture, Color c, String bug ){
        super(picture, c, bug);
        
    }
    
    public Doodlebug(String t){
        super(t);
    }
    
    public void hunt(Picture pic0)
    {
        getPic(pic0);
        int x= getX();
        int y= getY();
        int j=0;
        
        
    loop: for(int i=1;i<=4;i++){
        
        switch(i){
            case 1:
                if(y+1 <= 19){
                    if(getPic().getGrid(x,y+1) != null){
                        if(getPic().getGrid(x,y+1).getType() =="Ant" ){
                            
                            getPic().delGrid(x,y+1);
                            placeit(x,y+1);
                            getPic().addelement(this,x,y+1, Color.RED);
                            getPic().delGrid(x,y);
                            break loop;}
                        else
                            j++;}
                    else
                        j++;}
                else
                    j++;
                
                break;
                
            case 2:
                if(x+1<=19){
                    if(getPic().getGrid(x+1,y) != null){
                        if(getPic() .getGrid(x+1,y).getType() == "Ant" ){
                            getPic() .delGrid(x+1,y);
                            placeit(x+1,y);
                            getPic() .addelement(this,x+1,y,Color.RED);
                            getPic() .delGrid(x,y);
                            
                            break loop;} else j++;}
                    else
                        j++;}
                else
                    j++;
                
                break;
            case 3:
                if(y-1>=0){
                    if(getPic().getGrid(x,y-1) != null){
                        if(getPic().getGrid(x,y-1).getType() == "Ant" ){
                            getPic().delGrid(x,y-1);
                            placeit(x,y-1);
                            getPic().addelement(this,x,y-1,Color.RED);
                            getPic().delGrid(x,y);
                            
                            break loop;
                        }else
                            j++;
                    }else
                        j++;}
                else
                    j++;
                break;
            case 4:
                if(x-1>=0){
                    if(pic0.getGrid(x-1,y) != null){
                        if(getPic().getGrid(x-1,y).getType() == "Ant"){
                            getPic().delGrid(x-1,y);
                            placeit(x-1,y);
                            getPic().addelement(this,x-1,y,Color.RED);
                            getPic().delGrid(x,y);
                            
                            break loop;
                        }else
                            j++;
                    }else
                        j++;}
                else
                    j++;
                
                break;
        }
        
    }
        if(j==4){
            move(getPic(),Color.RED);
            this.setnumEat(0,getDayLastMoved());
            System.out.println("eat: 0 at "+ getnumEat(getDayLastMoved()) +getDayLastMoved());
            
        }
        else{
            this.setnumEat(1,getDayLastMoved());
            System.out.println("eat: 1 at "+getnumEat(getDayLastMoved())+ getDayLastMoved());}
        
        
        
    }
    
    public Doodlebug spawn(Picture pic, Color c, String name)
    {
        int x = getX();
        int y= getY();
        
        Doodlebug baby = new Doodlebug(name);
        int j=0;
        
    loop: for(int i=1; i<=4; i++){
        
        switch(i){
            case 1:
                if(y+1 <= 19){
                    if(pic.getGrid(x,y+1) ==null ){
                        baby.placeit(x,y+1);
                        pic.addelement(baby,x,y+1, c);
                        break loop;
                    }else
                        j++;
                }else
                    j++;
                break;
            case 2:
                if(x+1<=19){
                    if(pic.getGrid(x+1,y) == null ){
                        baby.placeit(x+1,y);
                        pic.addelement(baby,x+1,y,c);
                        break loop;
                    }else
                        j++;
                }else
                    j++;
                break;
            case 3:
                if(y-1>=0){
                    if(pic.getGrid(x,y-1) == null ){
                        baby.placeit(x,y-1);
                        pic.addelement(baby,x,y-1,c);
                        break loop;
                    }else
                        j++;
                }else
                    j++;
                break;
            case 4:
                if(x-1>=0){
                    if(pic.getGrid(x-1,y) == null){
                        baby.placeit(x-1,y);
                        pic.addelement(baby,x-1,y,c);
                        break loop;
                    }else
                        j++;
                }else
                    j++;
                break;
        }
        
    }
        if(j==4){
            
            return null;
        }else{
            
            return baby;
        }
    }
    
    
    
    public void starve(Picture pic0){
        int m=getDayLastMoved();
        int x= getX();
        int y= getY();
        if(m>=4){
            int n=0;
            
            for(int i=0;i<=2;i++){
                n=n+ getnumEat(m);
                m--;
            }
            if(n==0){
                
                pic0.delGrid(x,y,Color.GREEN);
                System.out.println("I am dying");
                
            } else
                System.out.println("survive...");
        }
        
    }
    
    
    /* public void hunt(Picture picture)
     {
     pic = picture;
     if(pic.grid[x][y+1] != 'A' || pic.grid[x+1][y]!='A'||pic.grid[x-1][y]!='A'|| pic.grid[x][y-1]!='A'){
     numEat[dayLastMoved]=0;
     int i=(int)(Math.random()*4+1);
     loop: while(i<=4){
     
     switch(i){
     case 1:
     if(pic.grid[x][y+1] ==null ){
     pic.grid[x][y]=null;
     placeit(x,y+1);
     pic.addelement(this,x,y+1);
     break loop;
     }
     break;
     case 2:
     if(pic.grid[x+1][y] == null){
     
     pic.grid[x][y]=null;
     placeit(x+1,y);
     pic.addelement(this,x+1,y);
     break loop;
     }
     break;
     case 3:
     if(pic.grid[x][y-1] == null){
     
     pic.grid[x][y]=null;
     placeit(x,y-1);
     pic.addelement(this,x,y-1);
     break loop;
     }
     break;
     case 4:
     if(pic.grid[x-1][y] == null){
     
     pic.grid[x][y]=null;
     placeit(x-1,y);
     pic.addelement(this,x-1,y);
     break loop;
     }
     break;
     }
     }
     
     
     }
     
     else{
     numEat[dayLastMoved]=1;
     int i=(int)(Math.random()*4+1);
     loop: while(i<=4){
     
     
     switch(i){
     case 1:
     if(pic.grid[x][y+1] == 'A'){
     
     pic.grid[x][y]=null;
     placeit(x,y+1);
     pic.addelement(this,x,y+1);
     break loop;
     }
     break;
     case 2:
     if(pic.grid[x+1][y] == 'A'){
     
     pic.grid[x][y]=null;
     placeit(x+1,y);
     pic.addelement(this,x+1,y);
     break loop;
     }
     break;
     case 3:
     if(pic.grid[x][y-1] == 'A'){
     
     pic.grid[x][y]=null;
     placeit(x,y-1);
     pic.addelement(this,x,y-1);
     break loop;
     }
     break;
     case 4:
     if(pic.grid[x-1][y] == 'A'){
     
     pic.grid[x][y]=null;
     placeit(x-1,y);
     pic.addelement(this,x-1,y);
     break loop;
     }
     break;
     
     }
     }
     }
     
     }
     public int spawn(Picture picture){
     pic = picture;
     
     Doodlebug baby = new Doodlebug(pic);
     if(pic.grid[x][y+1] == null || pic.grid[x+1][y]== null ||pic.grid[x-1][y]!= null|| pic.grid[x][y-1]== null){
     return 1;
     loop: while(i<=4){
     int i=(int)(Math.random()*4+1);
     switch(i){
     case 1:
     if(pic.grid[x][y+1] ==null ){
     baby.placeit(x,y+1);
     pic.addelement(baby,x,y+1);
     break loop;
     }
     break;
     case 2:
     if(pic.grid[x+1][y] == null){
     baby.placeit(x+1,y);
     pic.addelement(baby,x+1,y);
     break loop;
     }
     break;
     case 3:
     if(pic.grid[x][y-1] == null){
     baby.placeit(x,y-1);
     pic.addelement(baby,x,y-1);
     break loop;
     }
     break;
     case 4:
     if(pic.grid[x-1][y] == null){
     baby.placeit(x-1,y);
     pic.addelement(baby,x-1,y);
     break loop;
     }
     break;
     }
     }
     }else
     return 0;
     
     
     
     
     }
     public void starve(Picture picture){
     pic = picture;
     if(dayLastMoved>=3){
     int n=0;
     int m=dayLastMoved;
     for(int i=0;i<3;i++){
     n=n+numEat[m];
     m--;
     }
     if(n==0){
     pic[x][y]=null;
     }
     
     }
     
     }
     */
}
class Ant extends Creature
{
    public Ant(Picture pic, Color c, String ant){
        super(pic, c, ant);
    }
    
    public Ant(String t){
        super(t);
    }
    
    public Ant spawn(Picture pic, Color c, String name)
    {
        int x = getX();
        int y= getY();
        
        Ant baby = new Ant(name);
        int j=0;
        
    loop: for(int i=1; i<=4; i++){
        
        switch(i){
            case 1:
                if(y+1 <= 19){
                    if(pic.getGrid(x,y+1) ==null ){
                        baby.placeit(x,y+1);
                        pic.addelement(baby,x,y+1, c);
                        break loop;
                    }else
                        j++;
                }else
                    j++;
                break;
            case 2:
                if(x+1<=19){
                    if(pic.getGrid(x+1,y) == null ){
                        baby.placeit(x+1,y);
                        pic.addelement(baby,x+1,y,c);
                        break loop;
                    }else
                        j++;
                }else
                    j++;
                break;
            case 3:
                if(y-1>=0){
                    if(pic.getGrid(x,y-1) == null ){
                        baby.placeit(x,y-1);
                        pic.addelement(baby,x,y-1,c);
                        break loop;
                    }else
                        j++;
                }else
                    j++;
                break;
            case 4:
                if(x-1>=0){
                    if(pic.getGrid(x-1,y) == null){
                        baby.placeit(x-1,y);
                        pic.addelement(baby,x-1,y,c);
                        break loop;
                    }else
                        j++;
                }else
                    j++;
                break;
        }
        
    }
        if(j==4){
            
            return null;
        }else{
            
            return baby;
        }
    }
    
}

class Mylist
{
    private MyLnode head;
    
    public Mylist()
    {
        head = null;
    }
    
    public void insert(Creature v1)
    {
        MyLnode tmp = new MyLnode (v1);
        tmp.next = head;
        head = tmp;
    }
    
    public void pop()
    {
        if(head.next != null){
            head = head.next;}
        else
            head=null;
        
    }
    public Creature top()
    {
        if(head != null)
            return head.elem;
        else
            return null;
    }
    
    /*public void remove(Creature a)
     {
     Mylist curr = head;
     Mylist pre = null;
     while(curr!=null &&(curr.elem = a)){
     prev = curr;
     curr = curr.next;
     
     }
     if(curr == null){
     head = prev;
     }
     else {
     curr = curr.next;
     prev.next = curr;
     head = prev;
     }
     }
     */
    
}

class MyLnode
{
    public Creature elem;
    public MyLnode next;
    
    public MyLnode(Creature v1)
    {
        elem = v1;
        next = null;
    }
}



class GridDisplay extends JFrame
{
    private JLabel labels[];
    
    private Container container;
    private GridLayout grid1;
    int rowCount;
    int colCount;
    
    // set up GUI
    public GridDisplay(int rows, int cols)
    {
        super( "Yhao5Project5" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        // set up grid layout struture of the display
        rowCount = rows;
        colCount = cols;
        grid1 = new GridLayout( rows, cols );
        container = getContentPane();
        container.setLayout( grid1 );
        
        // create and add buttons
        labels = new JLabel[ rows * cols ];
        
        for ( int count = 0; count < labels.length; count++ ) {
            
            labels[count]= new JLabel("");
            
            labels[count].setOpaque(true);
            labels [count].setBackground(Color.GREEN);
            container.add( labels[ count ] );}
        
        
        // set up the size of the window and show it
        setSize( cols * 30 , rows * 30 );
        setVisible( true );
        
    } // end constructor GridLayoutDemo
    // display the given char in the (row,col)position of the display
    /*  public void setChar (int row, int col, char c)
     {
     if ((row >= 0 && row < rowCount) && (col >= 0 && col < colCount) )
     {
     int pos = row * colCount + col;
     labels [pos].setText("" + c);
     }
     }
     */
    public void setColor (int row, int col, Color c)
    {
        if ((row >= 0 && row < rowCount) && (col >= 0 && col < colCount) )
        {
            int pos = row * colCount + col;
            labels [pos].setBackground(c);
        }
    }
    public static void mySleep( int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException ie)
        {
        }
    }
}




