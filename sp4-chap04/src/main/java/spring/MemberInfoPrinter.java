package spring;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoPrinter {
    
	//타입을 이용해서 주입할 빈 객체를 검색
	// 1. @Qualifier가 명시되어 있을 경우,@Qualifier와 같은 값을 갖는 빈 객채여야 한다.
	// 2. 빈 객체가 두 개 이상 존재하면, @Qualifier로 지정한 빈 객체를 찾는다. 존재하면, 그 객체를 사용한다. 
	// 3. 타입이 같은 빈 객체가 두 개 이상 존재하고 @Qualifier가 없을 경우, 이름이 같은 빈 객체를 찾는다. 존재하면, 그 객체를 사용한다.
	//@Resource 생성자에 적용 불가 필드나, 메서드에만 적용가능, name 속성이 없을경우 타입으로 검색
	//@Resource(name="memberDao")
	@Autowired
	private MemberDao memDao;
	private MemberPrinter printer;
	
	//이 두 설정 메서드는 MemberDao 타입의 객체와 MemberPrinter 타입의 객체에 대한 의존 주입 
	//fslse 인경우 주입할 의존 객체가 존재하지 않아도 익셉션을 발생 시키지 않음
	@Autowired(required=false)
	public void setMemberDao(MemberDao memDao) {
		this.memDao = memDao;
	}
	//@Resource(name="memberPrinter")
	//이름이 printer인 빈 객체가 자동 주입 대상
	@Autowired
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
	public void printMemberInfo(String email) {
		 Member member = memDao.selecetByEmail(email);
		 if(member == null) {
			 System.out.println("데이터 없음\n");
			 return;
		 }
		 printer.print(member);
		 System.out.println();
		 
		 
	}
	
	
	
}
