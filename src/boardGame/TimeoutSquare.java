package boardGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TimeoutSquare extends Square {
	
	public TimeoutSquare(String squareName, int squarePosition) {
	    super(squareName, squarePosition);
	}
	
	
	@Override
	protected void displaySquareMessage() {
		
		// Add facts to an arraylist
		ArrayList<String> ecoFacts = new ArrayList<String>();
		
		ecoFacts.add("Around 27,000 trees are cut down each day. The world has over 3.04 trillion trees in the world. However, 27,000 of them are cut down daily to make toilet paper. This translates to about 9.8 million trees annually. One single recycled edition of the New York Times newspaper could save 75,000 tree.");
		ecoFacts.add("Humans use only 1% of all available water. About 71% of the earth is water. The oceans hold approximately 96.5% of this water and the ice caps hold about 2%. The remaining water exists in rivers, ponds, glaciers, ice caps, lakes, as water vapor and our taps, among other water bodies. Only 1% of the earth’s water is safe for human consumption.");
		ecoFacts.add("78% of marine mammals are at risk of choking on plastic. Seventy-eight percent of marine mammals are at risk of accidental deaths, such as getting caught in fishing nets. Plastic bags and other plastic garbage that ends up in the ocean kill over 1,000,000 sea animals every year.");
		ecoFacts.add("Americans throw away 25 trillion Styrofoam cups every year. Styrofoam is not biodegradable. Switching to single use options will help cut down on Styrofoam pollution.");
		ecoFacts.add("Fungi play a highly vital role in the environment. Fungi play a protective role in the environment. From digesting minerals out of rock formations to consuming fossil fuel spills, and even de-radiating the environment.");
		ecoFacts.add("Ants weigh more than humans. The combined weight of ants on the planet is higher than all human beings. The world has over 7 billion people, and 100 trillion ants.");
		ecoFacts.add("Every three months, Americans throw enough aluminum in the landfills to build our nation’s entire commercial air fleet. Recycling one aluminum can save enough energy to run a TV for three hours. During the time it takes you to read this sentence, 50,000 12-ounce aluminum cans are made.");
		ecoFacts.add("On average, one supermarket goes through 60 million paper bags each year. One of the best ways to cut down on single-use bags-both paper and plastic-is to switch to reusable.");
		ecoFacts.add("A glass bottle can take up to 1 million years to decompose. Glass takes a very, very long time to break down. It can take a glass bottle more than a million years to decompose in the environment, possibly even more if it’s in a landfill. This means that glass manufactured and used 5000 years ago may still be present in the environment. Because its life cycle is so long, and because glass doesn’t leach any chemicals, it’s better to repurpose and reuse it over and over again before recycling it.");
		ecoFacts.add("Recycling one glass bottle saves enough energy to power a normal light bulb for about four hours. While glass does not decompose easily, recycling it might be more beneficial to the environment. The recycling of one glass bottle not only saves energy but the energy saved can also power a normal light bulb for about four hours.");
		ecoFacts.add("The world’s oldest trees are 4,600 year old Bristlecone pines in the USA. The Great Basin Bristlecone Pine (Pinus Longaeva) has been deemed the oldest tree in existence, reaching an age of over 5,000 years old. The Bristlecone pines’ success in living a long life can be attributed to the harsh conditions it lives in. Very cold temperatures associated with high winds, in addition to a slow growth rate, create dense wood, meaning some years they grow so slowly, they don’t add a ring of growth. Learn More about these cool trees!");
		ecoFacts.add("If you walk a mile along an average US highway, you will see, on average, about 1,457 pieces of litter. According to Keep America Beautiful, if every American picked up 152 pieces of litter at the same time, we would have a litter-free nation.");
		ecoFacts.add("Paper from trees can be recycled 6 times. Paper comes from trees and can be recycled a maximum of six times, after which, its fibers become too weak to hold together. Learn More");
		ecoFacts.add("There is a giant floating patch of garbage. The Great Pacific Garbage Patch is a twisting and turning vortex composed of trash and waste. It is twice the size of continental America and contains about 100 million tons of garbage, it stretches from the West Coast of North America to Japan along the Pacific Ocean.");
		
		// Get random fact
		Random rand = new Random();
        int randomIndex = rand.nextInt(ecoFacts.size());
        String randomFact = ecoFacts.get(randomIndex);
        
		// Print message
		System.out.println("\nWelcome to the Timeout Treehouse!");
		System.out.println("You get to sit this round out. Here's an interesting fact while you wait:");
		System.out.println(randomFact + "\n");
		
		System.out.println("Would you like another fact? (y/n)");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();

        if (input.equalsIgnoreCase("y")) {
        	displaySquareMessage();
        } else {
            System.out.println("We hope you had a relaxing time at the Timeout Treehouse. \n");
        }
	}

	

}
