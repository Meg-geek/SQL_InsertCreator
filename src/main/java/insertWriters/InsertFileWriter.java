package insertWriters;

import java.io.IOException;

public interface InsertFileWriter extends InsertWriter {
    boolean setFile(String filePath) throws IOException;
    void closeFile() throws IOException;
}
