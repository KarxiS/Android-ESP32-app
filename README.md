# Android-ESP32-app 
aplikacia sluzi na ovladanie mojej vlastnej meteostanice ktoru som si vyskladal. 
meteostanica je naprogramovana v c. Flashnuta cez Arduino do esp32 jednotky. 

aplikacia je naprogramovaná zväčša v kotline s prvkami javy. ma 2 hlavne aktivity, kde jedna aktivita fetchuje JSON data z lokalneho servera 192.168.4.1/data.json a ulozi si ich do vlastnej databazy.Nasledne vramci aktivity a fragmentov zdieľam tuto databazu. Fragmenty v hlavnej aktivite dokážu zobrazit data v grafe alebo v zrhnuti. druha aktivita ponuka ovladanie LEDky cez GET requesty

odkaz na kod do ESP32(C, arduino only ):https://github.com/MaxerSK/AndroidESP
#TODO Pridat schemu ESP32 a 3D model na vytlacenie obalu v 3D tlaciarni, nech si mozu aj ostatny vyskladat domacu meteostanicu :)
