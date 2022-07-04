package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        super();
    }

    public MapSchema required() {
        getChecks().put("required", Map.class::isInstance);
        return this;
    }

    public MapSchema sizeof(int size) {
        getChecks().put("sizeof", obj -> ((Map<?, ?>) obj).size() == size);
        return this;
    }
}
