common.utils.mybatis下是自动生成mybaits基础代码的工具，使用说明如下：
1.首先建好数据库表
2.引入mybatis-generator和mybatis-generator-core两个jar
3.打开build-mybatis.xml，修改其中一些参数为自己环境的对应参数。
（1）classPathEntry location 为本地环境数据库驱动jar的物理路径。
（2）jdbcConnection 修改对应数据源连接信息
（3）javaModelGenerator targetPackage 为实体类代码包名
（4）sqlMapGenerator targetPackage 为mapper xml文件保存的包目录
（5）javaClientGenerator targetPackage 为数据持久dao层类代码包名
4.运行MyBatisGeneratorTool类的main方法，于是基础代码自动生成了。