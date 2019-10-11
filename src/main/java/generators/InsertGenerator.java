package generators;

import java.util.List;

public interface InsertGenerator {
    String createInsertSQLCommand(List<String>... parameters);
}
