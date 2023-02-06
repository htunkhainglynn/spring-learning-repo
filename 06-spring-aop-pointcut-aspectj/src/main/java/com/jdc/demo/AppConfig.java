package com.jdc.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = {
		"com.jdc.demo.admin",
		"com.jdc.demo.member",
		"com.jdc.demo.aspects"
})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ImportResource("/context.xml")
public class AppConfig {

}
