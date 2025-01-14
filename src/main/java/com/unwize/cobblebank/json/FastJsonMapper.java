package com.unwize.cobblebank.json;

import com.alibaba.fastjson2.JSON;
import io.javalin.json.JsonMapper;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.stream.Stream;

import static com.alibaba.fastjson.JSON.writeJSONString;

public class FastJsonMapper implements JsonMapper {

    // basic method for mapping to json
    public String toJsonString(@NotNull Object obj, @NotNull Type type) {
        return JSON.toJSONString(obj);
    }

    // most memory efficient method for mapping to json
    public void writeToOutputStream(@NotNull Stream<?> stream, @NotNull OutputStream outputStream) {
        try {
            writeJSONString(outputStream, stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // basic method for mapping from json
    @NotNull
    public <T> T fromJsonString(@NotNull String json, @NotNull Type targetType) {
        return JSON.parseObject(json, targetType);
    }


    // more memory efficient method for mapping from json
    public <T> T fromJsonStream(@NotNull InputStream json, @NotNull Type targetType) {
        return JSON.parseObject(json, targetType);
    }
}
