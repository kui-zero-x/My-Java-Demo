package com.demo.myjavademo.utils.convert;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

public class TreeUtil {

    /**
     * 将 List 转为树形结构
     *
     * @param origList          : 要转换的 List
     * @param idFieldName       : id字段名
     * @param parentIdFieldName : parentId 字段名   字段为null 第一级
     * @param childrenFieldName : children 字段名
     * @param <T>               : 拥有父子结构的 Entity
     * @return : 树形结果
     * @throws Exception .
     */
    public static <T> List<T> convert(List<T> origList, String idFieldName,
                                      String parentIdFieldName, String childrenFieldName) {

        // 用于保存当前 id 索引的实体类
        Map<String, T> idMaps = new HashMap<>();
        // 暂存区, 用于保存没有找到父 id 的控件
        List<T> tempList = new ArrayList<>();
        List<T> result = new ArrayList<>();
        try {
            for (T entity : origList) {
                // 获取 id, parentId, children
                String id = Objects.toString(getFieldValue(entity, idFieldName), "");
                String parentId = Objects.toString(getFieldValue(entity, parentIdFieldName), "");
                if (StringUtils.isEmpty(id)) {
                    //存在id为空的资料
                    return new ArrayList<>();
                }
                idMaps.put(id, entity);
                if (StringUtils.isEmpty(parentId)) {
                    // 如果父 id 为空, 则实体类为第一层
                    result.add(entity);
                } else {
                    // 根据父 id 获取实体类
                    T parentEntity = idMaps.get(parentId);
                    if (parentEntity == null) {
                        // 没找到先放入暂存区
                        tempList.add(entity);
                    } else {
                        // 父组件判断是否存在 children, 不存在新增, 存在则直接假如
                        setChildrenValue(childrenFieldName, entity, parentEntity);
                    }
                }
            }
            // 处理暂存区, 暂存区的一定不为根节点, 所以它只要父节点存在, 那么此轮查询一定能找到父节点(上一轮已经将全部节点放入 idMaps)
            for (T entity : tempList) {
                // 获取 parentId
                String parentId = Objects.toString(getFieldValue(entity, parentIdFieldName), "");
                // 根据父id获取实体类
                T parentEntity = idMaps.get(parentId);
                if (parentEntity == null) {
                    //存在孤立的子节点 跳过
                    continue;
                    //  throw new Exception("存在孤立的子节点:parentId=" + parentId);
                } else {
                    // 父组件判断是否存在children, 不存在新增, 存在则直接假如
                    setChildrenValue(childrenFieldName, entity, parentEntity);
                }
            }
        } catch (Exception e) {
            //存在id为空的资料
            return new ArrayList<>();
        }
        return result;
    }

    private static <T> void setChildrenValue(String childrenFieldName, T entity, T parentEntity) throws Exception {
        Object children = getFieldValue(parentEntity, childrenFieldName);
        List<T> childrenList;
        if (children == null) {
            childrenList = new ArrayList<>();
            childrenList.add(entity);
            setFieldValue(parentEntity, childrenFieldName, childrenList);
        } else {
            List<T> childrenReal = (List<T>) children;
            childrenReal.add(entity);
        }
    }

    private static <T> Object getFieldValue(T entity, String fieldName) {
        Field field = ReflectionUtils.findField(entity.getClass(), fieldName);
        if (field == null) {
            //字段名称[%s]不存在
            return null;
        } else {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            Object result = ReflectionUtils.getField(field, entity);
            field.setAccessible(accessible);
            return result;
        }
    }

    private static <T> void setFieldValue(T entity, String fieldName, Object value) {
        Field field = ReflectionUtils.findField(entity.getClass(), fieldName);
        if (null != field) {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            ReflectionUtils.setField(field, entity, value);
            field.setAccessible(accessible);
        }
    }
}
