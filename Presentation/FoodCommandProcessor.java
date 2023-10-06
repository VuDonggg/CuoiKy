package CuoiKy.Presentation;

import java.util.ArrayList;
import java.util.List;

public class FoodCommandProcessor {
    private List<FoodCommand> commandQueue = new ArrayList<>();

    public void addCommand(FoodCommand command) {
        commandQueue.add(command);
    }

    public void processCommands() {
        for (FoodCommand command : commandQueue) {
            command.execute();
        }
        commandQueue.clear();
    }
}
