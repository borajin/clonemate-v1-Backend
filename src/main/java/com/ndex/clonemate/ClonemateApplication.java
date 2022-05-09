package com.ndex.clonemate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy	//aop
@SpringBootApplication
public class ClonemateApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(ClonemateApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
