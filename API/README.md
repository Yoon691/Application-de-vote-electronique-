# Mif03_TP

***TP3***

* Les parties traiter :

***Nous avons traité toutes les parties demandé dans ce TP3 :***

- les suivants : 
    - Filtre Authentification
    - Filtre Autorisation
    - La chaîne de responsabilités
    - Pattern MVC
    - Utilisation des en-têtes HTTP de date
    - Utilisation d'Entity Tags (ETag)


* Dernière partie traiter : 

    ***La dernière partie***  

    - 3.2 Utilisation d'Entity Tags (ETag)

***Remarque*** : le ETag marche bien sur notre VM sur l'adresse http://192.168.75.70:8080/v2/ mais par contre sur nginx
à l'adresse https://192.168.75.70/api/v2/ n'affiche pas le Etag à cause du reverse proxy qui ne laisse pas passer l'en-tête If-None-Match.

***Problème technique de configuration pas de solution trouver malgré l'aide du prof.***

* Les différents choix d'implémentation :

    -  Suppression de la servlet DeleteVote:
       
       Un choix de conception où nous avons attribué la suppression de vote depuis ballot.jsp a la servlet Ballot 
       et la suppression de vote depuis listBallots.jsp a la servlet ListBallots
       
    - Changer index.html -> index.jsp :

      Pour avoir un menu dynamique selon le sujet qui affiche le menu selon le status de 
      l'utilisateur connecté ou non nous avons choisi d'utiliser un fichier .jsp pour faire 
      le test de connectivité.
      
    - Ajout d'un filtre Encodage : 

      Pour éviter tous les problèmes d'encodage en UTF-8 au plus de préciser ça 
      dans les jsp pour les réponses nous avons ajouté aussi un filtre Encodage 
      au début de la chaine de responsabilité qui fixe l'encodage des requêtes.
    
    - Test de connectivité dans resultat.jsp :
      Comme nous avons le lien à résultat avant la connexion et après
      le lien pour le css change entre les deux pour que la page garde 
      la forme imposer en css nous avons ajouté un test de connectivité 
      qui change le lien de css au fur et à mesure.
      
* Réponses aux questions :
    
    - à quoi vous sert le contrôleur principal ?

      C'est lui le chef d'orchestre de l'application après le filtrage du lien
      de la requête le contrôleur l'envoie à la servlet déléguer pour la traiter 
      et transmettre les informations nécessaires au jsp pour affiche la réponse.
* URLs de TP :
  
  - http://192.168.75.70:8080/v2/
  - https://192.168.75.70/api/v2/
    

    