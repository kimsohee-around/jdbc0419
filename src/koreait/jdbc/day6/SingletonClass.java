package koreait.jdbc.day6;

//싱글톤 패턴 : 객체를 만들때 오직 1개만 만들어서 사용하는 코드 패턴
//			웹(멀티쓰레드 환경)에서는 메소드만 있는 dao 와 같은 클래스를 싱글톤 으로 작성합니다.
//			static 은 정적인 영역.dao를 정적인 영역에 만들지는 않습니다.스레드 관리에 적합하지 않음.
public class SingletonClass {
	//1. 미리 객체를 만들어서 전역변수(필드)로 선언해 둡니다.private static 
	private static SingletonClass single = new SingletonClass();
	//SingletonClass 객체는 오직 한번만 만듭니다.
	
	//2. 생성자는 private 입니다.외부의 다른 클래스는 new SingletonClass() 실행 못합니다.
	private SingletonClass() {
	}
	
	//3. 외부의 다른 클래스에서 객체를 요청할 때 리턴해 주는 메소드 
	public static SingletonClass getInstance() {
		return single;
	}
}
