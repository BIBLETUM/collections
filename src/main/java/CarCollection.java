public interface CarCollection extends Iterable<Car>{
    boolean add(Car car);
    int size();
    void clear();
    boolean remove(Car car);
    boolean contains(Car car);
}
