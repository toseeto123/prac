<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productUpdate page</title>
<link rel="stylesheet" type="text/css" href="css/book.css">
<script type="text/javascript" src="script/product.js"></script>
</head>
<body>
<div id="wrap" align="center"> 
 <h1>상품수정 - 관리자 페이지</h1>
 <form method="post" enctype="multipart/form-data" name="frm">
  <input type="hidden" name=" code " value="${product.code }">
  <input type="hidden" name="nonmakeImg" value="${product.pictureUrl}">
  
  <table>
   <tr></tr>
    <td>
     <c:choose>
      <c:when test="${empty product.pictureUrl}">
       <img src="upload/noimage.gif">
      </c:when>
      <c:otherwise>
       <img src="upload/${product.pictureUrl}">
      </c:otherwise>
     </c:choose>
    </td>
    <td>
     <table>
      <tr>
       <th style="width:80px" >상품명</th>
       <td> <input type="text" name="name" value="${product.name }" size="80">
      </tr>
      
      <tr>
       <th>가격</th>
       <td><input type="text" name="price" value="${product.price }"></td>
      </tr>
      
      <tr>
       <th>사 진</th>
       <td><input type="file" name="pictureUrl" value="${product.pictureUrl}"><br>
       (주의 사항 : 이미지를 변경하고자 때만 선택하시오.)
       </td>
      </tr>
      
      <tr>
       <th>설 명</th>
       <td><textarea name="description" rows="10" column="80" style="width:600px"></textarea><br>
       </td>
      </tr>
      
     </table>
    </td>
  </table><br>
   <input type="submit" value="수정" onclick="return productCheck()">
   <input type="reset" value="다시작성">
   <input type="button" value="목록" onclick="location.href='productList.do'">
 </form>
</div>
</body>
</html>