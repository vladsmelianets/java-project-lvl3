package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        super();
    }

    public NumberSchema required() {
        requiresCheck = true;
        checks.put("required", Number.class::isInstance);
        return this;
    }

    public NumberSchema positive() {
        checks.put("positive", object -> (int) object > 0);
        return this;
    }

    public NumberSchema range(int from, int to) {
        checks.put("range", obj -> (int) obj >= from && (int) obj <= to);
        return this;
    }
}
