package cpaint;

public class EmptyScreen implements Screen {
    private String representation;

    public EmptyScreen() {
    }

    private EmptyScreen(String representation) {
        this.representation = representation;
    }

    @Override
    public Screen execute(Command command) {
        return command.executeWith(this);
    }

    @Override
    public String representation() {
        return representation;
    }

    @Override
    public Screen representText(String description) {
        return new EmptyScreen(description);
    }
}
