package servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import classe.Personne;

import javax.servlet.annotation.*;
import java.sql.*;


@WebServlet("/servlet/Select")
public class Select extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	PrintWriter out = res.getWriter();
	res.setContentType("text/html");
	
	/* NE PAS MODIFIER (Sauf indication)*/
	out.println("<!DOCTYPE html><html lang='fr'>");
	out.println("<head><meta charset='utf-8'><meta http-equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content='width=device-width, initial-scale=1'>");
	
		/* Titre de la page HTML */
	out.println("<title>Menu</title>");
		/* **************** */
	
	out.println("<!-- Bootstrap core CSS --><link href='https://maxcdn.bootstrapcdn.com/bootswatch/4.0.0-beta.3/darkly/bootstrap.min.css' rel='stylesheet'>");
	
	out.println("</head>");
	out.println("<body>");
	
	HttpSession session = req.getSession(true);
	Personne p = (Personne)session.getAttribute("login");
	if (p==null) {
		res.sendRedirect("Deconnect");
		return;
	}
	
	out.println("<div class='container'>");
	
		
	
		
	
	String query;
	if (p.role.equals("adm"))
	    {
		out.println("<div class='page-header'>");
			out.println("<h1>Affichage administrateur</h1>");
		out.println("</div>");
		query="select login,role,email,tel from users";
	    }
	else
	    {
		out.println("<div class='page-header'>");
			out.println("<h1>Vos informations</h1>");
		out.println("</div>");
		query="select * from users where login ='"+p.login+"'";
	    }
	
	out.println("<div class='row'>");
		out.println("<div class='col-xs-12'>");
	
	Connection con =null;
	try {
	    
		// enregistrement du driver
		Class.forName("com.mysql.jdbc.Driver");
		
		// connexion a la base
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/anima","root","truc");
	    
	    // execution de la requete
	    Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    
	    //out.println("<pre>"+query+"</pre>");
	    
	    out.println("<table class='table table-bordered table-striped'>");
	    
	    out.print("<thead>");
		out.print("<tr>");
	    out.print("<th>login</th>");
	    if (!p.role.equals("adm"))
	    out.print("<th>mdp</th>");
	    out.print("<th>role</th>");
	    out.print("<th>email</th>");
	    out.print("<th>tel</th>");
	    out.print("<th>modif</th>");
	    out.print("<th>supr</th>");
	    out.println("</tr>");
	    out.print("</thead>");
	    
	    out.print("<tbody>");
	    
	    while(rs.next())
		{
		    out.println("<tr>");

		    out.print("<td>"+rs.getString("login")+"</td>");
		    if (!p.role.equals("adm"))
		    out.print("<td>"+rs.getString("mdp")+"</td>");
		    out.print("<td>"+rs.getString("role")+"</td>");
		    out.print("<td>"+rs.getString("email")+"</td>");
		    out.print("<td>"+rs.getString("tel")+"</td>");
		    out.println("<td><a href=Maj?a="+rs.getString("login")+">Modif</a></td>");
		    out.println("<td><a href=Supr?a="+rs.getString("login")+">Supr</a></td>");
		    out.println("</tr>");
		}
	    
		out.print("</tbody>");
		
	    out.println("</table>");
	    out.println("<a href='Menu'><button type='button' class='btn btn-primary btn-lg'>Retour au menu</button></a>");
	    
	}
	catch (Exception e) {			
		out.println("<div class='alert alert-warning' role='alert'>Erreur "+e.getClass()+" : "+e.getMessage()+"</div>");
	}
	finally
	    {
		try{con.close();} catch (Exception e){}
	    }
			
			out.println("</div>");
		out.println("</div>");
	out.println("</div>");

	out.println("</body></html>");
    }
}
