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

### mybatis中的连接池

我们在实际开发中都会使用连接池，应为它可以减少获取连接所消耗的时间

mybatis连接池提供了3种方式的配置：

主配置文件 SqlMapConifg.xml 中的DataSource标签，type属性表示采用哪种连接方式

type属性取值：POOLED UNPLLOED JNDI

### mybatis中的动态sql语句

项目链接：<a href="https://github.com/senhao1104/ssm/tree/master/01_Mybatis/04_mybatis_dynamicSQL">04_mybatis_dynamicSQL</a>

#### 标签：&lt;where&gt; &lt;if&gt; 

```java
<select id="findUserByCondition" parameterType="domain.user" resultType="domain.User">
    select * from user
    <where>
        <if test="username != null">
            and username = #{username}
        </if>
        <if test="sex != null">
            and sex = #{sex}
        </if>
    </where>
</select>
```

#### 标签：&lt;foreach&gt;

```java
<select id="findUserInIds" resultType="domain.User" parameterType="domain.QueryVo">
<!-- select * from user where id in (1,2,3,4,5); -->
    select * from user
    <where>
        <if test="ids != null and ids.size() > 0">
            <foreach collection="ids" open="and id in ( " close=")" item="uid" separator=",">
                #{uid}
            </foreach>
        </if>
    </where>
</select>
```

### mybatis中的事务

1. 什么是事务
2. 事务的四大特性 ACID
3. 不考虑隔离性会产生的3个问题
4. 解决办法：四种隔离级别

它是通过sqlsession对象的commit方法和rollback方法实现事务的提交和回滚

### mybatis中的多表查询

表之间的关系有几种：
一对多、多对一、一对一、多对多

>例如：
用户和订单就是一对多，订单和用户就是多对一
人和身份证号就是一对一
老师和学生之间就是多对多

特例：
如果拿出每一个订单，它都只能属于一个用户
所以mybatis就把多对一看成了一对一

#### 示例一：用户和账户(一对一、一对多)

项目链接：<a href="https://github.com/senhao1104/ssm/tree/master/01_Mybatis/05_mybatis_one2many">05_mybatis_one2many</a>

一个用户可以有多个账户
一个账户只能属于一个用户（多个账户也可以属于同一个用户）

1. 建立两张表：用户表，账户表
让用户表和账户表之间具备一对多的关系：需要使用外键在账户表中添加

2. 建立两个实体类：用户实体类和账户实体类
让用户和账户的实体类能体现出来一对多的关系

3. 建立两个配置文件
用户的配置文件
账户的配置文件

4. 实现配置：
查询用户时，可以同时得到用户下所包含的账户信息
查询账户时，可以同时得到账户的所属用户信息

#### 示例二：用户和角色(多对多)

项目链接：<a href="https://github.com/senhao1104/ssm/tree/master/01_Mybatis/06_mybatis_many2many">06_mybatis_many2many</a>

一个用户可以有多个角色
一个角色可以赋予多个用户

1. 建立两张表：用户表，角色表
让用户表和角色表之间具备多对多的关系：需要使用中间表，
中间表中包含各自的主键，在中间表中是外键

2. 建立两个实体类：用户实体类和角色实体类
让用户和角色的实体类能体现出来多对多的关系
各自包含对方一个集合引用

3. 建立两个配置文件
用户的配置文件
角色的配置文件

4. 实现配置：
查询用户时，可以同时得到用户下所包含的角色信息
查询角色时，可以同时得到角色的所属用户信息