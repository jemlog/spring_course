package hello.advanced.trace;

import java.util.UUID;

public class TraceId {

    private String id; // 트랜젝션 id
    private int level;  // 깊이

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level)
    {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0,8); // substring => 원하는 길이만큼 자르는 법
    }

    public TraceId createNextId(){
        return new TraceId(id,level + 1);
    }

    private TraceId createPreviousId(){
        return new TraceId(id,level - 1);
    }

    public boolean isFirstLevel()
    {
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
