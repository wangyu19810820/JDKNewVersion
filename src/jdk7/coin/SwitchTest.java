package jdk7.coin;

import org.junit.Test;

public class SwitchTest {

    /**
     * switch语句中能放置字符串型数据
     */
    @Test
    public void test1() {
        String dayOfWeek = "Monday";
        switch (dayOfWeek) {
            case "Monday":
                System.out.println("星期一");
                break;
            case "Tuesday":
                System.out.println("星期二");
                break;
            case "Wednesday":
                System.out.println("星期三");
                break;
            case "Thursday":
                System.out.println("星期四");
                break;
            case "Friday":
                System.out.println("星期五");
                break;
            case "Saturday":
                System.out.println("星期六");
                break;
            case "Sunday":
                System.out.println("星期日");
                break;
            default:
                System.out.println("错误");
                break;
        }
    }
}
