package hello.core.singleton;

public class SingletonService {

    // static으로 싱글톤 인스턴스 하나 만들기
    private static final SingletonService instance = new SingletonService();

    // 조회 가능한거
    public static SingletonService getInstance(){
        return instance;
    }

    // 퍼블릭 생성자 막아버리기
    private SingletonService()
    {

    }

    public void logic()
    {
        System.out.println("싱글톤 객체 로직 호출");
    }


}
