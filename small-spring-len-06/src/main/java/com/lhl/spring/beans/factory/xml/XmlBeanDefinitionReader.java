package com.lhl.spring.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.lhl.spring.beans.BeansException;
import com.lhl.spring.beans.PropertyValue;
import com.lhl.spring.beans.factory.config.BeanDefinition;
import com.lhl.spring.beans.factory.config.BeanReference;
import com.lhl.spring.beans.factory.support.AbstractBeanDefinitionReader;
import com.lhl.spring.beans.factory.support.BeanDefinitionRegistry;
import com.lhl.spring.core.io.Resource;
import com.lhl.spring.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-07-30
 * @time 23:34
 * @describe: XmlBeanDefinitionReader
 * 解析 xml
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {


    public XmlBeanDefinitionReader(BeanDefinitionRegistry definitionRegistry, ResourceLoader resourceLoader) {
        super(definitionRegistry, resourceLoader);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry definitionRegistry) {
        super(definitionRegistry);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try(InputStream inputStream = resource.getInputStream()) {
            doLoadBeanDefinitions(inputStream);
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    /**
     * 通用inputStream解析xml方法
     * @param inputStream
     */
    private void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        // xml
        Document document = XmlUtil.readXML(inputStream);
        // 跟节点
        Element root = document.getDocumentElement();
        // 子节点
        NodeList childNodes = root.getChildNodes();
        // 解析 xml 标签
        for (int i = 0; i <childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            // 判断元素
            if (!(item instanceof Element)) {
                continue;
            }
            // 判断对象
            if (!item.getNodeName().equals("bean")) {
                continue;
            }
            Element bean = (Element) item;
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            // 反射获取Class对象
            Class<?> clazz = Class.forName(className);
            // id > name
            String beanName = StrUtil.isNotBlank(id) ? id : name;
            // 都是空的话 默认为Class对象首字母小写
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 定义BeanDefinition
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            // 解析属性并填充
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                Node propertyNode = bean.getChildNodes().item(j);
                if (!(propertyNode instanceof Element)) {
                    continue;
                }
                if (!propertyNode.getNodeName().equals("property")) {
                    continue;
                }
                // 解析Property
                Element propertyElement = (Element) propertyNode;
                String attrName = propertyElement.getAttribute("name");
                String attrValue = propertyElement.getAttribute("value");
                String attrRef = propertyElement.getAttribute("ref");
                // 获取属性值 确定为引用对象、值对象
                Object finalAttrValue = StrUtil.isNotBlank(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, finalAttrValue);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getBeanDefinitionRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册Bean
            getBeanDefinitionRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

}
