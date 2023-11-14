package christmas.model;

public enum Status {
    NOTHING(true),
    GOT_MESSAGE(false);

    private final boolean needLoop;

    Status(boolean needLoop) {
        this.needLoop = needLoop;
    }

    public boolean getStatus() {
        return needLoop;
    }
}
