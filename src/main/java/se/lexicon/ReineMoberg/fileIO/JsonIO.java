package se.lexicon.ReineMoberg.fileIO;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import se.lexicon.ReineMoberg.model.Car2;
import se.lexicon.ReineMoberg.model.Owner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonIO {

    private ObjectMapper objectMapper;

    public JsonIO() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        objectMapper.registerModule(new JavaTimeModule());
    }

    /*
    * Serialize from list to file in JSON format. Generic version.
    * */
    public <T> void serializeListToJson(List<T> tList, File target) {
        try {
            if (!target.exists()) {
                target.createNewFile();
            }
            objectMapper.writeValue(target, tList);
        } catch (JsonGenerationException | JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Deserialize from file in JSON format to list. Generic version. Just replacing data type
    * with a generic type doesn't work. The data type gets erased at runtime. JSON sends a
    * default collection of linked hash set instead of list of choice. Causes compatibility
    * problems in rest of code. Data type must be sent as a parameter when method is called,
    * and then rebuilt using a series of JSON commands.
    * */
    public <T> List<T> deserializeJsonToList(File source, Class<T> contentClass) {
        JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, contentClass);
        List<T> result = new ArrayList<>();
        try {
            result = objectMapper.readValue(source, type);
        } catch (JsonParseException | JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
    * Deserialize file with Car2 objects in JSON format to list.
    * */
    public List<Car2> deserializeJsonToCar2List(File source) {
        List <Car2> result = new ArrayList<>();
        try {
            result = objectMapper.readValue(source, new TypeReference<List<Car2>>() {
            });
        } catch (JsonParseException | JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
    * Deserialize file with Owner objects in JSON format to list.
    * */
    public List<Owner> deserializeJsonToOwnerList(File source) {
        List <Owner> result = new ArrayList<>();
        try {
            result = objectMapper.readValue(source, new TypeReference<List<Owner>>() {
            });
        } catch (JsonParseException | JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}

