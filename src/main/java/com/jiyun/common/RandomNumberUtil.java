package com.jiyun.common;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


/**
 * Created by Huoo
 * On 2018/9/19 10:57
 *  随机数生成工具
 */
public class RandomNumberUtil {
    private static final int[] prefix = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    private static SecureRandom random = new SecureRandom();
    /**
     * 随机产生最大为18位的long型数据(long型数据的最大值是9223372036854775807,共有19位)
     *
     * @param digit
     *            用户指定随机数据的位数
     */
    public static long randomLong(int digit) {
        if (digit >= 19 || digit <= 0)
            throw new RuntimeException("digit should between 1 and 18(1<=digit<=18)");
        String s = RandomStringUtils.randomNumeric(digit - 1);
        return Long.parseLong(getPrefix() + s);
    }
    public static Integer randomInt(int digit) {
        if (digit >= 19 || digit <= 0)
            throw new RuntimeException("digit should between 1 and 18(1<=digit<=18)");
        String s = RandomStringUtils.randomNumeric(digit - 1);
        return Integer.parseInt(getPrefix() + s);
    }
    /**
     * 随机产生在指定位数之间的long型数据,位数包括两边的值,minDigit<=maxDigit
     *
     * @param minDigit
     *            用户指定随机数据的最小位数 minDigit>=1
     * @param maxDigit
     *            用户指定随机数据的最大位数 maxDigit<=18
     */
    public static long randomLong(int minDigit, int maxDigit)  {
        if (minDigit > maxDigit) {
            throw new IllegalArgumentException("minDigit > maxDigit");
        }
        if (minDigit <= 0 || maxDigit >= 19) {
            throw new IllegalArgumentException("minDigit <=0 || maxDigit>=19");
        }
        return randomLong(minDigit + getDigit(maxDigit - minDigit));
    }

    private static int getDigit(int max) {
        return RandomUtils.nextInt(max + 1);
    }

    /**
     * 保证第一位不是零
     *
     * @return
     */
    private static String getPrefix() {
        return prefix[RandomUtils.nextInt(9)] + "";
    }

    public static Integer getPasswordForAppTimeLine(){
        String target = null;
        for (int i=0;i<6;i++){
            int te=(int)(Math.random()*10);
            target+=String.valueOf(System.currentTimeMillis()).substring(te,te+1);
            System.out.println(target);
        }
        return Integer.parseInt(target.substring(4));
    }

    public static String generateToken() {

        String token = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").withZone(ZoneOffset.UTC).format(Instant.now());
        for (int i = 0; i < 8; i++) {
            token += Integer.toHexString(random.nextInt());
        }
        return token;
    }
}
