package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        super();
    }

    public MapSchema required() {
        addCheck("required", Map.class::isInstance);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", obj -> ((Map<?, ?>) obj).size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addCheck("shape", obj -> schemas.entrySet().stream()
                .allMatch(entry -> entry.getValue().isValid(((Map<String, Object>) obj).get(entry.getKey()))));
        return this;
    }
}
