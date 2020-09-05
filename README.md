# NoteBook

#### 介绍
A simple SSM notebook CRUD backends impl

(developed by IntelliJ IDEA)

这么长的文档，如果没有特别的要求，不想用英文写
（以下是这句话的个人翻译，建议跳过，**手动狗头**）

As the length of the document considered,
it is not within my willing 
that is subjective
to write it in English
(or writing it with English language getting used 
does not contain in the range of my personal subjective willing)
if there are no specific international requirements
which is almost out of concern for the present time
when the project has just been in the status that its first commit
being put forward.
(or only with specific international requirements advanced
will I or other almost unexpectable contributors provide an version
that proximately meet the requirements of cross-language or culture programmers)
Any inconveniences as consequence would be regret
without requesting for excuses at least for present however. 

批语：嗯，用词不够High Level，水平偏低，高中英语作文既视感，下一位~

下面说正事：

#### 软件架构
1.  SpringBoot+MyBatis+MySql，前后端分离
2.  采用了基于Redis的SpringSession，保持一致性
3.  Controller遵循REST规范，无状态（支持Nginx负载均衡）
4.  API文档采取Swagger2生成，支持swagger-ui

#### 后续发展
1.  添加处理缓存访问的Dubbo RPC（Note字数一多DB压力有点大，
手头又没有多余的硬盘组FastDFS）
2.  动态Redis集群（Router反射添加），处理未修改的Note查询
3.  改用ElasticSearch分布式Note内容存储，DB里只存index（或许会换WordPress）
4.  受限于网络环境，K8S插件下载日常timeout，后续争取支持K8S集群部署，全盘容器化



#### 安装教程
1.  Docker拉取最新版Redis+MySql+Tomcat+Nginx镜像
2.  根据**application-dev.xml**里的配置run上述镜像
（一般-p 映射端口都走默认情况，_Nginx环境下删除server.port配置即可_）
3.  希望能习惯几乎没有界面的Note网站，因为作者不会js

#### 使用说明

1.  仓库**目前只包含后端API**，文档用Swagger2 API注解生成
位置在swagger-ui的默认路径

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
