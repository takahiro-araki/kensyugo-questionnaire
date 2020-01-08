package com.example;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Domain.Question;
import com.example.Repository.EmployeeRepository;
import com.example.Repository.QuestionRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Controller
@RequestMapping("/resource")
public class Execute {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	QuestionRepository questionRepository;

	public static void main(String[] args) {
		List<String> fileList = new ArrayList<String>(Arrays.asList(args));
		fileList.remove(0);
		

		try {
			String sqlQue = insertQue(fileList);
			/*
			 * String sqlEmp = insertEmp(fileList);
			 * System.out.println("-----------従業員テーブルインサート文----------------");
			 * System.out.println(sqlEmp);
			 * System.out.println("-----------終了----------------");
			 */
			System.out.println("-----------質問テーブルインサート文----------------");
			System.out.println(sqlQue);
			System.out.println("-----------終了----------------");
			/*
			 * System.out.println("-----------アンサーテーブルインサート文----------------"); List<String>
			 * sqlAnswer = insertAnswer(fileList); for (String sql : sqlAnswer) {
			 * System.out.println(sql); }
			 * System.out.println("-----------終了----------------");
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Autowired
	JdbcTemplate template;
	/*
	 * @Autowired EmployeeRepository employeeRepository;
	 * 
	 * @Autowired QuestionCategoryRepository questionCategoryRepository;
	 */

	/**
	 * ファイルから質問を抽出してDBにインサートする.
	 * 
	 * @param fileList コマンドラインに設定した、csvファイル
	 * @return SQL文
	 * @throws Exception
	 */
	public  static String insertQue(List<String> fileList) throws Exception {
		// ファイル名からDBにインサートするカテゴリ名を抽出する
		StringBuilder sb = new StringBuilder();
		String file = fileList.get(0);
		sb.append(" INSERT INTO questions (name,category_id) VALUES ");
		String fileFullName = new File(file).getName();
		String fileName = fileFullName.substring(1, fileFullName.indexOf("."));
		Integer fileNum = Integer.parseInt(fileName);
		// DBアクセス処理未実装
		CSVReader csvReader = new CSVReader(new FileReader(file));
		String[] queLine = csvReader.readNext();
		for (int i = 2; i < queLine.length; i++) {
			String queName = queLine[i].replace("'", "''");
			Execute e =new Execute();
			if (e.confirmQue(queName)==true) {
				sb.append("('");
				sb.append(queName + "' , ");
				sb.append("( select id from categories where categories.id=");
				sb.append(fileNum + ")),");
			} else {
				continue;
			}
		}
		sb.setLength(sb.length() - 1);
		sb.append(";");
		return sb.toString();
	}

	/**
	 * 社員名をファイルから抽出してインサートする.（DBアクセス処理未実装）
	 * 
	 * @param args ファイルの配列
	 * 
	 */
	public static String insertEmp(List<String> fileList) throws Exception {
		// fileNotFindException
		CSVReader csvReader = new CSVReaderBuilder(new FileReader(fileList.get(6))).withSkipLines(1).build();
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO employees(name) VALUES ");
		String[] nextLine;
		while ((nextLine = csvReader.readNext()) != null) {
			// IOException
			String name = nextLine[1];
			String trueName = name.replaceAll(" |　", "");
			sb.append("('");
			sb.append(trueName);
			sb.append("'),");
		}
		sb.setLength(sb.length() - 1);
		sb.append(";");
		return sb.toString();
	}

	/**
	 * ファイルからanswersテーブルへインサートする.
	 * 
	 * @param fileList
	 * @return
	 * @throws Exception
	 */
	public static List<String> insertAnswer(List<String> fileList) throws Exception {
		String file = fileList.get(0);
		StringBuilder sb = null;
		CSVReader csvReader = new CSVReader(new FileReader(file));
		List<String[]> lineAll = csvReader.readAll();
		String[] queLine = lineAll.get(0);
		lineAll.remove(0);
		List<String> stringList = new ArrayList<>();
		for (String[] line : lineAll) {
			sb = new StringBuilder();
			sb.append("insert into answers (employee_id,question_id,point) values ");
			for (int i = 2; i < line.length; i++) {
				String trueName = line[1].replaceAll(" |　", "");
				sb.append("((select e.id from employees as e where e.name='");
				sb.append(trueName + "'),");
				sb.append("(select q.id from questions  as q where q.name='");
				String queName = queLine[i].replace("'", "''");
				sb.append(queName + "'), ");
				sb.append(line[i] + ") ,");
			}
			sb.setLength(sb.length() - 1);
			sb.append(";");
			stringList.add(sb.toString());
		}
		return stringList;
	}

	public boolean confirmQue(String name) {
		System.out.println("検証　"+name);
			List<Question> question = questionRepository.findAll();
			
			if (question.get(0)==null) {
				return true;
			}else {
				return false;
			}
		
		 
	}

}
