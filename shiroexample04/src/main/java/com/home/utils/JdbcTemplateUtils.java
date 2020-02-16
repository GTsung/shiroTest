package com.home.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author guxc
 * @date 2020/2/16
 */
public class JdbcTemplateUtils {

    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate getJdbc(){
        if(null == jdbcTemplate){
            jdbcTemplate = createJdbc();
        }
        return jdbcTemplate;
    }

    private static JdbcTemplate createJdbc() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/shiro?serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("root");
        return new JdbcTemplate(ds);
    }
}
