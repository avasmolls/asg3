
void main() {
    // part 1
    Command[] commands;

    // create all commands first before initalizing commands
    Command fwd1 =  new Command.Forward(5);
    Command dw1 =   new Command.Down(5);
    Command fwd2 =  new Command.Forward(8);
    Command up1 =   new Command.Up(3);
    Command dw2 =   new Command.Down(8);
    Command fwd3 =  new Command.Forward(2);

    // initalize commands with all command values
    commands = new Command[]{fwd1, dw1, fwd2, up1, dw2, fwd3};

    // create sub with no arguments - sub is @ (0, 0)
    Submarine sub = new Submarine();

    // print all individual Commands in commands using enhanced for loop for array
    for (Command cmd: commands) {
        IO.print(describe(cmd) + " ");
    }

    IO.println();
    IO.println("Part 1 Test Cases:");
    IO.println("Starting position: (" + sub.horizontal() + ", " + sub.depth() + ")"); // (0, 0)

    // call executeAll() with our array of Commands
    sub = sub.executeAll(commands);

    IO.println("Final position: (" + sub.horizontal() + ", " + sub.depth() + ")"); // (15, -10)
    IO.println("Product: " + (sub.horizontal() * sub.depth())); // -150

    // part 2- using new surface command surface the sub
    Submarine surfaceSub = sub.execute(new Command.Surface());
    IO.println();
    IO.println("Part 2 Test Cases:");
    IO.println("Sub after surfacing (" + surfaceSub.horizontal() + ", " + surfaceSub.depth() + ")");
    IO.println("Unchanged sub: (" + sub.horizontal() + ", " + sub.depth() + ")" );

    // part 3 checks
    IO.println();
    IO.println("Part 3 Test Cases:");

    Command[] emptyCmds = {};
    Submarine sub2 = new Submarine(4, 7);
    sub2 = sub2.executeAll(emptyCmds);
    IO.println("Submarine after calling executeAll() with empty Command[]: (" + sub2.horizontal() +
            ", " + sub2.depth() + ")");

    Command[] cmds = {new Command.Forward(3), new Command.Surface(), new Command.Down(2)};
    sub2 = sub2.executeAll(cmds);
    IO.println("Submarine after executing executeAll() w/ commands: (" + sub2.horizontal() + ", " + sub2.depth() + ")");



}

// using switch will return String with what kind of command and its amount
static String describe(Command cmd) {
    return switch(cmd) {
        case Command.Down(var a) -> "down " + a;
        case Command.Forward(var a) -> "forward " + a;
        case Command.Up(var a) -> "up " + a;
        // part 2 - surface has no value so just return "surface"
        case Command.Surface() -> "surface";
    };
}

/*
Answer Questions: (1) explain which methods needed changes for Surface, (2) why executeAll
must keep each returned submarine, and (3) why surfacing leaves the original unchanged. Also identify
(4) which constructor or accessor code you had to write for the class that the record supplied
automatically.
1) Adding Surface broke both switch statements (describe() and execute()) because Command is sealed
   and the compiler checks that every switch covers all cases. With no default, it wouldn't compile until
   I added a case Command.Surface() to each one.
2) Submarine never changes itself, execute() always makes a new Submarine and
   returns it. So in executeAll(), if I don't save that new submarine each time
   (toReturn = toReturn.execute(cmd)), the next command won't build on the last move
   ,and it'll just redo everything from the same starting point.
3) Surfacing returns a new Submarine object instead of editing the old one. The original
   submarine's horizontal/depth values are set once in the constructor and never touched
   again, so it stays exactly the same.
4) As a class, I had to write the constructor and the horizontal()/depth() accessor
   methods myself. A record would have generated all of that automatically.
 */
