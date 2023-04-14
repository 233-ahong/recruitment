package com.ah.common.utils.uuid;

/**
 * ID生成器工具类
 *
 * @author 26471
 */
public class IdUtils {
    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID() {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 随机UUID
     */
    public static String fastUUID() {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String fastSimpleUUID() {
        return UUID.fastUUID().toString(true);
    }

    /**
     * 生成6位数字随机数
     *
     * @return
     */
    public static String mathRandom() {
        return UUID.mathRandom();
    }

    /**
     * 生成特定长度的数字随机数
     *
     * @param length 随机数长度
     * @return
     */
    public static String mathRandom(int length) {
        return UUID.mathRandom(length);
    }
}
