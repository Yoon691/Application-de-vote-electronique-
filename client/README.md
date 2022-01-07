# Mif03_TP

***TP5***

* Les parties traiter :

***Nous avons traité toutes les parties demandé dans ce TP5 :***

- les suivants : 
    - SPA fonctionnelle avec toutes les parties demander.
    - Menu différent entre utilisateur connecter et non connecter.
    - Menu déplier / replier, mécanisme de routage.
    - Édition du nom de l'utilisateur depuis l'interface.
    - Moteur de templating avec Mustache.
    - Requêtes AJAX en utilisant fetch.
    - Actualisation de résultats chaque 5 s.
    - Gestion des événements et des erreurs en utilisant des fenêtres modale.

* Dernière partie traiter : 

    ***La dernière partie***  

    - 2.4. Finalisation de votre application


* Les différents choix d'implémentation :

    -  3 fichier JS :
        - Client.js : contient le code de tout qui concerne les requêtes AJAX.
        - Spa.js : tout le code qui fait la gestion de la single-page-application.
        - template.js : contient la fonction de templeting.
       
    - Interdit l'accès a les opérations qui demandent une connexion à utilisateur non-connecter :

        Si l'utilisateur est non authentifié et il essaye d'accéder à des parties demande une authentification en tapant le hash dans l'URL talque : #vote, #monCompte ..., il sera rédiger vers index.
   
    
* URLs de TP :
    
       Nous avons déployé directement notre application sur Nginx (proposition du prof).
  
  - https://192.168.75.70/client
    
