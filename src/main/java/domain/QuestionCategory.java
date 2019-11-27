package domain;

/**質問カテゴリーテーブルのドメイン.
 * @author takahiro.araki
 *
 */
public class QuestionCategory {
	/**id */
	private Integer id;
	/**名前 */
	private String name;
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
	@Override
	public String toString() {
		return "QuestionCategory [id=" + id + ", name=" + name + "]";
	}
	
	
	

}
