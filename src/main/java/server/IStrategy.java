package server;

import java.util.List;

public interface IStrategy {
    String execute(List<String> request);
}
