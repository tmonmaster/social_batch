package kr.co.tmon.social.batch.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author 강이경
 * 
 *         키워드 정보 출력 테스트
 */
public class KeywordDaoTest {

	@Test
	public void testGetKeywordList() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		KeywordDao keywordDao = (KeywordDao) context.getBean("keywordDao");
		System.out.println(keywordDao.getKeywordList());
	}
}
