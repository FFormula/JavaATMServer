package server;

public interface IStorage {

    void save(Card card);
    Card load(String number);
    boolean exists(String number);

}
