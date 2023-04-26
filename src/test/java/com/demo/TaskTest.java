package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.ErrorHandler;

import java.util.Date;

/**
 * @author Q1sj
 * @date 2023.3.15 13:42
 */
@Slf4j
public class TaskTest {
    public static void main(String[] args) {
        MyThreadPoolTaskScheduler scheduler = new MyThreadPoolTaskScheduler();
        scheduler.setErrorHandler(new ErrorHandler() {
            @Override
            public void handleError(Throwable t) {
                log.error(t.getMessage(), t);
            }
        });
        scheduler.setPoolSize(2);
        scheduler.initialize();
//        scheduler.schedule(() -> log.info("MyCronTrigger"), new MyCronTrigger("0/10 * * * * ?"));
        scheduler.schedule(() -> log.info("AllowReFiresCronTrigger"), new AllowReFiresCronTrigger("0/10 * * * * ?"));
//        scheduler.scheduleWithFixedDelay(() -> log.info("2"), TimeUnit.SECONDS.toMillis(5));

//        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
//        executor.schedule(() -> log.info("executor"), 5, TimeUnit.SECONDS);
//        executor.scheduleWithFixedDelay(() -> log.info("scheduleWithFixedDelay"), 5, 5, TimeUnit.SECONDS);
    }

    @Slf4j
    static class MyCronTrigger implements Trigger {
        CronTrigger delegate;

        public MyCronTrigger(String cronExpression) {
            this.delegate = new CronTrigger(cronExpression);
        }

        @Override
        public Date nextExecutionTime(TriggerContext triggerContext) {
            Date date = delegate.nextExecutionTime(triggerContext);
            log.debug("nextExecutionTime:{}", date);
            return date;
        }
    }

    /**
     * 允许同一时间重复执行的cron触发器
     */
    @Slf4j
    static class AllowReFiresCronTrigger implements Trigger {
        private final CronSequenceGenerator sequenceGenerator;

        public AllowReFiresCronTrigger(String expression) {
            this.sequenceGenerator = new CronSequenceGenerator(expression);
        }

        @Override
        public Date nextExecutionTime(TriggerContext triggerContext) {
            return nextExecutionTime(triggerContext, new Date());
        }

        public Date nextExecutionTime(TriggerContext triggerContext, Date now) {
            Date next = sequenceGenerator.next(now);
            log.debug("nextExecutionTime:{}", next);
            return next;
        }
    }
}
