package christmas.controller;

import christmas.model.Status;
import christmas.view.OutputView;
import java.util.Optional;
import java.util.function.Supplier;

public class InputViewController {
    protected static Status state;

    public static <T> Optional<T> read(Supplier<T> supplier) {
        state = Status.NOTHING;
        Optional<T> result = Optional.empty();
        while (state.getStatus()) {
            result = getInput(supplier);
        }
        return result;
    }

    private static <T> Optional<T> getInput(Supplier<T> supplier) {
        Optional<T> result = Optional.empty();
        try {
            result = Optional.ofNullable(supplier.get());
            state = Status.GOT_MESSAGE;
        } catch (IllegalArgumentException e) {
            OutputView.println(e.getMessage());
        }
        return result;
    }
}
