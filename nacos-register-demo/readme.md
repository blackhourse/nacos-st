ʹ��nacos ��Ϊע������
�������
  <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- ʵ�ֶ� Nacos ��Ϊע�����ĵ��Զ������� -->
        <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>nacos-discovery-spring-boot-starter</artifactId>
            <version>0.2.4</version>
        </dependency>

���ע����������
nacos:
  # Nacos �������ĵ��������Ӧ NacosDiscoveryProperties ������
  discovery:
    server-addr: 127.0.0.1:8848 # Nacos ��������ַ
    auto-register: true # �Ƿ��Զ�ע�ᵽ Nacos �С�Ĭ��Ϊ false��
    namespace: # ʹ�õ� Nacos �������ռ䣬Ĭ��Ϊ null��
    register:
      service-name: ${spring.application.name} # ע�ᵽ Nacos �ķ�����
      group-name: DEFAULT_GROUP # ʹ�õ� Nacos ������飬Ĭ��Ϊ DEFAULT_GROUP��
      cluster-name: # ��Ⱥ����Ĭ��Ϊ�ա�


�� Provider��

����ʱ���� Registry ע���Լ�Ϊһ������Service����ʵ����Instance����
ͬʱ�������� Registry ���������������Լ�����
�ر�ʱ���� Registry ȡ��ע�ᡣ
�� Consumer��

����ʱ���� Registry ����ʹ�õ��ķ��񣬲���������ʵ���б����ڴ��С�
������Consumer ���Ӧ����� Provider �������ʱ�����ڴ��еĸ÷����ʵ���б�ѡ��һ��������Զ�̵��á�
�ر�ʱ���� Registry ȡ�����ġ�
�� Registry��

Provider ����һ��ʱ��δ����ʱ���ӷ����ʵ���б��Ƴ���
�����ʵ���б����仯�����������Ƴ���ʱ��֪ͨ���ĸ÷���� Consumer���Ӷ��� Consumer �ܹ�ˢ�±��ػ��档