import generators.InsertGenerator;
import generators.UsualInsertGenerator;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        InsertGenerator generator = new UsualInsertGenerator();
        List<String> one = new ArrayList<>();
        one.add("table");
        System.out.println(generator.createInsertSQLCommand(one, one, one));
    }
}
