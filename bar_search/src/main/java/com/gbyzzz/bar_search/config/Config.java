package com.gbyzzz.bar_search.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.gbyzzz.bar_search.repository.jpa")
@EnableElasticsearchRepositories(basePackages = "com.gbyzzz.bar_search.repository.elasticsearch")
public class Config {
}
