nacos-config-demo-profile �������ļ�
1. ��127.0.0.1:8848/nacos/index.html  ͨ��nacos ui ��������ռ� �ֱ𴴽�dev test prod 
2. �ֱ��� dev test prod ���� yaml��ʽ��  �������ж˿ں�
server:
    port: 8081  # dev
server:
   port: 8082  # test
server:
   port: 8084 #prod
   
 Ȼ��ֱ���resources�½���application-dev application-test application-prod �ļ�
 ����nacos.config.namespace ��Ӧ nacosUi�� �����ռ�id
 
�ֱ�ָ����ͬ���ļ�����  �鿴������Ӧ�˿ں�