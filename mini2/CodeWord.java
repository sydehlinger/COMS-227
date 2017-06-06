package mini2;

public class CodeWord {
	private java.lang.String data;
	
	private MatchingRule rule;
	
	private int unshifted;
	
	private int left = 0;
	
	private int checkLeft;
	
	private int right = 0;
	
	private int checkRight;
	
	public CodeWord(java.lang.String givenData){
		data = givenData;
	}
	
	public CodeWord(java.lang.String givenData, MatchingRule givenRule){
		data = givenData;
		rule = givenRule;
	}

	public int letterCount(char ch){
	    int count = 0;
	    int i = 0;
	    while (i < data.length()){
	      if (ch == data.charAt(i)){
	        count += 1;
	      }
	      i += 1;
	    }
	     return count;
	}
	
	public int maxMatches(java.lang.String target){
		for(int i = 0; i < Math.min(data.length(), target.length()); i += 1){
			unshifted = countMatchesUnshifted(target);
		}
		for(int j = 0; j < Math.min(data.length(), target.length()) + 1; j += 1){
			checkLeft = countMatchesInLeftShift(target, j);
			if(checkLeft > left){
				left = checkLeft;
			}
		}
		for(int k = 0; k < Math.min(data.length(), target.length()) + 1; k += 1){
			checkRight = countMatchesInRightShift(target, k);
			if(checkRight > right){
				right = checkRight;
			}
		}
		return Math.max(Math.max(unshifted, left), right);
	}

	public int countMatchesUnshifted(java.lang.String target){
		int count = 0;
		for(int i = 0; i < Math.min(data.length(), target.length()); i += 1){
			if(rule.matches(data.charAt(i), target.charAt(i))){
				count += 1;
			}
		}
		return count;
	}
	
	public int countMatchesInLeftShift(java.lang.String target, int shiftAmount){
		int count = 0;
		for(int i = 0; i < Math.min(data.length(), target.length() - shiftAmount); i += 1){
			if(rule.matches(data.charAt(i), target.charAt(shiftAmount + i))){
				count += 1;
			}
		}
		return count;
	}
	
	public int countMatchesInRightShift(java.lang.String target, int shiftAmount){
		int count = 0;
		for(int i = 0; i < Math.min(data.length() - shiftAmount, target.length()); i += 1){
			if(rule.matches(data.charAt(shiftAmount + i), target.charAt(i))){
				count += 1;
			}
		}
		return count;
	}
}
