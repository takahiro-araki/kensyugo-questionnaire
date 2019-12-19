package com.example.Domain;

import java.util.Date;


/**社員テーブルのドメイン
 * @author takahiro.araki
 *
 */
public class Employee {
	/**社員番号*/
	private Integer id;
	/**名前 */
	private String name;
	/**入社年月日*/
	private Date  joinDate;
	
	/**ジョブカテゴリーID */
	private Integer jobCategoryId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Integer getJobCategoryId() {
		return jobCategoryId;
	}

	public void setJobCategoryId(Integer jobCategoryId) {
		this.jobCategoryId = jobCategoryId;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", joinDate=" + joinDate + ", jobCategoryId=" + jobCategoryId
				+ "]";
	}
	
}
