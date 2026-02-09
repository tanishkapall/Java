public class MainForVehicle {

    public static void main(String[] args) {

        Vehicle v1 = new Vehicle();
        Vehicle v2 = new Vehicle("Mercedes", "Class C", 80000000);
        Vehicle v3 = new Vehicle("BMW", 7000000, 5);

        Vehicle v4 = v3;   // DO NOT CHANGE (as instructed)
        v4.setMfgCode("SUF8484884");
        v4.auto = true;

        Vehicle[] myFleet = new Vehicle[]{v1, v2, v3, v4};

        // Tabular format printing
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-15s\n", 
                          "Brand Name", "Model Name", "Price", "Mileage");
        System.out.println("---------------------------------------------------------------");

        for (Vehicle v : myFleet) {
            float mileage = v.calcMileage(45.0f, 10.98f);
            System.out.printf("%-15s %-15s %-15.2f %-15.2f\n",
                    v.brandName,
                    v.modelName,
                    v.price,
                    mileage);
        }

        System.out.println("---------------------------------------------------------------");
    }
}
