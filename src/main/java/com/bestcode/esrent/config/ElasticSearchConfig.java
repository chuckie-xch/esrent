package com.bestcode.esrent.config;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * es配置
 *
 * @author xch
 * @create 2018-07-24 22:48
 **/
@Configuration
public class ElasticSearchConfig {

    @Bean
    public TransportClient esClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("clust.name", "elasticsearch")
                .put("client.transport.sniff", true)
                .build();
        InetSocketTransportAddress master = new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300);
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(master);

    }
}
