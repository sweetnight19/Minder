## Eines necessàries per el funcionament del programa

- Client MySQL (en el nostre cas `phpmyadmin` )
- IDE (en el nostre cas `IntelIJ` )

---

## Llibreries extres utilitzades en tots els móduls del projecte

- GSON
- MySQL

Les dues llibreries s'afegeixen al projecte anant a `file` -> `projectStructure` -> `libraries`. 
Tot seguit es clica al `+` dins de l'apartat `java` del menú que apareix i seleccionar tots el móduls del projecte.

---

##Procés de funcionament del projecte

1. Posar en marxa el `phpmyadmin` per inicialitzar la base de dades
2. Inicialitzar `Intel IJ` per indexar tot el projecte
3. Inicialitzar el servidor mitjançant el botó `run` i connectar-lo automàticament a la base de dades 
4. Inicialitzar el client (o clients si es volen inicialitzar múltiples clients) mitjançant el botó `run`
        del `main` del mòdul del client.
5. En executar el client, s'obre una finestra que permet a l'usuari `iniciar sessió` en el cas que tingui
        un usuari registrat o, en el cas contrari `registrar-se`, per tal de poder utilitzar l'aplicació.
6. Ja iniciada la sessió, l'usuari es troba en la pantalla principal on pot anar acceptant o descartant altres
        usuaris segons el seu gust. 
   1. Si l'usuari ho prefereix, pot canviar la pantalla entre `veure el perfil`, `llista del xat` o la 
            `pantalla principal` mitjançant l'ús de la `toolbar`.
      1. `Veure el perfil`: Permet veure les teves dades personals i modificar-les segons les teves preferéncies.
                Permet tancar la sessió si l'usuari ho desitja, peró després haurà de tornar a introduïr les dades d'accés
        2. `Llista de xats`: Permet veure tots les conversacións actives amb altres usuaris i reprendre en qualsevol moment.
        3. `Pantalla principal`: Permet a l'usuari enviar "likes" a altres usuaris que comparteixin gustos o que físicament
                l'atraigui.