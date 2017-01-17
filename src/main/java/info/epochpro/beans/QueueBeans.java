package info.epochpro.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jin on 2016/12/16.
 */
@Component
public class QueueBeans {

    @Bean
    public ReentrantLock reentrantLock() {
        return new ReentrantLock();
    }

}
