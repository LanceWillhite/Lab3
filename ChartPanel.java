import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

public class ChartPanel extends JPanel {
    public ChartPanel(List<DataRecord> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.forEach(record -> dataset.addValue(record.getValue(), record.getCategory(), record.getName()));

        JFreeChart chart = ChartFactory.createBarChart("Data Chart", "Name", "Value", dataset, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);
    }
}
