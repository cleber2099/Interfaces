package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrasilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		System.out.println("Enter rental data: ");
		System.out.println("car model :");
		String carModel = sc.nextLine();
		System.out.println("Pickup (dd/MM/yyyy hh:ss): ");
		Date start = sdf.parse(sc.nextLine());		
		System.out.println("Return (dd/MM/yyyy hh:ss): ");
		Date finish = sdf.parse(sc.nextLine());
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.println("enter price per hour: ");
		double pricePerHour = sc.nextDouble();	
		System.out.println("Enter price per day: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService  rentalService = new RentalService(pricePerDay, pricePerHour, new BrasilTaxService());
		
		rentalService.processInvoice(cr);
		
		System.out.println("invoice :");
		System.out.println("Basic payment:"+String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Tax:"+String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Total payment:"+String.format("%.2f", cr.getInvoice().getTotalPayment()));

		
		sc.close();
	}

}
