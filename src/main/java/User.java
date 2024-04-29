
public class User {
	private String name;
	private String job;
	
	public void setName(String name) 
	{
		this.name = name;
	}

	public void setJob(String job )
	{
		this.job = job;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getJob()
	{
		return this.job;
	}
	
	public static User create()
	{
		User user = new User();
		user.setName("Bruna");
		user.setJob("QA");
		return user;
	}
}
