package domain;

/**回答テーブルのドメイン.
 * @author takahiro.araki
 *
 */
public class Answer {
	/**id */
	private Integer id;
	/**社員番号 */
	private Integer employeeId;
	/**点数 */
	private Integer point;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return "Answer [id=" + id + ", employeeId=" + employeeId + ", point=" + point + "]";
	}
	
	
	
	
}
