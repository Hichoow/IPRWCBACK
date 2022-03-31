package nl.hsleiden.iprwc.model.response;

public class MsgRes {
    private String message;

    public MsgRes(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
