package game.util;

// Generic outcome/result wrapper for game operations.
// T -> extra info about the result

public class Outcome<T> {
    private final boolean success;
    private final T details;

    public Outcome(boolean success, T details){
        this.success = success;
        this.details = details;
    }

    public boolean isSuccess(){
        return success;
    }

    public T getDetails(){
        return details;
    }

    @Override
    public String toString(){
        return "Outcome{success: " + success + " details: " + details + "}";
    }
}
