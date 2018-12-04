package server;

import java.util.List;

public class StrategyChangePin extends CardAction implements IStrategy {
    @Override
    public String execute(List<String> request) {
        //request
        //  0	ChangePin
        //  1	number
        //  2	old-pin
        //  3   new-pin
        String number = request.get(1);
        String oldPin = request.get(2);
        String newPin = request.get(3);

        if (!isCardPinCorrect(number, oldPin))
            return error;

        if (!newPin.matches("^[0-9]{4}$")) //   ^\\d\\d\\d\\d$
            return "Naujas pin kodas turi buti susidetas is 4 skaitmenu";
        card.setPin(newPin);
        storage.save(card);
        return "Pin kodas pakeistas";
    }
}
