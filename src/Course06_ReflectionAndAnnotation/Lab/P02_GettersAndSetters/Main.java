package Course06_ReflectionAndAnnotation.Lab.P02_GettersAndSetters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Main { public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        /*Class<Reflection> clazz = Reflection.class;

        System.out.println(clazz);

        System.out.println(clazz.getSuperclass());

        Arrays.stream(clazz.getInterfaces())
                .forEach(System.out::println);

        Constructor<Reflection> constructor = clazz.getConstructor();
        Reflection reflection = constructor.newInstance();
        System.out.println(reflection);*/
    //System.out.println(clazz.getConstructor().newInstance());
//----------------------------------------------------------------------------------------
        /*//Gets only the public constructors
        Constructor<?>[] constructors = clazz.getConstructors();
        //Gets ALL constructors
        Constructor<?>[] allConstructors = clazz.getDeclaredConstructors();*/

//----------------------------------------------------------------------------------------
    //Gets public fields
        /*Field name = clazz.getField("name");
        name.setAccessible(true);
        System.out.println(name.getName());
        System.out.println(name.getType());
        System.out.println(name.getDeclaringClass());

        System.out.println();

        //Gets ALL fields
        Field nickName = clazz.getDeclaredField("nickName");
        nickName.setAccessible(true);
        System.out.println(nickName.getName());
        System.out.println(nickName.get(null));
        System.out.println(nickName.getType());
        System.out.println(nickName.getDeclaringClass());
        System.out.println();*/
//----------------------------------------------------------------------------------------

        /*Reflection reflection1 = new Reflection();

        System.out.println(reflection1.getName());

        Method method = clazz.getMethod("getName");

        System.out.println(method.invoke(reflection1));
        System.out.println();*/
//----------------------------------------------------------------------------------------
    Class<Reflection> clazz = Reflection.class;

    Method[] methods = clazz.getDeclaredMethods();

    Comparator<Method> comparator = Comparator.comparing(Method::getName);

    Set<Method> getters = new TreeSet<>(comparator);
    Set<Method> setters = new TreeSet<>(comparator);

    for (Method method : methods) {
        String methodName = method.getName();
        if (methodName.contains("get")){
            getters.add(method);
        } else if (methodName.contains("set")) {
            setters.add(method);
        }
    }

    for (Method getter : getters) {
        System.out.printf("%s will return class %s\n", getter.getName(), getter.getReturnType().getName());
    }

    for (Method setter : setters) {
        System.out.printf("%s and will set field of class %s\n", setter.getName(), setter.getParameterTypes()[0].getName());
    }
}
}
