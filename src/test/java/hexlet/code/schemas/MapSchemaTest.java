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
    void testValidateMapWhenNotRequired() {
        Assertions.assertThat(schema.isValid(null)).as("null should be valid when not required").isTrue();
    }

    @Test
    void testValidateMapWhenRequired() {
        schema.required();
        Assertions.assertThat(schema.isValid(null)).as("null should not be valid when required").isFalse();
        Assertions.assertThat(schema.isValid(new HashMap())).as("empty map should be valid when required").isTrue();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        Assertions.assertThat(schema.isValid(data)).as("populated map should be valid when required").isTrue();
    }

    @Test
    void testValidateMapWhenSizeof() {
        schema.required();
        Assertions.assertThat(schema.sizeof(1).isValid(new HashMap()))
                .as("map with wrong size should not be valid when required sizeof").isFalse();
        Assertions.assertThat(schema.sizeof(1).isValid(Map.of("key", "val")))
                .as("map with matching size should be valid when required sizeof").isTrue();
    }

    @Test
    void testValidateMapWhenShape() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", HUNDRED);
        Assertions.assertThat(schema.isValid(human1)).as("map with valid entries should be valid when shape").isTrue();

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        Assertions.assertThat(schema.isValid(human2)).as("map with valid entries should be valid when shape").isTrue();

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        Assertions.assertThat(schema.isValid(human3))
                .as("map with failed string validation should not be valid when shape").isFalse();

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", NEGATIVE);
        Assertions.assertThat(schema.isValid(human4))
                .as("map with failed number validation should not be valid when shape").isFalse();
    }
}
