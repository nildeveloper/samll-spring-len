package com.lhl.spring.beans.factory;

import com.lhl.spring.beans.BeansException;

import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-31
 * @time 17:18
 * @describe: 扩展 Bean 工厂接口
 * Extension of the {@link BeanFactory} interface to be implemented by bean factories
 *  * that can enumerate all their bean instances, rather than attempting bean lookup
 *  * by name one by one as requested by clients. BeanFactory implementations that
 *  * preload all their bean definitions (such as XML-based factories) may implement
 *  * this interface.
 *
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     *  按照类型返回 Bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回所有BeanName
     * @return
     */
    String[] getBeanDefinitionNames();
}
