package util;

/**
 * Created by Administrator on 2016/1/6 0006.
 */
public class MyMessage {

    private boolean fromSaber;
    private String message;

    public MyMessage(boolean fromSaber, String message) {
        this.fromSaber = fromSaber;
        this.message = message;
    }

    public boolean isFromSaber() {
        return fromSaber;
    }

    public void setFromSaber(boolean fromSaber) {
        this.fromSaber = fromSaber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
