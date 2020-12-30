package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {
		
		int menu, num;
		boolean closeFlag = true;
		String name, hp, company, searchWord;
		
		List<PhoneVo> phoneVoList;
		PhoneDao phoneDao = new PhoneDao();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("************************************************");
		System.out.println("*           전화번호 관리 프로그램             *");
		System.out.println("************************************************");

		while(closeFlag) {
			
			System.out.println("\n1.리스트  2.등록  3.수정  4.삭제  5.검색  6.종료");
			System.out.println("------------------------------------------------");
			System.out.print(">메뉴번호 : ");
			
			menu = sc.nextInt();
			
			switch(menu) {
			case 1 : 
				phoneVoList = phoneDao.getPersonList();
				
				for(int i=0; i<phoneVoList.size();i++) {
					System.out.println(phoneVoList.get(i).personId+ ".   " +phoneVoList.get(i).name+ "    " +phoneVoList.get(i).hp+ "   "+ phoneVoList.get(i).company);
				}
				break;
			case 2 :
				sc.nextLine();
				
				System.out.print("이름 > ");
				name = sc.nextLine();
				System.out.print("휴대전화 > ");
				hp = sc.nextLine();
				System.out.print("회사번호 > ");
				company = sc.nextLine();
				
				phoneDao.phoneInsert(name, hp, company);
				
				break;
			case 3 : 
				System.out.print("번호 > ");
				num = sc.nextInt();
				
				sc.nextLine();
				System.out.print("이름 > ");
				name = sc.nextLine();
				System.out.print("휴대전화 > ");
				hp = sc.nextLine();
				System.out.print("회사번호 > ");
				company = sc.nextLine();
				
				phoneDao.phoneUpdate(num, name, hp, company);
				break;
				
			case 4 : 
				System.out.print("번호 > ");
				num = sc.nextInt();
				
				phoneDao.phoneDelete(num);
				break;
			case 5 : 
				sc.nextLine();
				System.out.print("검색어 > ");
				searchWord = sc.nextLine();
				
				phoneVoList = phoneDao.phoneSearch(searchWord);
				
				for(int i=0; i<phoneVoList.size();i++) {
					System.out.println(phoneVoList.get(i).personId+ ".   " +phoneVoList.get(i).name+ "    " +phoneVoList.get(i).hp+ "   "+ phoneVoList.get(i).company);
				}
				
				break;
			case 6 : 
				closeFlag = false;
				break;
			default : 
				System.out.println("[다시 입력해주세요.]");
				break;
		}
		}
		
		System.out.println("\n************************************************");
		System.out.println("*                 감사합니다.                  *");
		System.out.println("************************************************");
		sc.close();
	}

}
