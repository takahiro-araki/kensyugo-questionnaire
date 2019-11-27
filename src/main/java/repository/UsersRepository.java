package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.domain.Item;

/**
 * ユーザーテーブルのレポジトリ.
 * @author takahiro.araki
 *
 */
public class UsersRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;


	

}
