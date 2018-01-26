package com.example.roy.airtabletest.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by roy on 2018/1/9.
 */

public abstract class BaseDao<T, id> {

    private Dao<T, id> dao;
    private DatabaseHelper helper;

    public BaseDao(Context context, Class<T> tClass) {
        try {
            helper = DatabaseHelper.getHelper(context);
            dao = helper.getDao(tClass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<T, id> getDao() {
        return dao;
    }

    public int insert(T item) {
        int row;
        try {
            row = dao.create(item);
        } catch (SQLException e) {
            e.printStackTrace();
            row = -1;
        }
        return row;
    }

    public int deleteById(id primarKey) {
        int row;
        try {
            row = dao.deleteById(primarKey);
        } catch (SQLException e) {
            e.printStackTrace();
            row = -1;
        }
        return row;
    }

    public int update(T item) {
        int row;
        try {
            row = dao.update(item);
        } catch (SQLException e) {
            e.printStackTrace();
            row = -1;
        }
        return row;
    }

    public T getById(id id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<T> getAll() {
        try {
            return dao.queryBuilder().orderBy("id", false).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * get all data
     *
     * @param isAsc true asc, false desc
     * @return List<T> sort by id
     */
    public List<T> getAll(boolean isAsc) {
        try {
            return dao.queryBuilder().orderBy("id", isAsc).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * get all data
     *
     * @param field sort by field
     * @param isAsc true asc, false desc
     * @return List<T> sort by field
     */
    public List<T> getAll(String field, boolean isAsc) {
        try {
            return dao.queryBuilder().orderBy(field, isAsc).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public T maxOfFieldItem(String field) throws Exception {
        return dao.queryBuilder().orderBy(field, false).limit(1L).query().get(0);
    }

    public T minOfFieldItem(String field) throws Exception {
        return dao.queryBuilder().orderBy(field, true).limit(1L).query().get(0);
    }

    public QueryBuilder<T, id> getQueryBuilder() {
        return dao.queryBuilder();
    }

    public void insertWithTransaction(final List<T> items) {
        try {
            TransactionManager.callInTransaction(helper.getConnectionSource(), new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (T t : items) {
                        dao.create(t);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createWithTransaction(final List<T> items) {
        try {
            TransactionManager.callInTransaction(helper.getConnectionSource(), new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (T t : items) {
                        dao.update(t);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateWithtransaction(final List<id> items) {
        try {
            TransactionManager.callInTransaction(helper.getConnectionSource(), new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    dao.deleteIds(items);
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
