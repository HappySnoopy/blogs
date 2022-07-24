package net.loyintean.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;

import java.io.IOException;

public class JsonPathDeserializer extends JsonDeserializer<Object> {

    private JsonDeserializer<? extends JsonNode> jsonDeserializer = JsonNodeDeserializer.getDeserializer(JsonNode.class);

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        System.out.println("===========");

        JsonNode node = jsonDeserializer.deserialize(p, ctxt);


        FromJsonPath fromJsonPath = handledType().getAnnotation(FromJsonPath.class);

        if (fromJsonPath == null || fromJsonPath.fromPath() == null) {
            return JsonUtils.toObject(node, handledType());
        } else {
            String jsonPath = fromJsonPath.fromPath();

            JsonNode valueNode = node.get(jsonPath);

            return JsonUtils.toObject(valueNode, handledType());

        }
    }


}
