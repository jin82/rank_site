package info.epochpro.repository.impl;

import com.mongodb.BasicDBObject;
import info.epochpro.common.Range;
import info.epochpro.model.Queue;
import info.epochpro.repository.QueueRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import java.util.Arrays;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.NearQuery.near;

/**
 * Created by jin on 2016/12/18.
 */
public class QueueRepositoryImpl implements QueueRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${default.rankPointRange}")
    private Long rankPointRange;

    @Value("${default.locationRange}")
    private Long locationRange;




    @Override
    public List<Queue> findLegelTeam(Queue newbie, Integer size) {

        Range range = new Range(newbie.getPoint(), rankPointRange);
        Point point = new Point(newbie.getX(), newbie.getY());
//
//        BasicDBObject geoNear = new BasicDBObject(
//                "$geoNear", new BasicDBObject("distanceField","location")
//                .append("near","["+point.getX()+","+point.getY()+"]")
//                .append("distanceMultiplier",Metrics.KILOMETERS.getMultiplier())
//                .append("maxDistance",5)
//
//        );
//
//        BasicDBObject match = new BasicDBObject("$match",
//                new BasicDBObject("$and",new BasicDBObject[]{
//                        new BasicDBObject("point",
//                                new BasicDBObject("$gte",range.getFrom())
//                        ),
//                        new BasicDBObject("point",
//                                new BasicDBObject("$lte",range.getTo())
//                        )
//                })
//        );
//
//        BasicDBObject project = new BasicDBObject("$project",new BasicDBObject("uuid",1)
//            .append("point",1).append("location",1)
//            .append("sub",
//                new BasicDBObject("$abs",
//                        new BasicDBObject("$subtract","[$point,"+range.getDefaultValue()+"]"))
//            ).append("sub",1)
//        );
//
//        BasicDBObject sort = new BasicDBObject("$sort", new BasicDBObject("sub", 1));

//        List<BasicDBObject> pipeline = Arrays.asList(geoNear, match, project, sort);
//        AggregationOutput output = mongoTemplate.getCollection("queue").aggregate(pipeline);
//        List<DBObject> objs = (List<DBObject>) output.results();
//        System.out.println(objs);
//        return null;
        Aggregation agg = newAggregation(
                geoNear(near(point)
                                .distanceMultiplier(Metrics.KILOMETERS.getMultiplier())
                                .maxDistance(locationRange.doubleValue())
                                .num(size),
                        "location"),
                match(where("point")
                        .gte(range.getFrom())
                        .andOperator(where("point")
                                .lte(range.getTo()))),
                project("_id", "uuid", "location","point")
                .and(context -> new BasicDBObject("$abs",
                    new BasicDBObject("$subtract", Arrays.asList("$point",range.getDefaultValue()))
                )).as("sub"),
                sort(ASC, "sub")
        );

        AggregationResults<Queue> results = mongoTemplate.aggregate(agg,"queue",Queue.class);
        return results.getMappedResults();
    }
}
