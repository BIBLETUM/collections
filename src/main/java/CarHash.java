import java.lang.reflect.Array;
import java.util.Iterator;

public class CarHash<T> implements CarSet<T> {

    private static final int INIT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private Object[] array = new Object[INIT_CAPACITY];
    private int size = 0;

    @Override
    public boolean add(T car) {
        if ((LOAD_FACTOR * array.length) <= size) {
            increaseArray();
        }
        boolean added = add(car, array);
        if (added) {
            size++;
        }
        return added;
    }

    private boolean add(T car, Object[] dst) {
        int position = getElementPosition(car, dst.length);
        if (dst[position] == null) {
            dst[position] = new Entry(car, null);
            return true;
        } else {
            Entry element = (Entry) dst[position];
            while (true) {
                if (element.val.equals(car)) {
                    return false;
                } else if (element.next != null) {
                    element = element.next;
                } else {
                    element.next = new Entry(car, null);
                    return true;
                }
            }
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            private int arrayIndex = 0;
            private Entry entry;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                while(array[arrayIndex] == null){
                    arrayIndex++;
                }
                if (entry == null){
                    entry = (Entry) array[index];
                }
                T car = entry.val;
                entry = entry.next;
                if(entry == null){
                    arrayIndex++;
                }
                index++;
                return car;
            }
        };
    }

    private void increaseArray() {
        Object[] newArray = new Object[array.length * 2];
        for (Object entry : array) {
            Entry element = (Entry) entry;
            while (element != null) {
                add(element.val, newArray);
                element = element.next;
            }
        }
        array = newArray;
    }

    @Override
    public boolean contains(T car) {
        for (Object element : array) {
            Entry entry = (Entry) element;
            while (entry != null) {
                if (entry.val.equals(car)){
                    return true;
                }else {
                    entry = entry.next;
                }
            }
        }
        return false;
    }

    @Override
    public boolean remove(T car) {
        int position = getElementPosition(car, array.length);
        if (array[position] == null) {
            return false;
        }
        Entry entryPrev = (Entry) array[position];
        Entry entry = entryPrev.next;
        if (entryPrev.val.equals(car)) {
            array[position] = entry;
            size--;
            return true;
        }
        while (entry != null) {
            if (entry.val.equals(car)) {
                entryPrev.next = entry.next;
                size--;
                return true;
            } else {
                entryPrev = entry;
                entry = entry.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Object[INIT_CAPACITY];
        size = 0;
    }

    private int getElementPosition(T car, int arrayLen) {
        return Math.abs(car.hashCode()) % arrayLen;
    }

    private class Entry {
        T val;
        Entry next;

        public Entry(T val, Entry next) {
            this.val = val;
            this.next = next;
        }
    }
}
