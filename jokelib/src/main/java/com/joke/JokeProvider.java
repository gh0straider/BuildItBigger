package com.joke;

import java.util.Random;

public class JokeProvider {
    private static final String[] jokes = {
            "A neutron walks into a bar and asks how much for a drink. The bartender replies, ‘For you, no charge.",
            "How many programmers does it take to change a light bulb ? None, that's a hardware problem",
            "Why did the chicken cross the mobius strip? To get to the same side.",
            "Why do Android developers wear glasses? Because they can't C#",
            "Why did the programmer quit his job? Because he didn't get arrays.",
            "What is an astronaut's favorite place on a computer? The Space Bar",
            "What type of computer does a horse like to eat? A Macintosh",
            "Why are PCs like air conditioners? They stop working properly if you open Windows!",
            "Why did the computer break up with the Internet? There was no Connection",
            "How do trees use computers? They log in!"
    };

    public static String getJoke(){
        int size = jokes.length;

        Random r = new Random();
        int index = r.nextInt(size);

        return jokes[index];
    }
}
