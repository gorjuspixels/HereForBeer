public class userAccount{
	private String username;
	private String type;
	private float credit;
	
	public String getUsername(){
		return this.username;
	}
	public String getType(){
		return this.type;
	}
	public float getCredit(){
		return this.credit;
	}
	public void setUsername(String username){
		this.username = username;
	} 
	public void setType(String type){
		this.type = type;
	}
	public void setCredit(float credit){
		this.credit = credit;
	}
	public userAccount get(String username){
		//Place holder code
		return this;
	}
	public void save(){
	}
		
}
