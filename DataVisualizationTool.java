import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DataVisualizationTool extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel statsLabel;
    private JTextArea detailsArea;
    private ChartPanelComponent chartPanel;

    public DataVisualizationTool(List<DataRecord> data) {
        setTitle("Data Visualization Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());

        setupTable(data);
        setupStatsPanel(data);
        setupDetailsPanel();
        setupChartPanel(data);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.add(statsLabel);
        bottomPanel.add(new JScrollPane(detailsArea));

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(chartPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

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

        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                detailsArea.setText("Category: " + table.getValueAt(selectedRow, 0) +
                        "\nName: " + table.getValueAt(selectedRow, 1) +
                        "\nValue: $" + table.getValueAt(selectedRow, 2));
            }
        });
    }

    private void setupStatsPanel(List<DataRecord> data) {
        double avg = data.stream().mapToDouble(DataRecord::getValue).average().orElse(0);
        double min = data.stream().mapToDouble(DataRecord::getValue).min().orElse(0);
        double max = data.stream().mapToDouble(DataRecord::getValue).max().orElse(0);
        statsLabel = new JLabel("Avg: $" + avg + " | Min: $" + min + " | Max: $" + max);
    }

    private void setupDetailsPanel() {
        detailsArea = new JTextArea(5, 20);
        detailsArea.setEditable(false);
    }

    private void setupChartPanel(List<DataRecord> data) {
        chartPanel = new ChartPanelComponent(data);
    }

    public static void main(String[] args) {
        List<DataRecord> data = DataLoader.loadData("data.csv");
        SwingUtilities.invokeLater(() -> new DataVisualizationTool(data));
    }
}
