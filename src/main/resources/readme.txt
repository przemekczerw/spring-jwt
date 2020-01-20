Uruchamiany postgresql na dockerze.
Po pierwszym uruchomieniu zakomentować data.sql bo się czepia potem.

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

teraz powinna być 200
http://localhost:8080/api/test/admin