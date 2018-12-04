package server;

import java.sql.*;

public class StorageBase implements IStorage {

    private Connection connection;

    public StorageBase(String baseFilename) {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + baseFilename;
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            throw new RuntimeException("SQLite error: " + e.getMessage());
        }
    }

    @Override
    public void save(Card card) {
        try {
            String query;
            if (exists(card.getNumber()))
                query = "UPDATE card          " +
                        "   SET pin = ?,      " +
                        "       balance = ?,  " +
                        "       status = ?,   " +
                        "       expires = ?,  " +
                        "       pinerrors = ? " +
                        "WHERE number = ?";
            else
                query = "INSERT INTO card " +
                    "(pin, balance, status, expires, pinerrors, number) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, card.getPin());
               ps.setInt(2, card.getBalance());
            ps.setString(3, card.getStatus().toString());
            ps.setString(4, card.getExpires());
               ps.setInt(5, card.getPinErrors());
            ps.setString(6, card.getNumber());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("SQLite error: " + e.getMessage());
        }
    }

    @Override
    public Card load(String number) {
        try {
            String query =
                    "SELECT number, pin, balance, status, expires, pinerrors " +
                    "  FROM card " +
                    " WHERE number = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, number);
            ResultSet rs = ps.executeQuery();
            if (!rs.next())
                throw new RuntimeException("Card not found");
            return new Card()
                    .setNumber(rs.getString("number"))
                    .setPin(rs.getString("pin"))
                    .setBalance(rs.getInt("balance"))
                    .setStatus(CardStatus.valueOf(rs.getString("status")))
                    .setExpires(rs.getString("expires"))
                    .setPinErrors(rs.getInt("pinerrors"));
        } catch (Exception e) {
            throw new RuntimeException("SQLite error: " + e.getMessage());
        }
    }

    @Override
    public boolean exists(String number) {
        try {
            String query = "SELECT * FROM card WHERE number = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, number);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            throw new RuntimeException("SQLite error: " + e.getMessage());
        }
    }
}
