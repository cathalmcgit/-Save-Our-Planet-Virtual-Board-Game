package boardGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * @author cathalmcguigan
 *
 */
public class Game {
	
	public static void main(String[] args) {

		// method prompts user to enter number of players & their names to create player objects
		List<Player> players = registerPlayers();

		// game begins using list of registered players
		playGame(players);

	}

	private static List<Player> registerPlayers() {

		// creates an empty ArrayList to store Player objects
		List<Player> players = new ArrayList<Player>();

		// initialise an int to store the number of players
		int playerCount = 0;

		// print welcome messages and prompt the user to enter the number of players
		System.out.println("Welcome to Save the Planet!");
		System.out.println("You have a range of Ecological Resources at your disposal to invest in green projects.");
		System.out.println("These 'Ecos' include money, building equipment & advice from experts in green energy & construction.");
		System.out.println("You & your fellow players have agreed to support each other's projects whenever possible.\n");
		System.out.println("How many people are playing?");

		// scanner to take & store number of people entered
		Scanner scanner = new Scanner(System.in);
		int option;
		
		// a do while loop to deal with user input to establish number of players
		do {
			// try/catch block handles input of letters rather than numbers
			try {
				option = scanner.nextInt();
			} catch (InputMismatchException ex) {
				System.out.println("\nYou must enter a number between 2 and 4");
				scanner.next();
				continue;
			}
			// switch statement to assign correct player count
			switch (option) {
			case 2:
				playerCount = 2;
				break;
			case 3:
				playerCount = 3;
				break;
			case 4:
				playerCount = 4;
				break;
			default:
				// error message printed if invalid number of players entered
				System.out.println("\nYou must choose between 2, 3, or 4 players");
				System.out.println("How many people are playing?");
				continue;
//				option = scanner.nextInt();
//				break;
			}
			
			// prompt user for names once valid player count entered
			System.out.println("\nEnter the names for the players:");
			
			
		} while (playerCount < 2 || playerCount > 4);

		// creates an empty ArrayList to store unique names
		List<String> uniqueNames = new ArrayList<String>();

		Scanner namescanner = new Scanner(System.in);

		for (int i = 0; i < playerCount; i++) {
			String name;
			do {
				System.out.printf("\nEnter the name for player %d: ", (i + 1));
				name = namescanner.nextLine();

				if (uniqueNames.contains(name)) {
					System.out.println("That name is already taken");
				}
			} while (uniqueNames.contains(name));

			uniqueNames.add(name);
			// each player starts from the Start square with a balance of 80 Ecos
			Player player = new Player(name, 800, 0);
			players.add(player);
		}

		return players;

	}

	public static List<Square> createGameBoard() {
		List<Square> gameBoard = new ArrayList<Square>();

		GoSquare startSquare = new GoSquare("Start Square", 0);
		RecyclingSquare AluAlley = new RecyclingSquare("Aluminium Alley", 1);
		RecyclingSquare CardCourt = new RecyclingSquare("Cardboard Court", 2);
		CarbonOffsetSquare WalkWoods = new CarbonOffsetSquare("Walking Woods", 3);
		CarbonOffsetSquare BikeBeach = new CarbonOffsetSquare("Biker Beach", 4);
		CarbonOffsetSquare FootField = new CarbonOffsetSquare("Footprint Field", 5);
		TimeoutSquare TimeOut = new TimeoutSquare("Timeout Treehouse", 6);
		RenewableEnergySquare SolarCity = new RenewableEnergySquare("Solar City", 7);
		RenewableEnergySquare Turbine = new RenewableEnergySquare("Turbine Town", 8);
		RenewableEnergySquare Hydro = new RenewableEnergySquare("Hydro-Electric Avenue", 9);
		SustainabilitySquare VegValley = new SustainabilitySquare("Vegan Valley", 10);
		SustainabilitySquare Invest = new SustainabilitySquare("Investment Island", 11);

		gameBoard.addAll(Arrays.asList(startSquare, AluAlley, CardCourt, WalkWoods, BikeBeach, FootField, TimeOut,
				SolarCity, Turbine, Hydro, VegValley, Invest));

		if (gameBoard == null || gameBoard.isEmpty()) {
			throw new IllegalArgumentException("Invalid Game Board");
		} else {
			return gameBoard;
		}

	}

	private static void playGame(List<Player> players) {

		System.out.println();
		System.out.println("Welcome to the game!");
		System.out.println("Each player begins with a balance of 800 ecos.");

		List<Square> gameBoard = createGameBoard();
		Dice dice = new Dice();

		while (true) {
		 Iterator<Player> playerIterator = players.iterator();
		    while (playerIterator.hasNext()) {
		        Player player = playerIterator.next();
		        boolean takenTurn = false;
				
				System.out.println("\nIt's " + player.getName() + "'s turn.\n");

				while (!takenTurn) {
					System.out.println("Enter 1 to roll the dice");
					System.out.println("Enter 2 to build a minor or major development");
					System.out.println("Enter 3 to quit the game \n");
	
					Scanner choicescanner = new Scanner(System.in);
					int choice = choicescanner.nextInt();
	
					switch (choice) {
					case 1:
						dice.rollDice();
						int oldPosition = player.getPosition();
						int newPosition = (player.getPosition() + dice.getDiceTotal()) % gameBoard.size();
						player.setPosition(newPosition);
						Square currentSquare = gameBoard.get(newPosition);
	
						// Check if the player has passed Go and award bonus
						if (newPosition < oldPosition && newPosition != 0) {
							GoSquare goSquare = (GoSquare) gameBoard.get(0); // cast to GoSquare to access function
							player.addEcos(goSquare.getBonus());
							System.out.println(player.getName() + " passed Go and received a bonus of "
									+ goSquare.getBonus() + " Ecos.");
						}else if(newPosition == 0) {
							GoSquare goSquare = (GoSquare) gameBoard.get(0); // cast to GoSquare to access function
							player.addEcos(goSquare.getBonus());
						}
	
						currentSquare.displaySquareMessage();
	
						if (currentSquare instanceof StreetSquare) {
							StreetSquare property = (StreetSquare) currentSquare;
							if (property.isOwned() && (property.getOwner() != player)) {
								System.out.println("This area has already been invested in by " + property.getOwner().getName()+ "\n");
								// Work out rent. Polymorphism - don't need to use 'instanceof'
								int rent;
								if (property.hasGlampsite) {
									rent = property.getLandPriceWithGlampsite();
								} else if (property.hasCampsite) {
									rent = property.getLandPriceWithCampsite();
								} else {
									rent = property.getLandPrice();
								} 
								
								// Make player pay owner
								if (player.getBalance() >= rent) {
									player.deductEcos(rent);
									property.getOwner().addEcos(rent);
									System.out.println(player.getName() + " has paid " + rent + " Ecos to support "
											+ property.getOwner().getName() + "'s area");
									System.out.println(player.getName() + " new balance: " + player.getBalance());
									System.out.println(
											property.getOwner().getName() + "'s new balance: " + property.getOwner().getBalance()+ "\n");
								} else {
									System.out.println(player.getName() + " does not have enough Ecos to support "
											+ property.getSquareName() + "'s area");
									// Handle player having to sell project shares or exiting game
	
									System.out.println("You can sell your projects in one of your areas to support this areas.");
	
									if (player.getOwnedSquares().size() > 0) {
										System.out.println("The areas you have invested in :");
										for (StreetSquare ownedProperty : player.getOwnedSquares()) {
											System.out.println(ownedProperty.getSquareName() + " - sell price: "
													+ (ownedProperty.getBuyCost() / 2));
										}
										System.out.println(
												"Enter the name of the area you want to sell your projects from or type 'none' to exit the game:");
										Scanner sellScanner = new Scanner(System.in);
										String input = sellScanner.nextLine();
	
										if (!input.equalsIgnoreCase("none")) {
											for (StreetSquare ownedProperty : player.getOwnedSquares()) {
												if (ownedProperty.getSquareName().equalsIgnoreCase(input)) {
													player.sellProperty(ownedProperty, ownedProperty.getBuyCost() / 2);
													ownedProperty.setOwner(null);
													ownedProperty.setOwned(false);
													System.out.println(player.getName() + " sold "
															+ ownedProperty.getSquareName() + " for "
															+ (ownedProperty.getBuyCost() / 2) + " Ecos.");
	
													if (player.getBalance() >= rent) {
														// support green project
														player.deductEcos(rent);
														property.getOwner().addEcos(rent);
														System.out.println(player.getName() + " pays " + rent
																+ " Ecos to support " + property.getOwner().getName() + "'s area");
														System.out.println(
																player.getName() + " new balance: " + player.getBalance());
														System.out.println(property.getOwner() + " new balance: "
																+ property.getOwner().getBalance());
														break;
													} else {
														System.out.println(
																"You still don't have enough Ecos to support this area. You can sell more shares or type 'none' to exit the game.");
														break;
													}
												}
											}
										}
									}
	
									if (player.getBalance() < rent) {
										System.out
												.println(player.getName() + " has ran out of Ecos and is out of the game.");
										System.out.println("But their efforts & support could help to Save the Planet!");
										players.remove(player);
										for (StreetSquare square : player.getOwnedSquares()) {
											square.setOwner(null);
											square.setOwned(false);
										}
										if (players.size() == 1) {
											Player winner = players.get(0);
											System.out.println(winner.getName() + " has won the game!");
											System.out.printf("%s's final balance is %d Ecos and %s has invested in %d area(s).", winner.getName(), winner.getBalance(),winner.getName(),
													winner.getOwnedSquares().size());
											System.out.println(winner.getName() + "'s wise investments has created a green energy revolution, the Planet has been Saved!");
											return;
										}
									}
	
								}
							} else if (property.getOwner() == player){
								System.out.println("You already own this property");
							} else {
								System.out.println ("Your current balance is " +player.getBalance()+ " Ecos");
								System.out.println("Would you like to invest in " + property.getSquareName() + " for "
										+ property.getBuyCost() + " Ecos? (y/n)");
								Scanner scanner = new Scanner(System.in);
								String input = scanner.nextLine();
								if (input.equalsIgnoreCase("y")) {
									if (player.getBalance() >= property.getBuyCost()) {
										property.setOwner(player);
										property.setOwned(true);
										player.deductEcos(property.getBuyCost());
										player.addOwnedSquare(property);
										System.out.println(player.getName() + " has invested in " + property.getSquareName() + ".");
										if (player.ownsAllSquaresOfType(property.getClass())) {
											System.out.println(player.getName() + " has invested in all " +property.getFieldName() + " areas.");
											System.out.println("Enter 2 to build a " + property.getMinorDevName() + ". Enter 0 to skip.");
											Scanner buildScanner = new Scanner(System.in);
											int buildChoice = buildScanner.nextInt();
	
											switch (buildChoice) {
											case 2:
												buildOnSquare(player);
												break;
											case 0:
												break;
											default:
												System.out.println("Invalid choice. Skipping build option.");
												break;
											}
										}
									} else {
										System.out.println(player.getName() + " does not have enough Ecos to invest in "
												+ property.getSquareName() + ". \n");
									}
								} else if (input.equalsIgnoreCase("n")) {
									offerSquareToOtherPlayers(property, player, players);
								} else {
									System.out.println("Invalid input. Please enter 'y' or 'n'.");
								}
							}
								
	
						}
						// checking if player's balance is zero - rent deduction code to go above this
						if (player.getBalance() < 0) {
							System.out.println(player.getName() + " is bankrupt and out of the game.");
							players.remove(player);
							for (StreetSquare square : player.getOwnedSquares()) {
								square.setOwner(null);
								square.setOwned(false);
								
							}
							if (players.size() == 1) {
								Player winner = players.get(0);
								System.out.println(winner.getName() + " has won the game!");
								System.out.printf("%s's final balance is %d Ecos and %s has invested in %d areas", winner.getName(), winner.getBalance(),winner.getName(),
										winner.getOwnedSquares().size());
								System.out.println(winner.getName() + "'s wise investments has created a green energy revolution, the Planet has been Saved!");
								return;
							}
						}
	
						System.out.println("Your turn is now over " + player.getName());
						System.out.printf("Your current balance is %d Ecos and you have invested in %d area(s) \n", player.getBalance(),
								player.getOwnedSquares().size());
	
						takenTurn = true;
						break;
					case 2:
						if(player.getOwnedSquares().size() == 0) {
							System.out.println("You haven't invested in any areas yet.. Please select another option:");
							break;
						}else {
							buildOnSquare(player);
							takenTurn = true;
							break;
						}
					case 3:
						System.out.println(player.getName() + " has left the game.\n");
						playerIterator.remove();
						if (players.size() == 1) {
							Player winner = players.get(0);
							System.out.println(winner.getName() + " has won the game!");
							System.out.printf("%s's final balance is %d Ecos and %s has invested in %d areas \n", winner.getName(), winner.getBalance(),winner.getName(),
									winner.getOwnedSquares().size());
							System.out.println(winner.getName() + "'s wise investments has created a green energy revolution, the Planet has been Saved!");
							return;
						}
						takenTurn = true;
						break;
					default:
						System.out.println("Invalid choice. Please enter 1 to roll the dice or 3 to leave the game.");
						break;
					}
			    } // while !takenTurn loop end
			}

		}
	}

	private static void buildOnSquare(Player player) {
		/*
		if(player.getOwnedSquares().size() == 0) {
			System.out.println("You haven't invested in any areas yet.. Returning to game.");
			return;
		}
		*/
		System.out.println("Select the area you want to develop:");
		Set<StreetSquare> ownedSquares = player.getOwnedSquares();
		List<StreetSquare> ownedSquaresList = new ArrayList<>(ownedSquares);
		for (int i = 0; i < ownedSquares.size(); i++) {
			System.out.println((i + 1) + ". " + ownedSquaresList.get(i).getSquareName());
		}
		Scanner choicescanner = new Scanner(System.in);
		int selectedIndex = choicescanner.nextInt() - 1;
		if (selectedIndex >= 0 && selectedIndex < ownedSquares.size()) {
			StreetSquare selectedSquare = ownedSquaresList.get(selectedIndex);
			if (player.ownsAllSquaresOfType(selectedSquare.getClass())) {
				if (selectedSquare.getNumberOfMinorDevs() < 3) {
					int cost = selectedSquare.getBuildCampsiteCost();
					if (player.getBalance() >= cost) {
						player.deductEcos(cost);
						selectedSquare.addCampsite();
						System.out.println("You built a " +selectedSquare.getMinorDevName() + " on " + selectedSquare.getSquareName());
					} else {
						System.out.println("You don't have enough ecos to build a " +selectedSquare.getMinorDevName()+ ".");
					}
				} else {
					int cost = selectedSquare.getBuildGlampsiteCost();
					if (player.getBalance() >= cost) {
						player.deductEcos(cost);
						selectedSquare.setHasGlampsite(true);
						System.out.println("You built a " +selectedSquare.getMajorDevName() +" on " + selectedSquare.getSquareName());
					} else {
						System.out.println("You don't have enough ecos to build a " +selectedSquare.getMajorDevName()+ ".");
					}
				}
			} else {
				System.out.println("You must own all areas of the same type to build.");
			}
		} else {
			System.out.println("Invalid selection.");
		}
	}
	
	
	public static void offerSquareToOtherPlayers(StreetSquare currentSquare, Player currentPlayer, List<Player> players) {
	    int highestBid = 0;
	    Player highestBidder = null;
	    int minimumBid = (int) Math.ceil(currentSquare.getBuyCost() * 0.5); // Set minimum bid as 50% of the square's original price
	    
	    System.out.println();
	    System.out.println("This square will now be offered to the other players to bid on. \n");
        System.out.println("The starting bid is " + minimumBid + " Ecos.");
        
	    for (Player player : players) {
	        if (player == currentPlayer) {
	            continue; // skip current player
	        }
	        
	        
	        System.out.println(player.getName() +", your current balance is " + player.getBalance()+ " Ecos.");
	        int bid = askBidAmount(player.getName() + ", enter your bid for " + currentSquare.getSquareName() + " or enter -1 to skip:");

	        while(bid == highestBid) {
	        	bid = askBidAmount(player.getName() + ", please increase your bid or enter -1 to skip:");
	        }
	        
	        if (bid >= minimumBid && bid > highestBid && player.getBalance() >= bid) {
	            highestBid = bid;
	            highestBidder = player;
	        }else if (bid >= minimumBid && bid > highestBid && player.getBalance() < bid) {
	            System.out.println(player.getName() + ", your bid is higher, but you cannot afford this square.");
	        }
	    }

	    if (highestBidder != null) {
	        highestBidder.deductEcos(highestBid);
	        highestBidder.addOwnedSquare((StreetSquare) currentSquare);
	        currentSquare.setOwner(highestBidder);
	        currentSquare.setOwned(true);
	        System.out.println(highestBidder.getName() + " has bought " + currentSquare.getSquareName() + " for " + highestBid + " Ecos.");
	    } else {
	        // if no other players want to buy the square, do nothing
	        System.out.println("No other players want to buy " + currentSquare.getSquareName() + ".\n");
	      
	    }
	}

	public static int askBidAmount(String prompt) {
	    Scanner scanner = new Scanner(System.in);
	    while (true) {
	        System.out.println(prompt);
	        if (scanner.hasNextInt()) {
	            int bid = scanner.nextInt();
	            if (bid >= -1) {
	                return bid;
	            } else {
	                System.out.println("Invalid bid. Enter a valid amount or -1 to skip.");
	            }
	        } else {
	            System.out.println("Invalid input. Please enter a valid number or -1 to skip.");
	            scanner.next(); // Discard invalid input
	        }
	    }
	}


}
