package hashword;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class HashTable2 {
    static HashMap<String,String> hm;
    static File f;
    static Sheet s;
    static Workbook wb;
    static int count=0;
    static double loadFact;
    static String randomChar,randomChar2;
    static int capacity,capacity2;
    HashTable2(int capacity) throws IOException, BiffException{
        hm = new HashMap(capacity);
        loadTable();
        
    }
    public static void loadTable() throws IOException, BiffException{
        f=new File("words.xls");
        wb=Workbook.getWorkbook(f);
        s=wb.getSheet(0);
        int row=s.getRows();
        int col=s.getColumns();
        for(int i=1; i<row; i++){ 
            for(int j=1; j<col; j++){
                Cell c=s.getCell(j-1, i);
                Cell c2 = s.getCell(j, i);
               
                hm.put(c2.getContents(), c.getContents());
          
            }
        }
   }
    
    public static void main(String [] args) throws IOException, BiffException{
        capacity=62;
        capacity2=100;
        HashTable2 hashTable2 = new HashTable2(capacity);
        Cell c2 = null;
        for(int i=1; i<s.getRows(); i++){
            for(int j=1; j<s.getColumns(); j++){
                c2=s.getCell(j, i);
                //collision(c2.getContents());
            }
        }
        //random1();
        collisionOfFifty();
    }
    public static void print(){ 
        
        for (int i = 1; i < s.getRows(); i++) {
            for (int j = 1; j <s.getColumns(); j++) {
                Cell c= s.getCell(j, i);
        
    }
            
        }
    }
    public static void collision(Object key){
        Cell c = null;
        for(int i=1; i<s.getRows(); i++){
            for(int j=1; j<s.getColumns(); j++){
                c=s.getCell(j,i);
                if(c.getContents().equals(key)){
                ++count;
                }
            }
        }
        System.out.println("Total Collision:"+count);
    }
    public static void random1(){   
        for(int i=1; i<=capacity; ){
            loadFact=100*i/capacity;
            if(loadFact==80)
                break;
            randomChar = new ArrayList<>(hm.values()).get(new Random().nextInt(hm.size()));   
            System.out.println(i+":"+randomChar);          
            i++;
        }
   
        System.out.println("Load Factor(80%):"+loadFact);
    }

    public static void random2(){   
        for(int i=1; i<=capacity2; ){
            loadFact=100*i/capacity2;
            if(loadFact==50)
                break;
            randomChar = new ArrayList<>(hm.values()).get(new Random().nextInt(hm.size()));   
            System.out.println(i+":"+randomChar);          
            i++;
        }
   
        System.out.println("Load Factor(50%):"+loadFact);
    }

    public static void collisionOfFifty(){
        int count=0;
        for(int i=1; i<=capacity; ){
            loadFact=100*i/capacity;
            if(loadFact==80)
                break;
            randomChar2 = new ArrayList<>(hm.keySet()).get(new Random().nextInt(hm.size()));    
            i++;
            if(randomChar2.equals(randomChar2)){
                ++count;   
            }
        }
        System.out.println("Table Size :102 \t Collison of random words keys:"+count+"\t Load Factor: 0.8"); 
    
     int count2=0;
        for(int j=1; j<=capacity; ){
            loadFact=100*j/capacity;
            if(loadFact==50)
                break;
            randomChar2 = new ArrayList<>(hm.keySet()).get(new Random().nextInt(hm.size()));    
            j++;
            if(randomChar2.equals(randomChar2)){
                ++count2;   
            }
        }
        System.out.println("Table Size :102 \t Collison of random words keys:"+count2+"\t Load Factor: 0.5"); 
    
    }
}
