package com.qfedu.test;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleTriggerExample {

	public static void main(String[] args) throws SchedulerException {
		//创建job对象
		JobDetail job = JobBuilder.newJob(HelloJob.class)
			.withIdentity("dummyJobName", "group1").build();
		//第一种触发器
		//创建trigger对象
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("dummyTriggerName", "group1")
				.withSchedule(
					SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(5).repeatForever())
				.build();
		
		//第二种触发器
		Trigger trigger2 = TriggerBuilder
				.newTrigger()
				.withIdentity("dummyTriggerName", "group1")
				.withSchedule(
					CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
				.build();
		//创建调度器
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger2);
	}

}
