public class Submarine {
    // fields
    private int horizontal;
    private int depth;

    // const
    public Submarine() {
        this.horizontal = 0;
        this.depth = 0;
    }

    public Submarine(int horizontal, int depth) {
        this.horizontal = horizontal;
        this.depth = depth;
    }

    // accessors
    public int horizontal() {
        return horizontal;
    }

    public int depth() {
        return depth;
    }

    // methods

    public Submarine execute(Command cmd) {
        return switch(cmd) {
            case Command.Forward(var a) ->
                    new Submarine(this.horizontal + a, this.depth);
            case Command.Up(var a) ->
                    new Submarine(this.horizontal, this.depth - a);
            case Command.Down(var a) ->
                    new Submarine(this.horizontal, this.depth + a);
            // part 2 - add implementation for surface
            case Command.Surface() -> new Submarine(this.horizontal, 0);
        };
    }

    public Submarine executeAll(Command[] cmds) {
        if (cmds == null) { return this; }
        Submarine toReturn = this;
        for (Command cmd: cmds) {
            toReturn = toReturn.execute(cmd);
        }
        return toReturn;
    }

    @Override
    public String toString() {
        return "Sub @ position(%d, %d)".formatted(horizontal, depth);
    }
}
