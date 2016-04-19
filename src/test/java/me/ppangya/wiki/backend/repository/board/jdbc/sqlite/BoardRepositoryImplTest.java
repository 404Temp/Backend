package me.ppangya.wiki.backend.repository.board.jdbc.sqlite;

import me.ppangya.wiki.backend.repository.board.BoardRepository;
import me.ppangya.wiki.backend.repository.entity.Board;
import me.ppangya.wiki.test.annotation.JdbcTransactionalTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@JdbcTransactionalTest
public class BoardRepositoryImplTest {

	private static final Logger logger = LoggerFactory.getLogger(BoardRepositoryImplTest.class);

	private @Autowired BoardRepository boardRepository;

	@Test
	public void insertBoard() {
		Board board = new Board("Board Title");
		board = boardRepository.insert(board);
		Assert.assertNotNull(board);
		Assert.assertNotNull(board.getBoardId());
		Assert.assertNotNull(board.getTitle());
		logger.debug("Insert : {}", board);
	}
}