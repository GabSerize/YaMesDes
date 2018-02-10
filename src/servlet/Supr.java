package servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import classe.Personne;

import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/servlet/Supr")
public class Supr extends HttpServlet 
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");

		/* NE PAS MODIFIER (Sauf indication)*/
		out.println("<!DOCTYPE html><html lang='fr'>");
		out.println("<head><meta charset='utf-8'><meta http-equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content='width=device-width, initial-scale=1'>");

		/* Titre de la page HTML */
		out.println("<title>Supr</title>");
		/* **************** */

		out.println("<!-- Bootstrap core CSS --><link href='https://maxcdn.bootstrapcdn.com/bootswatch/4.0.0-beta.3/darkly/bootstrap.min.css' rel='stylesheet'>");

		out.println("</head>");
		out.println("<body>");

		// authentifie ?
		HttpSession session = req.getSession(true);
		Personne p = (Personne)session.getAttribute("login");
		if (p==null) res.sendRedirect("Deconnect");

		String a = req.getParameter("a");
		if (a!=null && p.role.equals("adm") && !p.login.equals(a)) {
			Connection con=null;
			try {

				// enregistrement du driver
				Class.forName("com.mysql.jdbc.Driver");

				// connexion a la base
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/anima","gabrielle","gaby");

				// execution de la requete
				Statement stmt = con.createStatement();
				String query = "delete from users where login='" + a + "'";
				System.out.println(a);
				stmt.executeUpdate(query);

				out.println("<div class='alert alert-success' role='alert'>Le compte "+a+" à bien été supprimer !</div>");
				out.println("<a href='Menu'><button type='button' class='btn btn-primary btn-lg'>Retour au menu</button></a>");
			}
			catch (Exception e) {
				out.println("<div class='alert alert-warning' role='alert'>Erreur "+e.getClass()+" : "+e.getMessage()+"</div>");
			}
			finally
			{
				try{con.close();} catch (Exception e){}
			}

			out.println("</body></html>");

		}else {
			out.println("<div class='alert alert-warning' role='alert'>Vous ne pouvez pas faire cela !</div>");
			out.println("<a href='Menu'><button type='button' class='btn btn-default btn-lg'>Retour au menu</button></a>");
		}

	}
}
