package info.epochpro.service;

import info.epochpro.model.Queue;
import info.epochpro.repository.QueueRepository;
import info.epochpro.service.inter.QueueService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/**
 * Created by jin on 2016/12/15.
 */
@Service
public class QueueServiceImpl implements QueueService{

    private final Log log = LogFactory.getLog(QueueServiceImpl.class);

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private ReentrantLock reentrantLock;


    @Override
    public Queue insertQueue(Queue queue) {
        Queue result = queueRepository.save(queue);
        legalConsmer(queue, 5);
        return result;
    }

    @Override
    public void deleteQueue(String id) {
        queueRepository.delete(id);
    }

    @Override
    public Queue findOne(String id) {
        return queueRepository.findOne(id);
    }
//
//    @Override
//    public List<Queue> findLegalQueue(Queue newbie, Integer size){
//        Range range = new Range(newbie.getPoint(), rankPointRange);
//        Point point = new Point(newbie.getX(), newbie.getY());
//        Distance distance = new Distance(locationRange, Metrics.KILOMETERS);
//        Pageable pageable = new PageRequest(0, size);
//        return queueRepository.findByPointBetweenAndLocationNearOrderByPointDesc(range.getFrom(), range.getTo(),point,distance,pageable);
//    }
//
//    @Override
//    public long findLegalQueueCount(Queue newbie) {
//        Range range = new Range(newbie.getPoint(), rankPointRange);
//        Point point = new Point(newbie.getX(), newbie.getY());
//        Distance distance = new Distance(locationRange, Metrics.KILOMETERS);
//        return queueRepository.countByPointBetweenAndLocationNear(range.getFrom(), range.getTo(),point,distance);
//    }

//    @Override
//    public boolean isMeetConditions(Queue newbie, Integer condition) {
//
//        List<Queue> legelTeam = queueRepository.findLegelTeam(newbie, condition);
//        if (condition.equals(legelTeam.size())) {
//            legalConsmer(legelTeam);
//        }
//
//    }

    @Async
    private Future<Integer> legalConsmer(Queue newbie, Integer condition) {
        Integer result = 0;
        try{
            if (reentrantLock.tryLock()) {
                System.out.println("=================");
                List<Queue> legelTeam = queueRepository.findLegelTeam(newbie, condition);
                result = legelTeam.size();
                System.out.println("temp is "+newbie+" result is " + result);
                if (condition.equals(result)) {
                    Consumer<Queue> c = System.out::println;
                    c = c.andThen(q -> this.deleteQueue(q.getId()));
                    //TODO 存储到其他的地方
                    legelTeam.forEach(c);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("=================");
            reentrantLock.unlock();
        }
        return new AsyncResult<>(result);
    }

}
