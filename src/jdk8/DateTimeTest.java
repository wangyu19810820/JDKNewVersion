package jdk8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class DateTimeTest {

    @Test
    public void test1() {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);

        LocalDateTime dateTime1 = dateTime.plusYears(2);
        System.out.println(dateTime1);

        LocalDateTime dateTime2 = dateTime.minusMonths(1);
        System.out.println(dateTime2);

        System.out.println(dateTime.getYear());
        System.out.println(dateTime.getMonthValue());
        System.out.println(dateTime.getDayOfMonth());
        System.out.println(dateTime.getDayOfWeek());
        System.out.println(dateTime.getHour());
        System.out.println(dateTime.getMinute());
        System.out.println(dateTime.getSecond());
    }

    @Test
    public void test2() {
        // 时间戳
        Instant instant = Instant.now();
        System.out.println(instant);

        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        System.out.println(instant.toEpochMilli());

        Instant instant1 = Instant.ofEpochSecond(60);
        System.out.println(instant1);
    }

    /**
     * Duration两个时间的间隔
     */
    @Test
    public void test3() {
        Instant instant1 = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        Instant instant2 = Instant.now();
        Duration duration1 = Duration.between(instant1, instant2);
        System.out.println(duration1);
        System.out.println(duration1.toMillis());

        LocalTime localTime1 = LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        LocalTime localTime2 = LocalTime.now();
        Duration duration2 = Duration.between(localTime1, localTime2);
        System.out.println(duration2.toMillis());
    }

    /**
     * Period两个日期的间隔
     */
    @Test
    public void test4() {
        LocalDate localDate = LocalDate.of(2018, 1, 1);
        LocalDate localDate1 = LocalDate.now();
        Period period = Period.between(localDate, localDate1);
        System.out.println(period);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    /**
     * TemporalAdjuster时间矫正器
     */
    @Test
    public void test5() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        // 指定日期为10号
        LocalDateTime localDateTime1 = localDateTime.withDayOfMonth(10);
        System.out.println(localDateTime1);

        // 指定下个星期日
        LocalDateTime localDateTime2 = localDateTime.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(localDateTime2);

        // 指定下个工作日
        LocalDateTime localDateTime3 = localDateTime.with(l -> {
            LocalDateTime localDateTime4 = (LocalDateTime) l;
            DayOfWeek dayOfWeek = localDateTime4.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.FRIDAY) {
                // 原始日期是周五，加3天
                return localDateTime4.plusDays(3);
            } else if (dayOfWeek == DayOfWeek.SUNDAY) {
                // 原始日期是周六，加2天
                return localDateTime4.plusDays(2);
            } else {
                // 其余都加1天
                return localDateTime4.plusDays(1);
            }
        });
        System.out.println(localDateTime3);
    }

    /**
     * DateTimeFormatter 格式化日期时间
     */
    @Test
    public void test6() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE;
        LocalDateTime localDateTime = LocalDateTime.now();
        String str = localDateTime.format(dateTimeFormatter);
        System.out.println(str);

        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String str1 = dateTimeFormatter1.format(localDateTime);
        System.out.println(str1);

        LocalDateTime localDateTime1 = localDateTime.parse(str1, dateTimeFormatter1);
        System.out.println(localDateTime1);
    }

    /**
     * 获取所有时区
     */
    @Test
    public void test7() {
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }

    @Test
    public void test8() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Europe/Helsinki"));
        System.out.println(localDateTime);

        LocalDateTime localDateTime1 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime zonedDateTime = localDateTime1.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime);
    }
}

