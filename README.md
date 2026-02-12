# Observability Stack Setup

Describes the complete observability stack implemented for small microservice application.

## Overview

The observability stack includes:
- **Prometheus**: Metrics collection and storage
- **Grafana**: Visualization and dashboards
- **Loki**: Log aggregation
- **Promtail**: Log collection agent
- **AlertManager**: Alerting and notifications
- **Zipkin**: Distributed tracing (already existing)
- **Node Exporter**: System metrics
- **cAdvisor**: Container metrics


## Services and Ports

| Service        | Port | Description |
|----------------|------|-------------|
| Prometheus     | 9090 | Metrics collection and storage |
| Grafana        | 3000 | Visualization and dashboards |
| Loki           | 3100 | Log aggregation |
| Promtail       | 9080 | Log collection agent |
| AlertManager   | 9093 | Alerting and notifications |
| Node Exporter  | 9100 | System metrics |
| cAdvisor       | 8080 | Container metrics |
| Zipkin         | 9411 | Distributed tracing |
| RabbitMQ       | 5672/15672 | Message broker |

## Quick Start

1. **Start the observability stack:**
   ```bash
   docker-compose up -d
   ```

2. **Access the services:**
   - Grafana: http://localhost:3000 (admin/admin)
   - Prometheus: http://localhost:9090
   - AlertManager: http://localhost:9093
   - Loki: http://localhost:3100
   - Zipkin: http://localhost:9411


## Configuration

### Prometheus Configuration

- **Config file**: `monitoring/prometheus/prometheus.yml`
- **Rules**: `monitoring/prometheus/rules/alerts.yml`
- **Targets**: Spring Boot apps, Node Exporter, cAdvisor, RabbitMQ

### Grafana Configuration

- **Datasources**: Auto-provisioned from `monitoring/grafana/provisioning/datasources/`
- **Dashboards**: Auto-provisioned from `monitoring/grafana/provisioning/dashboards/`

### Loki Configuration

- **Config file**: `monitoring/loki/local-config.yaml`
- **Storage**: Local filesystem

### Promtail Configuration

- **Config file**: `monitoring/promtail/config.yml`
- **Sources**: Spring Boot logs, Docker container logs, system logs

### AlertManager Configuration

- **Config file**: `monitoring/alertmanager/alertmanager.yml`
- **Routes**: Critical and warning alerts
- **Receivers**: Email and webhook notifications

## Metrics Available

### Spring Boot Metrics
- JVM metrics (memory, GC, threads)
- HTTP request metrics
- Custom application metrics
- RabbitMQ metrics

### System Metrics
- CPU usage
- Memory usage
- Disk usage
- Network I/O

### Container Metrics
- Container CPU usage
- Container memory usage
- Container network I/O
- Container disk I/O

## Alerts Configured

### Spring Boot Alerts
- Service down
- High memory usage (>80%)
- High CPU usage (>80%)
- High error rate (>10%)

### System Alerts
- High disk usage (>85%)
- Node down

### RabbitMQ Alerts
- RabbitMQ down
- High memory usage

## Logging

### Log Format
Logs are structured in JSON format with the following fields:
- timestamp
- level
- logger
- message
- mdc (Mapped Diagnostic Context)
- arguments
- stackTrace (for errors)

### Log Sources
- Spring Boot application logs