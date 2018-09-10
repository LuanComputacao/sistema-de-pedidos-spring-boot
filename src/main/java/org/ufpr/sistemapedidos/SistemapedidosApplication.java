package org.ufpr.sistemapedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SistemapedidosApplication {

//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(SistemapedidosApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(SistemapedidosApplication.class, args);
    }
}
