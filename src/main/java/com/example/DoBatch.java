package com.example;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.opencsv.CSVReader;

import domain.Employee;
import domain.QuestionCategory;

/**
 * プロジェクト内のcsvファイルを成形してDBに詰め込む実行クラス.
 * 
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

	@Autowired
	JdbcTemplate template;





	// DB情報をセット.
	@Bean
	public JobExecutionListener listener() {
		return new JobStartEndListner(new JdbcTemplate(dataSource));
	}

}
