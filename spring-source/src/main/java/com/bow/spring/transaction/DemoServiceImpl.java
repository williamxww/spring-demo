package com.bow.spring.transaction;

import com.bow.biz.dao.DemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @ClassName: TransactionServiceTest
 * @Description: 编程式事务，方法级事物
 * @author ViVi
 * @date 2015年10月2日 上午9:56:09
 */

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Transactional(rollbackFor = Exception.class)
    public void testTransaction() {
        DemoDao dao = new DemoDao();
        dao.add();
        System.out.println("-----------添加了----------------");
        dao.delete();
    }

    public void codeTransaction() {
        TransactionDefinition td = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(td);
        try {
            DemoDao dao = new DemoDao();
            dao.add();
            System.out.println("-----------添加了----------------");
            dao.delete();
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
        transactionManager.commit(status);
    }
}
