package core.basesyntax.impl;

import core.basesyntax.Storage;

public class StorageImpl<K, V> implements Storage<K, V> {
    private static int MIN_SIZE_STORAGE = 0;
    private final static int MAX_SIZE_STORAGE = 10;
    private final static int MAX_COUNT_GENERICS = 2;
    private final static int FIRST_INDEX_STORAGE = 0;
    private final static int SECOND_INDEX_STORAGE = 1;
    private Object[][] storage;

    /**
     * @return index if key exist in array,
     * else -1
     */
    private int searchOnKey(K key) {
        boolean flag = false;
        int index = 0;
        while (index < size()) {
            if (storage[index][FIRST_INDEX_STORAGE] != null) {
                if (storage[index][FIRST_INDEX_STORAGE].equals(key)) {
                    flag = true;
                    break;
                }
            } else {
                if (key == null) {
                    flag = true;
                    break;
                }
            }
            index++;
        }

        if (flag) return index;
        else return -1;
    }

    /**
     * @return an increased array
     */
    private Object[][] copyOf() {
        Object[][] temp = new Object[size() + 1][MAX_COUNT_GENERICS];
        for (int i = 0; i < size(); i++) {
            temp[i][FIRST_INDEX_STORAGE] = storage[i][FIRST_INDEX_STORAGE];
            temp[i][SECOND_INDEX_STORAGE] = storage[i][SECOND_INDEX_STORAGE];
        }
        return temp;
    }

    private void putKeyAndValue(K key, V value) {
        storage[size() - 1][FIRST_INDEX_STORAGE] = key;
        storage[size() - 1][SECOND_INDEX_STORAGE] = value;
    }

    @Override
    public void put(K key, V value) {
        if (MIN_SIZE_STORAGE < MAX_SIZE_STORAGE) {
            if (size() >= 1 && searchOnKey(key) != -1) {
                putKeyAndValue(key, value);
            } else {
                storage = copyOf();
                putKeyAndValue(key, value);
            }
        }
    }

    @Override
    public V get(K key) {
        int index = 0;
        while (index < size()) {
            if (storage[index][FIRST_INDEX_STORAGE] != null) {
                if (storage[index][FIRST_INDEX_STORAGE].equals(key)) {
                    return (V) storage[index][SECOND_INDEX_STORAGE];
                }
            } else {
                if (key == null) {
                    return (V) storage[index][SECOND_INDEX_STORAGE];
                }
            }
            index++;
        }
        return null;
    }

    @Override
    public int size() {
        return storage.length;
    }

    public StorageImpl() {
        storage = new Object[MIN_SIZE_STORAGE][MAX_COUNT_GENERICS];
    }

}
