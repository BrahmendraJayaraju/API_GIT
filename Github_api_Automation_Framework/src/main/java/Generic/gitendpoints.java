package Generic;

public interface gitendpoints {

	String getRepo = "/repos/{owner}/{repo}";
	String postoRepo = "/user/repos";
	String updateRpo = "/repos/{owner}/{repo}";
	String deleteRepo = "/repos/{owner}/{repo}";
}
