package com.kafka.eos.service;

import com.kafka.eos.avro.TransactionEvent;
import com.kafka.eos.util.AppUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventProcessServiceTest {

    @InjectMocks
    private EventProcessService eventProcessService;

    @Mock
    private TransactionEvent event;

    @BeforeEach
    void setUp() {
        // Mockito mocks initialized automatically
    }

    @Test
    void shouldThrowException_whenEventIdIsPrime() {
        when(event.get("eventId")).thenReturn("3");
        when(event.getEventId()).thenReturn("3");

        try (MockedStatic<AppUtil> mockedStatic = mockStatic(AppUtil.class)) {
            mockedStatic.when(() -> AppUtil.isPrime(3)).thenReturn(true);

            RuntimeException ex = assertThrows(RuntimeException.class, () -> eventProcessService.processEvent(event));
            assertEquals("Received prime number.", ex.getMessage());
        }
    }

    @Test
    void shouldProcessSuccessfully_whenEventIdIsNotPrime() {
        when(event.get("eventId")).thenReturn("4");

        try (MockedStatic<AppUtil> mockedStatic = mockStatic(AppUtil.class)) {
            mockedStatic.when(() -> AppUtil.isPrime(4)).thenReturn(false);

            assertDoesNotThrow(() -> eventProcessService.processEvent(event));
        }
    }
}