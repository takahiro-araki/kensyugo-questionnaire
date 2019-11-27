package domain;

/**職種テーブルのドメイン.
 * @author takahiro.araki
 *
 */
public class JobCategory {
	/**id */
	private Integer id;
	/**職種名*/
	private String name;
	/**社員番号 */
	private Integer employeeId;
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
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	@Override
	public String toString() {
		return "JobCategory [id=" + id + ", name=" + name + ", employeeId=" + employeeId + "]";
	}
	
	
}
