//package fr.univlyon1.m1if.m1if03.p1507426.servlets;
//
//import fr.univlyon1.m1if.m1if03.p1507426.classes.User;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Date;
//
//import static javax.ws.rs.HttpMethod.GET;
//import static javax.ws.rs.HttpMethod.POST;
//
////"/election/ballot",
//@WebFilter(filterName = "CacheDateBallot", urlPatterns = { "/election/listBallots", "/election/vote"})
//public class CacheDateBallot extends HttpFilter {
//    @Override
//    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//
//        String[] url = req.getRequestURL().toString().split("/");
//        String u = url[url.length-1];
//        ServletContext context = req.getSession().getServletContext();
//
//        if (req.getMethod().equals(POST) && u.equals("vote")){
//
//            Date date = new Date();
//            long d = date.getTime();
//            context.setAttribute("lmod",d);
//            chain.doFilter(req,res);
//
//
//
//        }else if ( context.getAttribute("lmod") != null && req.getMethod().equals(GET) && u.equals("listBallots")){
//            System.out.println("FiltreCase Get");
//
//            long d = (long) context.getAttribute("lmod");
//            res.setDateHeader("Last-Modified",d);
//            long lastModifiedFromBrowser = req.getDateHeader("If-Modified-Since");
//            System.out.println( "Last-Modified : " + d);
//            System.out.println("If-Modified-Since : " + lastModifiedFromBrowser);
//            if (lastModifiedFromBrowser != -1 || d >=lastModifiedFromBrowser){
//                System.out.println("d<=ifmod");
//                res.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
////                req.getRequestDispatcher("/WEB-INF/components/listBallots.jsp").include(req,res);
//            }
//            else chain.doFilter(req,res);
//
//        } else {
//            System.out.println("Filtre cache else");
//            chain.doFilter(req,res);
//        }
//        //  chain.doFilter(req,res);
//    }
//}
////    @Override
////    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
////        System.out.println("CacheDateBallot 1 ");
////        ServletContext context = req.getSession().getServletContext();
////        String uri = req.getRequestURL().toString().split("/election")[1];
////        if (req.getMethod().equals("POST") && req.getParameter("candidat-choisi") != null && uri.equals("/vote")) {
////            System.out.println("CacheDateBallot POST ");
////            Date date = new Date(System.currentTimeMillis());
////            context.setAttribute("date", date);
////            System.out.println("date :  " + date);
////            chain.doFilter(req, res);
////        }
//////        else chain.doFilter(req, res);
////        if (context.getAttribute("date") != null && req.getMethod().equals("GET") && uri.equals("/listBallots")) {
////            System.out.println("CacheDateBallot GET ");
////            Date mdate = (Date) context.getAttribute("date");
////            System.out.println("mdate : " + mdate);
////            System.out.println("mdata.getTime() : " + mdate.getTime());
////            res.setDateHeader("Last-Modified", mdate.getTime());
////            long IfModifiedSince = req.getDateHeader("If-Modified-Since");
////            System.out.println("If-Modified-Since : " + IfModifiedSince);
////            if (IfModifiedSince != -1 && (IfModifiedSince >= mdate.getTime())) {
////                System.out.println("IfModifiedSince != -1");
////                    System.out.println("HttpServletResponse.SC_NOT_MODIFIED");
////                    res.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
////            }
////        }
////    }
////
////}
