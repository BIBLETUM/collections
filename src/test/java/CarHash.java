import java.lang.reflect.Array;

public class CarHash implements CarSet {

    private static final int INIT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private Entry[] array = new Entry[INIT_CAPACITY];
    private int size = 0;

    @Override
    public boolean add(Car car) {
        if ((LOAD_FACTOR * array.length) <= size) {
            increaseArray();
        }
        boolean added = add(car, array);
        if (added) {
            size++;
        }
        return added;
    }

    private boolean add(Car car, Entry[] dst) {
        int position = getElementPosition(car, dst.length);
        if (dst[position] == null) {
            dst[position] = new Entry(car, null);
            return true;
        } else {
            Entry element = dst[position];
            while (true) {
                if (element.val.equals(car)) {
                    return false;
                } else if (element.next != null){
                    element = element.next;
                }
                else{
                    element.next = new Entry(car, null);
                    return true;
                }
            }
        }
    }

    private void increaseArray() {
        Entry[] newArray = new Entry[array.length * 2];
        for (Entry entry : array) {
            Entry element = entry;
            while (element != null) {
                int newPosition = getElementPosition(element.val, newArray.length);
                add(element.val, newArray);
                element = element.next;
            }
        }
        array = newArray;
    }

    @Override
    public boolean remove(Car car) {
        int position = getElementPosition(car, array.length);
        if (array[position] == null) {
            return false;
        }
        Entry entryPrev = array[position];
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
        array = new Entry[INIT_CAPACITY];
        size = 0;
    }

    private int getElementPosition(Car car, int arrayLen) {
        return Math.abs(car.hashCode()) % arrayLen;
    }

    private static class Entry {
        Car val;
        Entry next;

        public Entry(Car val, Entry next) {
            this.val = val;
            this.next = next;
        }
    }
}
