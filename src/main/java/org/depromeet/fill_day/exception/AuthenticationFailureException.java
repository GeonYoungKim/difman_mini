package org.depromeet.fill_day.exception;

public class AuthenticationFailureException extends RuntimeException {

    private final static String MESSAGE = "인증에 실패했습니다";

    public AuthenticationFailureException() {}

    public AuthenticationFailureException(Throwable t) {super(t);}

    public AuthenticationFailureException(String msg) { super(msg); }

    public AuthenticationFailureException(String msg, Throwable t) { super(msg, t); }

    @Override
    public String getMessage() {
        final String message = super.getMessage();
        if (message != null) {
            return message;
        }
        return MESSAGE;
    }
}
