Kanastra Payments

## Overview
A aplicação kanastra-payments tem como objetivo realizar a geração de boletos a partir do processamento de arquivos .csv.
Após a geração de cada boleto, um e-mail é enviado para uma fila (Redis) para que seja disparado para o respectivo cliente.
