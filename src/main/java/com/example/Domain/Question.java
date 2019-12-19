package com.example.Domain;

/**質問テーブルのドメイン.
 * @author takahiro.araki
 *
 */
public class Question {
	
	/**id */
	private Integer id;
	/**カテゴリー番号*/
	private Integer  categoryId;
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
		return "Question [id=" + id + ", categoryId=" + categoryId + "]";
	}
	
	

}
