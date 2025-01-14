package core.basesyntax.impl;

import core.basesyntax.Storage;

public class StorageImpl<K, V> implements Storage<K, V> {
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
        if (size() < MAX_SIZE_STORAGE) {
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
        return searchOnKey(key) != -1 ? (V) storage[searchOnKey(key)][SECOND_INDEX_STORAGE] : null;
    }

    @Override
    public int size() {
        return storage.length;
    }

    public StorageImpl() {
        storage = new Object[0][MAX_COUNT_GENERICS];
    }

    public void printResult() {
        for (int i = 0; i < size(); i++) {
            System.out.println("Key: " + storage[i][0] + " Value: " + storage[FIRST_INDEX_STORAGE][SECOND_INDEX_STORAGE]);
        }
    }
}
