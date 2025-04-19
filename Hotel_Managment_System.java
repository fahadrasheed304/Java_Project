
/**
 * HotelManagementSystem: Java program for managing hotel operations.

 * PROJECT NAME:    Welcome to the Hotel Management System
 * PROJECT MADE BY: Tab ish Nazir, Muhammad Rafay, Fahd Rasheed
 * SAP ID:          51702,        52520,          46098

 * This program allows users to perform various hotel management operations, including:
 * 1. Reservation System: Book rooms with details such as guest name, phone number, check-in/out dates, room type, and number of guests.
 * 2. Room Management System: Manage room-related operations.
 * 3. Check-in Process: Record guest check-in information including name, phone number, email, address, and identification.
 * 4. Check-out Process: Complete guest check-out, calculate total invoice with optional meal inclusion.
 * 5. Dine-In System: Record customer information, table number, and food orders.

 * Usage:
 * The program provides a user-friendly menu for selecting operations and guides the user through the input process.
 * Follow the on-screen instructions to perform desired operations.

 * Note: This code is a simplified representation for educational purposes and may not cover all edge cases or real-world scenarios.
 */


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;// java package


class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String enteredPassword) {
        return password.equals(enteredPassword);
    }
}


class Customer extends User {
    private final String customerName;
    private final String email;

    public Customer(String username, String password, String customerName, String email) {
        super(username, password);
        this.customerName = customerName;
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }
}


class Admin extends User {
    private final String adminName;

    public Admin(String username, String password, String adminName) {
        super(username, password);
        this.adminName = adminName;
    }

    public String getAdminName() {
        return adminName;
    }
}


class ValidationUtil {
    public static boolean isValidPassword(String password) {

        return password.matches("^(?=.*\\d).{6,}$");
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }
}


class HotelManagementSystem {
    private static final Map<String, GuestInformation> guestsMap = new HashMap<>();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");;

    private static String getInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }


    private static class GuestInformation {
        private final String phoneNumber;
        private final String email;
        private final String address;
        private final String identification;
        private final String roomType;
        private final String roomNumber;
        private final double roomCost;
        private final String checkInDate;
        private final String checkOutDate;

        public GuestInformation(String phoneNumber, String email, String address, String identification, String roomType, String roomNumber, double roomCost, String checkInDate, String checkOutDate) {
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.address = address;
            this.identification = identification;
            this.roomType = roomType;
            this.roomNumber = roomNumber;
            this.roomCost = roomCost;
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public String getAddress() {
            return address;
        }

        public String getIdentification() {
            return identification;
        }

        public String getRoomType() {
            return roomType;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public double getRoomCost() {
            return roomCost;
        }

        public String getCheckInDate() {
            return checkInDate;
        }

        public String getCheckOutDate() {
            return checkOutDate;
        }

    }


    private static void performCheckIn() throws InterruptedException {
        String guestName;
        String phoneNumber;
        String email;
        String address;
        String identification;
        String roomType;
        String roomNumber;
        String checkInDate;
        String checkoutDate;

        Scanner scanner = new Scanner(System.in);
        System.out.println();

        System.out.println("===== Check-In Process =====");
        System.out.println("Guest Information:");


        System.out.print("Please enter the name of the Guest: ");
        while (true) {
            guestName = scanner.nextLine();

            if (guestName.matches("[a-zA-Z ]+")) {
                break;
            } else {
                System.out.println("Invalid input! Please enter alphabets only.");
            }
        }


        while (true) {
            System.out.print("Please enter the Phone number: ");
            phoneNumber = scanner.nextLine();
            if (phoneNumber.matches("[0-9]+")) {
                break;
            } else {
                System.out.println("Invalid input! Please enter integers only.");
            }
        }


        System.out.print("Enter the e-mail: ");
        while (true) {
            email = scanner.nextLine();
            if (email.matches("^[a-zA-Z0-9_+&-]+@(gmail\\.com)$")) {
                break;
            } else {
                System.out.print("Invalid email format! Please enter a valid Gmail address: ");
            }
        }


        System.out.print("Please enter the Address of the guest: ");
        address = scanner.nextLine();


        System.out.print("Please enter the identification of the guest: ");
        identification = scanner.nextLine();


        System.out.println();

        System.out.println("Showing the room information");
        Thread.sleep(3000);


        System.out.println("Room Information:");
        System.out.print("Select Room Type (Single/Double): ");
        while (true) {
            roomType = scanner.nextLine().toLowerCase();
            if (roomType.equals("single") || roomType.equals("double")) {
                break;
            } else {
                System.out.println("Invalid input! Please enter either 'Single' or 'Double'.");
            }
        }


        System.out.println("Available Room Numbers:");
        if (roomType.equalsIgnoreCase("Single")) {
            System.out.println("101 102");
        } else if (roomType.equalsIgnoreCase("Double")) {
            System.out.println("103");
        } else {
            System.out.println("Invalid room type");
        }


        System.out.print("Enter the Room Number (101, 102, 103): ");
        while (true) {
            roomNumber = scanner.nextLine();

            if (roomNumber.matches("10[123]|20[123]")) {
                break;
            } else {
                System.out.println("Invalid input! Please enter a valid room number.");
            }
        }


        while (true) {
            System.out.print("Please enter the Check-in Date (yyyy-MM-dd): ");
            checkInDate = scanner.nextLine();
            if (isValidDate(checkInDate)) {
                break;
            } else {
                System.out.println("Invalid input! Please enter a valid date in yyyy-MM-dd format.");
            }
        }


        while (true) {
            System.out.print("Please enter the Check-out Date (yyyy-MM-dd): ");
            checkoutDate = scanner.nextLine();
            if (isValidDate(checkoutDate) && isAfter(checkoutDate, checkInDate)) {
                break;
            } else {
                System.out.println("Invalid input! Please enter a valid date in yyyy-MM-dd format and ensure it's after the Check-in Date.");
            }
        }


        double roomCost = roomType.equalsIgnoreCase("Single") ? 2500.0 : 3500.0;
        guestsMap.put(guestName, new GuestInformation(phoneNumber, email, address, identification, roomType, roomNumber, roomCost, checkInDate, checkoutDate));

        System.out.println();
        System.out.println("The Information of the guest is updating. Please wait for a while!");
        Thread.sleep(3000);
        System.out.println("The Check-in information of the guest is updated successfully");
        displayGuestInfo(guestName);
        System.out.println();
    }

    private static void displayGuestInfo(String guestName) throws InterruptedException {
        if (guestsMap.containsKey(guestName)) {
            GuestInformation guestInfo = guestsMap.get(guestName);
            LocalDateTime checkInTime = LocalDateTime.now();
            DateTimeFormatter checkInTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedCheckInTime = checkInTime.format(checkInTimeFormatter);

            System.out.println("Showing the Guest Inforamtion:");
            Thread.sleep(3000);
            System.out.println("Guest Information for " + guestName + " is:");
            System.out.println("Phone Number: " + guestInfo.getPhoneNumber());
            System.out.println("Email: " + guestInfo.getEmail());
            System.out.println("Address: " + guestInfo.getAddress());
            System.out.println("Identification: " + guestInfo.getIdentification());
            System.out.println();


            System.out.println("Showing the room check-in Information:");
            Thread.sleep(3000);
            System.out.println("Room check-in Information:");
            System.out.println("Check-in Time: " + formattedCheckInTime);
            System.out.println("Check-out Time: " + guestInfo.getCheckOutDate());
            System.out.println("Room Type: " + guestInfo.getRoomType());
            System.out.println("Room Number: " + guestInfo.getRoomNumber());
        } else {
            System.out.println("Guest not found: " + guestName);
        }
    }


    private static void performCheckOut() throws InterruptedException {
        String guestName;

        System.out.println("Performing Check-out Process operation. Please wait a second!...");
        Thread.sleep(3000);

        System.out.println("===== Check-Out Process =====");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Please enter the name of the Guest: ");
            guestName = scanner.nextLine();
            if (guestName.matches("[a-zA-Z]+")) {
                break;
            } else {
                System.out.println("Invalid input! Please enter alphabets only.");
            }
        }


        String lowercaseGuestName = guestName.toLowerCase();
        if (guestsMap.containsKey(lowercaseGuestName)) {
            GuestInformation guestInfo = guestsMap.get(lowercaseGuestName);
            displayGuestInfo(lowercaseGuestName);

            double roomCost = 2500.0;
            double mealCost = 1500.0;


            System.out.print("Do you want to include meals in the invoice? (yes/no): ");
            String includeMeals = scanner.nextLine();


            double totalInvoice = roomCost;

            if (includeMeals.equalsIgnoreCase("yes")) {
                totalInvoice += mealCost;
            }


            System.out.println("The Check-out process is completed.");
            System.out.println("Showing the Total Invoice");
            Thread.sleep(3000);
            System.out.println("Total Invoice: $" + totalInvoice);


            double additionalCharges = 100.0;
            double discountRate = 0.10;
            int numberOfGuest = 2;


            double totalInvoiceBeforeDiscount = roomCost * numberOfGuest + additionalCharges;
            double discount = totalInvoiceBeforeDiscount * discountRate;
            totalInvoice = totalInvoiceBeforeDiscount - discount;


            LocalDateTime checkoutTime = LocalDateTime.now();
            DateTimeFormatter checkoutTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedCheckoutTime = checkoutTime.format(checkoutTimeFormatter);


            System.out.println("\nTotal Invoice:");
            System.out.printf("%-20s $%.2f%n", "Room Charges:", roomCost * numberOfGuest);
            System.out.printf("%-20s $%.2f%n", "Additional Charges:", additionalCharges);
            System.out.printf("%-20s $%.2f%n", "Discount:", discount);
            System.out.println("Checkout Time: " + formattedCheckoutTime);
            System.out.println("----------------------------");
            System.out.printf("%-20s $%.2f%n", "Total Invoice:", totalInvoice);
        } else {
            System.out.println("Guest not found. Please check the name and try again.");
        }
    }



    private static void performReservation() throws InterruptedException {

        System.out.println();
        System.out.println("Performing Reservation System operation. Please wait a second!...");
        Thread.sleep(1000);
        System.out.println();
        System.out.println("===== Room Reservation Process =====");

        String guestName;
        String phoneNumber;
        String email;
        String address;
        String checkInDate = null;
        String checkoutDate;
        String roomType;
        int numberOfGuest;
        int roomNumber;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Please enter the name of the Guest: ");
            guestName = scanner.nextLine();
            if (guestName.matches("[a-zA-Z\\s]+")) {
                break;
            } else {
                System.out.println("Invalid input! Please enter alphabets and spaces only.");
            }
        }


        while (true) {
            System.out.print("Please enter the Phone number: ");
            phoneNumber = scanner.nextLine();
            if (phoneNumber.matches("[0-9]+")) {
                break;
            } else {
                System.out.println("Invalid input! Please enter integers only.");
            }
        }


        System.out.print("Enter the email: ");
        while (true) {
            email = scanner.nextLine();
            if (email.matches("^[a-zA-Z0-9_+&-]+@(gmail\\.com)$")) {
                break;
            } else {
                System.out.print("Invalid email format! Please enter a valid Gmail address: ");
            }
        }


        System.out.print("Enter the address: ");
        address = scanner.nextLine();


        while (true) {
            System.out.print("Please enter the Check-in Date (yyyy-MM-dd): ");
            checkInDate = scanner.nextLine();
            if (isValidDate(checkInDate)) {
                break;
            } else {
                System.out.println("Invalid input! Please enter a valid date in yyyy-MM-dd format.");
            }
        }


        while (true) {
            System.out.print("Please enter the Check-out Date (yyyy-MM-dd): ");
            checkoutDate = scanner.nextLine();
            if (isValidDate(checkoutDate) && isAfter(checkoutDate, checkInDate)) {
                break;
            } else {
                System.out.println("Invalid input! Please enter a valid date in yyyy-MM-dd format and ensure it's after the Check-in Date.");
            }
        }


        System.out.print("Please select the Room Type (Single, Double): ");
        roomType = scanner.nextLine();
        while (true) {
            roomType = roomType.toLowerCase();
            if (roomType.equals("single") || roomType.equals("double")) {
                break;
            } else {
                System.out.print("Invalid room type! Please enter 'Single' or 'Double': ");
                roomType = scanner.nextLine();
            }
        }


        System.out.print("Please select the Room Number (101, 102, 103): ");
        while (true) {
            if (scanner.hasNextInt()) {
                roomNumber = scanner.nextInt();

                if (roomNumber >= 101 && roomNumber <= 103) {
                    break;
                } else {
                    System.out.print("Invalid room number! Please enter a valid room number (101, 102, or 103): ");
                }
            } else {
                System.out.print("Invalid input! Please enter a valid integer room number (101, 102, or 103): ");
                scanner.next();
            }
        }


        while (true) {
            System.out.print("Please enter the total number of guests: ");

            if (scanner.hasNextInt()) {
                numberOfGuest = scanner.nextInt();

                if (numberOfGuest > 0) {
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a valid number greater than 0.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next();
            }
        }


        double roomCost = switch (roomType.toLowerCase()) {
            case "single" -> 2500.0;
            case "double" -> 3500.0;
            default -> 0.0;
        };


        double totalInvoice = roomCost * numberOfGuest;


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();

        System.out.println();
        System.out.println("===== Updating the guest details =====");
        Thread.sleep(4000);
        System.out.println("Guest Details updated:");
        System.out.println("Guest Name: " + guestName);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Address: " + address);
        System.out.println("Email: " + email);
        System.out.println();

        System.out.println("===== Updating room details =====");
        Thread.sleep(4000);
        System.out.println("Room Reservation details:");
        System.out.println("Check-in Date: " + checkInDate);
        System.out.println("Check-out Date: " + checkoutDate);
        System.out.println("Room Type: " + roomType);
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Number of Guests: " + numberOfGuest);
        System.out.println("Reservation Time: " + formatter.format(currentDate));


        double additionalCharges = 100.0;
        double discountRate = 0.10;


        double totalInvoiceBeforeDiscount = roomCost * numberOfGuest + additionalCharges;
        double discount = totalInvoiceBeforeDiscount * discountRate;
        totalInvoice = totalInvoiceBeforeDiscount - discount;

        System.out.println();
        System.out.println("===== Generating Invoice =====");
        Thread.sleep(3000);
        System.out.println("Total Invoice:");
        System.out.printf("%-20s $%.2f%n", "Room Charges:", roomCost * numberOfGuest);
        System.out.printf("%-20s $%.2f%n", "Additional Charges:", additionalCharges);
        System.out.printf("%-20s $%.2f%n", "Discount:", discount);
        System.out.println("----------------------------");
        System.out.printf("%-20s $%.2f%n", "Total Invoice:", totalInvoice);
    }


    private static boolean isValidDate(String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            System.out.println("Parsed date: " + parsedDate);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return false;
        }
    }


    private static boolean isAfter(String dateToCheck, String referenceDate) {
        try {
            LocalDate checkDate = LocalDate.parse(dateToCheck, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate referenceDateObj = LocalDate.parse(referenceDate, DateTimeFormatter.ISO_LOCAL_DATE);
            return checkDate.isAfter(referenceDateObj);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date in isAfter method: " + e.getMessage());
            return false;
        }
    }


    private static final Map<String, Boolean> roomAvailability = new HashMap<>();

    static {
        roomAvailability.put("101", true);
        roomAvailability.put("102", true);
        roomAvailability.put("103", true);
    }


    private static void performRoomManagement() throws InterruptedException {
        System.out.println();
        System.out.println("Performing Room Management System operation. Please wait a second!...");


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("===== Room Management Process =====");

        int choice;
        Scanner scanner = new Scanner(System.in);
        String continueChoice;


        do {
            displayMenu();
            while (true) {
                System.out.print("Enter your choice (1-5): ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 6) {
                        scanner.nextLine();
                        break;
                    } else {
                        System.out.println("Invalid input! Please enter a valid integer between 1 and 6.");
                        scanner.nextLine();
                    }
                } else {
                    System.out.println("Invalid input! Please enter a valid integer.");
                    scanner.nextLine();
                }
            }


            switch (choice) {
                case 1 -> viewRoomAvailability();
                case 2 -> reserveRoom();
                case 3 -> performMaintenance();
                case 4 -> viewRoomDetails();
                case 5 -> {
                    System.out.println("Returning to the main menu.");
                    break;
                }
                default -> System.out.println("Invalid choice. Please select from 1 - 5");
            }

            do {
                System.out.println();
                System.out.print("Do you want to perform another room management operation? (yes/no): ");
                continueChoice = scanner.nextLine();
            } while (!continueChoice.equalsIgnoreCase("yes") && !continueChoice.equalsIgnoreCase("no"));

        } while (continueChoice.equalsIgnoreCase("yes"));
    }

    private static void initializeRoomAvailability() {
        roomAvailability.put("101", true);
        roomAvailability.put("102", true);
        roomAvailability.put("103", true);
    }

    private static void displayMenu() {
        System.out.println("1. View Room Availability");
        System.out.println("2. Reserve a Room");
        System.out.println("3. Perform Maintenance");
        System.out.println("4. View Room Details");
        System.out.println("5. Go back to the main menu");
    }

    private static void viewRoomAvailability() {
        System.out.println("\n===== Room Availability =====");
        for (Map.Entry<String, Boolean> entry : roomAvailability.entrySet()) {
            System.out.println("Room " + entry.getKey() + ": " + (entry.getValue() ? "Available" : "Occupied"));
        }
    }

    private static void reserveRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("===== Reserve Room Process =====");

        System.out.print("Enter the room number to reserve (101, 102, or 103): ");
        String roomNumber = getValidRoomNumber(scanner);

        if (roomAvailability.containsKey(roomNumber) && roomAvailability.get(roomNumber)) {
            roomAvailability.put(roomNumber, false);
            System.out.println("Room " + roomNumber + " reserved successfully.");
        } else {
            System.out.println("Room " + roomNumber + " is not available for reservation.");
        }
    }


    private static void performMaintenance() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("===== Maintenance Process =====");

        System.out.print("Enter the room number to mark for maintenance: ");
        String roomNumber = getValidRoomNumber(scanner);

        if (roomAvailability.containsKey(roomNumber)) {
            roomAvailability.put(roomNumber, true);
            System.out.println("Room " + roomNumber + " marked for maintenance successfully.");
        } else {
            System.out.println("Room " + roomNumber + " does not exist.");
        }
    }

    private static void viewRoomDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("===== View Room Details =====");

        System.out.print("Enter the room number to view details: ");
        String roomNumber = getValidRoomNumber(scanner);

        if (roomAvailability.containsKey(roomNumber)) {
            System.out.println("Room " + roomNumber + " Details:");
            System.out.println("Status: " + (roomAvailability.get(roomNumber) ? "Available" : "Occupied"));
        } else {
            System.out.println("Room " + roomNumber + " does not exist.");
        }
    }

    private static String getValidRoomNumber(Scanner scanner) {
        String roomNumber;
        while (true) {
            roomNumber = scanner.nextLine();
            if (roomAvailability.containsKey(roomNumber)) {
                break;
            } else {
                System.out.print("Invalid room number! Please enter a valid room number: ");
            }
        }
        return roomNumber;
    }


    private static void performDineIn() throws InterruptedException {
        System.out.println();
        System.out.println("Performing Dine-In System operation. Please wait a second!...");
        Thread.sleep(3000);
        System.out.println("===== Dine-In Process =====");


        String customerName;
        String tableNumber;
        List<String> foodOrder = new ArrayList<>();


        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.print("Please enter the name of the customer: ");
            customerName = scanner.nextLine();

            if (customerName.matches("[a-zA-Z]+")) {
                break;
            } else {
                System.out.println("Invalid input! Please enter alphabets only.");
            }
        }


        System.out.print("Please enter the table Number: ");
        tableNumber = scanner.nextLine();


        displayDineInMenu();


        do {
            System.out.print("Enter the number of the item you want to order (enter 1 to 5): ");
            int choice;
            while (true) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a valid integer.");
                    scanner.nextLine();
                }
            }


            if (choice == 0) {
                break;
            } else if (choice >= 1 && choice <= menu.size()) {
                foodOrder.add(menu.get(choice - 1));
                System.out.println(menu.get(choice - 1) + " added to the order.");
            } else {
                System.out.println("Invalid choice. Please select a valid item from the menu.");
            }


            System.out.print("Do you want to order more food? (Yes/No): ");
        } while (scanner.nextLine().equalsIgnoreCase("yes"));

        System.out.println();
        System.out.println("===== Customer Information =====");
        System.out.println("Customer Name: " + customerName);
        System.out.println();

        System.out.println("===== Dine-in Information =====");
        System.out.println("Table Number: " + tableNumber);
        System.out.println();
        System.out.println("===== Generating Invoice =====");
        Thread.sleep(3000);
        System.out.println("Total Invoice:");

        double totalAmount = calculateTotalAmount(foodOrder);
        double tipAmount = 0.005 * totalAmount;

        System.out.printf("%-20s Rs%.2f%n", "Food Items:", totalAmount);
        System.out.printf("%-20s Rs%.2f%n", "Tip (0.5%):", tipAmount);


        double additionalCharges = 0.0;
        double discount = 0.0;

        System.out.printf("%-20s Rs%.2f%n", "Additional Charges:", additionalCharges);
        System.out.printf("%-20s Rs%.2f%n", "Discount:", discount);
        System.out.println("----------------------------");
        double totalInvoice = totalAmount + tipAmount + additionalCharges - discount;
        System.out.printf("%-20s Rs%.2f%n", "Total Invoice:", totalInvoice);
    }

    private static final List<String> menu = List.of("1. Burger - Rs300", "2. Pizza - Rs600", "3. Salad - Rs500", "4. Pasta - Rs400", "5. Drink - Rs50");

    private static void displayDineInMenu() {
        System.out.println("Menu:");
        for (String item : menu) {
            System.out.println(item);
        }
    }


    private static int calculateTotalAmount(List<String> foodOrder) {
        int totalAmount = 0;
        for (String orderItem : foodOrder) {
            int priceIndex = Character.getNumericValue(orderItem.charAt(0)) - 1;
            String[] parts = menu.get(priceIndex).split(" - Rs");
            int itemPrice = Integer.parseInt(parts[1]);
            totalAmount += itemPrice;
        }
        return totalAmount;
    }


    public static void main(String[] args) throws InterruptedException {

        System.out.println();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("    yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);


        System.out.println("\t\t\t\t\t\t\t\t\tCurrent Date and Time: " + formattedDateTime);
        System.out.println("\t\t\t\t\t\t\t\t\tPROJECT NAME:              Hotel Management System");
        System.out.println("\t\t\t\t\t\t\t\t\tPRESENTED BY:              Tabish Nazir  Muhammad Rafay  Fahad Rasheed");
        System.out.println("\t\t\t\t\t\t\t\t\tThis program allows users to perform various hotel management operations.");
        System.out.println();


        int choice;
        String continueChoice;


        Scanner scanner = new Scanner(System.in);
        Map<String, User> users = new HashMap<>();


        System.out.println("===== Sign-up and Log-in System =====");


        while (true) {
            System.out.println("1. Sign-up");
            System.out.println("2. Log-in");
            System.out.println("3. Exit");

            while (true) {
                System.out.print("Enter your choice (1-3): ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a valid integer.");
                    scanner.nextLine();
                }
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("\n===== You have selected Signup =====");
                    System.out.println();
                    System.out.println("Signup Menu.");
                    System.out.println("1. Customer");
                    System.out.println("2. User");
                    System.out.println("3. Admin");


                    int signupType;
                    while (true) {
                        System.out.print("Enter your choice (1-3): ");
                        if (scanner.hasNextInt()) {
                            signupType = scanner.nextInt();
                            if (signupType >= 1 && signupType <= 3) {
                                scanner.nextLine();
                                break;
                            } else {
                                System.out.println("Invalid input! Please enter a valid integer between 1 and 3.");
                            }
                        } else {
                            System.out.println("Invalid input! Please enter a valid integer.");
                            scanner.nextLine();
                        }
                    }


                    System.out.print("Enter user-name: ");
                    String signupUsername;
                    while (!(signupUsername = scanner.nextLine()).matches("[a-zA-Z]+(\\s[a-zA-Z]+)*")) {
                        System.out.print("Invalid input! Please enter a valid full name with alphabetic characters.");
                    }


                    System.out.print("Enter password (must be at least 6 characters long with a digit): ");
                    String signupPassword;
                    while (true) {
                        signupPassword = scanner.nextLine();
                        if (signupPassword.matches("^(?=.*\\d).{6,}$")) {
                            break;
                        } else {
                            System.out.print("Invalid input! Password must be at least 6 characters long and include a digit. Please try again: ");
                        }
                    }


                    System.out.print("Enter the e-mail: ");
                    String customerEmail;
                    while (!(customerEmail = scanner.nextLine()).matches("^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                        System.out.print("Invalid input! Please enter a valid email address: ");
                    }
                    if (ValidationUtil.isValidPassword(signupPassword) && ValidationUtil.isValidEmail(customerEmail)) {
                        if (signupType == 1) {
                            System.out.print("Enter customer name: ");
                            String customerName;

                            while (true) {
                                customerName = scanner.nextLine();
                                if (customerName.matches("[a-zA-Z]+")) {
                                    break;
                                } else {
                                    System.out.print("Invalid input! Please enter alphabets only for the customer name: ");
                                }
                            }


                            Customer newCustomer = new Customer(signupUsername, signupPassword, customerName, customerEmail);
                            users.put(signupUsername, newCustomer);
                            System.out.println("Customer Signup Successful!");


                        } else if (signupType == 2) {
                            User newUser = new User(signupUsername, signupPassword);
                            users.put(signupUsername, newUser);
                            System.out.println("Creating Account...");
                            Thread.sleep(3000);
                            System.out.println("Account Created Successful!");
                            Thread.sleep(3000);


                        } else {
                            System.out.print("Enter admin name: ");
                            String adminName;
                            while (true) {
                                adminName = scanner.nextLine();
                                if (adminName.matches("[a-zA-Z]+")) {
                                    break;
                                } else {
                                    System.out.print("Invalid input! Please enter alphabets only for the admin name: ");
                                }
                            }


                            Admin newAdmin = new Admin(signupUsername, signupPassword, adminName);
                            users.put(signupUsername, newAdmin);
                            System.out.println("Admin Signup Successful!");
                        }
                    } else {
                        System.out.println("Invalid password or email format. Signup failed.");
                    }
                    continue;
                }


                case 2 -> {
                    System.out.println();
                    System.out.println("You have selected Login.");


                    System.out.println("===== log-in =====");
                    System.out.print("Enter username: ");
                    String loginUsername;


                    while (true) {
                        loginUsername = scanner.nextLine();
                        if (loginUsername.matches("[a-zA-Z]+")) {
                            break;
                        } else {
                            System.out.print("Invalid input! Please enter alphabets only for the username: ");
                        }
                    }


                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();


                    User loginUser = users.get(loginUsername);
                    if (loginUser != null && loginUser.authenticate(loginPassword)) {
                        if (loginUser instanceof Customer) {
                            System.out.println("Customer Login Successful!");
                        } else if (loginUser instanceof Admin) {
                            System.out.println("Admin Login Successful!");
                        } else {
                            System.out.println("User Login Successful!");
                        }

                        choice = 1;
                    } else {
                        System.out.println("Login failed. Invalid username or password.");
                    }
                }


                case 3 -> {
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please select from 1 - 3");
            }
            if (choice == 2 && users.isEmpty()) {
                System.out.println("No users found. Please sign up first.");
                continue;
            }



            do {
                System.out.println();
                System.out.println("Showing you the menu of Hotel Management System");
                Thread.sleep(3000);
                System.out.println();
                System.out.println("===== Hotel Management System Operations =====");
                System.out.println("1. Reservation System");
                System.out.println("2. Check-in Process");
                System.out.println("3. Check-out Process");
                System.out.println("4. Dine-In System");
                System.out.println("5. Room Management System");
                System.out.println("6. Exit");


                while (true) {
                    System.out.print("Enter your choice (1-6): ");
                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        break;
                    } else {
                        System.out.println("Invalid input! Please enter a valid integer between 1 and 6.");
                        scanner.nextLine(); // Consume the invalid input
                    }
                }


                switch (choice) {
                    case 1 -> performReservation();
                    case 2 -> performCheckIn();
                    case 3 -> performCheckOut();
                    case 4 -> performDineIn();
                    case 5 -> performRoomManagement();
                    case 6 -> {
                        System.out.println("Exiting the program. BYE BYE!");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid! Please select from 1 - 6");
                }
                scanner.nextLine();


                do {
                    System.out.print("Do you want to perform another operation? (yes/no): ");
                    continueChoice = scanner.nextLine();
                } while (!continueChoice.equalsIgnoreCase("yes") && !continueChoice.equalsIgnoreCase("no"));

            } while (!continueChoice.equalsIgnoreCase("no"));
        }
    }


}
