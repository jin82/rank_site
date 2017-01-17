package info.epochpro.service.inter;

import info.epochpro.model.Queue;

/**
 * Created by jin on 2016/12/15.
 */
public interface QueueService {
    Queue insertQueue(Queue queue);

    void deleteQueue(String id);

    Queue findOne(String id);
//
//    List<Queue> findLegalQueue(Queue newbie, Integer size);
//
//    long findLegalQueueCount(Queue newbie);

//    boolean isMeetConditions(Queue queue, Integer condition);
}
