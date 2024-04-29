
public class UserProfile {
	private String name;
	private String job;
	private int age;
	
	public void setName(String name) 
	{
		this.name = name;
	}

	public void setJob(String job )
	{
		this.job = job;
	}
	
	public void setAge(int age) {
        this.age = age;
    }
	
	public String getName()
	{
		return this.name;
	}
	
	public String getJob()
	{
		return this.job;
	}
	
	public int getAge() {
        return age;
    }
	
	public static UserProfile create()
	{
		UserProfile user = new UserProfile();
		user.setName("Bruna");
		user.setJob("QA");
		user.setAge(28);
		return user;
	}
}
