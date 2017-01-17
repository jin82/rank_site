package info.epochpro.service;

import info.epochpro.model.Queue;
import info.epochpro.service.inter.QueueService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static info.epochpro.common.util.UUIDGenerator.uuid;
import static org.junit.Assert.*;

/**
 * 队列测试类
 * Created by jin on 2016/12/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QueueServiceImplTest {

    @Autowired
    private QueueService queueService;

    private Integer i = 0;

    @Test
    public void insertQueue() throws Exception {
        Queue queue = new Queue(uuid(),1500l,-73.9667, 40.78);
        queue = queueService.insertQueue(queue);
        String queueId = queue.getId();

        assertNotNull("插入排位队列失败",queueId);

        queueService.deleteQueue(queueId);

        queue = queueService.findOne(queueId);

        assertNull("移除排位队列失败",queue);

    }

//    @Test
//    public void findLegalQueue() throws Exception {
//        List<Queue> testCases = new ArrayList<>();
//        testCases.add(new Queue(uuid(),1500l,-73.9667, 40.78));
//        testCases.add(new Queue(uuid(),1301l,-73.9667, 40.78));
//        testCases.add(new Queue(uuid(),1200l,-73.9667, 40.78));
//        testCases.add(new Queue(uuid(),1600l,-73.9667, 40.78));
//        testCases.add(new Queue(uuid(),1699l,-73.9667, 40.78));
//        testCases.add(new Queue(uuid(),1800l,-73.9667, 40.78));
//        testCases.add(new Queue(uuid(),1900l,-73.9667, 40.78));
//        testCases.add(new Queue(uuid(),2200l,-73.9667, 40.78));
//
//
//        testCases.add(new Queue(uuid(),1500l,-20.9667, 40.78));
//        testCases.add(new Queue(uuid(),1500l,-20.9667, 40.78));
//
//        Queue lastQueue = new Queue(uuid(),1500l,-73.9667, 40.78);
//        testCases.add(lastQueue);
//        testCases.forEach(queueService::insertQueue);
//
//        List<Queue> legal = queueService.findLegalQueue(lastQueue, 10);
//
//        assertNotNull("查询合法队列失败", legal);
//        assertEquals("查询合法队列错误",legal.size(),5);
//
//        legal.forEach(q->assertTrue("查询合法队列数据合法性错误",q.getPoint()>=1300&&q.getPoint()<=1700));
//
//
//
//    }
//
////    @Test
////    public void testCount() {
////        List<Queue> testCases = new ArrayList<>();
////        testCases.add(new Queue(uuid(),1500l,-73.9667, 40.78));
////        testCases.add(new Queue(uuid(),1301l,-73.9667, 40.78));
////        testCases.forEach(queueService::insertQueue);
////
////        Queue lastQueue = new Queue(uuid(),1500l,-73.9667, 40.78);
////
////        long count = queueService.findLegalQueueCount(lastQueue);
////
////        assertEquals("查询个数出错", count, 2);
////
////        testCases.parallelStream().map(Queue::getId).forEach(queueService::deleteQueue);
////
////
////
////    }

    @Test
    public void testRemoveQueueItems() {
        List<Queue> testCases = new ArrayList<>();
        testCases.add(new Queue(i+++"",1501l,-73.9667, 40.78));
        testCases.add(new Queue(i+++"",1500l,-73.9667, 40.78));
        testCases.add(new Queue(i+++"",1500l,-73.9667, 40.78));
        testCases.add(new Queue(i+++"",1500l,-73.9667, 40.78));
        testCases.add(new Queue(i+++"",1300l,-73.9667, 40.78));

        testCases.add(new Queue(i+++"",1300l,-73.9667, 40.78));
        testCases.add(new Queue(i+++"",1300l,-73.9667, 40.78));
        testCases.add(new Queue(i+++"",1300l,-73.9667, 40.78));
        testCases.add(new Queue(i+++"",1350l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1500l,-73.9667, 40.78));
//
//        testCases.add(new Queue(i+++"",1301l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1699l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1301l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1699l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1500l,-73.9667, 40.78));
//
//        testCases.add(new Queue(i+++"",1301l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1699l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1301l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1699l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1500l,-73.9667, 40.78));
//
//        testCases.add(new Queue(i+++"",1301l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1699l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1301l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1699l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1500l,-73.9667, 40.78));
//
//        testCases.add(new Queue(i+++"",1301l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1699l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1301l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1699l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1500l,-73.9667, 40.78));
//
//        testCases.add(new Queue(i+++"",1301l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1699l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1301l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1699l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1500l,-73.9667, 40.78));
//
//        testCases.add(new Queue(i+++"",1301l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1699l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1301l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1699l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1500l,-73.9667, 40.78));
//
//        testCases.add(new Queue(i+++"",1301l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1699l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1301l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1699l,-73.9667, 40.78));
//        testCases.add(new Queue(i+++"",1500l,-73.9667, 40.78));
//


        Queue lastQueue = new Queue(uuid(),1360l,-73.9667, 40.78);
        testCases.add(lastQueue);
        testCases.forEach(queueService::insertQueue);

    }

}