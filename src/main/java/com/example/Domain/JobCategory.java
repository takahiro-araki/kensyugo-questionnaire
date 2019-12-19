package com.example.Domain;

/**職種テーブルのドメイン.
 * @author takahiro.araki
 *
 */
public class JobCategory {
	/**id */
	private Integer id;
	/**職種名*/
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
		return "JobCategory [id=" + id + ", name=" + name + "]";
	}
	
	
}
