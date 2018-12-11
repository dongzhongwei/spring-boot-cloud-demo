package com.roncoo.eshop.inventory.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        JsonUtils.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        JsonUtils.objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        JsonUtils.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonUtils.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private static final ObjectWriter writer = JsonUtils.objectMapper.writer();
    private static final ObjectWriter prettyWriter = JsonUtils.objectMapper.writerWithDefaultPrettyPrinter();

    public static String toJsonPrettyString(Object value) throws JsonProcessingException {
        return JsonUtils.prettyWriter.writeValueAsString(value);
    }

    public static String toJsonString(Object value) {
        try {
            return JsonUtils.writer.writeValueAsString(value);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Returns the deserialized object from the given json string and target class; or null if the given json string is
     * null.
     */
    public static <T> T fromJsonString(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        try {
            return JsonUtils.objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Unable to parse Json String.", e);
        }
    }

    public static <T> T fromJsonString(String json, TypeReference<T> typeReference) {
        if (json == null) {
            return null;
        }
        try {
            return JsonUtils.getObjectMapper().readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Unable to parse Json String.", e);
        }
    }



    public static JsonNode jsonNodeOf(String json) {
        return JsonUtils.fromJsonString(json, JsonNode.class);
    }

    public static JsonGenerator jsonGeneratorOf(Writer writer) throws IOException {
        return new JsonFactory().createGenerator(writer);
    }

    public static <T> T loadFrom(File file, Class<T> clazz) throws IOException {
        try {
            return JsonUtils.objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static void load(InputStream input, Object obj) throws IOException, JsonProcessingException {
        objectMapper.readerForUpdating(obj).readValue(input);
    }

    public static <T> T loadFrom(InputStream input, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        return JsonUtils.objectMapper.readValue(input, clazz);
    }

    public static ObjectMapper getObjectMapper() {
        return JsonUtils.objectMapper;
    }

    public static ObjectWriter getWriter() {
        return JsonUtils.writer;
    }

    public static ObjectWriter getPrettywriter() {
        return JsonUtils.prettyWriter;
    }


    public static <K> List<K> listFromJson(String json, Class<K> K) {
        List<K> data = new ArrayList<>();

        json =  json.replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");

        try {
            JavaType javaType = getCollectionType(ArrayList.class, K);
            data = objectMapper.readValue(json,javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
         return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }



}
