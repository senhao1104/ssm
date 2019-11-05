# Mybatis

### mybatis的环境搭建

1. 创建Maven工程并导入坐标 https://mvnrepository.com/
2. 创建实体类和dao接口
3. 创建mybatis的主配置文件 SqlMapConifg.xml
4. 创建映射配置文件 UserDao.xml

### 环境搭建的注意事项

1. mybatis的映射配置文件位置必须和dao接口的包结构相同
2. 映射配置文件的mapper标签namespace属性的取值必须是dao接口的全限定类名
3. 映射配置文件的操作配置（select等），id属性的取值必须是dao接口的方法名

>当我们遵从了这三点之后，我们在开发中就无须再写dao的实现类

### mybatis的入门案例

1. 读取配置文件
2. 创建 SqlSessionFactory工厂
3. 创建 SqlSession
4. 创建 Dao接口的代理对象
5. 执行dao中的方法
6. 释放资源

>注意：
不要忘记在映射配置中告知mybatis要封装到哪个实体类中
配置方式：指定实体类的全限定类名

#### mybatis基于注解的入门案例

将UserDao.xml移除，在dao接口的方法上使用 @Select注解，并指定sql语句。
同时需要在 SqlMapConifg.xml 中的mapper配置时，使用class属性指定dao接口的全限定类名。