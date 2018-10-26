package org.depromeet.fill_day.exception;

public class NoAccountFoundException extends RuntimeException {

    private final static String MESSAGE = "찾을 수 없는 계정입니다.";

    public NoAccountFoundException() {}

    public NoAccountFoundException(Throwable t) {super(t);}

    public NoAccountFoundException(String msg) { super(msg); }

    public NoAccountFoundException(String msg, Throwable t) { super(msg, t); }

    @Override
    public String getMessage() {
        final String message = super.getMessage();
        if (message != null) {
            return message;
        }
        return MESSAGE;
    }
}
