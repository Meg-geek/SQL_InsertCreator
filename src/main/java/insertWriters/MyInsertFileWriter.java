package insertWriters;

import generators.DefaultInsertGenerator;
import generators.InsertGenerator;
import generators.UsualInsertGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MyInsertFileWriter implements InsertFileWriter {
    private FileWriter fileWriter;

    public MyInsertFileWriter(){
    }

    @Override
    public boolean write(InsertType insertType, List<String>... parameters) throws IOException{
        InsertGenerator generator = null;
        if(insertType == InsertType.DEFAULT_INSERT){
            generator = new DefaultInsertGenerator();
        }
        if(insertType == InsertType.USUAL_INSERT){
            generator = new UsualInsertGenerator();
        }
        if(generator == null){
            return false;
        }
        String insert = generator.createInsertSQLCommand(parameters);
        if(insert == null){
            return false;
        }
        fileWriter.write(insert);
        return true;
    }

    @Override
    public boolean setFile(String filePath) throws IOException{
        File file = new File(filePath);
        if(!checkFile(file)){
            return false;
        }
        fileWriter = new FileWriter(file);
        return true;
    }

    private boolean checkFile(File file) throws IOException {
        boolean created = false;
        if(!file.exists()){
            created = file.createNewFile();
        }
        if(file.isDirectory() || !file.canWrite()){
            if(created){
                file.delete();
            }
            return false;
        }
        return true;
    }
}
