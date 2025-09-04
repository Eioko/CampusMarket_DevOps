package com.utils;

import com.Po.Dto.query.base.QueryDto;
import com.Po.Vo.ChartVO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 时间工具类
 */
public class DateUtil {

    public static QueryDto startAndEndTime(Integer days) {
        // 参数校验
        if (days == null || days < 0) {
            throw new IllegalArgumentException("Days must be a non-negative integer");
        }

        // 优化后的时间计算逻辑
        LocalDateTime now = LocalDateTime.now();
        // 计算起始时间：当前日期减去days后的下一天开始前1秒
        LocalDateTime startTime = now.minusDays(days)
                .plusDays(1)
                .with(LocalTime.MIN)
                .minusSeconds(1);

        return QueryDto.builder()
                .startTime(startTime)
                .endTime(now)
                .build();
    }

    /**
     * 统计指定天数内的记录数（保持原始过滤逻辑）
     */
    public static List<ChartVO> countDatesWithinRange(Integer dayRange, List<LocalDateTime> dates) {
        // 参数校验
        if (dayRange == null || dayRange < 0) {
            throw new IllegalArgumentException("Day range must be a non-negative integer");
        }
        if (dates == null || dates.isEmpty()) {
            return Collections.emptyList();
        }

        // 按日期分组统计
        Map<LocalDate, Long> dateCounts = dates.stream()
                .map(LocalDateTime::toLocalDate)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        // 生成日期序列
        LocalDate startDate = LocalDate.now().minusDays(dayRange);
        return IntStream.rangeClosed(0, dayRange)
                .mapToObj(startDate::plusDays)
                // 保持原始过滤逻辑：只包含有记录的日期
                .filter(dateCounts::containsKey)
                .map(date -> new ChartVO(
                        String.format("%02d-%02d", date.getMonthValue(), date.getDayOfMonth()),
                        dateCounts.get(date).intValue()
                ))
                .collect(Collectors.toList());
    }
}