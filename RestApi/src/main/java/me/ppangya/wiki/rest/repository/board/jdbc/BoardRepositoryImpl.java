package me.ppangya.wiki.rest.repository.board.jdbc;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.rest.exception.ResourceNotFoundException;
import me.ppangya.wiki.rest.repository.board.BoardRepository;
import me.ppangya.wiki.rest.repository.entity.Board;
import me.ppangya.wiki.framework.annotation.OrmConditional;
import me.ppangya.wiki.framework.constant.SystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@Repository
@OrmConditional(values = SystemProperties.ObjectRelationalMapping.JDBC)
public class BoardRepositoryImpl implements BoardRepository {

	private @Autowired JdbcTemplate jdbcTemplate;

	private static final String INSERT_SQL = "INSERT INTO BOARD (title) VALUES (?)";
	private static final String UPDATE_SQL = "UPDATE BOARD SET title = ? WHERE board_id= ?";
	private static final String DELETE_SQL = "DELETE FROM BOARD WHERE board_id = ?";
	private static final String FIND_ONE_SQL = "SELECT board_id AS boardId, title FROM BOARD WHERE board_id = ?";

	@Override
	public <S extends Board> Board save(S board) {
		Long boardId = board.getBoardId();
		if (boardId == null) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[]{"board_id"});
				ps.setString(1, board.getTitle());
				return ps;
			}, keyHolder);
			return new Board(keyHolder.getKey().longValue(), board.getTitle());
		} else {
			Optional<Board> boardOptional = findOne(boardId);
			Board findBoard = boardOptional.orElseThrow(() -> new ResourceNotFoundException("boardId={}", boardId));
			findBoard.setTitle(board.getTitle());
			jdbcTemplate.update(UPDATE_SQL, findBoard.getTitle(), findBoard.getBoardId());
			return findBoard;
		}
	}

	@Override
	public void delete(Board board) {
		Optional<Board> boardOptional = findOne(board.getBoardId());
		boardOptional.ifPresent(findBoard -> jdbcTemplate.update(DELETE_SQL, board.getBoardId()));
	}

	@Override
	public Optional<Board> findOne(Long boardId) {
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_ONE_SQL, new Object[]{boardId}, new BeanPropertyRowMapper<>(Board.class)));
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("boardId={}", boardId);
		}
	}

	@Override
	public Stream<Board> findListByTitleOrderByBoardIdDesc(String title) {
		return null;
	}

	@Override
	public Optional<Board> findOneByBoardIdOrderByBoardIdAsc(Long boardId) {
		return null;
	}
}
