package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        super();
    }

    public NumberSchema required() {
        addCheck("required", Number.class::isInstance);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", object -> object == null || object instanceof Integer integer && integer > 0);
        return this;
    }

    public NumberSchema range(int from, int to) {
        addCheck("range", obj -> (int) obj >= from && (int) obj <= to);
        return this;
    }
}
