package com.pil.controller;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pil.domain.BoardVO;
import com.pil.domain.SearchCriteria;
import com.pil.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class BoardDAOTest {

	@Inject
	private BoardDAO dao;

	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

//	@Test
//	public void testCreate() throws Exception {
//		BoardVO board = new BoardVO();
//		board.setTitle("새로운글");
//		board.setContent("새로운내용");
//		board.setWriter("user00");
//		dao.create(board);
//	}
//
//	@Test
//	public void testRead() throws Exception {
//		logger.info(dao.read(3).toString());
//	}
//
//	@Test
//	public void testUpdate() throws Exception {
//		BoardVO board = new BoardVO();
//		board.setNo(3);
//		board.setTitle("수정된글제목");
//		board.setContent("수정된글내용");
//		board.setWriter("user00");
//		dao.update(board);
//	}
//
//	@Test
//	public void tsetDelete() throws Exception {
//		dao.delete(2);
//	}
//
//	@Test
//	public void testListPage() throws Exception {
//		int page = 3;
//		List<BoardVO> list = dao.listPage(page);
//		for(BoardVO boardVO : list) {
//			logger.info(boardVO.getNo()+":"+boardVO.getTitle());
//		}
//	}
//	
//	@Test
//	public void testListCriteria() throws Exception {
//		Criteria cri = new Criteria();
//		cri.setPage(1);
//		cri.setPerPageNum(100);
//
//		List<BoardVO> list = dao.listCriteria(cri);
//		for(BoardVO boardVO : list) {
//			logger.info(boardVO.getNo()+":"+boardVO.getTitle());
//		}
//	}
//
//	@Test
//	public void testURI() throws Exception {
//		UriComponents uriComponents = UriComponentsBuilder.newInstance()
//											.path("/board/read")
//											.queryParam("no", 12)
//											.queryParam("perPageNum", 20)
//											.build();
//		UriComponents uriComponents2 = UriComponentsBuilder.newInstance()
//											.path("/{module}/{page}")
//											.queryParam("no", 12)
//											.queryParam("perPageNum", 20)
//											.build()
//											.expand("board", "read")
//											.encode();
//		logger.info(uriComponents.toString());
//		logger.info(uriComponents2.toString());
//	}

	@Test
	public void testDynamic() throws Exception {
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(1);
		cri.setKeyword("한글");
		cri.setSearchType("t");
		logger.info("-----------------------------------");

		List<BoardVO> list = dao.listSearch(cri);
		for(BoardVO boardVO : list) {
			logger.info(boardVO.getNo() + ":"+boardVO.getTitle());
		}
		logger.info("======== Count: "+dao.listSearchCount(cri) +"========");
	}

}
