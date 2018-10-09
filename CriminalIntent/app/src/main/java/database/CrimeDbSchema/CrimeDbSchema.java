package database.CrimeDbSchema;

/**
 * Created by Yangyulin on 2018/10/9.
 */
public class CrimeDbSchema {

    //描述数据表的CrimeTable内部类，用途：定义描述数据表元素的String常量
    public static final class CrimeTable{
        public static final String NAME = "crimes";//数据库表名

        //定义数据表字段
        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
        }
    }
}
