package me.ppangya.wiki.backend.repository.category.jdbc;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.repository.category.CategoryRepository;
import me.ppangya.wiki.backend.repository.entity.Category;
import me.ppangya.wiki.test.annotation.JdbcTransactionalTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@JdbcTransactionalTest
public class JdbcCategoryRepositoryImplTest {

	private @Autowired CategoryRepository categoryRepository;

	@Test
	public void saveTest() {
		Category category = new Category(null, "Category Name");
		category = categoryRepository.save(category);
		Assert.assertNotNull(category);
		Assert.assertNotNull(category.getCategoryId());
		Assert.assertNotNull(category.getName());
		log.debug("Insert : {}", category);
	}
}
