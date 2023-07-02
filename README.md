# Android-ESP32-app 
Táto aplikácia slúži na ovládanie vlastnej meteostanice, ktorú som si hardverovo aj zostavil. 
Meteostanica je naprogramovaná v jazyku C a bola flashnutá cez Arduino do jednotky ESP32.

Aplikácia je naprogramovaná prevažne v jazyku Kotlin s prvkami Javy. Má dve hlavné aktivity. Prvá aktivita načítava dáta vo formáte JSON z lokálneho servera 192.168.4.1/data.json a ukladá ich do vlastnej databázy. 
Tieto dáta sú následne zdieľané medzi fragmentmi v rámci hlavnej aktivity. 
Fragmenty dokážu zobrazovať dáta v grafe alebo v zhrnutí. Druhá aktivita ponúka ovládanie LED diódy prostredníctvom GET požiadaviek.

JSON formát musí byť dostupný na webovej adrese 192.168.4.1/data.json a musí obsahovať takúto štruktúru údajov : {name:name,temp:temp,humidity:humidity}. Odkaz na kód pre ESP32 (C, iba Arduino) je dostupný na adrese: https://github.com/MaxerSK/AndroidESP

#TODO Pridať schému pre ESP32

