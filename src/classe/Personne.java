package classe;
public class Personne
{
    public String login;
    public String mdp;
    public String role;
    public String email;
    public String tel;

    public Personne(String login, String mdp, String role,String email,String tel)
    {
	maj(login,mdp,role,email,tel);
    }

    public void maj(String login, String mdp,String role,String email,String tel)
    {
	this.login = login;
	this.mdp = mdp;
	this.role = role;
	this.email=email;
	this.tel=tel;
    }

    public void maj(String login, String mdp,String email,String tel)
    {
	this.login = login;
	this.mdp = mdp;
	// On n'autorise pas a changer le role
	this.email=email;
	this.tel=tel;
    }

    
}
