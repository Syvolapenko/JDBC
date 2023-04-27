package ex;

public class LongestProject {
    private String name;
    private int month;


    public LongestProject(String name, int month) {
        this.name = name;
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public int getMonth() {
        return month;
    }

    @Override
    public String toString() {
        return "LongestProject{" +
                "name='" + name + '\'' +
                ", month=" + month +
                '}';
    }
}
