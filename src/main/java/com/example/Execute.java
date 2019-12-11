package com.example;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Controller
@RequestMapping("/resource")
public class Execute {

	public static void main(String[] args) {
	
		List<String> fileList = new ArrayList<String>(Arrays.asList(args));
		fileList.remove(0);
		System.out.println("---------------------------");
		try {
			String sqlQC=insertQC(fileList);
			String sqlEmp=insertEmp(fileList);
			String sqlQue=insertQue(fileList);
			List<String> sqlAnswer=insertAnswer(fileList);
			
			
			/*
			 * System.out.println(sqlQC); System.out.println(sqlEmp);
			 * System.out.println(sqlQue);
			 * 
			 */			
			  for(String sql:sqlAnswer) { System.out.println(sql);
			  
			  
			 }
			 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * ファイルから質問カテゴリーを抽出してDBにインサートする.
	 * 
	 * @param fileList
	 * @return
	 * @throws Exception
	 */
	public static String insertQC(List<String> fileList) throws Exception {
		// ファイル名からDBにインサートするカテゴリ名を抽出する
		StringBuilder sb = new StringBuilder();
		String file = fileList.get(6);
		sb.append("with QC as( INSERT INTO question_categories (name,parent_id) VALUES ");
		String fileFullName = new File(file).getName();
		String fileName = fileFullName.substring(fileFullName.indexOf("（") + 1, fileFullName.indexOf("）"));
		// シングルコートをエスケープ
		String replaceFileName = fileName.replace("'", "''");
		sb.append("('");
		sb.append(replaceFileName);
		sb.append("', ");
		sb.append(" 0 )");
		sb.append("returning name,id ) ");
		sb.append("insert into question_categories(name,parent_id) values ");
		CSVReader csvReader = new CSVReader(new FileReader(file));
		String[] queLine = csvReader.readNext();
		// タイムスタンプ、氏名、入社年月日、職種を避けて、4列目から抽出
		for (int i = 2; i < queLine.length; i++) {
			sb.append("('");
			// シングルコートをエスケープ
			String queName = queLine[i].replace("'", "''");
			sb.append(queName + "' , ");
			sb.append("( select QC.id from QC where QC.name='");
			sb.append(replaceFileName + "')),");
		}
		sb.setLength(sb.length() - 1);
		sb.append(";");
		return sb.toString();
	}

	/**
	 * 社員名をファイルから抽出してインサートする.（2回目以降はインサートしない。）
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
	 * ファイルからquestionsテーブルにインサートする.
	 * 
	 * @param allFile ファイルの配列
	 */
	public static String insertQue(List<String> fileList) throws Exception {
		CSVReader csvReader = null;
		StringBuilder sb = new StringBuilder();
		String file = fileList.get(6);
		sb.append("insert into questions (category_id) ");
		// 同一カテゴリ名の質問カテゴリIDを質問テーブルにインサート
		String fileFullName = new File(file).getName();
		String fileName = fileFullName.substring(fileFullName.indexOf("（") + 1, fileFullName.indexOf("）"));
		sb.append("select id from question_categories as qc where qc.parent_id=");
		sb.append("(select id from question_categories as qc2 where qc2.name='");
		sb.append(fileName + "')");
		sb.append(";");
		  return sb.toString();
	}

	/**
	 * ファイルからanswersテーブルへインサートする.(SQL文長すぎるから従業員単位にしようかしらん)
	 * 
	 * @param fileList
	 * @return
	 * @throws Exception
	 */
	public static List<String> insertAnswer(List<String> fileList) throws Exception {
		String file = fileList.get(6);
		StringBuilder sb = null;
		CSVReader csvReader = new CSVReader(new FileReader(file));
		List<String[]> lineAll = csvReader.readAll();
		String[] queLine=lineAll.get(0);
		lineAll.remove(0);
		List<String>stringList=new ArrayList<>();
		for (String[] line : lineAll) {
			sb=new StringBuilder();
			sb.append("insert into answers (employee_id,question_id,point) values ");
			for(int i=2;i<line.length;i++) {
				String trueName=line[1].replaceAll(" |　","");
				sb.append("((select e.id from employees as e where e.name='");
				sb.append(trueName + "'),");
				sb.append(
						"(select q.id from questions as q join question_categories qc on q.category_id=qc.id where qc.name='");
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
}
