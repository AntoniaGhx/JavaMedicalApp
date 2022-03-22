# Servicii Medicale
___________
UserMedic
===========
Integer id
String name
?enum Specialitate ?
___________
UserPacient
===========
Integer id
String name
Denominatie type
String e-mail
String password
___________
Serviciu
===========
Integer id
String nume
Float price
LocalTime duration
___________
Programare
===========
Integer id
Integer idMedic
Integer idUser
Integer idServiciu
LocalDate date
LocalTime time
___________
Rating
===========
Integer idUser
Integer idMedic
Integer value
