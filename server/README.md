# Mif03_TP

***TP2***

Les parties traiter : 

Nous avons traité toutes les parties demander dans ce TP2 de la création de projet, amélioration du code donner par le prof, implémentation des servlet, jsp et les fonctionnalités demander et jusqu'à l'intégration continue
* Dernière partie traiter : 

    - 1.4(la dernière) pour les fonctionnalités  + Intégration contenue
  
* les différents choix d'implémentation :

    -  la possibilité que l'utilisateur puisse voter blanc :
       
        Nous avons créé un candidat s'appelle VOTE Blanc et nous l'avons ajouté dans la Map de Candidat dans la servlet Init.
   
    - Changer index.html -> index.jsp :
    
        Pour avoir un menu dynamique selon le sujet qui affiche le menu selon le status de l'utilisateur connecté ou non.
        Nous avons choisi cette méthode faire ça.
      
* Réponses au question :
    -  Pour la servlet de déconnexion : Quel est le type de redirection que vous devez employer pour cela :

            'response.sendRedirect("./");'  
                    
    qui donne :
            
           '302 Found'
      
* URLs de TP :
  
  - http://192.168.75.70:8080/v1/
  - https://192.168.75.70/api/v1/


#le 16/10/2021:

    * a faire  :
        - pas plusieur utilisateur connecter en méme temps dans un seul navigateur
        - regarde le nombre de vote 
        - la suppresion de vote 
        - regarde le cache 
        - regarde le Etag que dans :8080 pas dans https
        - .gitignore
        - change le nom de dossier a serverMVC
        - netoyer le code 
        - readme branch et master 
        - mergue request
    * fait : 

        - atualiser la page refresh 

    * marche : 
        * Post :
            - index.html sans login
            - Connextion
            - vote
            -- Code metier dans vote.jsp
            -deconxion vm https
        * Get : 
            - resultat
            - voter 
            - mettre a jour profil
            - votre Vote 
            - listBallot
            - deconection depuis la page d'accuile
            - deconection AVEC un lien /election
            - supprimer un vote
            - modifier nom