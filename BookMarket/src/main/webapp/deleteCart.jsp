<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Book"%>
<%@ page import="dao.BookRepository"%>
<%
	String id = request.getParameter("cartId");
	if (id == null || id.trim().equals("")) {
		response.sendRedirect("cart.jsp");
		return;
	}

	session.invalidate(); // 세션 정보를 전체 삭제
	response.sendRedirect("cart.jsp");
%>

    