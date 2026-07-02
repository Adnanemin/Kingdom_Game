# Kingdom Game

A console-based kingdom management and strategy game developed in Java as an Object-Oriented Programming project.

The player manages a kingdom by constructing buildings, training military units, managing resources, and making strategic decisions over multiple game days. The objective is to prepare the kingdom for a final battle against an enemy kingdom.

---

## Features

- Kingdom management
- Resource management (Gold, Food, Population, Happiness)
- Building construction system
- Military unit training
- Difficulty selection (Easy, Normal, Hard)
- Random event system
- Dynamic rule engine
- Final battle simulation
- Custom exception handling
- Modular manager-based architecture

---

## Project Structure

The project is organized into multiple packages with clear responsibilities:

- **buildings** – Building hierarchy and daily building effects
- **units** – Military units and army management
- **events** – Random event system
- **managers** – Game mechanics such as building, training, taxation, and events
- **rules** – Dynamic rule system
- **setup** – Game initialization, session management, and difficulty configuration
- **util** – Utility classes such as generic outcome handling
- **exceptions** – Custom exception classes

---

## Object-Oriented Concepts

This project demonstrates the following OOP concepts:

- Encapsulation
- Abstraction
- Inheritance
- Runtime Polymorphism
- Method Overloading
- Generics
- Exception Handling
- Separation of Concerns
- Information Hiding
- Subtyping & Multityping
- Composition
- Extensibility
- Programming to Interfaces

---

## How to Play

1. Start the game.
2. Choose a difficulty level.
3. Enter the name of your kingdom.
4. Manage your kingdom day by day by:
   - Building structures
   - Training military units
   - Adjusting tax rates
   - Creating custom rules
5. Survive until the final battle and defeat the enemy kingdom.

---

## Design Highlights

The project follows a modular architecture where responsibilities are separated into dedicated manager classes. Game state is maintained by the `Kingdom` and `GameSession` classes, while game mechanics such as random events, building construction, taxation, and unit training are delegated to specialized managers. Difficulty configuration is encapsulated within the `Difficulty` enum, and custom exceptions are used to handle invalid game operations in a controlled manner.

---

## Author

**Adnan Emin Nalçacı**
University of Messina  
Bachelor's Degree in Data Analysis
