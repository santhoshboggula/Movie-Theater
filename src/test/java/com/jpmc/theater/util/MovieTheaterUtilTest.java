package com.jpmc.theater.util;

import com.jpmc.theater.model.LocalDateProvider;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.ShowDetails;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class MovieTheaterUtilTest {

    @InjectMocks
    private MovieTheaterUtil movieTheaterUtil;

    @Spy
    private LocalDateProvider provider;

    @Test
    void moviesScheduleTest() {
        List<ShowDetails> showDetailsList = movieTheaterUtil.moviesSchedule();
        assertThat(9, equalTo(showDetailsList.size()));
    }

    @Test
    void calculateTicketPriceTest() {
        ShowDetails showDetails = getShowDetails();
        assertThat(10.0, equalTo(movieTheaterUtil.calculateTicketPrice(showDetails)));
    }

    private ShowDetails getShowDetails() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        ShowDetails showDetails = new ShowDetails(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        return showDetails;
    }

    @Test
    void totalAmountTest()
    {
        assertThat(50.0,equalTo(movieTheaterUtil.totalAmount(10,5)));
    }

    @Test
    void formatDurationTest()
    {
        assertThat("1 hour 30 minutes",equalTo(movieTheaterUtil.formatDuration(Duration.ofMinutes(90))));
    }

}
