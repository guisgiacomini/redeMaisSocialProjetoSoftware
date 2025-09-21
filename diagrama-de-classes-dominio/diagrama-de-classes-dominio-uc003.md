```plantuml
@startuml
class Candidato
class ONG
class Voluntário
class Campanha
class Afiliação
class Perfil
class Habilidade
class Interesse
class Representante
class E-mail
class Recomendação

Candidato "0..*" *-- "0..*" Habildade: possui
Candidato "1" *-- "1" Perfil: possui
Candidato "0..*" *-- "0..*" Interesse: possui

Voluntário "0..*" *-- "0..*" Habildade: possui
Voluntário "1" *-- "1" Perfil: possui
Voluntário "0..*" *-- "0..*" Interesse: possui

@enduml