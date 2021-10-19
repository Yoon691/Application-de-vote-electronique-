package fr.univlyon1.m1if.m1if03.p1507426.servlets;

import fr.univlyon1.m1if.m1if03.p1507426.classes.Ballot;
import fr.univlyon1.m1if.m1if03.p1507426.classes.Bulletin;
import fr.univlyon1.m1if.m1if03.p1507426.classes.Candidat;
import fr.univlyon1.m1if.m1if03.p1507426.classes.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebFilter(filterName = "FiltreCacheETag", urlPatterns = "/election/ballot")
public class FiltreCacheETag extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        if (req.getMethod().equals("GET")) {

            String eTagFromBrowser = req.getHeader("If-None-Match");
            ServletContext context = req.getSession().getServletContext();
            HttpSession session = req.getSession(true);
            String eTagFromServer = getTag(context, session);
            System.out.println("getTag(context, session) : " + eTagFromServer);
            System.out.println("If-None-Match : " + eTagFromBrowser);
            if (eTagFromBrowser != null && eTagFromBrowser.equals(eTagFromServer)) {
                System.out.println("If-None-Match : " + eTagFromBrowser);

                res.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                //return;
            }

            res.addHeader("ETag", getTag(context, session));
            chain.doFilter(req, res);


        } else {
            chain.doFilter(req, res);
        }
    }

    private String getTag(ServletContext context,HttpSession session){
        @SuppressWarnings("unchecked")
        Map<String, Ballot> ballots = (Map<String, Ballot>) context.getAttribute("ballots");
        User user = (User) session.getAttribute("user");
        Ballot ballot = ballots.get(user.getLogin());
        if (ballot != null) {
            Bulletin bulletin = ballot.getBulletin();
            Candidat candidat = bulletin.getCandidat();
            String nomCandiat = candidat.getNom();
            @SuppressWarnings("unchecked")
            List<Bulletin> bulletins = (List<Bulletin>) context.getAttribute("bulletins");
            Integer nombreDeVote = bulletins.size();

            return nomCandiat + nombreDeVote;
        }else return null;
    }

}
