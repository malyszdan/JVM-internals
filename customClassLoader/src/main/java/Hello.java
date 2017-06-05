public class Hello implements IHello {
    public Hello() {}
/* Content of resources/Hello.class
    @Override
    public String sayHello() {
        return " I am loaded from Service";
    }*/

    private String content;

    public String sayHello() {
        return getContent();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
