package fr.univlyon1.m1if.m1if03.p1507426.servlets;

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
import java.util.Date;

import static javax.ws.rs.HttpMethod.GET;
import static javax.ws.rs.HttpMethod.POST;

//"/election/ballot",
@WebFilter(filterName = "CacheDateBallot", urlPatterns = { "/election/listBallots", "/election/vote"})
public class CacheDateBallot extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

       ServletContext context = req.getSession().getServletContext();
       String uri = req.getRequestURL().toString().split("/election")[1];

        if (req.getMethod().equals("POST") && uri.equals("/vote")){
            Date date = new Date(System.currentTimeMillis() + 1);
            long dateTime = date.getTime();
            context.setAttribute("dateTime",dateTime);
            System.out.println("date :  " + date);
            chain.doFilter(req,res);

        }else if ( context.getAttribute("dateTime") != null && req.getMethod().equals("GET") && uri.equals("/listBallots")){
            long dateTime = (long) context.getAttribute("dateTime");
            res.setDateHeader("Last-Modified", dateTime);
            long lastModifiedFromBrowser = req.getDateHeader("If-Modified-Since");
            System.out.println( "Last-Modified : " + dateTime);
            System.out.println("If-Modified-Since : " + lastModifiedFromBrowser);
            //difference ici à cause la conversion de la date en long
            // j'ai remarqué en analysant les logs que If-Modified-Since
            // et Last-Modified sont égaux en date s’il n'y a pas de changement,
            // mais lorsque la conversion en long  il y a un changement dans
            // les 3 derniers bit faible donc j'ai créé cette variable difference qui calcul
            // la différence en long entre les deux et la comparer avec 1000
            // si elle inferieur a 1000 donc il n'y avait pas de changement on donne 304 sinon
            // il y a de changement donc on génère une nouvelle page.
            long difference = dateTime - lastModifiedFromBrowser;
            System.out.println("difference : " + difference);
            if (lastModifiedFromBrowser != -1 && difference < 1000 ){
                    res.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            }
            else chain.doFilter(req,res);

        } else {
            chain.doFilter(req,res);
        }

    }
}

