package com.ace.dao;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Created with smart-erp.
 * User: denghp
 * Date: 10/15/13
 * Time: 11:18 PM
 * Description:
 */
/**
 * 注意在测试sql脚本的时候一定要注意配置
 * @TransactionConfiguration 的defaultRollback为false
 * 这样就不会回滚事务，才会提交到数据库
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml","classpath:*.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public abstract class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {


    protected String nextRandom() {
        return System.currentTimeMillis() + RandomStringUtils.randomNumeric(5);
    }





}
