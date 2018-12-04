package server;

import java.util.List;

public class StrategyDemo extends CardAction implements IStrategy {
    @Override
    public String execute(List<String> request) {
        return "Demo";
    }
}
