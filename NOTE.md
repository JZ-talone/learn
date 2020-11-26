

#### 无状态服务
- 对于无状态服务，首先说一下什么是状态：如果一个数据需要被多个服务共享，才能完成一笔交易，那么这个数据被称为状态。进而依赖这个“状态”数据的服务被称为有状态服务，反之称为无状态服务。
- 那么这个无状态服务原则并不是说在微服务架构里就不允许存在状态，表达的真实意思是要把有状态的业务服务改变为无状态的计算类服务，那么状态数据也就相应的迁移到对应的“有状态数据服务”中。
- 场景说明：例如我们以前在本地内存中建立的数据缓存、Session缓存，到现在的微服务架构中就应该把这些数据迁移到分布式缓存中存储，让业务服务变成一个无状态的计算节点。迁移后，就可以做到按需动态伸缩，微服务应用在运行时动态增删节点，就不再需要考虑缓存数据如何同步的问题。


#### docker
- docker ps -a       查看所有容器               
- docker images       查看所有镜像
- docker pull xxx:latest       拉取镜像
- docker run -itd --name {别名} -p {宿主机端口}:{docker内端口} {镜像名称}      启动镜像          
- docker stop {别名}     停止容器 
- docker rm {别名}      删除容器
- docker exec -it {别名} bash           进入容器
- docker build -t {镜像名}:{tag} .        制作镜像


#### Dockerfile语法
- https://www.cnblogs.com/ityouknow/p/8595384.html


#### kafka
- kafka-topics --list --zookeeper localhost:2181
- kafka-console-producer --broker-list localhost:9092 --topic topic_input
- kafka-console-consumer --bootstrap-server localhost:9092 --topic topic_input --from-beginning