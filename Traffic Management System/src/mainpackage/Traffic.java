package mainpackage;

import java.util.*;
import java.io.*;
import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;

class Node
{
String element;
int number;
Node next;

Node()
{ element=" ";
next=null;
}
Node(String data)
{ element=data;    
next=null;
}
Node(String data, Node n)
{ element=data;
next=n;
}
}

class QSLL
{
Node head;
Node tail;

  QSLL()
  {
head=null;
tail=null;
  }
 QSLL(String s)
  {
tail=new Node(s);
head=tail;

  }
 void enqueue(String x)
 {
Node t=new Node(x);
if(tail==null)
{ tail=t;
head=tail;
}
else
{
tail.next=t;
tail=t;
}

 }
 String dequeue()
 {
String toreturn="xxx";
if(head==null)
System.out.println("Queue is empty");
else
{
toreturn=head.element;
head=head.next;
}
return toreturn;
 }

 void display()
 {
Node temp=head;
while(temp!=null)
{
System.out.print(" "+temp.element);
temp=temp.next;
}
 }
 String peek()
 {
if(head!=null)
return head.element;
else
return "Queue is empty";
 }

}

public class Traffic extends Canvas {

static QSLL topleft1=new QSLL();
static QSLL topleft2=new QSLL();
static QSLL topleft3=new QSLL();
static QSLL topleft4=new QSLL();
static QSLL topright1=new QSLL();
static QSLL topright2=new QSLL();
static QSLL topright3=new QSLL();
static QSLL topright4=new QSLL();
static QSLL bottom1=new QSLL();
static QSLL bottom2=new QSLL();
static QSLL bottom3=new QSLL();
static QSLL bottom4=new QSLL();
int globalint=0;
int cars=0;
int carwidth=30;
int carshift=70;
int carlength=60;
int startx=10;
int starty=10;

int sigx=500;
int sigy=10;


public static void main(String[] args)throws IOException, InterruptedException  {
        JFrame frame = new JFrame("Imaginary Bangalore City");//heading on the canvas
        Canvas canvas = new Traffic();
        canvas.setSize(1300, 800);//setting the canvas size
        frame.add(canvas);
        frame.pack();//aligns with normal screen
        frame.setVisible(true);//this is for visibility
}

void displayNodeLR(int x1, int y1, String name, Graphics gra)//one car will get displayed in a horizontal way(left to right)
{
	
     gra.draw3DRect(x1, y1, carlength, carwidth, true);//car size
     gra.drawString(name, x1+(carlength/4), (int)(y1+(carwidth/1.5)));//name inside the car
     gra.fillArc(x1+carlength-5, y1-(int)(carwidth/3), 20, 50, 150, 60);
     
   //text in the car
}
void displayNodeRL(int x1, int y1, String name, Graphics gra)//one car will get displayed in a horizontal way(left to right)
{
	
     gra.draw3DRect(x1, y1, carlength, carwidth, true);//car size
     gra.drawString(name, x1+(carlength/4), (int)(y1+(carwidth/1.5)));//name inside the car
     gra.fillArc(x1-15, y1-10, 20, 50, 330, 60);
   //text in the car
}
void displayqueueLR(int x1, int y1, QSLL que, Graphics gra)//displays the entire left queue(road)
{
Node temp=que.head;//for left to right queue, display at x1,y1
while(temp!=null)
{

	displayNodeRL(x1, y1, temp.element, gra);
temp=temp.next;
x1=x1+carshift;//next car should be shifted and displayed by carshift pixels
// y1=y1;
}
}
void displayqueueRL(int x1, int y1, QSLL que, Graphics gra)//for right to left
{
Node temp=que.head;
while(temp!=null)
{
displayNodeLR(x1, y1, temp.element, gra);
temp=temp.next;
x1=x1- carshift;
// y1=y1;
}
}
void displayNodeUD(int x1, int y1, String name, Graphics gra)//displays the vertical car
{
     gra.draw3DRect(x1, y1, carwidth, carlength, true);//vertical rectangle car
     gra.drawString(name, x1+5, y1+33);//car text
     gra.fillArc(x1, y1-10, 20, 50, 360, 60);

}
void displayNodeDU(int x1, int y1, String name, Graphics gra)//displays the vertical car
{
     gra.draw3DRect(x1, y1, carwidth, carlength, true);//vertical rectangle car
     gra.drawString(name, x1+5, y1+33);//car text
     gra.fillArc(x1+10, y1+20, 20, 50, 180, 60);

}
void displayqueueUD(int x1, int y1, QSLL que, Graphics gra)//up to down queue
{
Node temp=que.head;//up to down, head is on top
while(temp!=null)
{
displayNodeUD(x1, y1, temp.element, gra);
temp=temp.next;
//x1=x1;
y1=y1+ carshift;
}
}
void displayqueueDU(int x1, int y1, QSLL que, Graphics gra)//down to up queue
{
Node temp=que.head;//down to up, head is down
while(temp!=null)
{
displayNodeDU(x1, y1, temp.element, gra);
temp=temp.next;
// x1=x1;
y1=y1- carshift;
}
}

void clearqueueLR(int x1, int y1, int x2, int y2, Graphics gra) {
gra.clearRect(x1, y1, x2, y2);//clears a rectangular area in the graphics screen

}
//false==SIGNAL APPEARS,true==NO SIGNAL
void drawsignal(boolean L1, boolean L2, boolean B1, boolean B2, boolean R3, boolean R4, Graphics gra) {
 if (L1==false) {gra.fillOval(sigx, sigy+14, 20, 20);}else {//draws signal according to the light
 gra.clearRect(sigx, sigy+14, 20, 20);//clears the signal which is already there
//gra.drawOval(600,100,30,30);
}
 if (L2==false) {gra.fillOval(sigx, sigy+52, 20, 20);}else {
gra.clearRect(sigx, sigy+52, 20, 20);
//gra.drawOval(600,100,30,30);
}
 if (B1==false) {gra.fillOval(sigx+40, sigy+180, 20, 20);}else {
gra.clearRect(sigx+40, sigy+180, 20, 20);
//gra.drawOval(600,100,30,30);
}
 if (B2==false) {gra.fillOval(sigx+105, sigy+180, 20, 20);}else {
gra.clearRect(sigx+105, sigy+180, 20, 20);
//gra.drawOval(600,100,30,30);
}
 if (R3==false) {gra.fillOval(sigx+260, sigy+95, 20, 20);}else {
gra.clearRect(sigx+260, sigy+95, 20, 20);
//gra.drawOval(600,100,30,30);
}
 if (R4==false) {gra.fillOval(sigx+260, sigy+135, 20, 20);}else {
gra.clearRect(sigx+260, sigy+135, 20, 20);
//gra.drawOval(600,100,30,30);
}
//arrows coordinates

 if(L1==true) gra.fillArc(sigx+20, sigy-20, 100, 100, 165, 30);
 if(L2==true) gra.fillArc(sigx+20, sigy+40, 100, 100, 130, 30);
 if(B1==true) gra.fillArc(sigx-50, sigy+90, 100, 100, 320, 30);
 if(B2==true) gra.fillArc(sigx+100, sigy+90, 100, 100, 200, 30);
 if(R3==true)gra.fillArc(sigx+160, sigy+60, 100, 100, 350, 30);
 if(R4==true)gra.fillArc(sigx+200, sigy+150, 100, 100, 400, 30);

}


public void paint(Graphics g)    
{  
g.setColor(g.getColor());    
     //  
        boolean topleftlight1=true, topleftlight2=true, bottomlight1=true, bottomlight2=false, toprightlight3=false, toprightlight4=true;    
        String data = "xxx";
   
        int j=0;
        int i=1;
        while (1==1) {//declare an infinite while loop
        cars++;
        data = String.valueOf(cars);//we change int to string for naming proccess
         
        if(j<6) {//filling 72 sequential cars in 12 queues
        topleft1.enqueue(data);
        j++;
        }
        else if(j<12) {
        topleft2.enqueue(data);
        j++;
        }
        else if(j<18) {
        topleft3.enqueue(data);
        j++;
        }
        else if(j<24) {
        topleft4.enqueue(data);
        j++;
        }
        else if(j<30) {
        topright1.enqueue(data);
        j++;
        }
        else if(j<36) {
        topright2.enqueue(data);
        j++;
        }
        else if(j<42) {
        topright3.enqueue(data);
        j++;
        }
        else if(j<48) {
        topright4.enqueue(data);
        j++;
        }
        else if(j<54) {
        bottom1.enqueue(data);
        j++;
        }
        else if(j<60) {
        bottom2.enqueue(data);
        j++;
        }
        else if(j<66) {
        bottom3.enqueue(data);
        j++;
        }
        else if(j<72) {
        bottom4.enqueue(data);
        j++;
        }    
        else {
        break;
          }
        }
    int x=1;
    int numcarstomove=5;
    int counter=1;    
    String deq;
    int k;
    Scanner sc = new Scanner(System.in);

   
    // do
        for (i=1;i<30;i++){

            clearqueueLR(startx+55, starty+10, startx+(14*carwidth), starty+(5*carwidth), g );
            clearqueueLR(startx+(13*carlength), starty+10, startx+1290, starty+(5*carwidth), g );
            clearqueueLR(startx+30+(6*carshift), starty+(3*carshift), startx+30+(9*carshift), starty+(10*carshift), g );
            
            //Bottom code moves one car in each queue which has green signal
            
            if (topleftlight1) {//when topleftlight1 is green
             deq=topleft1.dequeue();//a car is leaving topleft
             topright1.enqueue(deq);//a car is entering topright1
             deq=topright1.dequeue();//an old car leaves topright1
        cars++;
             data = String.valueOf(cars);
             topleft1.enqueue(data);      //a new car enters topleft1
              }
   
            if (topleftlight2) {//when topleftlight2 is green
             deq=topleft2.dequeue();
             bottom3.enqueue(deq);
             deq=bottom3.dequeue();
        cars++;
             data = String.valueOf(cars);
             topleft2.enqueue(data);    
           }
            if (bottomlight1) {//when bottomlight1 is green
               deq=bottom1.dequeue();
             topleft4.enqueue(deq);
             deq=topleft4.dequeue();
        cars++;
             data = String.valueOf(cars);
             bottom1.enqueue(data);      
            }
            if (bottomlight2) {
               deq=bottom2.dequeue();
             topright2.enqueue(deq);
             deq=topright2.dequeue();
        cars++;
             data = String.valueOf(cars);
             bottom2.enqueue(data);      
            }
            if (toprightlight3) {
               deq=topright3.dequeue();
             topleft3.enqueue(deq);
             deq=topleft3.dequeue();
        cars++;
             data = String.valueOf(cars);
             topright3.enqueue(data);      
            }
         
            if (toprightlight4) {
             deq=topright4.dequeue();
             bottom4.enqueue(deq);
             deq=bottom4.dequeue();
        cars++;
             data = String.valueOf(cars);
             topright4.enqueue(data);      
          }
               
       
            counter++;
            
            /* For each signal condition the number of cars moving will be equal to numcarstomove */
           // And the signal gets changed after that many cars move by bottom code
            
        if((counter%numcarstomove)==0) {
        x++;
           
        }
        if(x==2) {
        topleftlight1=true; topleftlight2=false; bottomlight1=true; bottomlight2=true; toprightlight3=false; toprightlight4=true;
      	g.clearRect(sigx+100, sigy+80, 120, 30);//accident triangle
        }
        if(x==3) {;
        topleftlight1=true; topleftlight2=false; bottomlight1=true; bottomlight2=false; toprightlight3=true; toprightlight4=true;
         g.clearRect(sigx+100, sigy+80, 120, 30);//accident rectangle
        }
        if(x==4) {
        topleftlight1=false; topleftlight2=false; bottomlight1=false; bottomlight2=false; toprightlight3=false; toprightlight4=false;
       g.clearRect(sigx+100, sigy+80, 120, 30);
        }
        if(x==5) {
            topleftlight1=true; topleftlight2=true; bottomlight1=true; bottomlight2=true; toprightlight3=true; toprightlight4=true;
          //  g.clearRect(sigx+100, sigy+80, 80, 30);
         
            }
        
            if(x>5) {
            x=1;
            topleftlight1=true; topleftlight2=true; bottomlight1=true; bottomlight2=false; toprightlight3=false; toprightlight4=true;
        	g.clearRect(sigx+100, sigy+80, 120, 30);
  
            }
           
       if((topleftlight2==true&& bottomlight2==true)||(topleftlight2==true&& toprightlight3==true)||(toprightlight3==true&& bottomlight2==true)) {
           g.clearRect(sigx+100, sigy+80, 120, 30);

    	   String name1="ACCIDENT";
    	   g.drawString(name1,sigx+100,sigy+100);
    	   
       }
       if((topleftlight2==false && bottomlight2==false && topleftlight2== false && toprightlight3==false && toprightlight3==false && bottomlight2==false)) {
           g.clearRect(sigx+100, sigy+80, 120, 30);

    	   String name1="ZEBRA CROSSING";
    	   g.drawString(name1,sigx+100,sigy+100);
    	   
       }
       
       // Bottom code redraws road, cars and signals according to current conditions
       drawsignal(topleftlight1, topleftlight2, bottomlight1, bottomlight2, toprightlight3, toprightlight4, g);

       
        g.drawLine(startx, starty, startx+1280, starty);
        g.drawLine(startx, starty+180, startx+490, starty+180);
        g.drawLine(startx+790, starty+180, startx+1280, starty+180);
        g.drawLine(startx+490, starty+180, startx+490, starty+680);
        g.drawLine(startx+790, starty+180, startx+790, starty+680);

        displayqueueRL(startx+55+(5*carshift),starty+10,topleft1, g);
        displayqueueRL(startx+55+(5*carshift),starty+50,topleft2, g);
        displayqueueLR(startx+55,starty+90,topleft3, g);
        displayqueueLR(startx+55,starty+130,topleft4, g);
       
        displayqueueRL(startx+30+(16*carshift),starty+10,topright1, g);
        displayqueueRL(startx+30+(16*carshift),starty+50,topright2, g);
        displayqueueLR(startx+30+(11*carshift),starty+90,topright3, g);
        displayqueueLR(startx+30+(11*carshift),starty+130,topright4, g);
       
        displayqueueUD(startx+30+(7*carshift),starty+210,bottom1, g);
        displayqueueUD(startx+30+(8*carshift),starty+210,bottom2, g);

        displayqueueDU(startx+30+(9*carshift),starty+280+(4*carshift),bottom3, g);
        displayqueueDU(startx+30+(10*carshift),starty+280+(4*carshift),bottom4, g);
       
        k=sc.nextInt();
        
        }      
}   
}