package com.example;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * プロジェクト内のcsvファイルを成形してDBに詰め込む実行クラス.
 * @author takahiro.araki
 *
 */
public class DoBatch {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public DataSource dataSource;
	
	
	@Bean
	public FlatFileItemReader<Item>reader(){
		
		
		
	}
	
	
	
}
