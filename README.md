# SpamGuard AI – AI-Based SMS Spam Detection App

## About the Project
SpamGuard AI is an Android application that uses Artificial Intelligence and Machine Learning to identify whether an SMS message is **SPAM** or **NOT SPAM**.

The application uses a TensorFlow Lite model to classify messages directly on an Android device.

## Features
- Simple and user-friendly interface.
- AI-based SMS spam detection.
- Classifies messages as SPAM or NOT SPAM.
- Analyze Message button.
- Clear button to reset the input.
- Splash screen.
- On-device machine learning using TensorFlow Lite.

## Technologies Used
- Java
- XML
- Android Studio
- Python
- TensorFlow
- TensorFlow Lite
- Machine Learning

## Machine Learning Model
The model was trained using the SMS Spam Collection dataset.

- Total messages: 5,169
- Ham messages: 4,516
- Spam messages: 653
- Training samples: 4,135
- Testing samples: 1,034
- Test accuracy: Approximately 97.58%

*Note: This accuracy represents the model's performance on the test dataset.*

## How to Run the Project
1. Download or clone this repository.
2. Open the project in Android Studio.
3. Allow Gradle to sync.
4. Connect an Android device or start an emulator.
5. Click the Run button.

## Project Structure
```text
SpamGuard-AI/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       ├── res/
│   │       ├── assets/
│   │       │   ├── spam_model.tflite
│   │       │   └── vocab.txt
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew
└── gradlew.bat
```

## How to Use
1. Open SpamGuard AI.
2. Enter an SMS message.
3. Click **ANALYZE MESSAGE**.
4. View the prediction: SPAM or NOT SPAM.
5. Click **CLEAR** to analyze another message.

## Project Purpose
The purpose of this project is to demonstrate how a trained Machine Learning model can be integrated into an Android application using TensorFlow Lite.

## Author
BSCS Student Project

## License
This project is developed for educational purposes.
