package org.example.gymmanagement_api.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gymmanagement_api.entity.UserMembership;
import org.example.gymmanagement_api.repository.UserMembershipRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MembershipExpirationTask {

    private final UserMembershipRepository userMembershipRepository;

    @Scheduled(cron = "*/10 * * * * ?")//test icin
    public void checkExpiredMembershipPlans() {
        log.info("Üyelikler kontrol ediliyor!...");
        LocalDate targetTime = LocalDate.now().plusDays(3);

        LocalDateTime startOfDay = targetTime.atStartOfDay();
        LocalDateTime endOfDay = targetTime.atTime(23,59,59);

        List<UserMembership> expiringMembership = userMembershipRepository
                .findByIsActiveTrueAndEndDateTimeBetween(startOfDay, endOfDay);
        if (expiringMembership.isEmpty()) {
            log.info("3 gün içinde biticek herhangi bir üyelik bulunamamıştır.Kontrol kapatılıyor!");
            return;
        }
        for (UserMembership userMembership : expiringMembership) {
            String userEmail = userMembership.getUser().getEmail();
            log.warn("Uyarı: [{}] Kullanıcısının üyeliği 3 gün sonra bitiyor", userEmail);
        }

    }
}
