cangee
rpush
spring boot microservices
opt 
recuit
grocery

# Struttura della tesi
## Introduzione
– Contesto dei microservizi e importanza della qualità architetturale
– Obiettivi: rimuovere “architectural smells” e misurarne l’impatto
– Domande di ricerca
## Background e strumenti
– Definizione di architectural smell nei microservizi
– Metriche di qualità (coupling, cohesion, complexity, etc.)
– Panoramica di Arcan, SonarQube, UnderStand (punti di forza, output)
## Metodologia
– Selezione progetti e criteri (linguaggio, maturità, test coverage)
– Workflow di analisi e refactoring
– Metodi di confronto pre/post refactoring
## Esecuzione
– Descrizione dei progetti scelti
– Risultati delle analisi pre-refactoring
– Attività di refactoring (descrizione delle modifiche)
– Risultati post-refactoring
## Discussione dei risultati
– Variazione delle metriche
– Effetti sui build time, test coverage, manutenibilità
## Conclusioni e sviluppi futuri

# Workflow operativo e timeline (15 giorni)
## Giorno 1
• Scelta definitiva dei 3–4 progetti
• Setup ambienti (CI, SonarQube, Arcan, UnderStand)
## Giorni 2–4 (Per ciascun progetto)
• Analisi statica iniziale con SonarQube
• Analisi architetturale con Arcan/UnderStand
• Raccolta metriche baseline (numero smells, coupling, ecc.)
## Giorni 5–13 (Refactoring)
• Dedica ~3 giorni a progetto:
– Prioritizzare smells a impatto alto
– Applicare refactoring (estrazione di servizi, rimozione dipendenze cicliche, consolidamento API)
– Scrivere test di regressione se non presenti
## Giorni 14–15 (Analisi post-refactoring e report)
• Rianalisi con gli stessi tool
• Confronto side-by-side delle metriche
• Stesura dei risultati e grafici

# Dettagli metodologici
## • Architectural smells più comuni:
– Cyclic Dependency tra servizi
– God Service (servizi troppo “grassi”)
– Data Sharing eccessivo
– Endpoint spuri o troppi “chatty services”
• Metriche da estrarre:
– Coupling Between Services (numero di dipendenze)
– Service Size (LOC, numero di classi/pacchetti)
– Cyclomatic Complexity aggregata per servizio
– Test Coverage (lineare e branch)
## • Strumenti e automazione:
– Script CI per esportare report JSON da SonarQube
– Plugin Arcan per generare grafici di dipendenza
– UnderStand per navigare le architetture multi-service


# Consigli per essere efficienti
• Automazione: crea uno script bash/python che lanci SonarQube e Arcan su ogni repository e raccolga i risultati in CSV.
• Refactoring guidato: rimedi “quick-wins” (es. eliminare endpoint inutilizzati, semplificare DTO) prima di interventi più invasivi.
• Pair-programming o code review rapida tra componenti per validare le modifiche senza blocchi.
• Versioning: crea un branch per ogni progetto e conserva snapshot “pre” e “post” per comparazioni puntuali.

# Strumenti di reportistica
• Grafici a barre per numero di smells rimossi per progetto
• Trend line per coupling/cohesion prima e dopo
• Tabelle comparative delle metriche chiave


