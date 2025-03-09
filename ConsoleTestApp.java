import java.util.List;

public class ConsoleTestApp {
    public static void main(String[] args) {
        List<DataRecord> data = DataLoader.loadData("data.csv");

        if (data.isEmpty()) {
            System.out.println("No data found.");
            return;
        }

        System.out.println("First Item: " + data.get(0));
        if (data.size() >= 10) {
            System.out.println("Tenth Item: " + data.get(9));
        } else {
            System.out.println("Less than 10 items in the file.");
        }
        System.out.println("Total Entries: " + data.size());
    }
}
