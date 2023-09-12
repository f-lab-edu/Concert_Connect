package io.concertconnect.concerteventmanagement.service;

import io.concertconnect.concerteventmanagement.model.Event;
import io.concertconnect.concerteventmanagement.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class EventServiceConcurrencyTest {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void testConcurrentReserveEvent() throws InterruptedException {
        int eventId = 4; // 기존 이벤트 ID로 대체

        int totalThreads = 10; // 동시 스레드 수
        int expectedRemainingCapacity = eventRepository.findById(eventId).get().getCapacity() - totalThreads;

        // 스레드를 동기화하기 위한 카운트다운 래치 생성
        CountDownLatch latch = new CountDownLatch(totalThreads);

        // 스레드 풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(totalThreads);

        for (int i = 0; i < totalThreads; i++) {
            executorService.submit(() -> {
                try {
                    // 각 스레드는 티켓을 예약하려고 시도
                    eventService.reserveEvent(eventId);
                } finally {
                    latch.countDown(); // 래치 카운트 다운
                }
            });
        }

        // 모든 스레드가 완료될 때까지 대기
        latch.await(5, TimeUnit.SECONDS);

        // 모든 예약 후 이벤트를 검색
        Event eventAfterReservations = eventRepository.findById(eventId).orElse(null);

        // 예약 후 예상되는 남은 허용 인원과 일치하는지 확인
        assertEquals(expectedRemainingCapacity, eventAfterReservations.getCapacity());
    }
}
