package com.myshop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myshop.dto.NoticeDTO;

@Repository
public class NoticeDAOImpl implements NoticeDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<NoticeDTO> noticeList() throws Exception {
		return sqlSession.selectList("notice.noticeList");
	}

	@Override
	public NoticeDTO noticeDetail(int no) throws Exception {
		return sqlSession.selectOne("notice.noticeDetail", no);
	}

	@Override
	public void noticeInsert(NoticeDTO dto) throws Exception {
		sqlSession.insert("notice.noticeInsert", dto);
	}

	@Override
	public void noticeDelete(int no) throws Exception {
		sqlSession.delete("notice.noticeDelete", no);
	}

	@Override
	public void noticeEdit(NoticeDTO dto) throws Exception {
		sqlSession.update("notice.noticeEdit", dto);
	}
	
	@Override
	public void visitCount(int no) {
		sqlSession.update("notice.countUp", no);
	}
}
