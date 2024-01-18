public interface CarCollection<T> extends Iterable<T>{
    boolean add(T car);
    int size();
    void clear();
    boolean remove(T car);
    boolean contains(T car);
}
