# EXC02-springboot-demo
一个简易的javaweb练习
```
hytlias-web-management
├── EXC02-springboot-demo
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── sx
│   │   │           ├── TliasWebManagementApplication.java
│   │   │           ├── anno //切入点自定义注解
│   │   │           │   └── Log.java
│   │   │           ├── aop //AOP事务通知
│   │   │           │   └── LogAspect.java
│   │   │           ├── config
│   │   │           │   └── WebConfig.java
│   │   │           ├── controller //接收请求响应数据
│   │   │           │   ├── DeptController.java
│   │   │           │   ├── EmpController.java
│   │   │           │   ├── LoginController.java
│   │   │           │   └── UploadController.java
│   │   │           ├── exception
│   │   │           │   └── GlobalExceptionHander.java
│   │   │           ├── filter //过滤器
│   │   │           │   └── LoginCheckFilter.java
│   │   │           ├── interceptor //拦截器
│   │   │           │   └── LoginCheckInterceptor.java
│   │   │           ├── mapper //数据访问层操作数据库
│   │   │           │   ├── DeptLogMapper.java
│   │   │           │   ├── DeptMapper.java
│   │   │           │   ├── EmpMapper.java
│   │   │           │   └── OperateLogMapper.java
│   │   │           ├── pojo //对象
│   │   │           │   ├── Dept.java
│   │   │           │   ├── DeptLog.java
│   │   │           │   ├── Emp.java
│   │   │           │   ├── OperateLog.java
│   │   │           │   ├── PageBean.java
│   │   │           │   └── Result.java
│   │   │           ├── service //业务逻辑处理
│   │   │           │   ├── DeptLogService.java
│   │   │           │   ├── DeptService.java
│   │   │           │   ├── EmpService.java
│   │   │           │   └── impl 
│   │   │           │       ├── DeptLogServiceImpl.java
│   │   │           │       ├── DeptServiceImpl.java
│   │   │           │       └── EmpServiceImpl.java
│   │   │           └── utils //工具类
│   │   │               ├── AliOSSProperties.java
│   │   │               ├── AliOSSUtils.java
│   │   │               └── JwtUtils.java
│   │   └── resources
│   │       ├── application.yml
│   │       ├── com
│   │       │   └── sx
│   │       │       └── mapper
│   │       │           ├── DeptMapper.xml
│   │       │           └── EmpMapper.xml
│   │       ├── static
│   │       │   └── upload.html
│   │       └── templates
│   └── test
│       └── java
│           └── com
│               └── sx
│                   ├── Demo.java
│                   └── TliasWebManagementApplicationTests.java
└target
```
