package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        super();
    }

    public NumberSchema required() {
        getChecks().put("required", Number.class::isInstance);
        return this;
    }

    public NumberSchema positive() {
        getChecks().put("positive", object -> object == null || (int) object > 0);
        return this;
    }

    public NumberSchema range(int from, int to) {
        getChecks().put("range", obj -> (int) obj >= from && (int) obj <= to);
        return this;
    }
}
