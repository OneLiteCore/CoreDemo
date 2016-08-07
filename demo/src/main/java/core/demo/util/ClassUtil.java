package core.demo.util;

import android.text.TextUtils;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import core.demo.App;
import dalvik.system.DexFile;

/**
 * 该工具类的源代码可在本人的开源项目CoreMate中找到，
 * https://github.com/DrkCore/CoreMate
 *
 * @author DrkCore
 * @since 2015年10月10日19:52:17
 */
public final class ClassUtil {

    private ClassUtil() {
    }

	/* 类属性属性获取 */

    /**
     * 获取对象的类型的名称。如果该object本身就是class的类型的话，则直接返回名称。
     * 具体实现请参阅{@link #getTypeName(Class)}
     *
     * @param object
     * @return
     */
    public static String getTypeName(Object object) {
        return getTypeName(object instanceof Class ? (Class) object : object.getClass());
    }

    /**
     * 获取类型名称。当clazz不为匿名类时效果和{@link Class#getName()}相同，
     * 如果是匿名类的话，则获取其父类的名称。
     *
     * @param clazz
     * @return
     */
    public static String getTypeName(Class clazz) {
        String typeName = clazz.getName();
        int idx = typeName.lastIndexOf("$");
        if (idx != -1 && TextUtils.isDigitsOnly(typeName.substring(idx + 1))) {// 匿名内部类，逻辑上$必定不会出现在类路径的末尾
            return getTypeName(clazz.getSuperclass());
        }
        if (idx != -1) {
            return typeName.substring(idx + 1);
        }
        int dot = typeName.lastIndexOf('.');
        if (dot != -1) {
            return typeName.substring(dot + 1);
        }
        return typeName;
    }

    /**
     * 获取泛型中的类参数的类型。比如异步任务{@link android.os.AsyncTask}
     * 就带有Params，Progress和Result这三个泛型。
     * 使用该方法就可以获得者三个泛型的Type数组。
     * 如果clz并不存在泛型的话，该方法将会抛出异常导致程序奔溃。
     * <b>请谨慎使用该方法。</b>
     *
     * @param clz
     * @return
     */
    public static Type[] getGenericParametersType(Class clz) {
        ParameterizedType paramType = (ParameterizedType) clz.getGenericSuperclass();
        return paramType.getActualTypeArguments();
    }

	/* 获取包和类 */

    /**
     * 从pkgPath包中查找superClass的子类
     *
     * @param superClass 类或者接口
     * @param pkgPath    指定包路径
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static List<Class> getSubClassUnderPackage(Class superClass, String pkgPath)
            throws IOException, ClassNotFoundException {
        List<String> paths = getClassPathsUnderPackage(pkgPath);
        return getSubClassFromPaths(superClass, paths);
    }

    /**
     * 从paths路径中查找superClass的子类，具体实现请参阅{@link #getSubClassFromPaths(Class, List, boolean)}。
     * 默认情况下不加入抽象类。
     *
     * @param superClass
     * @param paths
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Class> getSubClassFromPaths(Class superClass, List<String> paths) throws ClassNotFoundException {
        return getSubClassFromPaths(superClass, paths, false);
    }

    /**
     * 从paths路径中查找superClass的子类。superClass可以是接口，但是查找的类型中只会有其实现类。
     *
     * @param superClass   类或者接口
     * @param paths
     * @param needAbstract
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Class> getSubClassFromPaths(Class superClass, List<String> paths, boolean needAbstract)
            throws ClassNotFoundException {

        List<Class> classes = new ArrayList<>();
        Class clazz;
        for (String path : paths) {
            clazz = Class.forName(path);
            if (superClass != clazz/*不包括自己*/ && !clazz.isInterface()/*且不是一个接口*/ && superClass.isAssignableFrom(clazz)/*是superClass的子类或者实现类*/
                    && (!Modifier.isAbstract(clazz.getModifiers()) || needAbstract)/*不是抽象类，或者需要抽象类*/) {
                classes.add(clazz);
            }
        }
        return classes;
    }

    /**
     * 获取指定包路径下的类或者接口的路径，具体实现请参阅{@link #getClassPathsUnderPackage(String, boolean, boolean, boolean)}。
     * 默认不包括子包及内部类。
     *
     * @param pkgPath 起始的包路径
     * @return
     * @throws IOException
     */
    public static List<String> getClassPathsUnderPackage(String pkgPath) throws IOException {
        return getClassPathsUnderPackage(pkgPath, false, false, false);
    }

    /**
     * 获取指定包路径下的类或者接口的路径，具体实现请参阅{@link #getClassPathsUnderPackage(String, boolean, boolean, boolean)}。
     * 默认不包括内部类。
     *
     * @param pkgPath       起始的包路径
     * @param needRecursive 是否递归获取子包
     * @return
     * @throws IOException
     */
    public static List<String> getClassPathsUnderPackage(String pkgPath, boolean needRecursive) throws IOException {
        return getClassPathsUnderPackage(pkgPath, needRecursive, false, false);
    }

    /**
     * 获取指定包路径下的类或者接口的路径
     *
     * @param pkgPath       起始的包路径
     * @param needRecursive 是否递归获取子包
     * @param needInner     是否获取内部类
     * @param needAnonymous 是否获取匿名类
     * @return
     * @throws IOException
     */
    private static List<String> getClassPathsUnderPackage(String pkgPath, boolean needRecursive, boolean needInner,
                                                          boolean needAnonymous) throws IOException {
        DexFile dexFile = new DexFile(App.getInstance().getPackageCodePath());
        Enumeration<String> entries = dexFile.entries();

        int pkgPathLen = pkgPath.length();
        String path;
        int pathLen;
        int innerIdx;
        List<String> paths = new ArrayList<>();
        while (entries.hasMoreElements()) {
            path = entries.nextElement();
            pathLen = path.length();
            if (pathLen > pkgPathLen && path.startsWith(pkgPath)) {// 指定包起始
                if (!needRecursive && path.lastIndexOf(".") > pkgPathLen) {// 如果在包名+1的位置之后还有点存在表明是子包，便是递归
                    continue;
                }
                innerIdx = path.lastIndexOf("$");
                if (!needInner && innerIdx != -1) {// 内部类
                    continue;
                }
                if (!needAnonymous && TextUtils.isDigitsOnly(path.substring(innerIdx + 1))) {// 匿名类
                    continue;
                }
                paths.add(path);
            }
        }
        return paths;
    }

	/* 反射创建对象 */

    /**
     * 使用默认构造方法为pkgPath包下直属的superClass的子类创建对象。
     *
     * @param pkgPath
     * @param superClass
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <Result> List<Result> createInstancesUnderPackage(String pkgPath, Class<Result> superClass)
            throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        List<Class> subClasses = getSubClassUnderPackage(superClass, pkgPath);
        List<Class<Result>> classes = new ArrayList<>(subClasses.size());
        for (Class clz : subClasses) {//只能一个个强转
            classes.add((Class<Result>) clz);
        }
        return createInstances(classes);
    }

    /**
     * 使用默认构造方法为classes中的每一个Class创建对象。
     *
     * @param classes
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <Result> List<Result> createInstances(List<Class<Result>> classes)
            throws InstantiationException, IllegalAccessException {
        List<Result> results = new ArrayList<>();
        for (Class<Result> tmp : classes) {
            results.add(tmp.newInstance());
        }
        return results;
    }

    /**
     * 使用默认构造方法为classes中的每一个Class创建对象。
     *
     * @param classes
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @SafeVarargs
    public static <Result> List<Result> createInstances(Class<Result>... classes)
            throws InstantiationException, IllegalAccessException {
        List<Result> results = new ArrayList<>();
        for (Class<Result> tmp : classes) {
            results.add(tmp.newInstance());
        }
        return results;
    }
}
