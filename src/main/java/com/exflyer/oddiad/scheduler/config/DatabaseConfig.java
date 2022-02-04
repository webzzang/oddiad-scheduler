package com.exflyer.oddiad.scheduler.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = {"com.exflyer.oddiad.scheduler"})
public class DatabaseConfig {

  public static final int DEFAULT_STATEMENT_TIMEOUT = 30;


//  @Bean
//  @Profile({"local", "dev"})
//  public DataSource datasource() {
//    return DataSourceBuilder.create()
//      .driverClassName("com.mysql.cj.jdbc.Driver")
//      .url("jdbc:mysql://ls-a2834d111b7d0d1385ca4360b6718cf409b75eff.cvxedmimklz0.ap-northeast-2.rds.amazonaws.com:3306")
//      .username("oddi")
//      .password("oddi1@#")
//      .build();
//  }

  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource.hikari")
  public DataSource dataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

  @Bean
  @Primary
  public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setMapperLocations(
        new PathMatchingResourcePatternResolver().getResources("classpath:query/**/*.xml"));

    sqlSessionFactoryBean.setTypeAliasesPackage("com.exflyer.oddiad.scheduler");
    if (sqlSessionFactoryBean.getObject() != null) {
      sqlSessionFactoryBean.getObject().getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
      sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
      sqlSessionFactoryBean.getObject().getConfiguration().setCacheEnabled(true);
      sqlSessionFactoryBean
          .getObject()
          .getConfiguration()
          .setDefaultStatementTimeout(DEFAULT_STATEMENT_TIMEOUT);
      sqlSessionFactoryBean
          .getObject()
          .getConfiguration()
          .setDefaultExecutorType(ExecutorType.REUSE);
    }
    return sqlSessionFactoryBean.getObject();
  }
}
