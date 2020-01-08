package com.example.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**質問テーブルのドメイン.
 * @author takahiro.araki
 *
 */
@Entity
@Table(name="questions")
public class Question {
	
	/**id */
	@Id
	private Integer id;
	/**カテゴリー番号*/
	@Column(name="category_id")
	private Integer  categoryId;
	
	/**名前 */
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", categoryId=" + categoryId + ", name=" + name + "]";
	}
	public Question(Integer id, Integer categoryId, String name) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
	}
	
	
	

	

}
