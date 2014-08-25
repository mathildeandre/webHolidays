web
===

SOLUTION pour enlever demande de mot de passe : 
Si tu as généré puis copié ta SSH key dans github (suivre point2)
git config remote.origin.url git@github.com:myusername/myrep.git


1. Se créer un utilisateur sur github :
https://github.com/join

2. Generate SSH key : 
https://help.github.com/articles/generating-ssh-keys

3. Clone le repertoire
	$ git clone https://github.com/mathildeandre/web.git


Si problèmes pour push genre impossible to push to git... alors faire : 

	git remote set-url origin https://github.com/my_user_name/my_repo.git




