package info.epochpro.repository;

import info.epochpro.model.Queue;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 队列
 * Created by jin on 2016/12/15.
 */
public interface QueueRepository extends MongoRepository<Queue,String>,QueueRepositoryCustom {

    List<Queue> findByPointBetweenAndLocationNearOrderByPointDesc(Long from, Long to, Point point, Distance distance, Pageable pageable);

    long countByPointBetweenAndLocationNear(Long from, Long to, Point point, Distance distance);
}
