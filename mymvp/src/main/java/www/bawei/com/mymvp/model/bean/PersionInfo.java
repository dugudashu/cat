package www.bawei.com.mymvp.model.bean;

import java.io.Serializable;

/**
 * Created by wmm on 2017/9/8 0008.
 */

public class PersionInfo implements Serializable {

    private String nameString;
    private boolean chick;   //标识

    public PersionInfo(String nameString) {
        this.nameString = nameString;
    }

    public String getNameString() {
        return nameString;
    }

    public void setNameString(String nameString) {
        this.nameString = nameString;
    }

    public boolean isChick() {
        return chick;
    }

    public void setChick(boolean chick) {
        this.chick = chick;
    }

}
