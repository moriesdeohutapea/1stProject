# First Project

## Overview
First Project is a modern Android application built with a focus on clean architecture and modularization. It leverages best practices to ensure maintainability, scalability, and testability.

## Tech Stack
- Kotlin  
- Coroutines  
- Flow  
- Koin (Dependency Injection)  
- Retrofit (Networking)  
- Chucker (Network Inspection)  
- ViewModel (Architecture Component)  
- Modular architecture (`core`, `app`)

## Architecture
The project follows Clean Architecture principles, ensuring a clear separation of concerns between layers:
- **Data:** Responsible for data sources and repositories  
- **Domain:** Contains business logic and use cases  
- **Presentation:** Handles UI and state management  

## Module Structure
- **core:** Implements domain and data logic, including use cases and repositories  
- **app:** Contains the presentation layer and serves as the dependency injection entry point  

## Setup Instructions
1. Clone the repository:  
   `git clone <repository-url>`  
2. Open the project in Android Studio  
3. Build and run the project on an emulator or device  

## Features
- User listing fetched from a REST API  
- Network calls implemented with Retrofit and Coroutines  
- Structured ViewModel with state management using Flow  

## Preview
*Screenshots and demo videos will be added here in future updates.*

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
