package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        super();
    }

    public StringSchema required() {
        requiresCheck = true;
        checks.put("required", obj -> obj instanceof String string && string.length() > 0);
        return this;
    }

    public StringSchema contains(String sequence) {
        checks.put("contains", obj -> ((String) obj).contains(sequence));
        return this;
    }

    public StringSchema minLength(int minLength) {
        checks.put("minLength", obj -> ((String) obj).length() >= minLength);
        return this;
    }
}
