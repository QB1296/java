/**
 * 文件名： DbMapState.java
 *  
 * 版本信息：  
 * 日期：2015年7月30日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.vion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import storm.trident.state.ITupleCollection;
import storm.trident.state.OpaqueValue;
import storm.trident.state.State;
import storm.trident.state.StateFactory;
import storm.trident.state.ValueUpdater;
import storm.trident.state.map.IBackingMap;
import storm.trident.state.map.MapState;
import storm.trident.state.map.OpaqueMap;
import storm.trident.state.map.RemovableMapState;
import storm.trident.state.map.SnapshottableMap;
import storm.trident.state.snapshot.Snapshottable;
import storm.trident.testing.MemoryMapState;
import backtype.storm.task.IMetricsContext;
import backtype.storm.tuple.Values;


/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年7月30日 下午4:02:53
 */
public class DbMapState<T> implements Snapshottable<T>, ITupleCollection, MapState<T>, RemovableMapState<T>{

	Logger logger = LoggerFactory.getLogger(getClass());
	
	public DbMapState(String id) {
        _backing = new MemoryMapStateBacking(id);
        _delegate = new SnapshottableMap(OpaqueMap.build(_backing), new Values("$MEMORY-MAP-STATE-GLOBAL$"));
    }

	MemoryMapStateBacking<OpaqueValue> _backing;
    SnapshottableMap<T> _delegate;
    List<List<Object>> _removed = new ArrayList();
    Long _currTx = null;

    public T update(ValueUpdater updater) {
        return _delegate.update(updater);
    }

    public void set(T o) {
        _delegate.set(o);
    }

    public T get() {
        return _delegate.get();
    }

    public void beginCommit(Long txid) {
        _delegate.beginCommit(txid);
        if(txid==null || !txid.equals(_currTx)) {
            _backing.multiRemove(_removed);
        }
        _removed = new ArrayList();
        _currTx = txid;
    }

    public void commit(Long txid) {
        _delegate.commit(txid);
    }

    public Iterator<List<Object>> getTuples() {
        return _backing.getTuples();
    }

    public List<T> multiUpdate(List<List<Object>> keys, List<ValueUpdater> updaters) {
        return _delegate.multiUpdate(keys, updaters);
    }

    public void multiPut(List<List<Object>> keys, List<T> vals) {
    	logger.info("keys========{}",keys);
    	logger.info("vals========{}",vals);
        _delegate.multiPut(keys, vals);
    }

    public List<T> multiGet(List<List<Object>> keys) {
        return _delegate.multiGet(keys);
    }

    @Override
    public void multiRemove(List<List<Object>> keys) {
        List nulls = new ArrayList();
        for(int i=0; i<keys.size(); i++) {
            nulls.add(null);
        }
        // first just set the keys to null, then flag to remove them at beginning of next commit when we know the current and last value are both null
        multiPut(keys, nulls);
        _removed.addAll(keys);
    }

    public static class Factory implements StateFactory {

        String _id;

        public Factory() {
            _id = UUID.randomUUID().toString();
        }

        @Override
        public State makeState(Map conf, IMetricsContext metrics, int partitionIndex, int numPartitions) {
            return new MemoryMapState(_id + partitionIndex);
        }
    }

    static ConcurrentHashMap<String, Map<List<Object>, Object>> _dbs = new ConcurrentHashMap<String, Map<List<Object>, Object>>();
    static class MemoryMapStateBacking<T> implements IBackingMap<T>, ITupleCollection {

    	Logger logger = LoggerFactory.getLogger(MemoryMapStateBacking.class);
    	
        public static void clearAll() {
            _dbs.clear();
        }
        Map<List<Object>, T> db;
        Long currTx;

        public MemoryMapStateBacking(String id) {
            if (!_dbs.containsKey(id)) {
                _dbs.put(id, new HashMap());
            }
            this.db = (Map<List<Object>, T>) _dbs.get(id);
        }

        public void multiRemove(List<List<Object>> keys) {
            for(List<Object> key: keys) {
                db.remove(key);
            }
        }

        @Override
        public List<T> multiGet(List<List<Object>> keys) {
            List<T> ret = new ArrayList();
            for (List<Object> key : keys) {
                ret.add(db.get(key));
            }
            return ret;
        }

        @Override
        public void multiPut(List<List<Object>> keys, List<T> vals) {
        	logger.info("key:{},vals:{}",keys);
            for (int i = 0; i < keys.size(); i++) {
                List<Object> key = keys.get(i);
                T val = vals.get(i);
                db.put(key, val);
            }
        }

        @Override
        public Iterator<List<Object>> getTuples() {
            return new Iterator<List<Object>>() {

                private Iterator<Map.Entry<List<Object>, T>> it = db.entrySet().iterator();

                public boolean hasNext() {
                    return it.hasNext();
                }

                public List<Object> next() {
                    Map.Entry<List<Object>, T> e = it.next();
                    List<Object> ret = new ArrayList<Object>();
                    ret.addAll(e.getKey());
                    ret.add(((OpaqueValue)e.getValue()).getCurr());
                    return ret;
                }

                public void remove() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            };
        }
    }
	
}
