package net.loyintean.utils;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonUtilsTest {

    @Test
    public void test() {

        TestJson tj = JsonUtils.toObject("{\"body\":{\"name\":\"n1\"}}", TestJson.class);

        System.out.println("tj=" + tj);

        assertEquals("n1", tj.getName());

    }
}

class TestJson {

    @JsonDeserialize(using = JsonPathDeserializer.class)
    @FromJsonPath(fromPath = "$.body.name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestJson{" +
                "name='" + name + '\'' +
                '}';
    }
}