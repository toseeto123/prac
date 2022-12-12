package com.saeyan.controller;

import java.io.IOException;
import java.nio.file.FileSystemLoopException;

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
 * Servlet implementation class ProductUpdateServlet
 */
@WebServlet("/productUpdate.do")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=request.getParameter("code");
		//
		
		ProductDAO pDao=ProductDAO.getInstance();
		
		ProductVO pvo=pDao.selectProductByCode(code);
		
		System.out.println(code);
		
		request.setAttribute("product", pvo);
		RequestDispatcher rd=request.getRequestDispatcher("product/productUpdate.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			ServletContext context=getServletContext();
			String path=context.getRealPath("upload");
			String encType="UTF-8";
			int sizeLimit= 10*1024*1024; //10메가
			//다중 업로드시 필수 5총사
			MultipartRequest multi=new MultipartRequest(
					request,
					path,
					sizeLimit,
					encType,
					new DefaultFileRenamePolicy());
			
			String code=multi.getParameter("code");
			String name=multi.getParameter("name");
			int price=Integer.parseInt(multi.getParameter("price"));
			String description=multi.getParameter("description");
			String pictureUrl=multi.getFilesystemName("pictureUrl");
			//넘어온 이미지값이 없는경우
			if(pictureUrl==null) {
				pictureUrl=multi.getParameter("nonmakeImg");
				
			ProductVO pVo= new ProductVO();
			pVo.setCode(Integer.parseInt(code));
			pVo.setName(name);
			pVo.setPictureUrl(pictureUrl);
			pVo.setPrice(price);
			pVo.setDescription(description);
			
			ProductDAO pDao= ProductDAO.getInstance();
			pDao.updateProduct(pVo);
			
			response.sendRedirect("productList.do");//업데이트 완료후 프로덕트리스트로 보냄
			
			
			}
	}

}
