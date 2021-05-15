import java.io.Serializable;

public class Reporting implements Serializable {
    private String text;
    private int code;

    public Reporting(int code, String text){
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public void addLine(String line){
        text = text + "\n" + line;
    }
}
