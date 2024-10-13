package burp;

public class RepeatedString {
    private String text;
    private int count;

    RepeatedString(String text) {
        this.text = text;
        this.count = 1;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    String repeatedText() {
        return text.repeat(count);
    }
}
