package com.jdc.hello.controller.interceptor;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Retention(RUNTIME)
@Target(TYPE)
@Component
public @interface Interceptor {

}
