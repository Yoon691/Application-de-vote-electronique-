# Mif03_TP

***TP7***

 1. Analyse de l'état initial de votre application

    Déploiement sur Tomcat : https://192.168.75.70/api/client
    Déploiement sur Nginx : https://192.168.75.70/client

    - le temps de chargement de la page HTML initiale:

        - script: (performance.timing.responseEnd -performance.timeOrigin);
        
        - valeur: 111.78 ms

    - le temps d'affichage de l'app shell:
        
        - script: (performance.getEntries().filter(x =>
                    x.name.endsWith('.js'))[performance.getEntries().filter(x =>
                        x.name.endsWith('.js')).length - 1].responseEnd) 
        
        - valeur: 151.58 ms

    - le temps d'affichage du chemin critique de rendu (CRP):

        - script: (performance.timing.domComplete -performance.timeOrigin);

        - valeur: 251.78 ms


2. Déploiement des fichiers statiques sur nginx

    Déploiement sur nginx https://192.168.75.96/client/

    - le temps de chargement de la page HTML initiale:

        - valeur :  24.45 ms

        - pourcentage d'amélioration : 78%

    - le temps d'affichage de l'app shell:

        - valeur : 92.45 ms

        - pourcentage d'amélioration : 39%

    - le temps d'affichage du chemin critique de rendu (CRP):

        - valeur : 173.45 ms

        - pourcentage d'amélioration : 31% 


3. Optimisation de votre application

    Utilisation de CDN:
        
        Déja fait dans le TP d'avant, Aucune amélioration dans les valeurs en essayent des liens differentes 
    pour inclure  differentes versions de Jquery en utilisant la CDN.

![Refactor](./Refactor.PNG)

![Async](./Async.PNG)

![Minification](./Minification.PNG)


