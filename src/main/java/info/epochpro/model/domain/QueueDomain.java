package info.epochpro.model.domain;

import info.epochpro.model.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.function.Predicate;

/**
 * Created by jin on 2016/12/16.
 */

public class QueueDomain {

    private Queue queue;

    private Predicate<Integer> conditions;

    public QueueDomain(Queue queue,Integer size) {
        this.queue = queue;
        this.conditions = e->e>size;
    }

    public QueueDomain(Queue queue, Predicate<Integer> conditions) {
        this.queue = queue;
        this.conditions = conditions;
    }


}
