
package bank_system;

import org.junit.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;

public class FilePathTest {

    @Test
    public void testFilePath() {
        Path expected = Paths.get("data", "test.txt");
        Path actual = FilePathHelper.createPath("data", "test.txt");

        assertEquals(expected, actual);
    }
}
