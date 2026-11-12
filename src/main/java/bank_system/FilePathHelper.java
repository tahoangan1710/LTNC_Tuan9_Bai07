package bank_system;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePathHelper {

    public static Path createPath(String folder, String fileName) {
        return Paths.get(folder, fileName);
    }
}
