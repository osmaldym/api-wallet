package com.osmy.wallet.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Convertions {
    public static class CastingError extends Exception {
        public CastingError(String message, Throwable cause){
            super(message, cause);
        }
    }

    public static Map<String, Object> classToMap(Object instance) throws IllegalArgumentException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();

        for (Field field : instance.getClass().getDeclaredFields()) {
            Class<?> targetType = field.getType();
            Object objValue = targetType.getClass();
            field.setAccessible(true);
            Object val = field.get(objValue);
            map.put(field.getName(), val);
        }

        return map;
    }

    public static <T> Object updateObjIfPropertyNotNull(T entity, T dto) throws CastingError {
        try {
            Class<?> entityClass = entity.getClass();
            Class<?> dtoClass = dto.getClass();

            for (Method dtoGetMethod : dtoClass.getDeclaredMethods()) {
                if (!dtoGetMethod.getName().startsWith("get")) continue;

                dtoGetMethod.setAccessible(true);
                Object responseOfGet = dtoGetMethod.invoke(dto);

                if (responseOfGet != null)
                    for (Method entitySetMethod : entityClass.getDeclaredMethods()) {
                        String dtoSetMethod = dtoGetMethod.getName().replace("get", "set"); 

                        if (entitySetMethod.getName().equals(dtoSetMethod)){
                            entitySetMethod.setAccessible(true);
                            entitySetMethod.invoke(entity, responseOfGet);
                        }
                    }
            }

            return entity;
        } catch (IllegalAccessException | SecurityException | InvocationTargetException e) {
            throw new CastingError("Error at updating data", e);
        }
    }

    private static <T> Object castTwoSameObjects(Class<?> objToCast, T instanceToGet) throws CastingError {
        try {
            Class<?> dtoClass = instanceToGet.getClass();
            Object entityInstance = objToCast.getDeclaredConstructor().newInstance();
        
            for (Method dtoMethod : dtoClass.getDeclaredMethods()) {
                if (!dtoMethod.getName().startsWith("get")) continue;

                String nameOfSetMethod = dtoMethod.getName().replace("get", "set");
                Method setMethod = null;

                for (Method method : objToCast.getDeclaredMethods())
                    if (method.getName().equals(nameOfSetMethod))
                        setMethod = method;

                if (setMethod == null) return entityInstance;
                
                setMethod.setAccessible(true);
                dtoMethod.setAccessible(true);
                setMethod.invoke(entityInstance, dtoMethod.invoke(instanceToGet));
            }

            // throw new CastingError("Cannot cast data objects", new NoSuchMethodException("xd"));
            return entityInstance;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            throw new CastingError("Cannot cast data objects", e);
        }
    }

    public static <T> Object toEntity(Class<?> entityToCast, T dto) throws CastingError {
        return Convertions.castTwoSameObjects(entityToCast, dto);
    }

    public static <T> Object toDTO(Class<?> dtoToCast, T entity) throws CastingError {
        return Convertions.castTwoSameObjects(dtoToCast, entity);
    }
}
