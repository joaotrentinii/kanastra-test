configure:
	- composer update

test:
	- php artisan test

open-report:
	- sensible-browser ./report/html/index.html
