package database;

/**
 * Created by Usuario on 03/02/15.
 */
public abstract class DTO<T> implements Comparable<DTO<T>> {

    private final T object;
    private final int id;

    public DTO(T object, int id){
        this.object = object;
        this.id = id;
    }

    protected T getObject() {
        return object;
    }

    protected int getId() {
        return id;
    }

    @Override
    public int compareTo(DTO<T> o) {
        return Integer.compare(id, o.id);
    }
}
