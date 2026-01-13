#!/usr/bin/env bash
set -euo pipefail

python -m zipfile -c /tmp/function.zip /var/task/handler.py

awslocal lambda create-function \
  --function-name order-recommendation \
  --runtime python3.11 \
  --handler handler.handler \
  --role arn:aws:iam::000000000000:role/lambda-role \
  --zip-file fileb:///tmp/function.zip
