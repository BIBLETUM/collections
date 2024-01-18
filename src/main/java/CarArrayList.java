import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class CarArrayList<T> implements CarList<T> {

    private Object[] array = new Object[10];
    int size = 0;

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public boolean add(T car) {
        checkArraySize();
        array[size] = car;
        size++;
        return true;
    }

    @Override
    public boolean add(T car, int index) {
        checkArraySize();
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = car;
        size++;
        return true;
    }

    @Override
    public boolean remove(T car) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(car)) {
                return removeAt(i);
            }
        }
        return false;
    }

    @Override
    public boolean contains(T car) {
        for (Object carInArr : array) {
            if (car.equals(carInArr)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return (T) array[index++];
            }
        };
    }

    @Override
    public boolean removeAt(int index) {
        checkIndex(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public void clear() {
        array = new Car[10];
        size = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkArraySize() {
        if (size >= array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }
}
