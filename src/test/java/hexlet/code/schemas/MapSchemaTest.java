package hexlet.code.schemas;

import hexlet.code.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class MapSchemaTest {

    public static final int HUNDRED = 100;
    public static final int NEGATIVE = -5;

    private final Validator validator = new Validator();
    private MapSchema schema;

    @BeforeEach
    void setSchema() {
        schema = validator.map();
    }

    @Test
    void shouldValidateAnythingWhenNotRequired() {
        Assertions.assertThat(schema.isValid(null)).as("null should be valid").isTrue();
    }

    @Test
    void shouldValidateMapWhenRequired() {
        schema.required();

        Assertions.assertThat(schema.required().isValid(null)).as("null should not be valid").isFalse();
        Assertions.assertThat(schema.isValid(new HashMap())).as("empty map should be valid").isTrue();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        Assertions.assertThat(schema.isValid(data)).as("map should be valid").isTrue();
    }

    @Test
    void shouldValidateMapOfGivenSizeWhenRequiredSizeof() {
        Assertions.assertThat(schema.required().sizeof(1).isValid(new HashMap()))
                .as("map with wrong size should not be valid")
                .isFalse();
        Assertions.assertThat(schema.sizeof(1).isValid(Map.of("key", "val")))
                .as("map with right size should be valid")
                .isTrue();
    }

    @Test
    void shouldValidateMapByKeysWhenShape() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", HUNDRED);
        Assertions.assertThat(schema.isValid(human1)).as("map with valid entries should be valid").isTrue();

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        Assertions.assertThat(schema.isValid(human2)).as("map with valid entries should be valid").isTrue();

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        Assertions.assertThat(schema.isValid(human3))
                .as("map with failed StringSchema.required() check should not be valid").isFalse();

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", NEGATIVE);
        Assertions.assertThat(schema.isValid(human4))
                .as("map with one negative number entry should not be valid when positive check").isFalse();
    }
}
