import "firebase/auth";
import "firebase/analytics";
import { initializeApp } from "firebase/app";

const firebaseConfig = {
    apiKey: "AIzaSyDhbTfQP1erNJ80_I4NCKmbuPv7s-xivo0",
    authDomain: "cubemaze-aa157.firebaseapp.com",
    projectId: "cubemaze-aa157",
    storageBucket: "cubemaze-aa157.appspot.com",
    messagingSenderId: "989476835420",
    appId: "1:989476835420:web:dc0e3bc2910fe58055d699",
    measurementId: "G-KZZC5WZ48Z"
};

export const app = initializeApp(firebaseConfig);