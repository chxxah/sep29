package com.peazh.board;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

// Inject 사용해보기 (연결방법 3가지 있음)

@Repository("boardDAO")
public class BoardDAO {
	
	@Inject
	@Named("sqlSession")
	private SqlSession sqlSession;
	
	public List<Map<String, Object>> boardList(){
		return sqlSession.selectList("board.boardList");
		
	}

	public BoardDTO detail(int bno) {
		return sqlSession.selectOne("board.detail", bno);// 네임스페이스.id, 값
	}

	public void write(BoardDTO dto) {
		sqlSession.insert("board.write", dto);// 네임스페이스.id, 값
	}

	public void delete(BoardDTO dto) {
		sqlSession.delete("board.delete", dto);
	}

	public void edit(BoardDTO dto) {
		sqlSession.update("board.edit", dto);
	}
}
