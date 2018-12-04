package server;

import java.util.List;

public class Context {

    private IStrategy strategy;

    private IStrategy getStrategy(String strategy) {
        switch (strategy) {
            case "GetBalance" : return new StrategyGetBalance();
            case "CashIn"     : return new StrategyCashIn();
            case "CashOut"    : return new StrategyCashOut();
            case "ChangePin"  : return new StrategyChangePin();
            case "CreateCard" : return new StrategyCreateCard();
            default : return null;
        }
    }

    private IStrategy getStrategyDinamic(String strategy) {
        try {
            return (IStrategy) Class.forName("server.Strategy" + strategy).newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    public String execute(IStorage storage, List<String> request) {
        if (request == null || request.size() < 1)
            return "Neteisingas parametru sarasas";
        strategy = getStrategyDinamic(request.get(0));
        if (strategy == null)
            return "Nepazistama funkcija";
        ((CardAction)strategy).setStorage(storage);
        try {
            return strategy.execute(request);
        } catch (IndexOutOfBoundsException e) {
            return "Neteisingas parametru skaicius";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
