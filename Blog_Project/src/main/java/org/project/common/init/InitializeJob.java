package org.project.common.init;

import org.project.common.util.CommonFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class InitializeJob {
	
	private static final Logger logger = LoggerFactory.getLogger(InitializeJob.class);

	@Value("${run.mode}")
    private String run_mode;
	
	@EventListener(ApplicationReadyEvent.class)
	@Async
	public void eventHandler() throws Exception {
		try {
		    logger.info("Run Mode : {}", run_mode);
		} catch (Exception e) {
			logger.error(CommonFunction.printStackTraceToString(e));
		}
	}
}