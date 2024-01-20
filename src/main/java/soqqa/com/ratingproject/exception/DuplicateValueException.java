package soqqa.com.ratingproject.exception;

public class DuplicateValueException extends RuntimeException{

    public DuplicateValueException(String message) {
        super(message);
    }
}