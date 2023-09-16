package com.example.awssqs.service;

import com.example.awssqs.dto.NotificationDto;
import com.example.awssqs.mapper.NotificationMapper;
import com.example.awssqs.repository.NotificationRepository;
import com.example.awssqs.repository.RecipientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final RecipientRepository recipientRepository;
    private final NotificationMapper notificationMapper;

    public Mono<NotificationDto> findNotificationByUid(String uid) {
        return notificationRepository.findById(uid)
                .map(notificationMapper::map);
    }

    public Mono<NotificationDto> findNotificationWithRecipientByUid(String uid) {
        return notificationRepository.findById(uid)
                .flatMap(notificationEntity -> recipientRepository
                        .findById(notificationEntity.getRecipientUid())
                        .map(recipientEntity -> {

                            notificationEntity.setRecipient(recipientEntity);

                            return notificationEntity;
                        }).map(notificationMapper::map));
    }
}
