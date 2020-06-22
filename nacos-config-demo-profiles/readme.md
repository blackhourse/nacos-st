nacos-config-demo-profile 多配置文件
1. 打开127.0.0.1:8848/nacos/index.html  通过nacos ui 点击命名空间 分别创建dev test prod 
2. 分别在 dev test prod 创建 yaml格式的  服务运行端口号
server:
    port: 8081  # dev
server:
   port: 8082  # test
server:
   port: 8084 #prod
   
 然后分别在resources下建立application-dev application-test application-prod 文件
 配置nacos.config.namespace 对应 nacosUi端 命名空间id
 
分别指定不同的文件启动  查看启动对应端口号