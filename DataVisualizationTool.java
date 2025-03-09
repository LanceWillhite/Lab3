import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class DataVisualizationTool extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel statsLabel;

    public DataVisualizationTool(List<DataRecord> data) {
        setTitle("Data Visualization Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        setupTable(data);
        setupStatsPanel(data);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(statsLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void setupTable(List<DataRecord> data) {
        String[] columns = {"Category", "Name", "Value"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);

        for (DataRecord record : data) {
            tableModel.addRow(new Object[]{record.getCategory(), record.getName(), record.getValue()});
        }
    }

    private void setupStatsPanel(List<DataRecord> data) {
        double avg = data.stream().mapToDouble(DataRecord::getValue).average().orElse(0);
        double min = data.stream().mapToDouble(DataRecord::getValue).min().orElse(0);
        double max = data.stream().mapToDouble(DataRecord::getValue).max().orElse(0);
        statsLabel = new JLabel("Avg: " + avg + " | Min: " + min + " | Max: " + max);
    }

    public static void main(String[] args) {
        List<DataRecord> data = DataLoader.loadData("data.csv");
        SwingUtilities.invokeLater(() -> new DataVisualizationTool(data));
    }
}
