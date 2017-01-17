package info.epochpro.repository;

import info.epochpro.model.Queue;

import java.util.List;

/**
 * Created by jin on 2016/12/18.
 */
public interface QueueRepositoryCustom {

    List<Queue> findLegelTeam(Queue newbie, Integer size);
}
