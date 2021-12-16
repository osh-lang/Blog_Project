package org.project.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
* @Class Name : TilesConfig.java
* @Description : Tile 환경 설정을 위한 클래스를 정의한다.
* @since 2020.12.21
* @version 1.0
* @see
*/

@Configuration
public class TilesConfig {
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] {"/WEB-INF/layouts/tiles.xml"});
		configurer.setCheckRefresh(true);
		return configurer;
	}
	
	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver resolver = new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		return resolver;
	}
	
}
