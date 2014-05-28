package kr.co.tmon.social.batch.dao;

import java.util.List;

import kr.co.tmon.social.batch.vo.AndroidApp;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 정승현 (RASPILLA16@tmon.co.kr)
 * 
 */
@Repository
public class AndroidAppDao {

	@Autowired
	private SqlSession sqlSession;

	public List<AndroidApp> getAppList() {
		return null;
	}
}
