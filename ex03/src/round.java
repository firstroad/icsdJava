import java.util.ArrayList; 
import java.util.HashMap;   
import java.util.Iterator;  

public final class round {
    private HashMap <String,String> words = new HashMap<>();    
    private ArrayList <Character> letters = new ArrayList<>();  
    private int[] found= new int[3];    
    int difficulty;
    int number; 
    int tries;
    public int getTries(){ return tries; }
    public boolean isNotDone(){ 
        return !(found[0]==1 && found[1]==1 && found[2]==1);
    }
    public int triesMinus(){    
        return --tries;
    }
    public void showLetters(){  
        System.out.println();
        Iterator<Character> itr = letters.iterator();
        while(itr.hasNext()) System.out.print(itr.next().toString()+" ");
        System.out.println();
    }
    public round(int number, int difficulty){   
        this.number=number; 
        this.tries=6;       
        this.difficulty=difficulty;
        this.found = new int[]{0,0,0};  
        System.out.println("\nΓύρος παιχνιδιού: "+number);
        for(int i=0;i<3;i++){   
            for (String tmp : Main.words.keySet()) {    
                words.put(tmp, Main.words.get(tmp));    
                Main.words.remove(tmp);                 
                break;
            }
        }
        for(String tmp:words.keySet()){
            System.out.format("%-55s \t",words.get(tmp));
            if(difficulty==1){
                for(int j=0;j<tmp.length();j++){
                    System.out.print(" _");
                    letters.add((int)(Math.random() * letters.size()),tmp.charAt(j));
                }
                System.out.println();
            }
            else {
                System.out.print(tmp.charAt(0));
                for(int j=1;j<(tmp.length()-1);j++){
                    System.out.print(" _");
                    letters.add((int)(Math.random() * letters.size()),tmp.charAt(j));
                }
                System.out.println(" "+tmp.charAt(tmp.length()-1));
            }
        }
        showLetters();
    }
    public boolean check(int word, String guess){
        Iterator<String> itr = words.keySet().iterator();
        int i=1;
        while(itr.hasNext()) {
            String tmp=itr.next();
            if(word==i++){
                if(guess.equals(tmp) && found[i-2]==0){
                    for(int j=0;j<tmp.length();j++){
                        if( ( difficulty==0 && j!=0 && j!=(tmp.length()-1) ) || difficulty==1 )
                         letters.remove((Character)tmp.charAt(j));
                        
                    }
                    found[i-2]=1;
                    return true;
                }
            }
        }
        return false;
    }
}
