package gdc.shop.shopService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan(value= {"gdc"})
@EntityScan("gdc.shop.datamanager")
@EnableJpaRepositories("gdc.shop.datamanager")
@EnableDiscoveryClient
public class ShopServiceApplication {

	@Bean
	public WebClient.Builder getWeClientBuilder(){
		return WebClient.builder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ShopServiceApplication.class, args);
	}

}
