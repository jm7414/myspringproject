package lx.edu.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lx.edu.springboot.dao.AddrBookDAO;
import lx.edu.springboot.vo.AddrBookVO;

@Controller
public class AddrBookController {
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	AddrBookDAO dao;

	@RequestMapping("/insert.do")
	public String insert(AddrBookVO vo) throws Exception {
		// 그럼 이제 뭘 해야하나?								
		// 1.
		System.out.println(vo);
		// 2.
		dao.insertDB(vo);
		return "redirect:addrbook_list.do";
	}
	
	@RequestMapping("/edit.do")
	public ModelAndView edit(@RequestParam("abId") int abId) throws Exception {
		// abId를 받아서 해당 데이터를 조회한 후 수정 폼을 보여줌
		AddrBookVO vo = dao.getDB(abId);
		ModelAndView result = new ModelAndView();
		result.addObject("ab", vo);
		result.setViewName("addrbook_edit_form");
		return result;
	}
	
	@RequestMapping("/update.do")
	public String update(AddrBookVO vo) throws Exception {
		// 실제 수정을 수행하는 메서드
		System.out.println(vo);
		dao.updateDB(vo);
		return "redirect:addrbook_list.do";
	}

	@RequestMapping("addrbook_form.do")
	public String form() {
		return "addrbook_form"; // 여기엔 뭘 리턴해야하나? -> jsp file name을 리턴해주면 됨!
	}


	
	// 이번엔 위의 String 리턴 타입의 list 매서드를 ModelAndVie 리턴 타입을 바꿔보자
	@RequestMapping("addrbook_list.do")
	public String list(HttpSession session, HttpServletRequest req) throws Exception {
//		if(session.getAttribute("userId")==null ) {
//			return "login";
//		}
		List<AddrBookVO> list = dao.getDBList();
		req.setAttribute("data", list);
		return "addrbook_list";
	}
	

}
