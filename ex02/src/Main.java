import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    protected static enum category { Ergasia, Proswpika, Hobby, Spiti, Katoikidio, Ypoxrewseis, Allo; }
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
    protected static DateTimeFormatter formatterD = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final TreeSet <task> todo = new TreeSet <>(new cmp());
    static class cmp implements Comparator<task>{
        @Override
        public int compare(task e1, task e2) {
            return e1.getTerm().compareTo(e2.getTerm());
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LocalDateTime date = LocalDateTime.now();
        todo.add(new task("Anaplhrwsh tennis", date.minusDays(2), Main.category.Proswpika, 'D'));
        todo.add(new task("Agora trofhs skulou", date.plusDays(1).withHour(14).withMinute(0), category.Katoikidio, 'A'));
        todo.add(new task("Volta me to makh prin fugei", date.plusDays(2), Main.category.Hobby, 'B'));
        todo.add(new task("Eksoflhsh OTE", date.plusDays(10), Main.category.Ypoxrewseis, 'C'));
        todo.add(new task("Eksoflhsh DEH", date.plusDays(12), Main.category.Ypoxrewseis, 'C'));
        todo.add(new task("Paradosh project", date.plusDays(2), Main.category.Ergasia, 'A'));
        int option = 9;
        do{
            System.out.println("***********\n*TaskMngr*\n***********\n0->Εισαγωγή νέας εκκρεμότητας\n1->Εμφάνιση ολόκληρης της λίστας εκκρεμοτήτων\n2->Αναζήτηση εκκρεμοτήτων με βάση την προτεραιότητα\n3->Αναζήτηση εκκρεμοτήτων με βάση την κατηγορία\n4->Αναζήτηση εκκρεμοτήτων για συγκεκριμένη ημέρα\n5->Αναζήτηση εκκρεμοτήτων μέχρι κάποιο συγκεκριμένο χρονικό διάστημα\n6->Χαρακτηρισμός μιας εργασίας ως ολοκληρωμένη\n7->Ανανέωση εκκρεμοτήτων\n8->Εμφάνιση ιστορικού με τις ολοκληρωμένες εργασίες\n9->Τερματισμός");
            option = in.nextInt();
            if(option!=1  && option!=9 && todo.isEmpty()) System.out.println("Δεν υπάρχουν εργασίες ή εκκρεμότητες");
            else if(option==0)  add();
            else if(option==1)  find(false);
            else if(option==2)  {
                char priority;
                do {
                    System.out.print("Εισάγετε προτεραιότητα (A-Z): ");
                    priority = in.next().charAt(0);
                }while(!Character.isAlphabetic(priority));
                find(priority);
            }
            else if(option==3)  {
                category cat=null;
                do{
                    System.out.print("Επιλέξτε κατηγορία (Ergasia, Proswpika, Hobby, Spiti, Katoikidio, Ypoxrewseis, Allo): ");
                    try{cat = category.valueOf(in.nextLine());}catch(Exception e){}
                }while (cat==null);
                find(cat);
            }
            else if(option==4)  {
                LocalDate term=null;
                do{ 
                    System.out.print("Εισάγετε ημερομηνία για εμφάνιση (μορφή: Η/Μ/ΕΕΕΕ): ");
                    try{term= LocalDate.parse(in.nextLine(),formatterD);}catch(Exception e){}
                }while (term==null);
                find(term);
            }
            else if(option==5)  {
                LocalDateTime term=null;
                do{ 
                    System.out.print("Εισάγετε ημερομηνία προθεσμίας(μορφή: Η/Μ/ΕΕΕΕ ΩΩ:ΛΛ): ");
                    try{term= LocalDateTime.parse(in.nextLine(),formatter);}catch(Exception e){}
                }while (term==null);
                find(term);
            }
            else if(option==6)  done();
            else if(option==7)  update();
            else if(option==8)  find(true);
        }while(option!=9);
    }
    private static void add(){
        Scanner in = new Scanner(System.in);
        System.out.print("Εισάγετε περιγραφή: ");
        String description=in.nextLine();
        LocalDateTime term=null;
        do{ 
            System.out.print("Εισάγετε ημερομηνία προθσμίας (μορφή: Η/Μ/ΕΕΕΕ ΩΩ:ΛΛ): ");
            try{term= LocalDateTime.parse(in.nextLine(),formatter);}catch(Exception e){}
        }while (term==null);
        category cat=null;
        do{
            System.out.print("Επιλέξτε κατηγορία (Ergasia, Proswpika, Hobby, Spiti, Katoikidio, Ypoxrewseis, Allo): ");
            try{cat = category.valueOf(in.nextLine());}catch(Exception e){}
        }while (cat==null);
        char priority;
        do {
            System.out.print("Εισάγετε προτεραιότητα (A-Z): ");
            priority = in.next().charAt(0);
        }while(!Character.isAlphabetic(priority));
        todo.add(new task(description,term,cat,priority));
    }
    public static void find(boolean done){
        Iterator <task> itr = todo.iterator();
        while(itr.hasNext()){
            task tmp = itr.next();
            if(tmp.getDone() == done)
                System.out.println(tmp);
        }
    }
    public static void find(char priority){
        Iterator <task> itr = todo.iterator();
        while(itr.hasNext()){
            task tmp = itr.next();
            if(!tmp.getDone() && priority==tmp.getPriority())
                System.out.println(tmp);
        }
    }
    public static void find(category cat){
        Iterator <task> itr = todo.iterator();
        while(itr.hasNext()){
            task tmp = itr.next();
            if(!tmp.getDone() && cat.equals(tmp.getCat()))
                System.out.println(tmp);
        }
    }
    public static void find(LocalDate term){
        Iterator <task> itr = todo.iterator();
        while(itr.hasNext()){
            task tmp = itr.next();
            if(!tmp.getDone() && tmp.getTerm().toLocalDate().isEqual(term))
                System.out.println(tmp);
        }
    }
    public static void find(LocalDateTime term){
        Iterator <task> itr = todo.iterator();
        while(itr.hasNext()){
            task tmp = itr.next();
            if(!tmp.getDone() && tmp.getTerm().isBefore(term))
                System.out.println(tmp);
        }
    }
    public static void done(){
        Scanner in = new Scanner(System.in);
        System.out.print("Εισάγετε περιγραφή: ");
        StringBuilder description = new StringBuilder();
        description.append(in.nextLine());
        System.out.print(description.toString());
        Iterator <task> itr = todo.iterator();
        while(itr.hasNext()){
            task tmp = itr.next();
            if(tmp.getDescription().equals(description)){
                tmp.setDone();
                System.out.println("Egine!");
            }
        }
    }
    public static void update(){
        Iterator <task> itr = todo.iterator();
        while(itr.hasNext()){
            task tmp = itr.next();
            if(tmp.getTerm().isBefore(LocalDateTime.now())){
                tmp.setDone();
            }
        }
        System.out.println("Egine!");
    }
    public static String toTitleCase(String tmp){
        return tmp.substring(0,1).toUpperCase() + tmp.substring(1).toLowerCase();
    }
}