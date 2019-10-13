import userManager.FileUserManager;
import userManager.UserManager;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        try{
            UserManager userManager = new FileUserManager(new File("src" + File.separator + "descr.txt"));
            userManager.start();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
