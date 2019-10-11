package generators;

import java.util.List;


public class DefaultInsertGenerator implements InsertGenerator {
   //insert into table_name default values;
    @Override
    public String createInsertSQLCommand(List<String>... parameters) {
        if(parameters == null || parameters[0] == null){
            return null;
        }
        StringBuilder sqlInsertBuilder = new StringBuilder("INSERT INTO ");
        sqlInsertBuilder.append(parameters[0].get(0));
        sqlInsertBuilder.append(" DEFAULT VALUES;");
        sqlInsertBuilder.append(System.lineSeparator());
        return sqlInsertBuilder.toString();
    }
}
