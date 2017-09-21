package www.bawei.com.mymvp.model.bean;

/**
 * Created by wmm on 2017/9/14 0014.
 */

public class UserBean {

    private String name;
    private String pwd;
    private int id;

    public UserBean() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public UserBean(String name, String pwd, int id) {
        this.name = name;
        this.pwd = pwd;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", id=" + id +
                '}';
    }
}
