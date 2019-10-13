package insertWriters;

import java.io.IOException;
import java.util.List;

public interface InsertWriter {
    boolean write(InsertType insertType, List<String>... parameters) throws IOException;
}
