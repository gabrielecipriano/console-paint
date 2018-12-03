package cpaint;

public class EmptyScreen implements Screen {
    private String representation;

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
        this.representation = description;
        return this;
    }
}
