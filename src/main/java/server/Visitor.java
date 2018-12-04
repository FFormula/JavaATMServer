package server;

public interface Visitor {
    boolean visit(StrategyCreateCard strategy);
    boolean visit(StrategyCashIn strategy);
    boolean visit(StrategyCashOut strategy);
    boolean visit(StrategyChangePin strategy);
    boolean visit(StrategyGetBalance strategy);
}
