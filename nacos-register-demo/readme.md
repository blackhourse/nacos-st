使用nacos 作为注册中心
添加依赖
  <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- 实现对 Nacos 作为注册中心的自动化配置 -->
        <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>nacos-discovery-spring-boot-starter</artifactId>
            <version>0.2.4</version>
        </dependency>

添加注册中心配置
nacos:
  # Nacos 配置中心的配置项，对应 NacosDiscoveryProperties 配置类
  discovery:
    server-addr: 127.0.0.1:8848 # Nacos 服务器地址
    auto-register: true # 是否自动注册到 Nacos 中。默认为 false。
    namespace: # 使用的 Nacos 的命名空间，默认为 null。
    register:
      service-name: ${spring.application.name} # 注册到 Nacos 的服务名
      group-name: DEFAULT_GROUP # 使用的 Nacos 服务分组，默认为 DEFAULT_GROUP。
      cluster-name: # 集群名，默认为空。


① Provider：

启动时，向 Registry 注册自己为一个服务（Service）的实例（Instance）。
同时，定期向 Registry 发送心跳，告诉自己还存活。
关闭时，向 Registry 取消注册。
② Consumer：

启动时，向 Registry 订阅使用到的服务，并缓存服务的实例列表在内存中。
后续，Consumer 向对应服务的 Provider 发起调用时，从内存中的该服务的实例列表选择一个，进行远程调用。
关闭时，向 Registry 取消订阅。
③ Registry：

Provider 超过一定时间未心跳时，从服务的实例列表移除。
服务的实例列表发生变化（新增或者移除）时，通知订阅该服务的 Consumer，从而让 Consumer 能够刷新本地缓存。