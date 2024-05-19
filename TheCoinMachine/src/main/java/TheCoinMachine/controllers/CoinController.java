package TheCoinMachine.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Map;

@RestController
public class CoinController {

	@PostMapping("/coins")
	public Map<String, Integer> calculateCoins(@RequestBody Map<String, List<String>> actions) {
	    int rightPerson = 3;
	    int leftPerson = 3;

	    List<String> rightActions = actions.get("rightPerson");
	    List<String> leftActions = actions.get("leftPerson");

	    for (int i = 0; i < rightActions.size(); i++) {
	        String rightAction = rightActions.get(i);
	        String leftAction = leftActions.get(i);

	        if ("P".equals(rightAction) && "R".equals(leftAction)) {
	            rightPerson--;
	            leftPerson += 3;
	        }
	    
	        else if ("R".equals(rightAction) && "P".equals(leftAction)) {
	            rightPerson += 3;
	            leftPerson--;
	        }
	        
	        else if ("P".equals(rightAction) && "P".equals(leftAction)) {
	            rightPerson -= 1;
	            leftPerson -= 1;
	            rightPerson += 3;
	            leftPerson += 3;
	        }
	        // TODO: people didn't set to both receive
	        
	        // transaction, result classes
	    }

	    return Map.of("rightPerson", rightPerson, "leftPerson", leftPerson);
	}
}
