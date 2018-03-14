package com.tv189.zookeeper.zk;

import lombok.extern.slf4j.Slf4j;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@EnableConfigurationProperties(ZkProperties.class) // 开启对@ZkProperties注解配置Bean的支持
@Configuration
public class ZkConfiguration {
	
	private static ZkProperties zkProperties;
    
    //zookeeper链接client
    private static CuratorFramework  client;
    
    //选举类
    private static LeaderSelector leaderSelector;
    
    public static void init() {
        //初始化连接
        client = CuratorFrameworkFactory.newClient(zkProperties.getAddress(), new ExponentialBackoffRetry(zkProperties.getBaseSleepTimeMs(), zkProperties.getMaxRetries()));
        
        //启动一个客户端
        client.start();
        
        
        //实例化一个选举器//特别注意LeaderListener，如果takeLeadership方法执行完毕，则会自动释放leaders身份，故我们使用countDownLatch来阻塞此方法使其不主动放弃leaders身份
        //同时这也给我们一个思路，我们可以通过在外部修改countDownLatch的值来控制leader是否主动放弃其身份
        leaderSelector = new LeaderSelector(client, zkProperties.getDataDir(), new LeaderListener());//放弃leader权限后还可以参加选举
        leaderSelector.autoRequeue();
        
        //开始服务
        leaderSelector.start();
        
        log.info("zookeeper start success");
        
    }
    
    public static boolean isLeader(){
    	if(client == null){
    		synchronized (ZkConfiguration.class) {
				if(client == null){
					init();
				}
			}
    	}
    	return leaderSelector.hasLeadership();
    }

    @Autowired
	public void setZkProperties(ZkProperties zkProperties) {
    	ZkConfiguration.zkProperties = zkProperties;
	}
        
}
