# SSM笔记

## Maven

Maven功能：构建管理，依赖管理

Maven工程的**GAVP属性**：GroupId,ArtifactId,Version,Packaging.

1. GroupID格式：com.{BU}.业务线.{子业务线}，最多四级
2. ArtifactID格式：产品线名-模块名
3. Version格式：主版本号.次版本号.修订号

Packaging定义规则：jar(default),war(web),pom

**web工程配置**----javaee

文件结构	需要手动补全

```
main|
	|
	|_webapp
		|
		|_WEB-INF
			|
			|_web.xml
```

### 依赖管理

#### 第三方依赖信息怎么查找？

1. https://mvnrepository.com
2. 插件 maven-search

### 拓展

1. xml里声明变量——properties中`<xxx.version>xxx.xxx.xxx</xxx.version>`，然后引用`${xxx.version}`
2. 可选属性scope——引入依赖的作用域

### 依赖传递和依赖冲突

依赖传递：依赖包含的依赖，不用重复导入

依赖冲突：A->B->C->A，会终止，报依赖冲突 &

​				 两个依赖依赖一个依赖的不同版本：1.谁短谁优先 2.谁先谁优先

### 扩展构建管理和插件配置

项目构建是指将源代码、依赖库和资源文件等转换成可执行或可部署的过程

```shell
mvn clean #清理编译或打包后的项目结构，删除target文件夹
mvn compile #编译项目，生成target文件
mvn test #执行测试源码
mvn site #生成一个项目依赖信息的展示页面
mvn package #打包项目，生成war/jar文件
mvn install #打包后上传到本地maven仓库
mvn deploy #只打包，上传到maven私服仓库
```

### maven继承和聚合特性

对一个比较大的项目进行模块拆分，每个module都需要配置自己的依赖信息

多个模块要使用同一个框架，他们应该是同一版本

1. 父工程直接引入依赖
2. 父工程不引入依赖，只做依赖版本的声明`<dependeniesManagement>`

```xml
<!-- 父工程-->
<groupId>com.xxx.xxx</groupId>
<aritfactId>xxx-xxx</aritfactId>
<version></version>
<!-- 当前工程为父工程，要去管理子工程，所以打包方式必须是pom-->
<packaging>pom</packaging>

<!-- 子工程-->
<parent>
	父工程的gav
</parent>
```

聚合：在父工程pom中有modules中声明子模块

## Spring

### 技术体系机构

**单一结构**：

表述层：SpringMVC——Tomcat、Servlet

业务逻辑：XxxService——JavaSE

持久化层：Mybatis——JDBC

![image-20231016170317985](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20231016170317985.png)

**分布式架构**：



### SpringFramework

#### SpringFramework是基础框架，Spring的基础



### SpringIoC

#### 组件和组件管理概念

业务的三层结构：控制层组件（controller）、业务逻辑层组件（service）、持久化组件（dao）

*组件：*可以复用的java对象

#### 核心容器理解

普通容器：数组，集合——只做存储作用

复杂容器：Servlet容器，SpringIoC——存储的同时进行管理

SpringIoC负责实例化、配置和组装bean（组件）

#### SpringIoc容器具体接口和实现类

容器接口：BeanFactory提供了配置框架和基本功能，而ApplicationContext添加了更多特定于企业的功能。

Spring框架提供了多种配置方式：

1. xml配置方式：最早的配置方式之一，通过在xml文件中定义bean以及依赖关系、bean的作用域等信息，让springioc容器来管理bean之间的依赖关系
2. 注解：spring2.5版本开始支持，可以通过在bean类上使用注解来取代xml文件配置（@Component@Service@Autowired）
3. java配置类：从spring3.0开始支持，通过java类来定义bean、bean之间的依赖关系和配置信息，从而取代xml配置文件

#### IoC（Inversion of Control）控制反转

IoC主要是针对对象的创建和调用控制而言的，也就是说，当应用程序需要使用一个对象时，不再是应用程序直接创建该对象，而是由IoC容器来创建和管理，即控制权由应用程序转移到IoC容器中，也就是“反转”了控制权。这种方式基本上是通过**依赖查找**的方式来实现的，即IoC容器维护这构成应用程序的对象，并负责创建这些对象。

#### DI（Dependency Injection）依赖注入

#### 基于xml配置ioc

步骤：

1. 编写配置信息
2. 实例化ico容器对象（指定配置信息）
3. 获取组件

配置元数据

xml方式

```xml
<beans>
	<bean id="" class="">
    </bean>
</beans>
```

实例化ioc容器对象

```java
ApplicationContext context = new ClassPathXmlApplicationContext("service.xml","daos.xml");
```

获取bean组件

```java
PetStoreService service = context.getBean("petStore",PetStoreService.class);
```

##### 基于xml的ioc管理

```xml
<!-- 1.可以使用无参构造函数实例化的组件，如何进行ioc配置呢-->
<bean id="xxx" class="com.xxx.xxx.classname" />

<!-- 2.静态工厂类进行ioc配置-->
<bean id="xxx" class="com.xxx.xxx.factoryname" factory-method="createInstance" />

<!-- 3.非静态工厂类进行ioc配置-->
<!-- 配置工厂类的组件信息-->
<bean id="xxxfac" class="com.xxx.xxx.classname" />
<!-- 通过指定非静态工厂对象和方法名来配置生成的ioc信息-->
<bean id="xxx" factory-bean="xxxfac" factory-method="createInstance" />
```

##### 基于xml的DI管理

```xml
<bean id="userDao" class="com.xxx.xxx.xxx" />

<bean id="userService" class="com.xxx.xxx.xxx">
	<!-- 构造参数传参
		<constructor-arg
			value = 直接属性值
			ref = 引用其他的bean beanId的值
		-->
    <constructor-arg ref="userDao">
</bean>
```

##### ioc容器的创建和使用

创建

```java
//1.
ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation:"spring-03.xml");
//2.
ClassPathXmlApplicationContext applicationContext1 = new ClassPathXmlApplicationContext();
applicationContext1.setConfigLocation("spring-03.xml");
applicationContext1.refresh();
```

使用

```java
//1.
HappyComponent happyComponent = (HappyComponent) applicationContext.getBean(name:"happyComponent");
//2.
HappyComponent happyComponent1 = applicationContext.getBean(name:"happyComponent",HappyComponent.class);
//3.
HappyComponent happyComponent2 = applicationContext.getBean(HappyComponent.class)
```

##### Bean的作用域和周期方法

周期方法的声明

```java
public class BeanOne {
    /**
    *必须是public 返回值必须void 无参
    */
    public void init(){
        
    }
}

public class BeanTwo {
    public void cleanup(){
        
    }
}
```

周期方法配置

```xml
<beans>
	<bean id="beanOne" class="com.xxx.xxx.BeanOne" init-method="init" />
    <bean id="beanTwo" class="com.xxx.xxx.BeanTwo" init-method="cleanup" />
</beans>
```

PS：正常结束ioc容器

`applicationContext.close();`

**作用域**

`<bean>`标签声明bean是将Bean的配置信息给到Springioc容器，在容器中转换成BeanDefinition对象，Springioc容器可以根据BeanDefinition对象反射创建多个bean对象实例。

作用域可选值（scope）：

1. `singleton` 单例，创建对象的时机：ioc容器初始化时
2. `prototype` 多例，创建对象的时机：获取bean时
3. `request` 每次请求创建
4. `session` 每次会话创建

```xml
<bean id="xxx" class="xxx.xxx.xxx" scope="xxx" />
```

##### FactoryBean特性和使用

在xml中配置工厂模式，都需要指定工厂方法`factory-method`

```xml
<!-- 2.静态工厂类进行ioc配置-->
<bean id="xxx" class="com.xxx.xxx.factoryname" factory-method="createInstance" />

<!-- 3.非静态工厂类进行ioc配置-->
<!-- 配置工厂类的组件信息-->
<bean id="xxxfac" class="com.xxx.xxx.classname" />
<!-- 通过指定非静态工厂对象和方法名来配置生成的ioc信息-->
<bean id="xxx" factory-bean="xxxfac" factory-method="createInstance" />
```

`FactoryBean<T>`接口提供的三种方法：

- `T getObject():` 编写实例化逻辑
- `boolean isSingleton():`
- `Class<?> getObjectType():`

Factorybean使用场景;

- 代理类的拆功能键
- 第三方框架整合
- 复杂对象的实例化



ps：FactoryBean和BeanFactory的区别

1. BeanFactroy：IOC容器
2. FactoryBean：标准化组件工厂的接口

##### 实验Lab1

