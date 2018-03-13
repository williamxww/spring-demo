package com.bow.spring.aop;

import org.springframework.aop.TargetSource;

/**
 * 此类用来展示{@link TargetSource}的用途<br/>
 * TargetSource目标源指代的是真正的服务
 *
 * @see TargetSourceTest
 * 
 * @author vv
 * @since 2017/1/30.
 */
public class UserServiceTargetSource implements TargetSource {

    private UserService service1;

    private UserService service2;

    private int counter;

    public UserServiceTargetSource(UserService service1, UserService service2) {
        this.service1 = service1;
        this.service2 = service2;
    }

    @Override
    public Class<?> getTargetClass() {
        return UserService.class;
    }

    /**
     * 当前目标源是否是静态的。 如果为false，则每次方法调用结束后会调用releaseTarget()释放目标对象.
     * 如果为true，则目标对象不可变，也就没必要释放了。
     * 
     * @see org.springframework.aop.target.SingletonTargetSource 这个是静态的
     * @return 此处的target有service1&2 因此不是静态的
     */
    @Override
    public boolean isStatic() {
        return false;
    }

    @Override
    public Object getTarget() throws Exception {
        int c = counter++;
        if (c % 2 == 0) {
            return service1;
        } else {
            return service2;
        }
    }

    @Override
    public void releaseTarget(Object target) throws Exception {
        // do nothing
    }
}
