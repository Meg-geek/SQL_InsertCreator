package generators;

import java.util.List;

public class UsualInsertGenerator implements InsertGenerator {

    @Override
    public String createInsertSQLCommand(List<String>... parameters) {
        if(!checkParameters(parameters)){
            return null;
        }
        List<String> columns = parameters[1];
        List <String> values = parameters[2];
        StringBuilder sqlInsertBuilder = new StringBuilder
                ("INSERT INTO " + parameters[0].get(0) + System.lineSeparator());
        sqlInsertBuilder.append(toInsertForm(columns));
        sqlInsertBuilder.append(System.lineSeparator());
        sqlInsertBuilder.append("VALUES");
        sqlInsertBuilder.append(System.lineSeparator());
        sqlInsertBuilder.append(toValuesForm(values, columns.size()));
        sqlInsertBuilder.append(";");
        sqlInsertBuilder.append(System.lineSeparator());
        return sqlInsertBuilder.toString();
    }

    private String toValuesForm(List<String> values, int columnsAmount){
        StringBuilder valuesBuilder = new StringBuilder();
        for(int i = 0; i < values.size() - columnsAmount; i += columnsAmount){
            valuesBuilder.append(toInsertForm(values.subList(i, i + columnsAmount)));
            valuesBuilder.append(",");
            valuesBuilder.append(System.lineSeparator());
        }
        valuesBuilder.append(toInsertForm(values.subList(values.size() - columnsAmount, values.size())));
        return valuesBuilder.toString();
    }

    private String toInsertForm(List <String> parameters){
        StringBuilder insertFormBuilder = new StringBuilder("( ");
        for(int i = 0; i < parameters.size() - 1; i++){
            insertFormBuilder.append(parameters.get(i));
            insertFormBuilder.append(", ");
        }
        insertFormBuilder.append(parameters.get(parameters.size() - 1));
        insertFormBuilder.append(" )");
        return insertFormBuilder.toString();
    }

    private boolean checkParameters(List<String>... param){
        if(param == null){
            return false;
        }
        if(param.length < 3){
            return false;
        }
        for(List<String> paramList : param){
            if(paramList.size() < 1){
                return false;
            }
        }
        return (param[2].size() % param[1].size() == 0);
    }
}
