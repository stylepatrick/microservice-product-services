package stylepatrick.api.core.event;

import java.util.Date;

public class Event<K, T> {

    public enum Type {
        CREATE,
        DELETE
    }

    private final Type eventType;
    private final K key;
    private final T data;

    private final Date crdt;

    public Event() {
        this.eventType = null;
        this.key = null;
        this.data = null;
        this.crdt = null;
    }

    public Event(Type eventType, K key, T data) {
        this.eventType = eventType;
        this.key = key;
        this.data = data;
        this.crdt = new Date();
    }

    public Type getEventType() {
        return eventType;
    }

    public K getKey() {
        return key;
    }

    public T getData() {
        return data;
    }
}