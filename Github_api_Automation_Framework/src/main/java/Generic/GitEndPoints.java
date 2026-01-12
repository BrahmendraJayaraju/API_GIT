package Generic;

public interface GitEndPoints {

	String getRepo = "/repos/{owner}/{repo}";// when ever end points is having parameters  store end points in class directly 
	String postoRepo = "/user/repos"; // which doesnt have parameter that only store in interface 
	String updateRpo = "/repos/{owner}/{repo}";
	String deleteRepo = "/repos/{owner}/{repo}";
}
