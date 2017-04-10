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
		System.out.println("�������� ���α׷� �Դϴ�.");
		System.out.println("1. ��ü��� ���");
		System.out.println("2. ���� �˻�");
		System.out.println("3. �ű� ���� �߰�");
		System.out.println("4. ���� ���� ����");
		System.out.println("0. ���α׷� ����");
		System.out.println("");
	}

	public int InputChoice() {
		int choice = -1;
		System.out.println("�޴��� �����ϼ��� : ");
		choice = scan.nextInt();
		scan.nextLine();
		
		System.out.println();
		return choice;
	}

	public void deleteBook() throws IOException {
		
		String tmpFilepath = filepath + ".tmp";
		int count = 1;
		
		System.out.println("������ å ��ȣ�� �Է��� �ּ��� : ");
		int deleteLineNumber = scan.nextInt();
		System.out.println("å ��ȣ : " + deleteLineNumber);
		
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

		System.out.println("�߰��� å�� ������ �Է����ּ��� : ");
	
		
		System.out.print("å �̸� : ");
		b.setName(scan.nextLine());
		
		System.out.print("�� ��: ");
		b.setAuthor(scan.nextLine());
		
		System.out.print("���ǻ� : ");
		b.setPublisher(scan.nextLine());
		
		System.out.print("�� �� :");
		b.setCost(scan.nextLine());

		System.out.println(b.toString());
		bw.write(b.getName() + "\t" + b.getAuthor() + "\t" + b.getPublisher() + "\t" + b.getCost());
		bw.newLine();
		bw.close();
	}

	public void searchBook() throws FileNotFoundException {
		System.out.println("�˻��� Ű���带 �Է��ϼ��� :");
		
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
			System.out.println("å�������о�ü������ϴ�.");
			System.out.println("����� ������ ã�� �� �����ϴ�.");
			e.printStackTrace();
		}
	}
}
