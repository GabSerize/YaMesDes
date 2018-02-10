package servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;


@WebServlet("/servlet/New")
public class New extends HttpServlet 
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
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

		out.println("<div class='container'>");

		out.println("<div class='page-header'>");
		out.println("<h1>Inscription</h1>");
		out.println("</div>");

		out.println("<div class='row'>");
		out.println("<div class='col-xs-12'>");

		out.println("<form class='form-horizontal' method='get' action='New2'>");
		out.println("<div class='form-group'>");
		out.println("<label for='inputLogin' class='col-sm-20 control-label'>Login</label>");
		out.println("<div class='col-sm-30'>");
		out.println("<input type='text' class='form-control' id='inputLogin' name='login'>");
		out.println("</div>");
		out.println("</div>");

		out.println("<div class='form-group'>");
		out.println("<label for='inputPassword' class='col-sm-20 control-label'>Mot de passe</label>");
		out.println("<div class='col-sm-30'>");
		out.println("<input type='text' class='form-control' id='inputPassword' name='mdp'>");
		out.println("</div>");
		out.println("</div>");

		out.println("<div class='form-group'>");
		out.println("<label for='inputEmail' class='col-sm-20 control-label'>Email (facultatif)</label>");
		out.println("<div class='col-sm-30'>");
		out.println("<input type='text' class='form-control' id='inputEmail' name='email'>");
		out.println("</div>");
		out.println("</div>");

		out.println("<div class='form-group'>");
		out.println("<label for='inputTel' class='col-sm-20 control-label'>Tel (facultatif)</label>");
		out.println("<div class='col-sm-30'>");
		out.println("<input type='text' class='form-control' id='inputTel' name='tel'>");
		out.println("</div>");
		out.println("</div>");

		out.println("<div class='form-group'>");
		out.println("<div class='col-sm-offset-2 col-sm-30'>");
		out.println("<button type='submit' class='btn btn-success btn-block'>Ajouter</button>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form>");
		out.println("<a href='../login.html' class=\"btn btn-default btn-block\">Connexion !</a>");

		out.println("</div>");
		out.println("</div>");
		out.println("</div>");

		out.println("</body></html>");
	}
}
