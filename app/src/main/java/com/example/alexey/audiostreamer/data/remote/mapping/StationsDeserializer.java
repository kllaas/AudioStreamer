package com.example.alexey.audiostreamer.data.remote.mapping;

import com.example.alexey.audiostreamer.data.entity.Station;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by alexey
 */

public class StationsDeserializer implements JsonDeserializer<Station> {

    @Override
    public Station deserialize(JsonElement json, Type typeOfT,
                                     JsonDeserializationContext context) throws JsonParseException {
        Type listType = new TypeToken<Station>() {}.getType();

        return new Gson().fromJson(json, listType);
    }

}
