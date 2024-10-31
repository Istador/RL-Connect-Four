# English

RL: Connect Four is a software project that I've worked on in the winter semester 2011/12 at the [Project: Learning Agents](https://rcl.blackpinguin.de/haw/bai/11ws/Proj_LA/) course of [B.Sc. Applied Computer Science (B-AI)](https://rcl.blackpinguin.de/haw/bai/).

The project's goal was to apply Reinforcement Learning (RL) on the game [Connect Four](https://en.wikipedia.org/wiki/Connect_Four), to teach a computer to play it successfully. This has turned out to be unviable, because the game's state space is too big. The needed database would grow on to about 49.5 TiB during the training phase.

Daniel Wehring created RL: Connect Four in the summer semester 2011. He used the RL framework by Patrick Boekhoven, that was developed as part of his [bachelor thesis](http://edoc.sub.uni-hamburg.de/haw/volltexte/2011/1269/) with the JADE agent framework.

My contribution mainly consisted of a detailed code refactoring[1], runtime optimizations and additional documentation for the game and the RL framework.

The documentation of my work for the project is available [here](https://wiki.blackpinguin.de/index.php/Vier_gewinnt).

At the end of the project, I gave a [presentation](https://rcl.blackpinguin.de/proj/RLVierGewinnt/vg.pdf) for the course participants of the next semester, in which I briefly explained Reinforcement Learning and introduced RL: Connect Four.

|                  |                                                                              |
| ---------------- | ---------------------------------------------------------------------------- |
| __Languages__    | Java, UML                                                                    |
| __Technologies__ | JADE, Reinforcment Learning, Q-Learning, SARSA, Temporal difference learning |
| __Tools__        | Dia, MediaWiki, Libre Office Impress                                         |
| __IDE__          | Eclipse                                                                      |
| __Participants__ | 1 (2 total)                                                                  |

### Footnotes

- [1]	The RL algorithms of the RL framework, as well as the Connect Four application of the framework, contained severe bugs, that prohibited effectual learning.
  
  For example: there was a 64-bit integer overflow in the calculation of the Connect Four state IDs, which prevented them to address states bijectively.

# Deutsch

RL: Vier gewinnt ist ein Softwareprojekt, an dem ich im Wintersemester 2011/12 im Modul [Projekt: Lernende Agenten](https://rcl.blackpinguin.de/haw/bai/11ws/Proj_LA/de.html) des Studienganges [Bachelor Angewandte Informatik (B-AI)](https://rcl.blackpinguin.de/haw/bai/de.html) gearbeitet habe.

Das Projekt beschäftigte sich damit mittels Verstärkendem Lernen (Englisch: Reinforcement Learning, kurz: RL) einem Computer das Spielen von [Vier gewinnt](https://de.wikipedia.org/wiki/Vier_gewinnt) beizubringen. Dies hat sich als unrentabel herausgestellt, weil die Datenbank die dafür notwendig wäre, durch den großen Zustandsraum, beim Training auf bis zu etwa 49,5 TiB anwachsen würde.

Ursprünglich wurde RL: Vier gewinnt im Sommersemester 2011 von Daniel Wehring erstellt. Dabei wurde das RL-Framework von Patrick Boekhoven verwendet, welches im Rahmen seiner [Bachelor-Arbeit](http://edoc.sub.uni-hamburg.de/haw/volltexte/2011/1269/) entstanden ist und das Agenten-Framework JADE verwendet.

Meine Arbeit an dem Projekt bestand hauptsächlich in einer ausführlichen Refaktorisierung[1], Optimierung und ergänzender Dokumentation des Spieles und des RL-Frameworks.

Die Dokumentation meiner Arbeit für das Projekt ist [hier](https://wiki.blackpinguin.de/index.php/Vier_gewinnt) zu lesen.

Am Ende des Projektes habe ich eine [Präsentation](https://rcl.blackpinguin.de/proj/RLVierGewinnt/vg.pdf) für die Kursteilnehmer des nächsten Semesters gehalten, in der ich zunächst kurz Verstärkendes Lernen erklärt habe und anschließend RL: Vier gewinnt vorstellte.

|                  |                                                                             |
| ---------------- | --------------------------------------------------------------------------- |
| __Sprachen__     | Java, UML                                                                   |
| __Technologien__ | JADE, Verstärkendes Lernen, Q-Learning, SARSA, Temporal difference learning |
| __Tools__        | Dia, MediaWiki, Libre Office Impress                                        |
| __IDE__          | Eclipse                                                                     |
| __Beteiligte__   | 1 (2 gesamt)                                                                |

### Fußnoten

- [1]	Sowohl in den RL-Algorithmen des RL-Frameworkes, als auch in der Anwendung für Vier Gewinnt, befanden sich schwerwiegende Fehler, wodurch das verstärkende Lernen nicht korrekt erfolgen konnte.
  
  Beispielsweise existierte in der Berechnung der Vier Gewinnt Zustands-ID ein 64-bit Ganzzahlüberlauf, wodurch nicht alle Spielzustände eineindeutig adressiert wurden.
