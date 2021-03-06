package com.lsj.util;

public interface LMap<K, V> {

	int size();										//返回kv对个数
	boolean isEmpty();
	boolean containsKey(Object key);				//判断map中的key是否包含指定的key
	boolean containsValue(Object value);			//判断map中的value是否包含指定的value
	V get(Object key);								//根据key获得value，如果没有对应的key，返回null
	V put(K key, V value);							//添加一个新的kv对，若k重复覆盖旧的，并将旧的kv对返回
	V remove(Object key);
	void clear();									//清空kv对
	LCollection<V> values();						//返回值的集合
	LSet<LEntry<K, V>> entrySet();				//返回所有的kv对，kv对保存在set中
	boolean equals(Object o);
	int hashCode();
	
}
