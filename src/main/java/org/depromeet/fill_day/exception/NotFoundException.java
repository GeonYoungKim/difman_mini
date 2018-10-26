package org.depromeet.fill_day.exception;

public class NotFoundException extends RuntimeException {

    private final static String MESSAGE = "찾을 수 없는 리소스입니다.";

    public NotFoundException() {}

    public NotFoundException(Throwable t) {super(t);}

    public NotFoundException(String msg) { super(msg); }

    public NotFoundException(String msg, Throwable t) { super(msg, t); }

    @Override
    public String getMessage() {
        final String message = super.getMessage();
        if (message != null) {
            return message;
        }
        return MESSAGE;
    }
}
