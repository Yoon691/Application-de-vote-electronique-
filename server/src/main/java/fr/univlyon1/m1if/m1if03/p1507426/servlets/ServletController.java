package fr.univlyon1.m1if.m1if03.p1507426.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "ServletController", urlPatterns = {"/election/resultat", "/election/user", "/election/vote","/election/listBallots"})
public class ServletController extends HttpServlet {
}
