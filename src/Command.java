public sealed interface Command {

    // record
    record Forward(int amount) implements Command {}
    record Up(int amount) implements      Command {}
    record Down(int amount) implements    Command {}

    // part 2
    record Surface() implements           Command {}
}
