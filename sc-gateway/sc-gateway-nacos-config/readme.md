#打开nacos客户端 
#创建gate-application 配置表

spring:
  cloud:
    gateway:
      route:
        config:
          data-id:
          group: DEFAULT_GROUP
      routes:
        - id: user-service #路由的编号
          uri: lb://user-service #跳转的地址
          predicates: # 断言，作为路由的匹配条件，对应 RouteDefinition 数组
            - Path=/user-service/**
          filters:
            - RewritePath=/user-service/(?<remaining>.*), /${remaining} # 将 /user-service 前缀剔除
        - id: order-service #路由的编号
          uri: lb://order-service #跳转的地址
          predicates: # 断言，作为路由的匹配条件，对应 RouteDefinition 数组
            - Path=/order/**
          filters:
            - RewritePath=/order-service/(?<remaining>.*), /${remaining} # 将 /user-service 前缀剔除