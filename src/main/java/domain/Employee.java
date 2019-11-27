package domain;

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
	private Date  joiningDate;
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
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", joiningDate=" + joiningDate + "]";
	}
	
	
}
