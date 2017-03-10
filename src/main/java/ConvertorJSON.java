import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

import static org.reflections.ReflectionUtils.getAllMethods;
import static org.reflections.ReflectionUtils.withModifier;
import static org.reflections.ReflectionUtils.withPrefix;

/**
 * Parser handles only primitive data types.
 */
public class ConvertorJSON {

    private static final String NAME = "$NAME";
    private static final String VALUE = "$VALUE";
    private static final String SEPARATOR = ",";
    public final String ELEMENT = "\"" + NAME + "\":" + VALUE;
    private Set<Method> getters;
    private Object object;
    private Class aClass;
    private Field[] fields;
    private StringBuilder stringBuilder;


    public ConvertorJSON(Object object) {
        this.object = object;
        aClass = this.object.getClass();
        fields = aClass.getFields();
        stringBuilder = new StringBuilder("{");
        Method[] methods = aClass.getMethods();
        getters = getAllMethods(aClass, withModifier(Modifier.PUBLIC), withPrefix("get"));
    }

    private void valuesFromGetters() throws InvocationTargetException, IllegalAccessException {
        for (Method method : getters) {
            String element = ELEMENT;
            String value;
            Object getterMethod = method.invoke(object);
            value = String.format("\"%s\"", getterMethod);
            stringBuilder.append(element.replace(NAME, method.getName().substring(3).toLowerCase())
                    .replace(VALUE, value) + SEPARATOR);
        }
    }

    public String toJSON() throws IllegalAccessException, InvocationTargetException {
        for (Field field : fields) {
            String element = ELEMENT;
            stringBuilder.append(element.replace(NAME, field.getName())
                    .replace(VALUE, String.valueOf(field.get(object))))
                    .append(SEPARATOR);
        }
        valuesFromGetters();
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(SEPARATOR))
                .append("}");
        return stringBuilder.toString();
    }
}
