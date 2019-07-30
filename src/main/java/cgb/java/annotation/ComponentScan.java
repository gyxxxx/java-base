package cgb.java.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/***
 * @target 注解的作用是用于描述自己写的注解
 * 能够修饰哪些成员(例如ElementType.TYPE表示
 * 只能修饰类)
 * @Retention 注解的作用是用于描述自己写的注解
 * 何时有效?(例如RetentionPolicy.RUNTIME表示
 * 运行时有效,RetentionPolicy.SOURCE表示编译
 * 时有效.)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ComponentScan {//ComponentScan.class
	 //注解中特殊的属性
	 String value() default "";//借助default指定默认值
}//自定义注解







