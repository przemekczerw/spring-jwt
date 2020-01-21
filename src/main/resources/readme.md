Uruchamiany postgresql na dockerze.
Po pierwszym uruchomieniu zakomentować data.sql bo się czepia potem.

odkomentować data.sql

uruchomić aplikację

zakomentować data.sql

http://localhost:8080/api/auth/signup

{
"username": "przemek",
"email": "przemek@wp.pl",
"password": "string",
"role": ["admin"]
}

To nie powinno działać ->
http://localhost:8080/api/test/admin

localhost:8080/api/auth/signin

{
	"username" : "przemek",
	"password": "string"
}

dokleić do headera Autoryzacje i dodać token
Baerer token

teraz powinna być 200
http://localhost:8080/api/test/admin