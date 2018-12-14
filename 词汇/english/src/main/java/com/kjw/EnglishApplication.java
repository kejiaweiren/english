package com.kjw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;

@SpringBootApplication
@MapperScan("com.kjw.dao")
@Cacheable
public class EnglishApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(EnglishApplication.class, args);
    }
}
