package com.saeyan.dao;

import java.sql.*;
import java.util.*;

import com.saeyan.dto.ProductVO;
import com.saeyan.util.DBManager;

public class ProductDAO {

	private ProductDAO() {}
	
	//싱글톤 방식
	private static ProductDAO instance=new ProductDAO();
	
	public static ProductDAO getInstance() {
		return instance;
	}
	
	//최근 등록한 상품 먼저 출력하는 select문 메소
	public List<ProductVO> selectALLProducts(){
		String sql="select * from product order by code desc"; // 내림차수으로 하기위한 descending 표시
		List<ProductVO> list=new ArrayList<>();
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			con=DBManager.getConnction(); //static 으로 지정하였기 때문에 클래스이름과 메소드로 접근가능
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery(); //select 쿼리에 결과담기
			
			//객체가 여러개므로 while문 사용
			while(rs.next()) {
				ProductVO pvo=new ProductVO();
				pvo.setCode(rs.getInt("code"));
				pvo.setName(rs.getString("name"));
				pvo.setPrice(rs.getInt("price"));
				pvo.setPictureUrl(rs.getString("pictureUrl"));
				pvo.setDescription(rs.getString("description"));
				//한줄에 pvo정보담으며 계속 돌아감
				list.add(pvo);
				
			}
		}catch(Exception e) {
			
		}finally {
			DBManager.close(con, ps, rs); //생성해놓은 DBManager 클래스 활용
		}
		return list;
	}
	
	//상품 등록 메소드 작성
	public void insertProduct(ProductVO vo) {
		String sql="insert into product values(null,?,?,?,?)";
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=DBManager.getConnction();
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setInt(2, vo.getPrice());
			ps.setString(3, vo.getPictureUrl());
			ps.setString(4, vo.getDescription());
			//쿼리가 아닌 업데이트사용
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, ps);
		}
		
		}
	
	// 선택한 상품 검색하는 메소드(수정에 필요함)
	public ProductVO selectProductByCode(String code) {
		String sql="select * from product where code=?";
		ProductVO pVo=null;
		
		try {
			Connection con=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			try {
				con=DBManager.getConnction();
				ps=con.prepareStatement(sql);
				//물음표에 대한 값가져오기
				ps.setString(1, code);
				rs=ps.executeQuery();
				//하나니깐 if문 사용
				if(rs.next()) {
					pVo=new ProductVO();
					pVo.setCode(rs.getInt("code"));
					pVo.setName(rs.getString("name"));
					pVo.setPrice(rs.getInt("price"));
					pVo.setPictureUrl(rs.getString("pictureUrl"));
					pVo.setDescription(rs.getString("description"));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.close(con, ps, rs);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
			return pVo;
		
	}
	
	//상품 업데이트 메소드
	public void updateProduct(ProductVO pVo) {
		String sql="update product set name=?,price=?,pictureUrl=?,description=? where code=?";
		Connection con=null;
		PreparedStatement ps=null;
		
	    try {
	    	con=DBManager.getConnction();
	    	ps=con.prepareStatement(sql);
	    	ps.setString(1, pVo.getName());
	    	ps.setInt(2,pVo.getPrice());
	    	ps.setString(3, pVo.getPictureUrl());
	    	ps.setString(4, pVo.getDescription());
	    	ps.setInt(5, pVo.getCode());
	    	
	    	ps.executeUpdate();
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	DBManager.close(con, ps);
	    }
	}
	//상품 삭제 메소드
	public void deleteProductByCode(int code) {
		String sql="delete from product where code=?";
			Connection con=null;
			PreparedStatement ps=null;
			
			try {
				con=DBManager.getConnction();
		    	ps=con.prepareStatement(sql);
		    	
//		    	ps.setInt(int parameterindex, int x);
//		    	ps.setString(int parameterIndex, String x);
		    	
		    	ps.setInt(1, code);
		    	ps.executeUpdate();
		    	
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.close(con, ps);
			}
		}
	}
