import java.time.ZonedDateTime;

/**
 * @ Author：Aijinhui
 * @ Description：
 * @ Date：2024-05-30-11:03
 */
public class ZonedDateTimeDemo
{
    public static void main(String[] args)
    {
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        System.out.println(zbj);
    }
}
