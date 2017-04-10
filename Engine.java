package g;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Engine {
	Scanner scan = new Scanner(System.in);
	String filepath = "c:\\test\\book.txt";
	BufferedReader br;
	BufferedWriter bw;

	public void dipalyMenu() {
		System.out.println("");
		System.out.println("도서관리 프로그램 입니다.");
		System.out.println("1. 전체목록 출력");
		System.out.println("2. 도서 검색");
		System.out.println("3. 신규 도서 추가");
		System.out.println("4. 노후 도서 제거");
		System.out.println("0. 프로그램 종료");
		System.out.println("");
	}

	public int InputChoice() {
		int choice = -1;
		System.out.println("메뉴를 선택하세요 : ");
		choice = scan.nextInt();
		scan.nextLine();
		
		System.out.println();
		return choice;
	}

	public void deleteBook() throws IOException {
		
		String tmpFilepath = filepath + ".tmp";
		int count = 1;
		
		System.out.println("삭제할 책 번호를 입력해 주세요 : ");
		int deleteLineNumber = scan.nextInt();
		System.out.println("책 번호 : " + deleteLineNumber);
		
		br = new BufferedReader(new FileReader(filepath));
		bw = new BufferedWriter(new FileWriter(tmpFilepath));
		String str = "";
		
		while((str = br.readLine()) != null){
			
			if(count != deleteLineNumber){
			bw.write(str);
			bw.write("\r\n");
			}
			count++;
		}
		bw.close();
		br.close();
		
		FileInputStream fis = new FileInputStream(tmpFilepath);
		FileOutputStream fos = new FileOutputStream(filepath);
		
		int data = 0;
		while ((data = fis.read()) != -1){
			fos.write(data);
			
		}
		fis.close();
		fos.close();
		
		File f = new File(tmpFilepath);
		f.deleteOnExit();
	}

	public void insertBook() throws IOException {

		Book b = new Book();
		BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));

		System.out.println("추가할 책의 정보를 입력해주세요 : ");
	
		
		System.out.print("책 이름 : ");
		b.setName(scan.nextLine());
		
		System.out.print("저 자: ");
		b.setAuthor(scan.nextLine());
		
		System.out.print("출판사 : ");
		b.setPublisher(scan.nextLine());
		
		System.out.print("가 격 :");
		b.setCost(scan.nextLine());

		System.out.println(b.toString());
		bw.write(b.getName() + "\t" + b.getAuthor() + "\t" + b.getPublisher() + "\t" + b.getCost());
		bw.newLine();
		bw.close();
	}

	public void searchBook() throws FileNotFoundException {
		System.out.println("검색할 키워드를 입력하세요 :");
		
		String keyword = scan.nextLine();

		String str = "";
		br = new BufferedReader(new FileReader(filepath));
			try {
				while ((str = br.readLine()) != null) {
					System.out.println(str);
					
					if(str.contains(keyword)){
						System.out.println(str);
					}
				br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void printBooks() throws IOException {
		int count = 1;
		br = new BufferedReader(new FileReader(filepath));
		String str = "";
		
		try {

			while ((str = br.readLine()) != null) {
				System.out.println("(" + count + ")" + str);
				count ++;
			}
			br.close();
			
		} catch (IOException e) {
			System.out.println("책정보를읽어올수없습니다.");
			System.out.println("저장된 파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}
	}
}
