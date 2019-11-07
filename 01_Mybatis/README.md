# Mybatis

## mybatis的环境搭建

项目链接：<a href="https://github.com/senhao1104/ssm/tree/master/01_Mybatis/01_mybatis_first">01_mybatis_first</a>

1. 创建Maven工程并导入坐标 https://mvnrepository.com/
2. 创建实体类和dao接口
3. 创建mybatis的主配置文件 SqlMapConifg.xml
4. 创建映射配置文件 UserDao.xml

## 环境搭建的注意事项

1. mybatis的映射配置文件位置必须和dao接口的包结构相同
2. 映射配置文件的mapper标签namespace属性的取值必须是dao接口的全限定类名
3. 映射配置文件的操作配置（select等），id属性的取值必须是dao接口的方法名

>当我们遵从了这三点之后，我们在开发中就无须再写dao的实现类

## mybatis的入门案例

1. 读取配置文件
2. 创建 SqlSessionFactory工厂
3. 创建 SqlSession
4. 创建 Dao接口的代理对象
5. 执行dao中的方法
6. 释放资源

<img src = "https://github.com/senhao1104/ssm/blob/master/01_Mybatis/resources/images/2019-11-05_16-45-59.jpg" >

>注意：
不要忘记在映射配置中告知mybatis要封装到哪个实体类中
配置方式：指定实体类的全限定类名

<img src = "https://github.com/senhao1104/ssm/blob/master/01_Mybatis/resources/images/2019-11-05_17-14-59.jpg" >

<img src = "https://github.com/senhao1104/ssm/blob/master/01_Mybatis/resources/images/2019-11-05_17-16-07.jpg" width = "60%" >

<img src = "https://github.com/senhao1104/ssm/blob/master/01_Mybatis/resources/images/2019-11-05_17-16-25.jpg" width = "60%" >

### 读取配置文件

以上用到的技术就是dom4j解析xml技术

### 查询操作的执行过程

1. 根据配置文件的信息创建Connection对象
注册驱动 获取连接

2. 获取预处理对象PreparedStatement，此时需要sql语句
```java
conn.preparedStatement(sql);
```

3. 执行查询
```java
ResultSet resultSet = preparedStatement.executeQuery();
```

4. 遍历结果集用于封装
```java
List<E> list = new ArrayList();
while(resultSet.next()){
    E element = (E)Class.forName(配置的全限定类名).newInstance();

    进行封装，把每个rs的内容都添加到element中

    我们的实体类属性和表中的列名是一致的

    于是我们就可以把表的列名看成是实体类的属性名称

    就可以使用反射的方式来根据名称获取每个属性，并把值赋进取

    把element加入到list中

    list.add(element);
}
```

5. 返回 list
```java
return list;
```

### mybatis基于注解的入门案例

将UserDao.xml移除，在dao接口的方法上使用 @Select注解，并指定sql语句
同时需要在 SqlMapConifg.xml 中的mapper配置时，使用class属性指定dao接口的全限定类名

项目链接：<a href="https://github.com/senhao1104/ssm/tree/master/01_Mybatis/02_mybatis_annotation">02_mybatis_annotation</a>

### mybatis 增删查改

项目链接：<a href="https://github.com/senhao1104/ssm/tree/master/01_Mybatis/03_mybatis_crud">03_mybatis_crud</a>