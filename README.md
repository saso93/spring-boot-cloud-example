# Spring Boot & Spring Cloud
In questo progetto si è fatto utilizzo di:
1) Spring Boot
2) Spring Cloud Netflix Eureka (service discovery per i microservizi)
3) Spring Cloud Config (configurazione distribuita)
4) Spring Cloud Gateway Zuul

![Arch](/img/arch.png)

Nel nostro esempio abbiamo due microservizi indipendenti, che si registrano (service discovery) e recuperano le property dal config service (concetto di configurazione distribuita). L'intero sistema è "nascosto" dietro un API gateway (ZUUL).

**INFRASTRUTTURALI**
1. config: Config Server (sulla porta 7777)
2. discovery: Eureka Server (sulla porta 8761)
3. gateway: Zuul Proxy (sulla porta 8085)

**APPLICATIVI**
4. hello-service: servizio a scopo didattico molto basilare che utilizza una property per stampare un nome (sulla porta 8090)
5. pokemon: servizio a scopo didattico molto basilare che utilizza api esterne (pokemon) (sulla porta 8091)
					
## Come avviare i microservizi?
Avvia prima i microservizi "infrastrutturali":
1) discovery
2) config
3) gateway.

Poi tutti gli altri microservizi "applicativi":
- hello
- pokemon

## Esempio
Su http://localhost:8761/ troviamo eureka:

![EurekaDashboard](/img/EurekaTest.png)

Su http://localhost:8090/hello/hi (oppure, utilizzando il gateway su: http://localhost:8085/hello-service/hello/hi):

![HelloProperty](/img/HelloProperty.png)


Su http://localhost:8091/pokemon/type/7 (oppure, utilizzando il gateway su: http://localhost:8085/pokemon/pokemon/type/7/):

![PokemonGateway](/img/PokemonGateway.png)



## Spring Cloud Config
Grazie a questo componente abbiamo a disposizione un server centralizzato per gestire le configurazioni (e ovviamente il supporto ai client).

Spring Cloud Config (Server + Client)


### Spring Cloud Config Server
Fornisce un'API basata su risorse HTTP per la configurazione esterna (coppie nome-valore o equivalentemente, contenuto YAML). Il server è integrabile in un'applicazione Spring Boot, utilizzando l'annotazione @EnableConfigServer.
Come tutte le applicazioni Spring Boot, esso gira sulla porta 8080 di default, ma lo puoi switchare ad un’altra porta modificando l’application properties.


### Spring Cloud Config Client
Il comportamento di default per una qualsiasi applicazione che ha il Spring Cloud Config Client nel classpath è il seguente: quando viene avviato un config client, esso si lega al Config Server attraverso la proprietà bootstrap “spring.cloud.config.uri” e inizializza lo Spring Environment con le property sources remote. C’è un bootstrap.yml con l’indirizzo del server settato in “spring.cloud.config.uri” di default a localhost:8888.
Se utilizzi un’implementazione di DiscoveryClient, come Spring Cloud Netflix e Eureka Service Discovery, puoi avere il Config Server registrato con il Discovery Service.

Se preferisci usare DiscoveryClient per localizzare il Config Server, puoi farlo settando “spring.cloud.config.discovery.enabled=true” (di default è false). Il risultato è che le applicazioni client hanno bisogno di un bootstrap.yml con le appropriate configurazioni di discovery. Ad esempio con Spring Cloud Netflix hai bisogno di definire l’indirizzo server Eureka (eureka.client.serviceUrl.defaultZone). Il service ID di default è configserver, ma puoi cambiare il nome del client settando “spring.cloud.config.discovery.serviceId”.



## Spring Cloud Netflix
E’ il cuore della piattaforma Spring Cloud che in pratica integra Spring Boot con Netflix OSS, la piattaforma realizzata da Netflix per indirizzare le sfide dei sistemi distribuiti.

- Eureka fa da service registry, abilitando la service discovery tra i servizi del nostro sistema

- Hystrix: circuit breaker

- Ribbon: client side load balancer

- Zuul: API gateway. Fa da unico punto di ingresso al sistema e dirotta le chiamate ai servizi “sottostanti”. E’ basato su Hystrix e Ribbon così da garantire load balancing e circuit breaker. Se abilitato come proxy (@EnableZuulProxy), è capace quindi di mappare dinamicamente gli endpoint dei servizi “sottostanti”.


### Spring Cloud Netflix Eureka
Con Netflix Eureka, ogni client può simultaneamente agire come un server, per replicare il suo stato ad un nodo connesso. In altre parole, un client riceve una lista di tutti i nodi connessi da un “service registry”.
È quindi necessario:
- Un service registry (Eureka Server)
- Un REST service (Eureka Client) per registrare i servizi (sono quelli che vedi registrati nella dashboard): sono da considerare Eureka Client tutti i microservizi che effettivamente s’intende registrare (nel progetto di esempio: config, gateway, hello-service e pokemon sono eureka client).
- Applicazione web che utilizza il servizio REST con un client che riconosce il registro (Spring Cloud Netflix Feign Client)



### Spring Cloud Netflix Zuul Gateway
Un server gateway è un'eccellente applicazione nell'architettura dei microservizi in quanto consente a tutte le risposte di provenire da un singolo host.
Ad esempio, possiamo accedere a:
http://localhost:8090/hello/hi
oppure, tramite il gateway a:
http://localhost:8085/hello-service/hello/hi



## Ulteriori guide
https://dzone.com/articles/quick-guide-to-microservices-with-spring-boot-20-e
