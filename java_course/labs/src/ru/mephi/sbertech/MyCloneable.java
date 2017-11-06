package ru.mephi.sbertech;

public class MyCloneable {

    public static void main(String[] args) {
        Bike bike1 = new Bike(2, 26);
        Bike bike2 = (Bike) bike1.clone();
        bike1.wheelCount = 1;
        bike1.w.d = 29;

        System.out.println(bike1.toString());
        System.out.println(bike2.toString());

        System.out.println(bike1.hashCode());
        System.out.println(bike2.hashCode());
    }
}

class Wheel {
    int d;
    Wheel (int di) {
        d = di;
    }
}

class Bike implements Cloneable {
    int wheelCount;
    Wheel w;
    Bike (int wCount, int wDiameter) {
        wheelCount = wCount;
        w = new Wheel(wDiameter);
    }

    @Override
    public Object clone() {
        try {
            Bike cloned =  (Bike)super.clone();
            Wheel w = new Wheel(cloned.w.d);
            cloned.w = w;
            return cloned;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "A bike with " + this.wheelCount +
                (this.wheelCount > 1 ? " wheels" : " wheel") + " of the diameter " + this.w.d;
    }

    @Override
    public boolean equals(Object bike) {
        if (this.wheelCount == ((Bike)bike).wheelCount &&
                this.w.d == ((Bike)bike).w.d) {
            return true;
        } else {
            return false;
        }
    }

}
