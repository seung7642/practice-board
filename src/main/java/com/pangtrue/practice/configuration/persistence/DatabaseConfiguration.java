package com.pangtrue.practice.configuration.persistence;

//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 1. 10.
 * Time: 오후 8:18
 */
//@Slf4j
//@Configuration
//public class DatabaseConfiguration {
//
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.hikari")
//    public HikariConfig hikariConfig(){
//        return new HikariConfig();
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        return new HikariDataSource(hikariConfig());
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//
//        // 설정 파일 경로 설정.
//        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mappers/mybatis-config.xml"));
//
//        // 매퍼 위치 설정. sql 접두사를 생략하면 부트는 mybatis-config.xml 설정 파일도 읽어버린다.
//        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/sql-*.xml"));
//
//        // 해당 패키지 하위에서 @Alias 어노테이션을 찾아서 등록한다.
//        sqlSessionFactoryBean.setTypeAliasesPackage("com.pangtrue.practice");
//
//        return sqlSessionFactoryBean.getObject();
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}
