package jdbc;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T> {

    private final Class<T> tClass;

    protected BaseDao() {
        Type genericSuperClass = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) genericSuperClass;
        Type[] typeArgs = paramType.getActualTypeArguments();
        tClass = (Class<T>)typeArgs[0];
    }

    protected int executeUpdate(Connection connection, String sql, Object... args) throws Exception {
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            for(int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }
            return statement.executeUpdate();
        }
    }

    protected T getInstance(Connection connection, String sql, Object...args) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }
            try (ResultSet rs = statement.executeQuery()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                if (rs.next()) {
                    T result = tClass.getDeclaredConstructor().newInstance();
                    for (int i = 0; i < columnCount; i++) {
                        Object value = rs.getObject(i + 1);
                        LocalDate date = null;
                        int type = rsmd.getColumnType(i + 1);
                        if (type == Types.DATE) {
                            date = rs.getObject(i + 1, LocalDate.class);
                        }
                        String columnLabel = rsmd.getColumnLabel(i + 1);
                        Field field = tClass.getDeclaredField(columnLabel);
                        Class fieldType = field.getType();
                        field.setAccessible(true);
                        if (fieldType == LocalDate.class){
                            field.set(result, date);
                        } else {
                            field.set(result, value);
                        }
                    }
                    return result;
                } else {
                    return null;
                }
            }
        }
    }

    protected List<T> getForList(Connection connection, String sql, Object...args) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    statement.setObject(i + 1, args[i]);
                }
            }
            try (ResultSet rs = statement.executeQuery()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                List<T> list = new ArrayList<>();
                while (rs.next()) {
                    T result = tClass.getDeclaredConstructor().newInstance();
                    for (int i = 0; i < columnCount; i++) {
                        Object value = rs.getObject(i + 1);
                        LocalDate date = null;
                        int type = rsmd.getColumnType(i + 1);
                        if (type == Types.DATE) {
                            date = rs.getObject(i + 1, LocalDate.class);
                        }
                        String columnLabel = rsmd.getColumnLabel(i + 1);
                        Field field = tClass.getDeclaredField(columnLabel);
                        Class fieldType = field.getType();
                        field.setAccessible(true);
                        if (fieldType == LocalDate.class){
                            field.set(result, date);
                        } else {
                            field.set(result, value);
                        }
                    }
                    list.add(result);
                }
                return list;
            }
        }
    }

    protected <U> U getValue(Connection connection, String sql, Object...args) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    statement.setObject(i + 1, args[i]);
                }
            }
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return (U) rs.getObject(1);
                } else {
                    return null;
                }
            }
        }
    }
}
