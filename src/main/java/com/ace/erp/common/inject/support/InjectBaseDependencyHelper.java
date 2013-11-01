/**
 * Copyright (c) 2009-2015 http://demi-panda.com
 *
 * Licensed 
 */
package com.ace.erp.common.inject.support;

/**
 * BaseService 的工具类
 * Created with ace.
 * User: denghp
 * Date: 11/1/13
 * Time: 11:23 PM
 */
import com.ace.erp.annotation.BaseComponent;
import com.ace.erp.common.mybatis.BaseMapper;
import com.ace.erp.controller.BaseController;
import com.ace.erp.service.sys.BaseService;
import com.google.common.collect.Sets;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

public class InjectBaseDependencyHelper {


    public static void findAndInjectBaseRepositoryDependency(BaseService<?> baseService) {
        final Set<Object> candidates =
                findDependencies(baseService, BaseComponent.class);

        if (candidates.size() == 0 || candidates.size() > 1) {
            throw new IllegalStateException(
                    "expect 1 @BaseComponent anntation BaseRepository subclass bean, but found " + candidates.size() +
                            ", please check class [" + baseService.getClass() + "] @BaseComponent annotation.");
        }

        Object baserMapper = candidates.iterator().next();

        if (baserMapper.getClass().isAssignableFrom(BaseComponent.class)) {
            throw new IllegalStateException("[" + baseService.getClass() + "] @BaseComponent annotation bean " +
                    "must be BaseRepository subclass");
        }
        //baseService.setBaseRepository((BaseRepository) baseRepository);
        baseService.setBaseMapper((BaseMapper)baserMapper);
    }


    public static void findAndInjectBaseServiceDependency(BaseController<?> baseController) {
        final Set<Object> candidates =
                findDependencies(baseController, BaseComponent.class);

        if (candidates.size() > 1) {
            throw new IllegalStateException(
                    "expect 1 @BaseComponent anntation BaseService subclass bean, but found " + candidates.size() +
                            ", please check class [" + baseController.getClass() + "] @BaseComponent annotation.");
        }

        Object baseService = candidates.iterator().next();

        if (baseService.getClass().isAssignableFrom(BaseComponent.class)) {
            throw new IllegalStateException("[" + baseController.getClass() + "] @BaseComponent annotation bean " +
                    "must be BaseService subclass");
        }

        baseController.setBaseService((BaseService) baseService);
    }


    /**
     * 根据注解在目标对象上的字段上查找依赖
     *
     * @param target
     * @param annotation
     */
    private static Set<Object> findDependencies(final Object target, final Class<? extends Annotation> annotation) {

        final Set<Object> candidates = Sets.newHashSet();

        ReflectionUtils.doWithFields(
                target.getClass(),
                new ReflectionUtils.FieldCallback() {
                    @Override
                    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                        ReflectionUtils.makeAccessible(field);
                        Object obj = ReflectionUtils.getField(field, target);
                        candidates.add(obj);
                    }
                },
                new ReflectionUtils.FieldFilter() {
                    @Override
                    public boolean matches(Field field) {
                        return field.isAnnotationPresent(annotation);
                    }
                }
        );

        ReflectionUtils.doWithMethods(
                target.getClass(),
                new ReflectionUtils.MethodCallback() {
                    @Override
                    public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                        ReflectionUtils.makeAccessible(method);
                        PropertyDescriptor descriptor = BeanUtils.findPropertyForMethod(method);
                        candidates.add(ReflectionUtils.invokeMethod(descriptor.getReadMethod(), target));
                    }
                },
                new ReflectionUtils.MethodFilter() {
                    @Override
                    public boolean matches(Method method) {
                        boolean hasAnnotation = false;
                        hasAnnotation = method.isAnnotationPresent(annotation);
                        if (!hasAnnotation) {
                            return false;
                        }

                        boolean hasReadMethod = false;
                        PropertyDescriptor descriptor = BeanUtils.findPropertyForMethod(method);
                        hasReadMethod = descriptor != null && descriptor.getReadMethod() != null;

                        if (!hasReadMethod) {
                            return false;
                        }

                        return true;
                    }
                }
        );

        return candidates;
    }

}
