/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ID3_Parth;

import com.sun.corba.se.impl.io.ValueHandlerImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.HTML;
import static ID3_Parth.EntropyCalculator.a;
import sun.security.krb5.internal.KDCOptions;

/**
 *
 * @author Parth
 */
public class Reader_ID3 {

    /**
     * @param args the command line arguments
     */
    
     public static int data[][]=new int[10][4];
    
    public static void main(String[] args) throws FileNotFoundException, Exception {
        // TODO code application logic here
       // System.out.println("Hi");
        
        EntropyCalculator c=new EntropyCalculator();
        c.Entropy(args[0],args[1]);
        //readFileFromPartition();
    }
    
   // This method reads data from input file.
    public  ArrayList<ArrayList<Integer>> readFile(String dataFile) throws FileNotFoundException{
//        String[][] recieverArray=new String[20][2];
    BufferedReader in = null;
    ArrayList<ArrayList<Integer>> arr=new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> tem;
    
    String[] temp=new String[40];
            int i=0;
            int m=0;
        
             int a[][]=new int[10][4];
            //System.out.println("got here");
            
            
            in = new BufferedReader(new FileReader(new File(".\\"+dataFile)));
            int line = 0;
        try {
            
            
            
            for (String x = in.readLine(); x != null ; x = in.readLine()) {
                line++;
                tem=new ArrayList<>();
                
                String[] s=x.split(" ");
                
                if(line>1){
                for(int k=0;k<s.length;k++){
            
                          //a[line-1][k]=Integer.parseInt(s[k+1]);
                          int mi=Integer.parseInt(s[k]);
                          //System.out.println(a[line-1][k]+"line:"+line+" K:"+k);
                          tem.add(mi);
                          
                }
            arr.add(tem);
            
                
            }
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Reader_ID3.class.getName()).log(Level.SEVERE, null, ex);
        }
                return arr;
        
    }
 
    // This method reads data from partition file.
    public ArrayList<ArrayList<Integer>> readFileFromPartition(String partitionFile) throws FileNotFoundException{
    BufferedReader in = null;
    String[] temp=new String[40];
    ArrayList<ArrayList<Integer>> arr=new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> tem;
            int i=0;
            int m=0;
        
             int a[][]=new int[10][4];
             
      
            in = new BufferedReader(new FileReader(new File(".\\"+partitionFile)));
            int line = 0;
        try {
            
//            for( int n=0;n<10;n++){
//                for (int j=0;j<4;j++){
//                
//                a[n][j]=0;
//            }
//            }
            
            for (String x = in.readLine(); x != null ; x = in.readLine()) {
                line++;
                tem=new ArrayList<>();
                
                String[] s=x.split(" ");
                for(int k=0;k+1<s.length;k++){
            
                          //a[line-1][k]=Integer.parseInt(s[k+1]);
                          int mi=Integer.parseInt(s[k+1]);
                          //System.out.println(a[line-1][k]+"line:"+line+" K:"+k);
                          tem.add(mi);
                          
                }
            arr.add(tem);
            
                
            }
            
             for( int n=0;n<10;n++){
                for (int j=0;j<4;j++){
                
               //System.out.print(" "+a[n][j]);
            }
                 //System.out.println(" ");
            }
            
           
            
            
        } catch (IOException ex) {
            Logger.getLogger(Reader_ID3.class.getName()).log(Level.SEVERE, null, ex);
        }
                return arr;
    
    }
    
    }


