package com.example.awssqs.poller;

import com.example.awssqs.dto.NotificationDto;
import com.example.awssqs.entity.NotificationEntity;
import com.example.awssqs.mapper.NotificationMapper;
import com.example.awssqs.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import io.awspring.cloud.sqs.annotation.SqsListener;
@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationsSQSPoller {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @SqsListener(value = "${sqs.notifications.queue.name}")
    public void receiveMessage(@Payload NotificationDto message) {
        log.info("received notification: {}", message);
        NotificationEntity entity = notificationMapper.map(message);
        notificationRepository.save(entity).subscribe();
    }
}
