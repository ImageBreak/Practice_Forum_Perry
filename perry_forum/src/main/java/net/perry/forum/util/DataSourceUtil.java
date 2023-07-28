package net.perry.forum.util;

import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

/**
 * 数据库连接池工具类
 */
public class DataSourceUtil {
    private static DataSource dataSource;
    static {
        try {
            InputStream is = DataSourceUtil.class.getClassLoader().getResourceAsStream("database.properties");
            Properties p = new Properties(null);
            p.load(is);
            dataSource = BasicDataSourceFactory.createDataSource(p);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new ExceptionInInitializerError("DBCP初始化失败");
        }
    }

    /**
     * 获取连接池
     * 
     * @return
     */
    public static DataSource getDataSource() {
        return dataSource;
    }
}
