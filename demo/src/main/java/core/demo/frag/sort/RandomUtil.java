package core.demo.frag.sort;

import java.util.List;
import java.util.Random;

public final class RandomUtil {

    private RandomUtil() {
    }

	/*初始化*/

    private static Random random;
    private static long seed;

    static {
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    public static void setSeed(long seed) {
        RandomUtil.seed = seed;
        random = new Random(RandomUtil.seed);
    }

    public static long getSeed() {
        return seed;
    }

	/*随机数字*/

    public static int nextInt() {
        return random.nextInt();
    }

    public static int nextInt(int max) {
        return random.nextInt(max);
    }

    public static int nextInt(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min不允许大于max：min = " + min + " max = " + max);
        }
        return min + nextInt(max - min);
    }

    public static float nextFloat() {
        return random.nextFloat();
    }

    public static float nextFloat(float max) {
        return random.nextFloat() * max;
    }

    public static float nextFloat(float min, float max) {
        if (min > max) {
            throw new IllegalArgumentException("min不允许大于max：min = " + min + " max = " + max);
        }
        return min + random.nextFloat() * (max - min);
    }

    public static double nextDouble() {
        return random.nextDouble();
    }

    public static double nextDouble(double max) {
        return random.nextDouble() * max;
    }

    public static double nextDouble(double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException("min不允许大于max：min = " + min + " max = " + max);
        }
        return min + nextDouble() * (max - min);
    }

    public static long nextLong() {
        return random.nextLong();
    }

    public static long nextLong(long max) {
        return (long) (random.nextFloat() * max);
    }

    public static long nextLong(long min, long max) {
        if (min > max) {
            throw new IllegalArgumentException("min不允许大于max：min = " + min + " max = " + max);
        }
        return (long) (min + random.nextFloat() * (max - min));
    }

    public static short nextShort() {
        return (short) (random.nextFloat() * Short.MAX_VALUE);
    }

    public static char nextChar() {
        return (char) (random.nextFloat() * Character.MAX_VALUE);
    }

    public static boolean nextBoolean() {
        return random.nextBoolean();
    }

    public static byte[] nextBytes() {
        return nextBytes(nextInt(16, 32));
    }

    public static byte[] nextBytes(int size) {
        byte[] bytes = new byte[size];
        random.nextBytes(bytes);
        return bytes;
    }

    public static double nextGaussian() {
        return random.nextGaussian();
    }

	/*字符*/

    /**
     * 返回一个长度在16~32之间的随机字符串。
     * 具体实现请参阅{@link #nextRandomEngStr(int)}。
     *
     * @return
     */
    public static String nextRandomEngStr() {
        return nextRandomEngStr(nextInt(16, 32));
    }

    /**
     * 返回随机字符串，同时包含数字、大小写字母
     *
     * @param len 字符串长度，不能小于3
     * @return String 随机字符串
     */
    public static String nextRandomEngStr(int len) {
        if (len < 3) {
            throw new IllegalArgumentException("字符串长度不能小于3");
        }
        StringBuilder builder = new StringBuilder(len);

        //保证必须包含数字、大小写字母
        builder.append((char) ('0' + nextInt(0, 10))).append((char) ('A' + nextInt(0, 26))).append((char) ('a' + nextInt(0, 26)));

        char[] codes = {
                '0',
                '1',
                '2',
                '3',
                '4',
                '5',
                '6',
                '7',
                '8',
                '9',
                //
                'A',
                'B',
                'C',
                'D',
                'E',
                'F',
                'G',
                'H',
                'I',
                'J',
                'K',
                'L',
                'M',
                'N',
                'O',
                'P',
                'Q',
                'R',
                'S',
                'T',
                'U',
                'V',
                'W',
                'X',
                'Y',
                'Z',
                //
                'a',
                'b',
                'c',
                'd',
                'e',
                'f',
                'g',
                'h',
                'i',
                'j',
                'k',
                'l',
                'm',
                'n',
                'o',
                'p',
                'q',
                'r',
                's',
                't',
                'u',
                'v',
                'w',
                'x',
                'y',
                'z'};
        //charArr[3..len-1]随机生成codes中的字符
        for (int i = 3, codesLen = codes.length; i < len; i++) {
            builder.append(codes[nextInt(0, codesLen)]);
        }

        //将数组chArr随机排序
        int ran;
        char temp;
        for (int i = 0; i < len; i++) {
            ran = i + nextInt(len - i);
            if (ran != i) {//跳过重复的交换
                continue;
            }
            temp = builder.charAt(i);
            builder.setCharAt(i, builder.charAt(ran));
            builder.setCharAt(ran, temp);
        }

        return builder.toString();
    }

	/*数组*/

    public static byte randomIn(byte... array) {
        return array[nextInt(array.length)];
    }

    public static boolean randomIn(boolean... array) {
        return array[nextInt(array.length)];
    }

    public static char randomIn(char... array) {
        return array[nextInt(array.length)];
    }

    public static int randomIn(int... array) {
        return array[nextInt(array.length)];
    }

    public static long randomIn(long... array) {
        return array[nextInt(array.length)];
    }

    public static float randomIn(float... array) {
        return array[nextInt(array.length)];
    }

    public static double randomIn(double... array) {
        return array[nextInt(array.length)];
    }

    @SafeVarargs
    public static <T> T randomIn(T... array) {
        return array[nextInt(array.length)];
    }

    public static <T> T randomIn(List<T> list) {
        return list.get(nextInt(list.size()));
    }
}
