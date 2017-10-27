package com.gittoy.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * QueueListener.java
 *
 * @author GaoYu 2017年10月25日 下午3:55:35
 */
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private GittoyQueue gittoyQueue;

	@Autowired
	private DeferredResultHolder deferredResultHolder;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		// 启动监听器新线程
		new Thread(() -> {
			// 模拟监听业务逻辑
			while (true) {

				// 当订单完成消息队列中存在值的情况下
				if (StringUtils.isNotBlank(gittoyQueue.getCompleteOrder())) {

					// 取得消息队列中的值
					String orderNumber = gittoyQueue.getCompleteOrder();
					logger.info("返回订单处理结果：" + orderNumber);
					deferredResultHolder.getMap().get(orderNumber).setResult("place order success");

					// 设置为空防止死循环
					gittoyQueue.setCompleteOrder(null);

				} else {
					// 没有值的情况下，睡眠100毫秒，再重新循环监听。
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
