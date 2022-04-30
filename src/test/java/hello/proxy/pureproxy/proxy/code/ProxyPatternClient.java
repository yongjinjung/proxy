package hello.proxy.pureproxy.proxy.code;

public class ProxyPatternClient {

    private Subject subject;

    public ProxyPatternClient(Subject subject) {
        this.subject = subject;
    }

<<<<<<< HEAD
    public void execute(){
        subject.operation();
    }
}
=======
    public void execute() {
        subject.operation();
    }
}
>>>>>>> cf46abd592ed7d3d46150a21c2fc98843ff81775
