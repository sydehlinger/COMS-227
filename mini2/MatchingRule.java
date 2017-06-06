package mini2;

public class MatchingRule {
	private java.lang.String sourceChars;
	
	private java.lang.String targetChars;
	
	public MatchingRule(){
		sourceChars = "abcdefghijklmnopqrstuvwxyz";
		targetChars = "abcdefghijklmnopqrstuvwxyz";
	}
	
	public MatchingRule(java.lang.String givenSourceChars, java.lang.String givenTargetChars){
		sourceChars = givenSourceChars;
		targetChars = givenTargetChars;
	}
	
	public boolean isValidSource(java.lang.String s){
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		for(int i = 0; i < s.length(); i += 1){
			char ch = s.charAt(i);
			if(alphabet.indexOf(ch) == -1){
				return false;
			}
		}
		return true;
	}
	
	public boolean matches(char sourceChar, char targetChar){
		if(sourceChars.length() > targetChars.length()){
			if(sourceChars.indexOf(sourceChar) > targetChars.indexOf(targetChar)){
		    	return true; 
		     }
		}
		for(int i = 0; i < sourceChars.length(); i += 1){
	    	if(sourceChars.charAt(i) == sourceChar){
	    		for(int j = 0; j < targetChars.length(); j += 1){
	    			if(targetChars.charAt(j) == targetChar){
	    				if(i == j){
	    					return true;
	    				}
	    			}
	    		}
	    	}
	    }
	    return false;
	}
}

