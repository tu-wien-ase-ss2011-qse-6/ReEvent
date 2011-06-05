package reevent.util;

import org.apache.commons.lang.NotImplementedException;

import java.math.BigDecimal;
import java.util.*;

public class ClassHandlerResolver<K, V> {
    Map<Class<? extends K>, V> handlers = new HashMap<Class<? extends K>, V>();
    List<Map.Entry<Class<? extends K>, V>> orderedHandlers;

    public synchronized V register(Class<? extends K> type, V handler) {
        V old = handlers.put(type, handler);
        orderedHandlers = null;
        return old;
    }
    
    public V resolve(Class<? extends K> type) {
        List<Map.Entry<Class<? extends K>, V>> ordered = getOrderedHandlers();
        for (Map.Entry<Class<? extends K>, V> e : ordered){
            // first found handler is more general than desired
            if (e.getKey().isAssignableFrom(type)) return e.getValue();
        }
        return null;
    }

    public V resolve(K key) {
        return resolve((Class<? extends K>) key.getClass());
    }

    /**
     * Returns handlers ordered from most to least specific.
     * @return
     */
    private synchronized List<Map.Entry<Class<? extends K>, V>> getOrderedHandlers() {
        if (orderedHandlers == null) {
            orderedHandlers = new ArrayList<Map.Entry<Class<? extends K>, V>>();

            ArrayList<Map.Entry<Class<? extends K>, V>> entries = new ArrayList<Map.Entry<Class<? extends K>, V>>(handlers.entrySet());
            // minsort entries - from least to most specific
            while (!entries.isEmpty()) {
                Map.Entry<Class<? extends K>, V> min = entries.remove(entries.size() - 1);
                for (int i = 0; i < entries.size(); i++) {
                    Map.Entry<Class<? extends K>, V> e = entries.get(i);
                    if (e.getKey().isAssignableFrom(min.getKey())) {
                        // found new min
                        entries.set(i, min);
                        min = e;
                    }
                }
                orderedHandlers.add(min);
            }
            Collections.reverse(orderedHandlers);
        }
        return orderedHandlers;
    }

    public static void main(String[] args) {
        ClassHandlerResolver<Object, String> resolver = new ClassHandlerResolver<Object, String>();
        resolver.register(Object.class, "object");
        resolver.register(Number.class, "number");
        resolver.register(Exception.class, "exception");
        resolver.register(Integer.class, "integer");
        resolver.register(BigDecimal.class, "decimal");
        resolver.register(RuntimeException.class, "runtime");
        resolver.register(UnsupportedOperationException.class, "unsupported");
        resolver.register(NotImplementedException.class, "notimplemented");

        System.out.println("resolver.getOrderedHandlers() = " + resolver.getOrderedHandlers());
        System.out.println("resolver.resolve(RuntimeException.class) = " + resolver.resolve(RuntimeException.class));
        System.out.println("resolver.resolve(Integer.class) = " + resolver.resolve(Integer.class));
        System.out.println("resolver.resolve(String.class) = " + resolver.resolve(String.class));
    }
}
