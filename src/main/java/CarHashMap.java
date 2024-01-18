import java.util.*;

public class CarHashMap<K, V> implements CarMap<K, V> {

    private static final int INIT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private Object[] array = new Object[INIT_CAPACITY];
    private int size = 0;

    @Override
    public void put(K key, V value) {
        if ((LOAD_FACTOR * array.length) <= size) {
            increaseArray();
        }
        if(add(key, value, array)){
            size++;
        }
    }

    private boolean add(K key, V value, Object[] dst) {
        int position = getElementPosition(key, dst.length);
        if (dst[position] == null) {
            dst[position] = new Entry(key, value, null);
            return true;
        } else {
            Entry element = (Entry) dst[position];
            while (element.next != null) {
                if (element.key.equals(key)) {
                    element.value = value;
                    return false;
                } else {
                    element = element.next;
                }
            }
            if (element.key.equals(key)) {
                element.value = value;
                return false;
            } else {
                element.next = new Entry(key, value, null);
                return true;
            }
        }
    }

    private void increaseArray() {
        Object[] newArray = new Object[array.length * 2];
        for (Object entry : array) {
            Entry element = (Entry) entry;
            while (element != null) {
                add(element.key, element.value, newArray);
                element = element.next;
            }
        }
        array = newArray;
    }

    @Override
    public V get(K key) {
        int position = getElementPosition(key, array.length);
        Entry element = (Entry) array[position];
        while (element != null){
            if(element.key.equals(key)){
                return element.value;
            }
            element = element.next;
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> owners = new HashSet<>();
        for (Object entry : array) {
            Entry element = (Entry) entry;
            while (element != null) {
                owners.add(element.key);
                element = element.next;
            }
        }
        return owners;
    }

    @Override
    public List<V> values() {
        List<V> cars = new ArrayList<>();
        for (Object entry : array) {
            Entry element = (Entry) entry;
            while (element != null) {
                cars.add(element.value);
                element = element.next;
            }
        }
        return cars;
    }

    @Override
    public boolean remove(K key) {
        int position = getElementPosition(key, array.length);
        if (array[position] == null) {
            return false;
        }
        Entry entryPrev = (Entry) array[position];
        Entry entry = entryPrev.next;
        if (entryPrev.key.equals(key)) {
            array[position] = entry;
            size--;
            return true;
        }
        while (entry != null) {
            if (entry.key.equals(key)) {
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

    private int getElementPosition(K key, int arrayLen) {
        return Math.abs(key.hashCode()) % arrayLen;
    }

    private class Entry {
        K key;
        V value;
        Entry next;

        public Entry(K key, V value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
