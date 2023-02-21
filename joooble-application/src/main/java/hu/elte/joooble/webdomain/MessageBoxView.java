package hu.elte.joooble.webdomain;

public class MessageBoxView {
    private final String type;
    private final String title;
    private final String message;

    public MessageBoxView(String type, String title, String message) {
        this.type = type;
        this.title = title;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}
