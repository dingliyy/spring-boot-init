package com.tv189.zookeeper.schedule;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tv189.zookeeper.zk.ZkConfiguration;

/**
 * 话题定时任务调度
 * @author dingli
 * @date 2018年2月7日 下午2:14:49
 */
@Component
@Slf4j
public class TopicScheduler {
	
	/**
	 * 定时统计话题相关数据
	 */
	@Scheduled(cron = "${topic.scheduler.cron}")
	public void statisticTopic(){
		if(ZkConfiguration.isLeader()){
			log.info("schedule is running...");
		}else{
			log.info("schedule is not running!");
		}
		
	}
	
	
}
