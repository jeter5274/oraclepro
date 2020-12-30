package com.javaex.phone2;

public class PhoneApp {

	public static void main(String[] args) {
		
		boolean closeFlag = true;
		
		PhoneExcute pExcute = new PhoneExcute(); 
		
		pExcute.printTitle();
				
		while(closeFlag) {
		
			int menu = pExcute.menuNavi();
			
			switch(menu) {
				case 1 : 
					pExcute.showList();
					break;
				case 2 : 
					pExcute.addPs();
					break;
				case 3 : 
					pExcute.modifyPs();
					break;
				case 4 : 
					pExcute.deletePs();
					break;
				case 5 : 
					pExcute.searchPs();
					break;
				case 6 : 
					closeFlag = pExcute.closePA();
					break;
				default : 
					System.out.println("[다시 입력해주세요.]");
					break;
			}
		}
	}
}


