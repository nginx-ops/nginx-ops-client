package io.github.nginx.ops.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
@EnableDiscoveryClient
public class NginxOpsClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(NginxOpsClientApplication.class, args);
  }
}
