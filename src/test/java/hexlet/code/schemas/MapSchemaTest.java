package hexlet.code.schemas;

import hexlet.code.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class MapSchemaTest {

    private MapSchema schema;

    @BeforeEach
    void setSchema() {
        schema = new Validator().map();
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
}
