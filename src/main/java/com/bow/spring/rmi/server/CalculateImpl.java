package com.bow.spring.rmi.server;

import com.bow.spring.rmi.Calculate;

/**
 * @author acer
 * @since 2016年2月28日
 */
public class CalculateImpl implements Calculate {

    @Override
    public int addOne(int a) {
        return a + 1;
    }

}
