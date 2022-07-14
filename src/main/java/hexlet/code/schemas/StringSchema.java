package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        super();
    }

    public StringSchema required() {
        addCheck("required", obj -> obj instanceof String string && string.length() > 0);
        return this;
    }

    public StringSchema contains(String sequence) {
        addCheck("contains", obj -> ((String) obj).contains(sequence));
        return this;
    }

    public StringSchema minLength(int minLength) {
        addCheck("minLength", obj -> ((String) obj).length() >= minLength);
        return this;
    }
}
