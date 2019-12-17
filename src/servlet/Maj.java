package servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import classe.Personne;

import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/servlet/Maj")
public class Maj extends HttpServlet 
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
		if (p==null) { res.sendRedirect("Deconnect"); return;}
		
		String a = req.getParameter("a");
		if (a!=null && p.role.equals("adm")) {
			Connection con=null;
			try {

				// enregistrement du driver
				Class.forName("com.mysql.jdbc.Driver");

				// connexion a la base
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/anima","root","truc");

				// execution de la requete
				Statement stmt = con.createStatement();
				String query = "select * from users where login='" + a + "'";
				ResultSet rs = stmt.executeQuery(query);
				rs.next();
				p = new Personne(rs.getString("login"),rs.getString("mdp"),rs.getString("role"),rs.getString("email"),rs.getString("tel"));
			}
			catch (Exception e) {
				out.println("<div class='alert alert-warning' role='alert'>Erreur "+e.getClass()+" : "+e.getMessage()+"</div>");
			}
			finally
			{
				try{con.close();} catch (Exception e){}
			}
		}

		out.println("<div class='container'>");

		out.println("<div class='page-header'>");
		out.println("<h1>Modifier vos informations</h1>");
		out.println("</div>");

		out.println("<div class='row'>");
		out.println("<div class='col-xs-12'>");

		out.println("<form class='form-horizontal' method='get' action='Maj2'>");
		out.println("<div class='form-group'>");
		out.println("<label for='inputLogin' class='col-sm-20 control-label'>Login</label>");
		out.println("<div class='col-sm-30'>");
		out.println("<input type='text' class='form-control' id='inputLogin' name='login' value='"+p.login+"' disabled >");
		out.println("<input type='text' class='form-control' id='inputLogin' name='login' value='"+p.login+"' hidden>");
		out.println("</div>");
		out.println("</div>");

		out.println("<div class='form-group'>");
		out.println("<label for='inputPassword' class='col-sm-20 control-label'>Mot de passe</label>");
		out.println("<div class='col-sm-30'>");
		out.println("<input type='text' class='form-control' id='inputPassword' name='mdp' value='"+p.mdp+"'>");
		out.println("</div>");
		out.println("</div>");

		out.println("<div class='form-group'>");
		out.println("<label for='inputEmail' class='col-sm-20 control-label'>Email (facultatif)</label>");
		out.println("<div class='col-sm-30'>");
		out.println("<input type='text' class='form-control' id='inputEmail' name='email' value='"+p.email+"'>");
		out.println("</div>");
		out.println("</div>");

		out.println("<div class='form-group'>");
		out.println("<label for='inputTel' class='col-sm-20 control-label'>Tel (facultatif)</label>");
		out.println("<div class='col-sm-30'>");
		out.println("<input type='text' class='form-control' id='inputTel' name='tel' value='"+p.tel+"'>");
		out.println("</div>");
		out.println("</div>");

		out.println("<div class='form-group'>");
		out.println("<div class='col-sm-offset-2 col-sm-30'>");
		out.println("<button type='submit' class='btn btn-success btn-block'>Mettre à jour</button>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form>");
		out.println("<a href='Menu' class=\"btn btn-default btn-block\">Retour au menu</a>");

		out.println("</div>");
		out.println("</div>");
		out.println("</div>");

		out.println("</body></html>");

	}

}
