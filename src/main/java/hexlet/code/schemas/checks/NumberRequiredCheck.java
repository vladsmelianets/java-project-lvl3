package hexlet.code.schemas.checks;

public class NumberRequiredCheck implements Check {

    @Override
    public boolean perform(Object object) {
        return object instanceof Number;
    }
}
