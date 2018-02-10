package servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import classe.Personne;

import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/servlet/Maj2")
public class Maj2 extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
	PrintWriter out = res.getWriter();
	res.setContentType("text/html");
	
	/* NE PAS MODIFIER (Sauf indication)*/
	out.println("<!DOCTYPE html><html lang='fr'>");
	out.println("<head><meta charset='utf-8'><meta http-equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content='width=device-width, initial-scale=1'>");
	
		/* Titre de la page HTML */
	out.println("<title>Administration</title>");
		/* **************** */
	
	out.println("<!-- Bootstrap core CSS --><link href='https://maxcdn.bootstrapcdn.com/bootswatch/4.0.0-beta.3/darkly/bootstrap.min.css' rel='stylesheet'>");
	
	out.println("</head>");
	out.println("<body>");
	
	// authentifie ?
	HttpSession session = req.getSession(true);
	Personne p = (Personne)session.getAttribute("login");
	if (p==null) res.sendRedirect("Deconnect");

	out.println("<div class='container'>");
	
		out.println("<div class='page-header'>");
			out.println("<h1>Administration des informations.</h1>");
		out.println("</div>");
		
		out.println("<div class='row'>");
			out.println("<div class='col-xs-12'>");
	
	Connection con=null;
	try {
	    
	    // enregistrement du driver
	    Class.forName("com.mysql.jdbc.Driver");
	    
	    // connexion a la base
	    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/anima","gabrielle","gaby");
	    
	    String login = req.getParameter("login");
	    String mdp = req.getParameter("mdp");
	    String email = req.getParameter("email");
	    String tel = req.getParameter("tel");
	    
	    Statement stmt = con.createStatement();
	    
	    
	    // Verification du login
	    if (!login.equals(p.login)) // il a change son login
		{
		   
		}
	    
	    String query2 = "update users set ";
	    query2 += ("login='"+login+"',");
	    query2 += ("mdp='"+mdp+"',");
	    query2 += ("email='"+email+"',");
	    query2 += ("tel='"+tel+"'");
	    query2 += " where login ='"+login+"'";
	    
	    
	    stmt.executeUpdate(query2);
	    
	    // modif de l'objet : ici on ne peut pas changer le role
	    p.maj(login,mdp,email,tel);
	    
	    out.println("<div class='alert alert-success' role='alert'>"+login + ", vos donnees ont ete mises a jour !</div>");
	    
	    out.println("<a href='Menu'><button type='button' class='btn btn-primary btn-lg'>Retour au menu</button></a>");
	    
	    con.close();
	}
	catch (Exception e) {
		out.println("<div class='alert alert-warning' role='alert'>Erreur "+e.getClass()+" : "+e.getMessage()+"</div>");
	}
	finally
	    {
		try{con.close();} catch (Exception e){}
	    }
    }
}
