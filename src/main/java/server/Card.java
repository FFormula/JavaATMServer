package server;

public class Card {

    private String number;
    private String pin;
    private int balance;
    private CardStatus status;
    private String expires;
    private int pinErrors;

    protected Card setNumber(String number) {
        this.number = number;
        return this;
    }
    Card setPin(String pin) {
        this.pin = pin;
        return this;
    }
    Card setBalance(int balance) {
        this.balance = balance;
        return this;
    }
    Card setStatus(CardStatus status) {
        this.status = status;
        return this;
    }
    Card setExpires(String expires) {
        this.expires = expires;
        return this;
    }
    Card setPinErrors(int pinErrors) {
        this.pinErrors = pinErrors;
        return this;
    }

    String getNumber() {
        return number;
    }
    String getPin() {
        return pin;
    }
    int getBalance() {
        return balance;
    }
    CardStatus getStatus() {
        return status;
    }
    String getExpires() {
        return expires;
    }
    int getPinErrors() {
        return pinErrors;
    }

    @Override
    public String toString() {
        return "Card{" +
                "number='" + number + '\'' +
                ", pin='" + pin + '\'' +
                ", balance=" + balance +
                ", status=" + status +
                ", expires='" + expires + '\'' +
                ", pinErrors=" + pinErrors +
                '}';
    }
}
