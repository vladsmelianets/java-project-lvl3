package hexlet.code.schemas.checks;

public class PositiveCheck implements Check {

    @Override
    public boolean perform(Object object) {
        try {
            return (int) object > 0;
        } catch (ClassCastException e) {
            return false;
        }
    }
}
