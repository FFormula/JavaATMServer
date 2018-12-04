package server;

import java.util.List;

public class StrategyGetBalance
        extends CardAction
        implements IStrategy {

    @Override
    public String execute(List<String> request) {
        //request
        //  0	GetBalance
        //  1	number
        //  2	pin
        String number = request.get(1);
        String pin = request.get(2);

        if (!isCardPinCorrect(number, pin))
            return error;

        return "Korteles likutis yra: " + card.getBalance() + " EUR";
    }
}
