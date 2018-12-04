package server;

import java.util.List;

public class StrategyCashIn extends CardAction implements IStrategy {
    @Override
    public String execute(List<String> request) {
        //request
        //  0	CashIn
        //  1	number
        //  2	pin
        //  3   amount
        String number = request.get(1);
        String pin = request.get(2);
        int amount = Integer.parseInt(request.get(3));

        if (!isCardPinCorrect(number, pin))
            return error;
        if (amount <= 0)
            return "Pinigu suma turi buti daugiau uz nuli";
        card.setBalance(card.getBalance() + amount);
        storage.save(card);
        return "Pinigu suma inesta: " + amount;
    }
}
