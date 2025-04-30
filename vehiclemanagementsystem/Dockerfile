FROM openjdk:17

COPY . /app
WORKDIR /app

# Compile from inside /app using correct path
RUN javac vehiclemanagementsystem/Main.java

# Run the main class with its full package name
CMD ["java", "vehiclemanagementsystem.Main"]
