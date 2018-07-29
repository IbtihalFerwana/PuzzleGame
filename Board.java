/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;
import java.util.*;
/**
 *
 * @author user
 */
public class Board {
       private static MinPQ <Board> Open=new MinPQ<Board>();
       private static LinkedList<Board> Closed=new LinkedList();
    private int N;
        private int board[][]= new int [N][N];
        private Board parent=null;
        private int cost;

     private int iofzero;
        private int jofzero;
        public Board(int N, int [][] board, Board parent, int cost){
        this.N=N;
        this.board=board;
        this.parent=parent;
        this.cost=cost; 
    }

    Board() {
        
    }

          


    public int getN() {
        return N;
    }

    public void setN(int N) {
        this.N = N;
    }

    public int[][] getBoard() {
        return board;
    }

   

    public Board getParent() {
        return parent;
    }

    public void setParent(Board parent) {
        this.parent = parent;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
    public int getIofzero() {
        return iofzero;
    }

    public void setIofzero(int iofzero) {
        this.iofzero = iofzero;
    }

    public int getJofzero() {
        return jofzero;
    }

    public void setJofzero(int jofzero) {
        this.jofzero = jofzero;
    }
       
    
    public boolean isEqual(int [][] B)
    {
        boolean check=Arrays.deepEquals(this.getBoard(), B);
        return check;
    }
    
    
   public int manhattan(){
       
          int goalj;
          int ManhSum=0;
          int man;
          int [][] currentState=getBoard();
         for(int i=0;i<getN();i++){
             
             for(int j=0;j<N;j++){
             int  X=currentState[i][j];
               if(X!=0){
               if(X%N==0)
                   goalj=N-1;
               else
               goalj=(X%N)-1;
              int goali=(X-goalj)/N;
           
            man=Math.abs(i-goali)+Math.abs(j-goalj); 
                 
            ManhSum+=man;
                
               }
               else{
                   setIofzero(i);
                   setJofzero(j);
               }
             }
         }  
        return ManhSum;
    }
   
   public int hamming(){
       
        int n=1;
        int hammcount=0;
        int [][] currentState=getBoard();
        for(int i=0;i<N;i++){
            
            for(int j=0;j<N;j++){
                
                if(i==(N-1)&&j==(N-1))
                    break;
               
                if(currentState[i][j]!=n)
                    hammcount++;
               n++;
            }
            }
            
     
      return hammcount; 
   }
   
  
   public boolean isGoal(){
       boolean goal=true;
      if(!(hamming()==0))
          goal=false;
       
   return goal;}
   
   
   public int[][] CopyBoard(){
      int[][] dupBoard=Arrays.copyOf(getBoard(), getBoard().length);
       
       
   return dupBoard;}
   
   public boolean isSolvable(){
        int getRowOfzero=0;
        int [][] B=getBoard();
        LinkedList <Integer>list=new LinkedList ();
        for (int i=0; i<B.length;i++)
        {
            for (int j=0; j<B.length; j++)
            {
                list.add(B[i][j]);
                if (B[i][j]==0)
                    getRowOfzero=i;
            }
        }
        boolean check=false;
       
        int TotalInv=0;
        
        for (int i=0; i<list.size(); i++)
        {   
//            System.out.println("Num= "+(int)list.get(i));
            for (int j=i+1; j<list.size();j++)
            {   
                if((int)list.get(i)>(int)list.get(j) && (int)list.get(j) != 0)
                {    TotalInv++;
                }
            }
//            System.out.println("Inv= "+countInv);
           
        }
        
        if(B.length%2==1) //if odd
        {   
            if(TotalInv%2==0)
                check=true;
        }
        else if(B.length%2==0) //if even
        {   
            if((TotalInv+getRowOfzero)%2==1)
                check=true;
           
        }
        System.out.println("Total: "+TotalInv);
        return check;
    }
  

   
   
   
    
        public int [][] copyBoard()
    {
        int [][] newboard=new int [this.getN()][this.getN()];
        int [][] old= this.getBoard();
        for(int i=0; i<this.getN(); i++)
        {
            for(int j=0; j<this.getN(); j++)
            {
                newboard[i][j]=old[i][j];
            }
        }
        return newboard;
    }
//    public int[][] copyBoard()
//    {
//      return Arrays.copyOf(this.getBoard(), this.getN());
//    }
       
    
    public void printBoard()
    {
       int [][] B=getBoard();
       int size=B.length;
       for(int i=0; i<size; i++)
       {
           for (int j=0; j<size; j++)
           {
               System.out.print(B[i][j]+" ");
           }
           System.out.println();
       }
    }
    

 public void  successors()
    {
        //public Board(int N, int [][] board, Board parent, int cost)
        
        if(this.getIofzero()>0) //Action up
        {
            int [][] newboard=this.copyBoard();
            swapRows(this.getIofzero(),this.getIofzero()-1, this.getJofzero(), newboard);
            Board b=new Board(this.getN(),newboard,this,this.getCost()+1 );
            
           if(Closed.contains(b)==false&&Open.Contains(b)==false){
                    Open.insert(b);
                    System.out.println("added 1");
                }  
               
        }
        if(this.getIofzero()<this.getN()-1)  //Action down
        {
            int [][] newboard=this.copyBoard();
            swapRows(this.getIofzero(), this.getIofzero()+1, this.getJofzero(), newboard);
            Board b=new Board(this.getN(),newboard,this,this.getCost()+1 );
            
             
                if(Closed.contains(b)==false&&Open.Contains(b)==false){
                    
                    Open.insert(b);
                    System.out.println("added 2");
                } 
        }
                
        
        if(this.getJofzero()>0) //Action left
        {
            int [][] newboard=this.copyBoard();
            swapCols(this.getIofzero(), this.getJofzero(), this.getJofzero()-1, newboard);
            Board b=new Board(this.getN(),newboard,this,this.getCost()+1 );
         
                if(Closed.contains(b)==false&&Open.Contains(b)==false){
                    
                    Open.insert(b);
                    System.out.println("added 3");
                } 
        }

            
        
        if(this.getJofzero()<this.getN()-1)//Action right
        {
            int [][] newboard=this.copyBoard();
            swapCols(this.getIofzero(), this.getJofzero(), this.getJofzero()+1, newboard);
            Board b=new Board(this.getN(),newboard,this,this.getCost()+1 );
 
                if(Closed.contains(b)==false&&Open.Contains(b)==false){
                    Open.insert(b);
                    System.out.println("added 4");
                }  
            
        }
        
    }
    
    private void swapRows(int i1, int i2, int j, int [][] b)
    {
        int temp=b[i1][j];
        b[i1][j]=b[i2][j];
        b[i2][j]=temp;
    
    }
    private void swapCols(int i, int j1, int j2, int [][]b)
    {
        int temp=b[i][j1];
        b[i][j1]=b[i][j2];
        b[i][j2]=temp;
    
    }
    
    public int fhamming(){
        
   return this.getCost()+this.hamming(); }
    
public int fman(){
        
   return this.getCost()+this.manhattan(); }

public void  ASearch(){

    Board current=new Board(this.getN(),this.getBoard(),this.getParent(), this.getCost());
    Open.insert(this);
    current=(Board)Open.delMin();
    Closed.add(current);
    while(!(current.isGoal()))
    {
        
        System.out.println("Min Board");current.printBoard();
        current.successors();
        current=(Board)Open.delMin();
        Closed.add(current);
    }
    }
   
    
    
    List path=new ArrayList<Board>();
//   while(!(goal==null)){
//       goal.printBoard();
//     path.add(goal);
//     goal=goal.getParent();
       
   } 
//return path;




    
  
    

