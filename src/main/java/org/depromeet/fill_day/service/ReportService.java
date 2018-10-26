package org.depromeet.fill_day.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.depromeet.fill_day.domain.AccountDetailsImpl;
import org.depromeet.fill_day.domain.dto.AccountDTO;
import org.depromeet.fill_day.domain.dto.ReportDTO;
import org.depromeet.fill_day.domain.dto.TimelineDTO;
import org.depromeet.fill_day.domain.entity.Account;
import org.depromeet.fill_day.domain.entity.Day;
import org.depromeet.fill_day.domain.entity.Timeline;
import org.depromeet.fill_day.exception.AuthenticationFailureException;
import org.depromeet.fill_day.exception.NotFoundException;
import org.depromeet.fill_day.repository.AccountRepository;
import org.depromeet.fill_day.repository.DayRepository;
import org.depromeet.fill_day.repository.TimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportService {

    private final TimelineRepository timelineRepository;
    private final DayRepository dayRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public ReportService(TimelineRepository timelineRepository,
                         DayRepository dayRepository,
                         AccountRepository accountRepository) {
        this.timelineRepository = timelineRepository;
        this.dayRepository = dayRepository;
        this.accountRepository = accountRepository;
    }

    public ReportDTO calculateByDate(Date from, Date to) {
        Optional<Account> account = Optional.empty();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String email = ((AccountDTO) authentication.getPrincipal()).getEmail();
            account = accountRepository.findByEmail(email);
        }

        account.orElseThrow(AuthenticationFailureException::new);
        List<Day> days = dayRepository.findByTodayBetweenAndAccountUidOrderByToday(from, to, account.get().getUid());

        final List<ReportDTO.Achievement> achievements = new ArrayList<>();

        for (Day day: days) {
            OptionalDouble dayScoreAvg = day.getTimelines().stream()
                    .map(Timeline::getScore)
                    .mapToInt(Timeline.EvaluationScore::getValue)
                    .average();

            if (dayScoreAvg.isPresent()) {
                achievements.add(
                        ReportDTO.Achievement.builder()
                                .date(day.getToday())
                                .scoreAvg(dayScoreAvg.getAsDouble())
                                .build()
                );
            }
        }

        final OptionalDouble totalScoreAvg = achievements.stream()
                .mapToDouble(ReportDTO.Achievement::getScoreAvg)
                .average();

        ReportDTO reportDTO = ReportDTO.builder()
                .from(from)
                .to(to)
                .achievements(achievements)
                .build();

        totalScoreAvg.ifPresent(reportDTO::setScoreAvg);
        return reportDTO;
    }
}
