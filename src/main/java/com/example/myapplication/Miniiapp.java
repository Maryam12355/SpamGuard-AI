
package com.example.miniiapp;

import com.example.myapplication.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.tensorflow.lite.Interpreter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Miniiapp extends Activity {

    // UI components
    EditText edtMessage;
    Button btnAnalyze, btnClear;
    TextView txtResult;

    // TensorFlow Lite AI model
    Interpreter interpreter;

    // Stores words and their numerical indexes
    Map<String, Integer> vocabulary = new HashMap<>();

    // Maximum number of words accepted by the model
    final int SEQUENCE_LENGTH = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miniiapp);

        // Connect Java variables with XML components
        edtMessage = findViewById(R.id.edtMessage);
        btnAnalyze = findViewById(R.id.btnAnalyze);
        btnClear = findViewById(R.id.btnClear);
        txtResult = findViewById(R.id.txtResult);

        // Load AI model and vocabulary when app starts
        try {
            interpreter = new Interpreter(loadModelFile());
            loadVocabulary();

            Toast.makeText(this,
                    "AI Model Loaded Successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this,
                    "Model Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        // Analyze button click event
        btnAnalyze.setOnClickListener(v -> {

            // Get message entered by user
            String message = edtMessage.getText()
                    .toString().trim();

            // Check if input is empty
            if (message.isEmpty()) {
                Toast.makeText(this,
                        "Please enter a message",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // Check whether AI model is loaded
            if (interpreter == null) {
                txtResult.setText("AI Model is not loaded.");
                return;
            }

            try {
                // Convert message into numerical input
                float[][] input = convertMessage(message);

                // Store AI prediction
                float[][] output = new float[1][1];

                // Run the TensorFlow Lite model
                interpreter.run(input, output);

                float probability = output[0][0];

                // Classify message using 0.5 threshold
                if (probability >= 0.5f) {
                    txtResult.setText(
                            "SPAM MESSAGE!\n\nSpam Probability: "
                                    + String.format(Locale.US, "%.2f",
                                    probability * 100) + "%"
                    );
                } else {
                    txtResult.setText(
                            "NOT SPAM!\n\nNormal Message\n\nSpam Probability: "
                                    + String.format(Locale.US, "%.2f",
                                    probability * 100) + "%"
                    );
                }

            } catch (Exception e) {
                txtResult.setText("Prediction Error: " + e.getMessage());
            }
        });

        // Clear button removes input and previous result
        btnClear.setOnClickListener(v -> {
            edtMessage.setText("");
            txtResult.setText("Your result will appear here.");
        });
    }

    // Load trained TensorFlow Lite model from assets folder
    private MappedByteBuffer loadModelFile() throws IOException {

        android.content.res.AssetFileDescriptor fileDescriptor =
                getAssets().openFd("spam_model.tflite");

        FileInputStream inputStream =
                new FileInputStream(fileDescriptor.getFileDescriptor());

        FileChannel fileChannel = inputStream.getChannel();

        MappedByteBuffer modelBuffer = fileChannel.map(
                FileChannel.MapMode.READ_ONLY,
                fileDescriptor.getStartOffset(),
                fileDescriptor.getDeclaredLength()
        );

        inputStream.close();
        fileDescriptor.close();

        return modelBuffer;
    }

    // Load vocabulary words from vocab.txt
    private void loadVocabulary() throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        getAssets().open("vocab.txt")
                )
        );

        String word;
        int index = 0;

        while ((word = reader.readLine()) != null) {
            vocabulary.put(word, index);
            index++;
        }

        reader.close();
    }

    // Convert user message into numerical sequence for AI model
    private float[][] convertMessage(String message) {

        float[][] input = new float[1][SEQUENCE_LENGTH];

        // Convert text to lowercase and remove punctuation
        String cleanedMessage = message.toLowerCase(Locale.ROOT)
                .replaceAll("[\\p{Punct}]", "")
                .trim();

        if (cleanedMessage.isEmpty()) {
            return input;
        }

        // Split message into individual words
        String[] words = cleanedMessage.split("\\s+");

        // Convert each word into its vocabulary index
        for (int i = 0; i < Math.min(words.length, SEQUENCE_LENGTH); i++) {

            int wordIndex = vocabulary.getOrDefault(words[i], 1);

            input[0][i] = (float) wordIndex;
        }

        return input;
    }

    // Close AI interpreter when activity is destroyed
    @Override
    protected void onDestroy() {
        if (interpreter != null) {
            interpreter.close();
        }

        super.onDestroy();
    }
}
