package cn.puhy.demo.agent;

public class TraceSession {

    private String traceId;
    private String eventId;
    private String parentEventId;
    private static ThreadLocal<TraceSession> session = new ThreadLocal<>();

    public TraceSession(String traceId, String eventId, String parentEventId) {
        this.traceId = traceId;
        this.eventId = eventId;
        this.parentEventId = parentEventId;
        session.set(this);
    }

    public static TraceSession getSession() {
        return session.get();
    }
}
