package g;

import java.io.IOException;

public class Main {

	
	
	public static void main(String[] args) throws IOException {
		Engine e = new Engine();

		int choice =-1;
	
		
		while(choice != 0){
			
		e.dipalyMenu();	
		choice = e.InputChoice();
		
		switch (choice) {
		case 1:
			e.printBooks();
			break;
		case 2:
			e.searchBook();
			break;
		case 3:
			e.insertBook();
			break;
		case 4:
			e.deleteBook();
			break;
		case 0:
			System.out.println("종료 합니다.");
			System.exit(0);
			break;
		default:
			System.out.println("다시 입력해 주세요");
			}
		}

	}


}
