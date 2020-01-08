package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Domain.Question;

/**質問テーブルを操作するレポジトリー.
 * 
 * @author takahiro.araki
 *
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>  {
	
	List<Question> findAll();
	List<Question>findByNameEquals(String name);
	
	List<Question> findByNameContains(String name);
	
	@Query("select id,name,category_id from questions q where q.name = :name ")
	List<Question> findQue(@Param("name") String name);

}
