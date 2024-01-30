package mate.academy;

public record BookingResult(String user, boolean success, String message) {
    public BookingResult(String user, boolean success, String message) {
        this.user = user;
        this.success = success;
        this.message = message;
    }

    public String user() {
        return this.user;
    }

    public boolean success() {
        return this.success;
    }

    public String message() {
        return this.message;
    }
}
