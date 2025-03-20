import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartPanelComponent extends JPanel {
    public ChartPanelComponent(List<DataRecord> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.forEach(record -> dataset.addValue(record.getValue(), record.getCategory(), record.getName()));

        JFreeChart chart = ChartFactory.createBarChart(
                "Data Chart", "Items", "Value",
                dataset, PlotOrientation.VERTICAL, true, true, false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
    }
}
