package org.moichekionline.parcer.task;


import lombok.RequiredArgsConstructor;
import org.moichekionline.parcer.service.MoiChekiOnlineService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenRefreshTask {

    private final MoiChekiOnlineService moiChekiOnlineService;

    @Scheduled(cron = "${parcer.cron.token-refresh}")
    public void tokenRefresh() {
        moiChekiOnlineService.refreshToken();
    }
}
