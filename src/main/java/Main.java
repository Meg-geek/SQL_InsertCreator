import generators.DefaultInsertGenerator;
import generators.InsertGenerator;
import generators.UsualInsertGenerator;
import userManager.ConsoleUserManager;
import userManager.UserManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        UserManager userManager = new ConsoleUserManager();
        try{
            userManager.start();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
