package org.delivery.api.common.annotation;


import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service    // 실제 service를 붙이는 역할과 같은 역할을 하게 하기 위해 만듬
public @interface Business {

    @AliasFor(annotation = Service.class)   //Service의 value값을 그대로 사용한다.
    String value() default "";
}
