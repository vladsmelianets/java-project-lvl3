package hexlet.code.schemas.checks;

public class RangeCheck implements Check {

    private final int from;
    private final int to;

    public RangeCheck(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean perform(Object object) {
        return (int) object >= from && (int) object <= to;
    }
}
