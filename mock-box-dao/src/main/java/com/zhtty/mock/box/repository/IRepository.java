package com.zhtty.mock.box.repository;


import java.util.Optional;

/**
 * @param <KEY>
 * @param <T>
 * @author A8142
 */
public interface IRepository<KEY, T> {

    /**
     * @param key
     * @return
     */
    Optional<T> selectById(KEY key);

    /**
     * @param t
     * @return
     */
    KEY insert(T t);

    /**
     * @param t
     * @return
     */
    boolean updateByPrimaryKey(KEY id, T t);

}