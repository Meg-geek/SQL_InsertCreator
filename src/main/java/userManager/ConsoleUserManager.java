package userManager;

import insertWriters.InsertFileWriter;
import insertWriters.InsertType;
import insertWriters.InsertWriter;
import insertWriters.MyInsertFileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUserManager implements UserManager {
    private Scanner in;
    private InsertFileWriter writer;
    private static final String EXIT_WORD = "exit";
    private static final String END_WORD = "end";

    public ConsoleUserManager(){
        in = new Scanner(System.in);
    }

    @Override
    public void start() throws IOException {
        introduce();
        writer = new MyInsertFileWriter();
        if(!setFile()){
            writer.closeFile();
            return;
        }
       createInserts();
    }

    private void createInserts() throws IOException{
        String line = in.nextLine();
        while(!line.equals(EXIT_WORD)){
            if(line.equals("usual")){
                createUsual();
            }
            if(line.equals("default")){
                createDefault();
            }
            line = in.nextLine();
        }
    }

    private void createUsual() throws IOException{
        List<String> tableName = getTableName();
        if(tableName == null){
            return;
        }
        System.out.println("write columns, new line for new column. write end to start write values");
        List<String> columnsList = getParametersList();
        if(columnsList == null){
            return;
        }
        System.out.println("write values, new line for new column. write end to start write values");
        List<String> valuesList = getParametersList();
        if(valuesList == null){
            return;
        }
        writer.write(InsertType.USUAL_INSERT, tableName, columnsList, valuesList);
    }

    private List<String> getParametersList(){
        List<String> parametersList = new ArrayList<>();
        String line = in.nextLine();
        while(!line.equals(EXIT_WORD) && !line.equals(END_WORD)){
            parametersList.add(line);
            line = in.nextLine();
        }
        if(line.equals(EXIT_WORD)){
            return null;
        }
        return parametersList;
    }


    private List<String> getTableName(){
        System.out.println("write table name, please");
        String table = in.nextLine();
        if(table.equals(EXIT_WORD)){
            return null;
        }
        List<String> tableName = new ArrayList<>();
        tableName.add(table);
        return tableName;
    }

    private void createDefault() throws IOException{
        System.out.println("How many default inserts do you want?");
        int amount = in.nextInt();
        List<String> tableName = getTableName();
        if(tableName == null){
            return;
        }
        for(int i = 0; i < amount; i++){
            writer.write(InsertType.DEFAULT_INSERT, tableName);
        }
    }

    private boolean setFile() throws IOException{
        String line = in.nextLine();
        while(!line.equals(EXIT_WORD) && !writer.setFile(line)){
            System.out.println("Wrong file name. Please, try again..");
            line = in.nextLine();
        }
        return (!line.equals(EXIT_WORD));
    }

    private void introduce(){
        System.out.println("Hello, welcome to SQL insert creator!");
        System.out.println("how to use this creator:");
        System.out.println("if you want default insert, write " + "default");
        System.out.println("if you want usual insert write usual");
        System.out.println("First, you need to write file where you will get all your commands");
        System.out.println("Print " + EXIT_WORD + " to exit");
    }
}
