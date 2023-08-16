# backenddevtest
Service that provides similar product details from initial product identifier.

## Structure of the multi-module project
<img src="https://imgur.com/H0rIi8S.png"/>

## Getting started
### Prerequisites
The prerequisites to be able to run this microservice locally are:
- Have a version of Azul Zulu OpenJDK 17 or higher installed.
- Have an installed version of Maven 3.9.3 or higher.
- Have a git client installed.

### Installing

The first step is clone this repository from GitHub:

```
git clone https://github.com/feguiluz799/backenddevtest.git
```

To generate code from the definitions of api contracts you must execute (in```/backenddevtest```folder):

```
mvn clean install
```

### Running the app in local environment

To start the application in a local environment you must execute (in```/backenddevtest```folder):

```
mvn spring-boot:run
```

