package domain;

/**質問テーブルのドメイン.
 * @author takahiro.araki
 *
 */
public class Question {
	
	/**id */
	private Integer id;
	/**質問文 */
	private String content;
	/**カテゴリー番号*/
	private Integer  categoryId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", content=" + content + ", categoryId=" + categoryId + "]";
	}
	
	
	

}
