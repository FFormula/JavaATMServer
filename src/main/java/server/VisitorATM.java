package server;

public class VisitorATM implements Visitor {
    @Override
    public boolean visit(StrategyCreateCard strategy) {
        return false;
    }

    @Override
    public boolean visit(StrategyCashIn strategy) {
        return false;
    }

    @Override
    public boolean visit(StrategyCashOut strategy) {
        return false;
    }

    @Override
    public boolean visit(StrategyChangePin strategy) {
        return false;
    }

    @Override
    public boolean visit(StrategyGetBalance strategy) {
        return false;
    }
}
