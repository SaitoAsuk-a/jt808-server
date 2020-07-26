package org.yzh.framework.mvc;

import org.yzh.framework.orm.model.AbstractMessage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author zhihao.ye (1527621790@qq.com)
 * @home http://gitee.com/yezhihao/jt-server
 */
public class Handler {

    public final Object targetObject;
    public final Method targetMethod;
    public final Class<?>[] parameterTypes;
    public final String desc;


    public Handler(Object actionClass, Method actionMethod, String desc) {
        this.targetObject = actionClass;
        this.targetMethod = actionMethod;
        this.desc = desc;

        Type[] types = actionMethod.getGenericParameterTypes();
        Class<?>[] parameterTypes = new Class<?>[types.length];
        for (int i = 0; i < types.length; i++)
            parameterTypes[i] = (Class<?>) types[i];
        this.parameterTypes = parameterTypes;
    }

    public Handler(Object targetObject, Method actionMethod) {
        this(targetObject, actionMethod, null);
    }

    public <T extends AbstractMessage> T invoke(Object... args) throws InvocationTargetException, IllegalAccessException {
        return (T) targetMethod.invoke(targetObject, args);
    }

    public Class<?>[] getTargetParameterTypes() {
        return parameterTypes;
    }

    @Override
    public String toString() {
        return desc;
    }
}