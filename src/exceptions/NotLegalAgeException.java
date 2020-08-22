package exceptions;

public class NotLegalAgeException extends Exception {

    private static final long serialVersionUID = 1L;

    public NotLegalAgeException() {
        super("This person is not of legal age. He/She can't enter the minimarket.");
    }
}