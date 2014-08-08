/**This program gets information about a steam user and displays it in [close to] real time
@author Robert Webb*/
public class steamDriver 
{

	public static void main(String[] args) throws Exception
	{
		steamController cont = new steamController();
		steamView view = new steamView(cont);
	}
}


