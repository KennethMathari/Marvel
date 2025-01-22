# Marvel Character Guide
This project provides a solution to the Marvel Character Guide coding test. The app allows users to browse a list of Marvel characters fetched from the Marvel API and view detailed information about selected characters. Additional features like search functionality and data caching are implemented to enhance user experience. The project showcases best practices in Android development with an emphasis on clean, maintainable, and testable code.

# Tools & Technologies Used
- **Clean Architecture**: The project follows a layered architecture with Data, Domain, and Presentation layers to ensure separation of concerns and scalability.
- **Koin**: Used for dependency injection to manage class dependencies efficiently.
- **Kotlin Serialization Converter**: Integrated for seamless JSON parsing and serialization when communicating with the Marvel API.
- **Room DB**: Provides caching for the list of characters, enabling offline access and reducing network usage.
- **List-Detail Layout**: Implements an intuitive interface for listing characters and navigating to detailed character views.
- **Unit, Mockk, & Turbine**: Employed for unit and flow testing to ensure reliable business logic.
- **Secrets Gradle Plugin**: Used for securely handling sensitive API keys.
- **CI/CD Pipeline**: Configured with GitHub Actions to automate build, test, and lint checks.
- **KtLint**: Enforces Kotlin coding standards to maintain code quality.
- **Jetpack Compose**: Utilized for building modern, declarative UI components.

# Features
- **Character List Screen**: Displays a list of Marvel characters fetched from the Marvel API.
- **Character Detail Screen**: Shows detailed information about a selected character, including their description and available comics.
- **Search Functionality**: Enables users to search for characters by name.
- **Data Caching**: Implements caching using Room DB to persist character data for offline access.

# Key Design Choices
- **Clean Architecture**: Separation into Data, Domain, and Presentation layers ensures a modular, maintainable, and scalable codebase.
- **Dependency Injection with Koin**: Simplifies dependency creation and injection across layers, promoting flexibility and testability.
- **CI/CD Integration**: A GitHub Actions workflow automates linting, testing, and building, ensuring continuous integration and code quality.

# Setup Instructions
- Clone this repository.
- Create an account on the Marvel Developer Portal to obtain your API keys.
- Add your public and private keys to the local.properties file using the Secrets Gradle Plugin:
> MARVEL_PUBLIC_KEY=your_public_key
> MARVEL_PRIVATE_KEY=your_private_key
- Build and run the project in Android Studio.

# Testing
Unit and flow tests are implemented using JUnit, Mockk, and Turbine.
Run tests with:
> ./gradlew test

# Continuous Integration
The CI/CD pipeline uses GitHub Actions to automate linting, testing, and building. This ensures code quality and reliability before changes are merged.

# Linting
KtLint is used to enforce coding standards. Run the lint check with:
> ./gradlew ktlintCheck

# App Recording