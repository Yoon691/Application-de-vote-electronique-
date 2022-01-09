# Mif03_TP

***TP7***

 1. Analyse de l'état initial de l'application

    ***Tomcat***

    Déploiement sur Tomcat : https://192.168.75.70/api/client
    

    - VPN

        - le temps de chargement de la page HTML initiale:

            - script: (performance.timing.responseEnd -performance.timeOrigin);
        
            - valeur: 156.80 ms

        - le temps d'affichage de l'app shell:
        
            - script: (performance.getEntries().filter(x =>
                    x.name.endsWith('.js'))[performance.getEntries().filter(x =>
                        x.name.endsWith('.js')).length - 1].responseEnd) 
        
            - valeur: 415.35 ms

        - le temps d'affichage du chemin critique de rendu (CRP):

            - script: (performance.timing.domComplete -performance.timeOrigin);

            - valeur: 682.5 ms

    - wifi depuis l'intérieur de la fac

        - le temps de chargement de la page HTML initiale:

            - script: (performance.timing.responseEnd -performance.timeOrigin);
        
            - valeur: 156.80 ms

        - le temps d'affichage de l'app shell:
        
            - script: (performance.getEntries().filter(x =>
                    x.name.endsWith('.js'))[performance.getEntries().filter(x =>
                        x.name.endsWith('.js')).length - 1].responseEnd) 
        
            - valeur: 415.35 ms

        - le temps d'affichage du chemin critique de rendu (CRP):

            - script: (performance.timing.domComplete -performance.timeOrigin);

            - valeur: 682.5 ms
    - machines de TP
        - le temps de chargement de la page HTML initiale:

            - script: (performance.timing.responseEnd -performance.timeOrigin);
        
            - valeur: 156.80 ms

        - le temps d'affichage de l'app shell:
        
            - script: (performance.getEntries().filter(x =>
                    x.name.endsWith('.js'))[performance.getEntries().filter(x =>
                        x.name.endsWith('.js')).length - 1].responseEnd) 
        
            - valeur: 415.35 ms

        - le temps d'affichage du chemin critique de rendu (CRP):

            - script: (performance.timing.domComplete -performance.timeOrigin);

            - valeur: 682.5 ms        

2. Déploiement des fichiers statiques sur nginx

    Déploiement sur Nginx : https://192.168.75.70/client

    - VPN

        - le temps de chargement de la page HTML initiale:

            - valeur :  22.10 ms

            - pourcentage d'amélioration : 78%

        - le temps d'affichage de l'app shell:

            - valeur : 128.10 ms

            - pourcentage d'amélioration : 39%

        - le temps d'affichage du chemin critique de rendu (CRP):

            - valeur : 285.45 ms

            - pourcentage d'amélioration : 31% 
    - wifi depuis l'intérieur de la fac

        - le temps de chargement de la page HTML initiale:

            - valeur :  22.10 ms

            - pourcentage d'amélioration : 78%

        - le temps d'affichage de l'app shell:

            - valeur : 128.10 ms

            - pourcentage d'amélioration : 39%

        - le temps d'affichage du chemin critique de rendu (CRP):

            - valeur : 285.45 ms

            - pourcentage d'amélioration : 31% 

    - machines de TP

        - le temps de chargement de la page HTML initiale:

            - valeur :  22.10 ms

            - pourcentage d'amélioration : 78%

        - le temps d'affichage de l'app shell:

            - valeur : 128.10 ms

            - pourcentage d'amélioration : 39%

        - le temps d'affichage du chemin critique de rendu (CRP):

            - valeur : 285.45 ms

            - pourcentage d'amélioration : 31% 



3. Optimisation de votre application

    Utilisation de CDN:
        
        Déja fait dans le TP d'avant, Aucune amélioration dans les valeurs en essayent des liens differentes 
    pour inclure  differentes versions de Jquery en utilisant la CDN.

![Refactor](./Refactor.PNG)

![Async](./Async.PNG)

![Minification](./Minification.PNG)


