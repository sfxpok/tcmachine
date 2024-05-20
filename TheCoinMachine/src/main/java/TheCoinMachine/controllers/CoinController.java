package TheCoinMachine.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import TheCoinMachine.domain.Transaction;
import TheCoinMachine.domain.Result;

@RestController
public class CoinController {

	@PostMapping("/coins")
	public Result calculateCoins(@RequestBody Transaction transaction) {
	    int rightPersonInitial = 3;
	    int leftPersonInitial = 3;
	    
	    for (int i = 0; i < transaction.getRightPerson().size(); i++) {
	        String moveRight = transaction.getRightPerson().get(i);
	        String moveLeft = transaction.getLeftPerson().get(i);

	        if ("P".equals(moveRight) && "P".equals(moveLeft)) {
	            rightPersonInitial += 2;
	            leftPersonInitial += 2;
	            
	        } else if ("P".equals(moveRight) && "R".equals(moveLeft)) {
	        	rightPersonInitial--;
	        	leftPersonInitial += 3;
	        	
	        } else if ("R".equals(moveRight) && "P".equals(moveLeft)) {
	            rightPersonInitial += 3;
	            leftPersonInitial--;
	            
	        } else if ("R".equals(moveRight) && "R".equals(moveLeft)) {
	            continue;
	        }
	    }

	    return new Result(rightPersonInitial, leftPersonInitial);
	}
}
