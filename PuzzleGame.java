/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;
import java.io.*;
import java.util.*;
/**
 *
 * @author user
 */
public class PuzzleGame {
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
      String file = "C:\\Users\\Owner\\Documents\\NetBeansProjects\\Puzzle\\src\\puzzle\\puzzle04.txt";
        Scanner input=new Scanner(new File (file));
        int N= input.nextInt();
        int num=N;
        System.out.println(N);
        int [][] initboard= new int [N][N];
        for (int i=0; i<N; i++)
        {
            for (int j=0; j<N; j++)
            {  
                if(input.hasNext())
                    num=input.nextInt();
                initboard[i][j]=num;
            }
            
        }
    /**
     * @param args the command line arguments
     */
   
        //public Board(int N, int [][] board, Board parent, int cost)
        Board b= new Board(initboard.length,initboard,null,0);

        System.out.println(b.manhattan());
        System.out.println(b.hamming());
        System.out.println(b.isSolvable());
        System.out.println(b.isGoal());

        System.out.println("***********");
        System.out.println("Successors: ");
        System.out.println("***********");
//this.Open.insert(init);
       
        
         MinPQ Open=new MinPQ<>();
         b.ASearch();
    



}
    
    
    
    
   
}
    

