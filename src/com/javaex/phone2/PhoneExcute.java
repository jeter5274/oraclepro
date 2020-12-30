package com.javaex.phone2;

import java.util.List;
import java.util.Scanner;

public class PhoneExcute {

	//필드	
	PhoneDao phoneDao = new PhoneDao();
	
	Scanner sc = new Scanner(System.in);
	
	//생성자
	
	//메소드 getter/setter

	
	//메소드 일반
	
	//타이틀 출력
	public void printTitle() {
		System.out.println("************************************************");
		System.out.println("*           전화번호 관리 프로그램             *");
		System.out.println("************************************************");
	}
	
	//메뉴 네비게이션 출력 및 메뉴 입력
	public int menuNavi() {
		
		System.out.println("\n1.리스트  2.등록  3.수정  4.삭제  5.검색  6.종료");
		System.out.println("------------------------------------------------");

		return numScan("메뉴번호");
	}
	
	//전체 리스트 출력
	public void showList() {
		printList(phoneDao.getPersonList());
	}
	
	//연락처 추가
	public void addPs() {
		sc.nextLine();	//개행문자 추가 : 정수형 입력 후 문자형 오류 해결
		phoneDao.phoneInsert(strScan("이름"), strScan("휴대전화"), strScan("회사번호"));
	}
	
	//연락처 수정
	public void modifyPs() {
		int num = numScan("번호");
		sc.nextLine(); //개행문자 추가 : 정수형 입력 후 문자형 오류 해결
		
		phoneDao.phoneUpdate(num, strScan("이름"), strScan("휴대전화"), strScan("회사번호"));
	}
	
	//연락처 삭제
	public void deletePs() {
		phoneDao.phoneDelete(numScan("번호"));
	}
	
	//연락처 검색
	public void searchPs() {
		sc.nextLine(); //개행문자 추가 : 정수형 입력 후 문자형 오류 해결
		String searchWord = strScan("검색어");
		
		printList(phoneDao.phoneSearch(searchWord));
	}
	
	//리스트 출력
	public void printList(List<PhoneVo> phoneVoList){
		for(int i=0; i<phoneVoList.size();i++) {
			System.out.println(phoneVoList.get(i).personId+ ".   " +phoneVoList.get(i).name+ "    " +phoneVoList.get(i).hp+ "   "+ phoneVoList.get(i).company);
		}
	}
	
	//정수형 입력 폼
	public int numScan(String title) {
		if("메뉴번호".equals(title)) {
			System.out.print(">" +title+ " : ");
		}else {
			System.out.print(title+ " > ");
		}
		
		return sc.nextInt();
	}
	
	//문자형 입력 폼
	public String strScan(String title) {
		System.out.print(title+ " > ");
		return sc.nextLine();
	}

	//프로그램 종료
	public boolean closePA() {
		System.out.println("\n************************************************");
		System.out.println("*                 감사합니다.                  *");
		System.out.println("************************************************");
		sc.close();
		return false;
	}
}
