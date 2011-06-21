package designer;

public class ChartSet
{
    String tag;
    String title;
    String name;

    public ChartSet( String name, String title )
    {
        this.name = name;
        this.title = title;

        this.tag = name;
    }
    public String getTag ()
    {
        return tag;
    }
    public String getDisplay ()
    {
        return "[%$"+tag+"/"+title+"$%]";
    }
    public String getTitle ()
    {
        return title;
    }
    public String toString ()
    {
        return title;
    }
}
