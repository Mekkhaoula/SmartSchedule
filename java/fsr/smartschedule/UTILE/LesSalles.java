package fsr.smartschedule.UTILE;

/**
 * Created by ENVY on 10/05/2015.
 */

import java.util.LinkedList;

import fsr.smartschedule.CLASS.AS;

public class LesSalles extends LinkedList<AS> {
    public LesSalles(){}


    @Override
    public AS[] toArray() {
        AS[] a=new AS[this.size()];
        for(int i=0;i<this.size();i++){
            a[i]=this.get(i);
        }
        return a;
    }


    public String[] string() {
        String[] a=new String[this.size()];
        for(int i=0;i<this.size();i++){
            a[i]=this.get(i).get_id();
        }
        return a;
    }

   /* @Override
    public boolean add(AS object) {
        return super.add(object);
    }

    public LesSalles search(String a){
        LesSalles b=new LesSalles();
        for(int i=0;i<size();i++){
            if(get(i).getName().toLowerCase().contains(a.toLowerCase())){
                b.add(get(i));
            }
        }
        return b;
    }*/

}
