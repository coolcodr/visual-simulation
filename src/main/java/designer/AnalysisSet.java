package designer;

public class AnalysisSet {
    String tag;
    String title;
    String name;

    public AnalysisSet(String name, String title) {
        this.name = name;
        this.title = title;

        tag = title + ":\t[%$" + name + "|" + title + "$%]";
    }

    public void setTag(String middle) {
        tag = middle + " " + title + ":\t\t[%$" + name + "|" + middle.substring(0, 3) + "|" + title.substring(0, 3) + "$%]";
    }

    public String getTag() {
        return tag;
    }

    public String toString() {
        return title;
    }
}
