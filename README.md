### Hexlet tests and linter status & CI

[![Actions Status](https://github.com/vladsmelianets/java-project-lvl3/workflows/hexlet-check/badge.svg)](https://github.com/vladsmelianets/java-project-lvl3/actions)
[![Java CI Status](https://github.com/vladsmelianets/java-project-lvl3/actions/workflows/java-ci.yml/badge.svg)](https://github.com/vladsmelianets/java-project-lvl3/actions/workflows/java-ci.yml)

### Code Climate

[![Maintainability](https://api.codeclimate.com/v1/badges/129b3c83bac6ed9a36ca/maintainability)](https://codeclimate.com/github/vladsmelianets/java-project-lvl3/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/129b3c83bac6ed9a36ca/test_coverage)](https://codeclimate.com/github/vladsmelianets/java-project-lvl3/test_coverage)

### Validator

Validator is a schema builder for different values validation.

### Validator features

* Fluent API
* String, Number and Map implementations

#### Imports

```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
```

#### String validation

```java
Validator v=new Validator();
        StringSchema schema=v.string();

        schema.required().contains("what").minLength(FOUR).isValid("what does the fox say"); //true
        schema.required().contains("whatthe").minLength(FOUR).isValid("what does the fox say"); //false
```

#### Number validation

```java
Validator v=new Validator();
        StringSchema schema=v.number();

        schema.required().positive().range(5,10).isValid(5); //true
        schema.required().positive().range(5,10).isValid(4); //false
```

#### Map validation

```java
Validator v=new Validator();
        StringSchema schema=v.map();

        schema.required().isValid(null); //false
        schema.required().schema.sizeof(1).isValid(Map.of("key","val")); //true

// shaping validation for map values by key
        Map<String, BaseSchema> schemas=new HashMap<>();
        schemas.put("name",validator.string().required());
        schemas.put("age",validator.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1=new HashMap<>();
        human1.put("name","Bob");
        human1.put("age",100);
        schema.isValid(human1)); //true
```
