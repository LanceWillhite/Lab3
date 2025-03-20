import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class DataLoader {
    public static List<DataRecord> loadData(String filePath) {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.skip(1) // Skip header row
                        .map(line -> line.split(","))
                        .filter(parts -> parts.length == 3)
                        .map(parts -> new DataRecord(parts[0].trim(), parts[1].trim(), Double.parseDouble(parts[2].trim())))
                        .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
