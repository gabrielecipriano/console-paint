package cpaint;

public class EmptyScreen implements Screen {
    @Override
    public Screen execute(Command command) {
        return command.executeWith(this);
    }

    @Override
    public String representation() {
        return null;
    }
}
