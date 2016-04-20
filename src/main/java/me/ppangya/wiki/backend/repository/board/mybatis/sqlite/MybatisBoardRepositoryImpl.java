package me.ppangya.wiki.backend.repository.board.mybatis.sqlite;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.repository.board.BoardRepository;
import me.ppangya.wiki.backend.repository.entity.Board;
import me.ppangya.wiki.framework.annotation.MybatisRepository;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@MybatisRepository
@Slf4j
public class MybatisBoardRepositoryImpl implements BoardRepository {

	private @Autowired SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Optional<Board> findOne(Long id) {
		return null;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	public <S extends Board> Board save(S board) {
		log.debug("Save Parameter : {}", board.toString());
		sqlSessionTemplate.insert("board.insertBoard", board);
		return board;
	}
}

