package core.demo.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
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

	/*对象（仅供测试）*/

    public static <T> List<T> randomBeanInstances(Class<T> bean, int size) {
        return randomBeanInstances(bean, size, true);
    }

    public static <T> List<T> randomBeanInstances(Class<T> bean, int size, boolean skipId) {
        List<T> list = new ArrayList<>(size);
        T instance;
        while (size-- > 0) {
            instance = randomBeanInstance(bean, skipId);
            if (instance != null) {
                list.add(instance);
            }
        }
        return list;
    }

    /**
     * 具体实现请参阅{@link #randomBeanInstance(Class, boolean)}，默认跳过id字段。
     */
    public static <T> T randomBeanInstance(Class<T> bean) {
        return randomBeanInstance(bean, true);
    }

    /**
     * 创建bean对象，并填充原始类型的随机数据。
     * 任何一步失败都将返回null。该方法只供用于测试。
     *
     * @param bean
     * @param skipId
     * @param <T>
     * @return
     */
    public static <T> T randomBeanInstance(Class<T> bean, boolean skipId) {
        T instance = null;
        if (bean.getSuperclass() == Object.class) {//只处理最简单的Bean对象
            try {
                instance = bean.newInstance();
                Field[] fields = bean.getDeclaredFields();
                Class fieldType;
                for (Field field : fields) {
                    if (skipId && field.getName().toLowerCase().contains("id")) {
                        continue;
                    }
                    fieldType = field.getType();
                    if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers()) || fieldType.isArray() || fieldType.isInterface()) {
                        continue;
                    }

                    field.setAccessible(true);//打开权限

                    if (fieldType == Integer.class || fieldType == int.class) {
                        field.set(instance, nextInt(0, 128));
                    } else if (fieldType == Float.class || fieldType == float.class) {
                        field.set(instance, nextFloat(0, 128));
                    } else if (fieldType == Double.class || fieldType == double.class) {
                        field.set(instance, nextDouble(0, 128));
                    } else if (fieldType == Long.class || fieldType == long.class) {
                        field.set(instance, nextLong(0, 128));
                    } else if (fieldType == Short.class || fieldType == short.class) {
                        field.set(instance, nextShort());
                    } else if (fieldType == Byte.class || fieldType == byte.class) {
                        field.set(instance, nextBytes());
                    } else if (fieldType == Character.class || fieldType == char.class) {
                        field.set(instance, nextChar());
                    } else if (fieldType == Boolean.class || fieldType == boolean.class) {
                        field.set(instance, nextBoolean());
                    } else if (fieldType == String.class || fieldType == CharSequence.class) {
                        field.set(instance, nextRandomEngStr());
                    }
                }
            } catch (Throwable t) {
            }
        }
        return instance;
    }
}
