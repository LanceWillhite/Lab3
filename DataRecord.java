public class DataRecord {
    private String category;
    private String name;
    private double value;

    public DataRecord(String category, String name, double value) {
        this.category = category;
        this.name = name;
        this.value = value;
    }

    public String getCategory() { return category; }
    public String getName() { return name; }
    public double getValue() { return value; }

    @Override
    public String toString() {
        return category + " - " + name + " ($" + value + ")";
    }
}
