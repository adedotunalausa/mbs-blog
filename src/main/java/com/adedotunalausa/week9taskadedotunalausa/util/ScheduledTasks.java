package com.adedotunalausa.week9taskadedotunalausa.util;

import com.adedotunalausa.week9taskadedotunalausa.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ScheduledTasks {

    private final UserService userService;

    @Scheduled(fixedRate = 3000)
    public void scheduleTaskWithFixedRate() {
         userService.deactivateUsers();
    }
}
