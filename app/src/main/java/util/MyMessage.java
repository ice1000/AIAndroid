package util;

/**
 * Created by Administrator on 2016/1/6 0006.
 */
public class MyMessage {

    public static final String ID = "id";
    public static final String MSG = "msg";
    public static final String FROM_SABER = "fromSaber";
    public static final int IS_FROM_SABER = 1;

    private boolean fromSaber;
    private boolean idAvailable;
    private String message;
    private int id;

    public MyMessage(boolean fromSaber, String message) {
        this.fromSaber = fromSaber;
        this.message = message;
        idAvailable = false;
    }

    public MyMessage(int fromSaber, String message) {
        this.fromSaber = fromSaber == IS_FROM_SABER;
        this.message = message;
        idAvailable = false;
    }

    public MyMessage(int fromSaber, String message, int id) {
        this.fromSaber = fromSaber == IS_FROM_SABER;
        this.message = message;
        idAvailable = true;
        this.id = id;
    }

    public MyMessage(boolean fromSaber, String message, int id) {
        this.fromSaber = fromSaber;
        this.message = message;
        idAvailable = true;
        this.id = id;
    }

    public int getId() {
        return idAvailable ? id : 0;
    }

    public boolean isFromSaber() {
        return fromSaber;
    }

    public String getMessage() {
        return message;
    }

    public boolean isIdAvailable() {
        return idAvailable;
    }
}
