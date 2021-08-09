package com.nicolasmoncarz.requestcache.cron;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskHolder;

@SpringBootTest
public class CleanJobTests {
    
    @Autowired
    private ScheduledTaskHolder scheduledTaskHolder;

    @Test
    public void cleanJobRuns() {
        Set<ScheduledTask> scheduledTasks = scheduledTaskHolder.getScheduledTasks();
        scheduledTasks.forEach(scheduledTask -> scheduledTask.getTask().getRunnable().getClass().getDeclaredMethods());
        long count = scheduledTasks.stream()
            .map(scheduledTask -> (CronTask) scheduledTask.getTask())
            .filter(cronTask -> 
                cronTask.getExpression().equals("0 0 0 * * ?") && 
                cronTask.toString().equals("com.nicolasmoncarz.requestcache.cron.CleanJob.removeOldResponses")
            ) 
            .count();
        assertEquals(count, 1L);
    }
}
