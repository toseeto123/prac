package com.saeyan.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.saeyan.dao.ProductDAO;
import com.saeyan.dto.ProductVO;

/**
 * Servlet implementation class ProductWriteServlet
 */
@WebServlet("/productWrite.do")
public class ProductWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("product/productWrite.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//
		ServletContext context=getServletContext();
		//
		String path=context.getRealPath("upload");
		String encType="utf-8";
		int maxSize=10*1024*1024; //10메가바트
		//필수 5총사 선언
		MultipartRequest multi=new MultipartRequest(
				request,
				path,
				maxSize,
				encType,
				new DefaultFileRenamePolicy());
		//multipart값 가져옴
		String name=multi.getParameter("name");
		String description=multi.getParameter("decription");
		int price=Integer.parseInt(multi.getParameter("price"));
//		
		String pictureUrl=multi.getFilesystemName("pictureUrl");
		//VO객체 불러오기
		ProductVO pVo=new ProductVO();
		pVo.setName(name);
		pVo.setPrice(price);
		pVo.setDescription(description);
		pVo.setPictureUrl(pictureUrl);
		//DAO 객체 생성
		ProductDAO pDao=ProductDAO.getInstance();
		pDao.insertProduct(pVo);
		response.sendRedirect("productList.do");
	}

}
