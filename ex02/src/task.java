import java.time.LocalDateTime; 

public class task {
    private final StringBuilder description=new StringBuilder();    
    private final LocalDateTime term;   
    private final Main.category cat;    
    private final char priority;        
    private boolean done;               
    public StringBuilder getDescription(){ return description; }    
    public LocalDateTime getTerm(){ return term; }
    public Main.category getCat(){ return cat; }
    public char getPriority(){ return priority; }
    public boolean getDone(){return done; }
    public void setDone(){ done=true; }
    public task(String descr, LocalDateTime term, Main.category cat, char priority){    
        this.description.append(descr);
        this.term = term;
        this.cat = cat;
        this.priority = Character.toUpperCase(priority);    
        done=(term.isBefore(LocalDateTime.now()));          
    }
    public task(String descr, LocalDateTime term, Main.category cat){   
        this.description.append(descr);
        this.term = term;
        this.cat = cat;
        this.priority = 'Z';    
        done=(term.isBefore(LocalDateTime.now()));
    }
    public task(String descr, LocalDateTime term, char priority){   
        this.description.append(descr);
        this.term = term;
        this.cat = Main.category.Allo;  
        this.priority = Character.toUpperCase(priority);
        done=(term.isBefore(LocalDateTime.now()));
    }
    public task(String descr, LocalDateTime term){  
        this.description.append(descr);
        this.term = term;
        this.cat = Main.category.Allo;  
        this.priority = 'Z';            
        done=(term.isBefore(LocalDateTime.now()));
    }
    @Override
    public String toString(){   
        StringBuilder tmp = new StringBuilder();
        tmp.append(' ');
        tmp.append(priority);
        tmp.append(", ");
        tmp.append(cat);
        tmp.append(", «");
        tmp.append(description);
        tmp.append("», ");
        tmp.append(term.format(Main.formatter));
        return tmp.toString();
    }
}
