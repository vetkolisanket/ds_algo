import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class CachingLibrary {

    /**
     * Let's design a caching library which should be able to cache data following different caching strategies and also should allow users to 
     implement their own custom caching strategy, The caching library can be used on any kind of storage for e.g in memory, db, shared pref or anything
     **/

    interface CacheableKey {
    }

    interface CacheableValue {
    }

    interface Strategy<K extends CacheableKey> {

        K removeKeyToEvict();

        void keyUsed(K key);

    }

    class LRUStrategy<K extends CacheableKey> implements Strategy<K> {

        class Node<K> {
            K val;
            Node next, pre;

            public Node(K val) {
                this.val = val;
            }

            public Node() {

            }
        }

        Map<K, Node> map = new HashMap();
        Node head = new Node(), tail = new Node();

        void addToHead(Node node) {
            node.next = head.next;
            node.pre = head;
            head.next = node;
        }

        Node remove(Node node) {
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            next.pre = pre;
            return node;
        }

        public void keyUsed(K key) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                addToHead(remove(node));
            } else {
                Node node = new Node(key);
                map.put(key, node);
                addToHead(node);
            }
        }


        public K removeKeyToEvict() {
            Node node = remove(tail.pre);
            map.remove(node.val);
            return (K) node.val;
        }

    }

    interface Storage<K extends CacheableKey, V extends CacheableValue> {
        void store(K key, V val);

        V get(K key);

        boolean isStorageFull();

        V remove(K key);

        boolean contains(K key);
    }

    class InMemoryStorage<K extends CacheableKey, V extends CacheableValue> implements Storage<K, V> {
        private final Map<K, V> map = new HashMap();
        private final int size;

        public void store(K key, V val) {
            map.put(key, val);
        }

        @Nullable
        public V get(K key) {
            return map.getOrDefault(key, null);
        }

        public InMemoryStorage(int size) {
            this.size = size;
        }

        public boolean isStorageFull() {
            return map.size() >= size;
        }

        public V remove(K key) {
            V val = map.get(key);
            map.remove(key);
            return val;
        }

        public boolean contains(K key) {
            return map.containsKey(key);
        }

    }

    class Cache<K extends CacheableKey, V extends CacheableValue> {
        private final Strategy strategy;
        private final Storage storage;

        public Cache(Strategy strategy, Storage storage) {
            this.strategy = strategy;
            this.storage = storage;
        }

        public void put(K key, V val) {
            if (storage.isStorageFull()) {
                K keyToEvict = (K) strategy.removeKeyToEvict();
                storage.remove(keyToEvict);
            }
            strategy.keyUsed(key);
            storage.store(key, val);
        }

        @Nullable
        public V get(K key) {
            strategy.keyUsed(key);
            return (V) storage.get(key);
        }

        public boolean contains(K key) {
            return storage.contains(key);
        }
    }

    // Main class should be named 'Solution'
    public static void main(String[] args) {
        System.out.println("Hello, World");
    }
    
}

