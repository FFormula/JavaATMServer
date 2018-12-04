package server;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class StrategyCreateCard
        extends CardAction
        implements IStrategy {
    @Override
    public String execute(List<String> request) {
        //request
        //  0	CreateCard
        try {
            create();
            return "Korteles numeris: " + card.getNumber() +
                   ", pin-kodas: " + card.getPin();
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private void create() {
        String number = generateUnusedNumber();
        String pin = generatePin();
        String expires = generateExpireDate(); // YYYY-MM-DD HH:II:SS
        card = new Card()
                .setNumber(number)
                .setPin(pin)
                .setExpires(expires)
                .setStatus(CardStatus.ACTIVE)
                .setPinErrors(0)
                .setBalance(0);
        storage.save(card);
    }

    private String generateUnusedNumber() {
        for (int j = 0; j < 1; j ++) {
            String number = (Math.random() + "").substring(2, 8);
            if (!storage.exists(number))
                return number;
        }
        throw new RuntimeException("Korteles pasibaige");
    }

    private String generateExpireDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(calendar.getTime());
    }

    private String generatePin() { // 0.012345675
        //   ^^^^
        return (Math.random() + "").substring(2, 6);
    }

}
