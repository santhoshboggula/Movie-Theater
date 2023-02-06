package com.jpmc.theater.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class LocalDateProviderTest {

    @Test
    void currentDateTest() {
        LocalDate actualDate = LocalDateProvider.getLocalDateProvider().currentDate();
        assertThat(actualDate.getDayOfYear(), equalTo(LocalDate.now().getDayOfYear()));
    }
}
