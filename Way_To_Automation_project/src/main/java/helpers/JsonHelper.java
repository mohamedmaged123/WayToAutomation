package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class JsonHelper {
        private static final ObjectMapper mapper = new ObjectMapper();

        public static <T> T hydrateEntity(Class<T> clazz, String module, String sectionName) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
            T instance = clazz.getDeclaredConstructor().newInstance();
            ObjectReader reader = mapper.readerForUpdating(instance);
            File configFile = new File(module + "\\data\\" + clazz.getSimpleName() + ".json");
            reader.readValue(configFile);
            return instance;
        }
    }



