#!/bin/bash

echo "🛑 Deteniendo contenedores..."
docker-compose down

echo "🔥 Eliminando volúmenes..."
docker volume rm \
  back-end_ordenes_data \
  back-end_inventario_data \
  back-end_cobros_data \
  back-end_despacho_data \
  back-end_envios_data

echo "🔄 Reconstruyendo microservicios..."
docker-compose up --build
