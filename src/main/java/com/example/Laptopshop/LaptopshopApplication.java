package com.example.Laptopshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LaptopshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaptopshopApplication.class, args);

		// ApplicationContext HoidanIt =
		// SpringApplication.run(LaptopshopApplication.class, args);
		// for (String s : HoidanIt.getBeanDefinitionNames()) {
		// System.out.println(s);
		// }
	}
}