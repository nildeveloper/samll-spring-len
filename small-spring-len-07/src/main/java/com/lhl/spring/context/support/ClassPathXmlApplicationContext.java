package com.lhl.spring.context.support;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-31
 * @time 18:42
 * @describe:
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] locations;

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     * @param locations
     */
    public ClassPathXmlApplicationContext(String[] locations) {
        this.locations = locations;
        refresh();
    }

    public ClassPathXmlApplicationContext(String location) {
        this(new String[]{location});
    }

    @Override
    protected String[] getConfigLocations() {
        return locations;
    }
}
