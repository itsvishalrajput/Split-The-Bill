package splitthebill.dto;

import java.io.Serializable;

public class Item implements Serializable{

    String name;
    Integer imgid;

    public Item(){

    }

    public Item(String name, Integer imgid) {
        this.name = name;
        this.imgid = imgid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImgid() {
        return imgid;
    }

    public void setImgid(Integer imgid) {
        this.imgid = imgid;
    }
}
