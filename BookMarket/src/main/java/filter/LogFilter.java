package filter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class LogFilter implements Filter { //jakarta.servlet.Filter
   // 웹 서버에 접속하는 요청에 대한 로그를 파일로 저장 하려 한다.
   // 0순위로 처리되는 필터 인터페이스를 사용하는 구현체, implements로 구현함.

   @Override
   public void init(FilterConfig filterConfig) throws ServletException {
      // 필터 시작 시 초기화 시키는 메서드
      System.out.println("BookMarket 필터 초기화");
   }
   
   @Override // 재정의 서블릿에서 제공하는 메서드를 내가 수정해서 사용함.
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
         throws IOException, ServletException {
      // 요청과 응답에 대한 로그 기록
      System.out.println("접속한 클라이언트 IP : "+request.getRemoteAddr());
      long start = System.currentTimeMillis();// 시작시간
      System.out.println("접근한 URL 경로 (custom) : "+ getURLPath(request));
      System.out.println("요청 처리 시작 시간 (custom) :" + getCurrentTime());
      chain.doFilter(request, response); // 필터에서 요청과 응답에 대한 자료를 전송함.
      
      long end = System.currentTimeMillis();//종료시간 저장용
      System.out.println("요청 처리 종료 시간 (custom) : "+getCurrentTime());
      System.out.println("요청 처리 소요 시간 (시작 - 종료) : "+ (end-start)+"ms");
      System.out.println("============================ 필터 종료 =============================");
   }
   
   @Override
      public void destroy() {
         // 필터 종료전에 처리되는 메서드
      System.out.println("BookMarket 필터 종료 중");
      }
   
   
   //로그 시간과 종료 등을 기록하는 메서드 추가 p436

   private String getURLPath(ServletRequest request) {
      
      HttpServletRequest req;
      String currentPath = "";
      String queryString = "";
      
      if(request instanceof HttpServletRequest) {   //같은 객체 인지 비교용
         req =(HttpServletRequest)request;      //강제 타입 변환으로 객체 삽입
         currentPath=req.getRequestURI();      //전달받은 객체를 생성한 객체에 넣음
         queryString = req.getQueryString();      //전달받은 객체를 생성한 객체에 넣음
         queryString = queryString==null ? "" : "?" + queryString;   // 3항 연산자로 비교
         // http://192.168.111.101:8080/books.jsp
         // http://192.168.111.101:8080/books.jsp?id=ISBN1234
      }
   
      return currentPath + queryString;
   }
   
   private String getCurrentTime() {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(System.currentTimeMillis());
      return formatter.format(calendar.getTime());
   }
   
   
}
