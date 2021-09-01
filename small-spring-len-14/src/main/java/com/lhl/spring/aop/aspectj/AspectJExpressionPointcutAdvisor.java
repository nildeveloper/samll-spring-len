package com.lhl.spring.aop.aspectj;

import com.lhl.spring.aop.Pointcut;
import com.lhl.spring.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-08-16
 * @time 19:43
 * @describe: AspectJExpressionPointcutAdvisor
 * 把切面 pointcut、拦截方法 advice 和具体的拦截表达式包装在一起
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {


    /**
     * 切面
     */
    private AspectJExpressionPointcut pointcut;

    /**
     * 具体的拦截方法
     */
    private Advice advice;

    /**
     * 表达式
     */
    private String expression;

    public void setPointcut(AspectJExpressionPointcut pointcut) {
        this.pointcut = pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (pointcut == null) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
}
