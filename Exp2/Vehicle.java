public class Vehicle {

    public String brandName;
    public String modelName;
    public String color;
    public double price;
    private String mfgCode;
    public int seatingCapacity;
    public boolean auto;

    // Getter and Setter for private member
    public void setMfgCode(String mCode){
        mfgCode = mCode;
    }

    public String getMfgCode(){
        return mfgCode;
    }

    // Default constructor
    public Vehicle() {
        brandName = "BMW";
        modelName = "Getz";
        color = "Red";
        price = 99.99;
        mfgCode = "ABC7437749";
        auto = false;
        seatingCapacity = 5;
    }

    // Parameterized constructor 1
    public Vehicle(String bName, String mName, double price) {
        brandName = bName;
        modelName = mName;
        this.price = price;
        color = "Black";
        mfgCode = "DEFAULT001";
        seatingCapacity = 5;
        auto = false;
    }

    // Parameterized constructor 2
    public Vehicle(String bName, double p, int s) {
        brandName = bName;
        price = p;
        seatingCapacity = s;
        modelName = "Standard";
        color = "White";
        mfgCode = "DEFAULT002";
        auto = false;
    }

    // Copy constructor
    public Vehicle(Vehicle v) {
        brandName = v.brandName;
        modelName = v.modelName;
        color = v.color;
        price = v.price;
        mfgCode = v.mfgCode;
        seatingCapacity = v.seatingCapacity;
        auto = v.auto;
    }

    // Methods
    public void start() {
        System.out.println("This vehicle has started.");
    }

    public void drive() {
        System.out.println("This vehicle can be driven on all surfaces.");
    }

    public void stop() {
        System.out.println("Stop the ignition. You are at 0 kmph now!");
    }

    public float changeSpeed(float sp) {
        if (sp < 20)
            sp += 20;
        else
            sp -= 20;
        return sp;
    }

    public float calcMileage(float dist, float fuel) {
        return dist / fuel;
    }
}
