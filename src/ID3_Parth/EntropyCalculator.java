/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ID3_Parth;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import sun.security.krb5.internal.KDCOptions;

/**
 *
 * @author Parth
 */
public class EntropyCalculator{

       public int height;
       public int width;

       ArrayList<ArrayList<Integer>> partitionFile=new ArrayList<ArrayList<Integer>>();
       ArrayList<ArrayList<Integer>> readerFile=new ArrayList<ArrayList<Integer>>();
       ArrayList<Integer> tem;
    
      public static int data1[][]=new int[10][4];
      public static int a[][]=new int[10][4];        

      int data2[][]=new int[10][4];
      Reader_ID3 assignment1=new Reader_ID3();
      public int b[]=new int[]{5,6,7,8};
      double gain;
      
      
//Below method is main method in the project  which calculates final entropy and write file.
public void Entropy(String dataFile,String partitionFile) throws FileNotFoundException, Exception
    {
        
      
         this.partitionFile=assignment1.readFileFromPartition(partitionFile);
         readerFile=assignment1.readFile(dataFile);
         
         
         gainFinder(readerFile, this.partitionFile);
        
          }    

//Below method calculates log. 
public static  double logCalculator(double item){
    if(item==0)
        return  0;
    
    
    return (Math.log10(item)/Math.log10(2));
}

//Below method calculates entropy.
public static double entropyCalculator(double item1,double item2)
{
    if((item1==0)&&(item2==0) ){    
    return 0;
    }
     if(item1==0){
     return ((item2)*logCalculator(1/item2));
    }
    if(item2==0){
     return ((item1)*logCalculator(1/item1));
    }
    return (((item1)*logCalculator(1/item1))+((item2)*logCalculator(1/item2)));
}

//Below method calculates entropy for given class.
public static double entropyByClass(ArrayList<ArrayList<Integer>> item,ArrayList<Integer> index,int identifier)throws Exception{
  int indexRef=0;
  int last=item.get(0).size()-1;
  double firstYes=0;
  double secondYes=0;
  double thirdYes=0;
  double firstNo=0;
  double secondNo=0;
  double thirdNo=0;
  double totalZero=0;
  double totalOne=0;
  double totalTwo=0;
  double totalElements=0;
  double totalYes=0;
  double totalNo=0;
  double entropy=0;
  double entropyAbsolute=0;
  double gainAbsolute=0;
  double totalYesDemo=0;
  double totalNoDemo=0;
  double totalElementsDemo=0;
  double entropyDemo=0;
  double entropyAbsoluteDemo=0;
  double gainAbsoluteDemo=0;
  
  
  double YesNoCalculator[][]=new double[item.get(0).size()][last];
  
    for(int i=0;i<item.size();i++)
    {
        if(i==(index.get(indexRef)-1))
        {
 //           System.out.println("Matched index"+i);
            indexRef++;
            
            if(indexRef==index.size()){
               indexRef=0;   
        }
           
            //Logic for updating the values using ID3 algorithm
            
            for(int k=0;k<last;k++){
            
            if((item.get(i).get(identifier)==k)&&(item.get(i).get(last)==0)){
                firstYes++;
                totalZero++;
                YesNoCalculator[k][0]++;
            }
            
            if((item.get(i).get(identifier)==k)&&(item.get(i).get(last)==1)){
                firstNo++;
                totalZero++;
                YesNoCalculator[k][1]++;
            }
            }
            
        }
    }
    
    
    for(int i=0;i<item.get(0).size();i++)
    {
        totalYesDemo+=YesNoCalculator[i][0];
        totalNoDemo+=YesNoCalculator[i][1];
    }
    
    totalYes=firstYes+secondYes+thirdYes;
    totalNo=firstNo+secondNo+thirdNo;
    
    totalElementsDemo=totalYesDemo+totalNoDemo;
    
   // System.out.println("YesNoCalculator");
    
    for(int i=0;i<last;i++){
     //   System.out.println(" "+i+" Data");
        for(int j=0;j<2;j++){
           // System.out.println(""+YesNoCalculator[i][j]);
        }
       // System.out.println("-------------- ");
    }
    
    
    
    
    
    totalElements=totalOne+totalTwo+totalZero;
   
    if(totalZero==0){
        entropy+=0;
    }
    else{
        entropy+=(totalZero/totalElements)*entropyCalculator(firstYes/totalZero, firstNo/totalZero);
    }
    
    if(totalOne==0){
        entropy+=0;
    }
    else{
        entropy+=(totalOne/totalElements)*entropyCalculator(secondYes/totalOne, secondNo/totalOne);
    }
    
    if(totalTwo==0){
        entropy+=0;
    }
    else{
        entropy+=(totalTwo/totalElements)*entropyCalculator(thirdYes/totalTwo, thirdNo/totalTwo);
    }
   
    
    for(int k=0;k<3;k++){
       double temp= (YesNoCalculator[k][0]+YesNoCalculator[k][1]);
        
        if(temp==0)
        {
        entropyDemo+=0;
        }
      else
        {
        entropyDemo+=(temp/totalElementsDemo)*entropyCalculator(YesNoCalculator[k][0]/temp,YesNoCalculator[k][1]/temp);
        }
    }
    
  entropyAbsolute=entropyCalculator(totalYes/totalElements, totalNo/totalElements);
  entropyAbsoluteDemo=entropyCalculator(totalYesDemo/totalElementsDemo,totalNoDemo/totalElementsDemo);
  
  
  
  
  gainAbsolute=entropyAbsolute-entropy;
  gainAbsoluteDemo=entropyAbsoluteDemo-entropyDemo;
    return gainAbsoluteDemo;

}

//Below method calculates final gain and write file.
public static void gainFinder(ArrayList<ArrayList<Integer>> item,ArrayList<ArrayList<Integer>> index) throws Exception{
      
    int Identifier[]=new int[index.size()];
    
    double maxGainAbsolute[]=new double[index.size()];
    double maxGain[]=new double[index.size()];
             
           
         
         for(int k=0;k<index.size();k++)
         {
    
             double temp=0;

             for(int i=0;i<item.get(0).size()-1;i++){
             temp=entropyByClass(item,index.get(k),i);
             if(temp>maxGainAbsolute[k])
             {
                 maxGainAbsolute[k]=temp;
                 Identifier[k]=i+1;
             }
             
             
             
             maxGain[k]=((double)index.get(k).size()/(double)item.size())*maxGainAbsolute[k];
         }
         
         
         
         System.out.println("K="+k);
         System.out.println("MaxGain absolute:"+maxGainAbsolute[k]);
         System.out.println("MaxGain :"+maxGain[k]);
         System.out.println("Identifier"+Identifier[k]);
  
             System.out.println("---------------------------------------------");
         }
         
         
         
         double FinalGain=0;
         int finalPartition=0;
         
         for(int k=0;k<index.size();k++){
             if(maxGain[k]>FinalGain){
                 FinalGain=maxGain[k];
                 finalPartition=k;
             }
         }
         
         System.out.println("FinalGain:"+FinalGain);
         System.out.println("Partition:"+(finalPartition+1));
         System.out.println("Attribute"+Identifier[finalPartition]);
         
    
         
         int a[]=new int[30];
        
        ArrayList<Integer> finalPart1ArrayList1=new ArrayList<Integer>(); 
        ArrayList<Integer> finalPart1ArrayList2=new ArrayList<Integer>();
        ArrayList<Integer> finalPart1ArrayList3=new ArrayList<Integer>();
      
       
        
         for(int i=0;i<index.get(finalPartition).size();i++){
         
             
             int row=(index.get(finalPartition).get(i))-1;
             int column=Identifier[finalPartition]-1;
             
              
              if(item.get(row).get(column)==0){
                  //System.out.println(row+" : "+item.get(row).get(column)+" :0");
                  finalPart1ArrayList1.add(row+1);
              }
              if(item.get(row).get(column)==1){
                  //System.out.println(row+" : "+item.get(row).get(column)+" :1");
                  finalPart1ArrayList2.add(row+1);
              }
              if(item.get(row).get(column)==2){
                  //System.out.println(row+" : "+item.get(row).get(column)+" :2");
                  finalPart1ArrayList2.add(row+1);
              }
             }
         
         
          PrintWriter writer = new PrintWriter("partition-3.txt", "UTF-8");
          
         writer.println("partition "+index.get(finalPartition)+" is changed using feature :"+Identifier[finalPartition]);
         
       index.remove(finalPartition);
       
       
       int last=0;
       
       if(finalPart1ArrayList1.size()>0){
       index.add(finalPartition,finalPart1ArrayList1);
       last++;
       }
       
       if(finalPart1ArrayList2.size()>0){
       index.add(finalPartition,finalPart1ArrayList2);
       last++;
       }
       
       
      if(finalPart1ArrayList3.size()>0){
       index.add(finalPartition,finalPart1ArrayList3);
       last++;
      }
       
       
      
       for(int i=0;i<index.size();i++){
           System.out.println(index.get(i));
       }
       
       //int last=finalPart1ArrayList1.size()+finalPart1ArrayList2.size()+finalPart1ArrayList3.size();
       
       //PrintWriter writer = new PrintWriter("partition-3.txt", "UTF-8");
       int c=65;
       int k=0;
       int m=1;
       
       for(int i=0;i<index.size();i++){
           
      
           char d=(char)(c+k) ;

           //writer.print(d+" ");
           
           
           
               if((i>=finalPartition)&&(i<finalPartition+last)){
               
               //System.out.println(" "+i+" "+finalPartition+" "+(finalPartition+last));
               writer.print(d+""+m+" ");
               m++;
               
               if(i==(finalPartition+last-1)){
                   k++;
               }
           }
           
           else{
           
           k++;
           //d=(char)(c+k) ;
               //System.out.println("k:"+k);
           writer.print(d+" ");
           }
           
           for(int j=0;j<index.get(i).size();j++){
               writer.print(" "+index.get(i).get(j));
           }
           writer.println("");
       }
       
       writer.close();
           
             
         }
        
         
}







