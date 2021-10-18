//package fr.univlyon1.m1if.m1if03.p1507426.servlets;
//
//import fr.univlyon1.m1if.m1if03.p1507426.classes.Ballot;
//import fr.univlyon1.m1if.m1if03.p1507426.classes.Bulletin;
//import fr.univlyon1.m1if.m1if03.p1507426.classes.Candidat;
//import fr.univlyon1.m1if.m1if03.p1507426.classes.User;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@WebFilter(filterName = "FiltreCacheETag", urlPatterns = "/election/ballot")
//public class FiltreCacheETag extends HttpFilter {
//
//    @Override
//    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException
//    {
////        ServletContext context = req.getSession().getServletContext();
////        @SuppressWarnings("unchecked")
////        Map<String, Ballot> ballots = (Map<String, Ballot>) context.getAttribute("ballots");
////        HttpSession session = req.getSession(true);
////        User user = (User) session.getAttribute("user");
////        Ballot ballot = ballots.get(user.getLogin());
////        Bulletin bulletin = ballot.getBulletin();
////        @SuppressWarnings("unchecked")
////        List<Bulletin> bulletins = (List<Bulletin>) context.getAttribute("bulletins");
//
//
//
//        if(req.getMethod().equals("GET") ) {
//
//            String eTagFromBrowser = req.getHeader("If-None-Match");
//            System.out.println("If-None-Match : " + eTagFromBrowser);
//            if(eTagFromBrowser != null) {
//                System.out.println("If-None-Match : " + eTagFromBrowser);
//                ServletContext context = req.getSession().getServletContext();
//                HttpSession session = req.getSession(true);
//
//
//                String eTagFromServerSalles = getTag(context,session);
//                System.out.println("getEtag : " + eTagFromServerSalles);
//                if (eTagFromServerSalles.equals(eTagFromBrowser)) {
//                    //setting 304 and returning with empty body
//                    res.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
//                    return;
//                }
//
//                res.addHeader("ETag", getTag(context,session));
//                chain.doFilter(req, res);
//
//            }else {
//                chain.doFilter(req, res);
//            }
//
//        }else {
//            chain.doFilter(req, res);
//        }
//
//    }
//
//    private String getSallesETag (ServletContext context,HttpSession session) {
//
//        String salles = "";
//
//    	/*
//    	List<Passage> passages =((GestionPassages) context.getAttribute("passages"))
//    			.getPassagesByUser(((User) session.getAttribute("user")));
//
//
//    	for(Passage p : passages) {
//    		salles += "("+p.getSalle().getNom()+";"+p.getSalle().getSaturee()+"), ";
//    	}
//    	*/
//
//        return salles;
//    }
//    private String getTag(ServletContext context,HttpSession session){
//        @SuppressWarnings("unchecked")
//        Map<String, Ballot> ballots = (Map<String, Ballot>) context.getAttribute("ballots");
//        User user = (User) session.getAttribute("user");
//        Ballot ballot = ballots.get(user.getLogin());
//        Bulletin bulletin =  ballot.getBulletin();
//        Candidat candidat = bulletin.getCandidat();
//        String nomCandiat = candidat.getNom();
//        @SuppressWarnings("unchecked")
//        List<Bulletin> bulletins = (List<Bulletin>) context.getAttribute("bulletins");
//        Integer nombreDeVote = bulletins.size();
////        Map<Ballot, Integer> couple = new HashMap<>();
////        couple.put(ballot, nombreDeVote);
////        String couple = ballot + nombreDeVote;
//        return  nomCandiat + nombreDeVote;
//
//    }
//
//}
