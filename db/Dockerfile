FROM postgres
EXPOSE 5432
COPY bardb_dump.sql /var/lib/postgresql/backup/
ADD restore.sh /docker-entrypoint-initdb.d/