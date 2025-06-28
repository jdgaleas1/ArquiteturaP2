#!/bin/bash

MICROSERVICIOS=("ordenes" "inventario" "cobros" "despacho" "envios")

echo "🔧 Compilando microservicios con Maven..."

for servicio in "${MICROSERVICIOS[@]}"; do
  echo "📦 Compilando $servicio..."
  cd $servicio
  ./mvnw clean package -DskipTests
  if [ $? -ne 0 ]; then
    echo "❌ Error al compilar $servicio"
    exit 1
  fi
  cd ..
done

echo "✅ Todos los microservicios fueron compilados con éxito."
