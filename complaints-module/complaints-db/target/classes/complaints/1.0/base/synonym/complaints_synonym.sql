DECLARE
    v_sql_stmnt VARCHAR2(32767);
    str_sql VARCHAR2(32767);
    v_sql_err VARCHAR2(32767);
    sql_str        VARCHAR2(32767);
    tab_name       VARCHAR2(32767);
    pos            NUMBER(22);
    tab_name_start NUMBER(22);
    tab_name_end   NUMBER(22);
    v_count        PLS_INTEGER;
    V_VIEW_NAME VARCHAR2(30 CHAR);
BEGIN
  -- drop invalid synonym
    FOR rec IN (
        SELECT
            s.*
        FROM
            all_objects o
            JOIN all_synonyms s ON
                s.owner = o.owner
            AND
                s.synonym_name = o.object_name
        WHERE
                s.table_owner = 'COMPLAINTS'
            AND
                o.object_type IN (
                    'SYNONYM'
                )
            AND
                o.status = 'INVALID'
    ) LOOP
        EXECUTE IMMEDIATE 'DROP SYNONYM '
         || rec.owner
         || '.'
         || rec.synonym_name;
    END LOOP;

            -- grant COMPLAINTS_USER - for table synonyms

    FOR rec IN (
        SELECT
            t.owner,
            t.table_name
        FROM
            all_tables t
        WHERE
                t.owner = 'COMPLAINTS'
            AND
                t.table_name NOT LIKE 'X%'
            AND
                t.table_name NOT LIKE 'MLOG$%'
            AND
                t.table_name NOT LIKE 'RUPD$%'
        ORDER BY t.table_name
    ) LOOP
        str_sql := 'GRANT SELECT,INSERT,UPDATE ON '
         || rec.owner
         || '.'
         || rec.table_name
         || ' TO COMPLAINTS_USER';

        EXECUTE IMMEDIATE str_sql;
    END LOOP;

    FOR rec IN (
        SELECT
            t.owner,
            t.table_name
        FROM
            all_tables t
        WHERE
                t.owner = 'COMPLAINTS'
        ORDER BY t.table_name
    ) LOOP
        str_sql := 'CREATE OR REPLACE SYNONYM COMPLAINTS_USER.'
         || rec.table_name
         || ' FOR COMPLAINTS.'
         || rec.table_name;
            --DBMS_OUTPUT.PUT_LINE(STR_SQL);
        EXECUTE IMMEDIATE str_sql;
    END LOOP;

            --synonyms for sequences

    FOR rec IN (
        SELECT
            asq.sequence_owner,
            asq.sequence_name
        FROM
            all_sequences asq
        WHERE
            asq.sequence_owner = 'COMPLAINTS'
    ) LOOP
        str_sql := 'GRANT SELECT ON COMPLAINTS.'
         || rec.sequence_name
         || ' TO COMPLAINTS_USER';
        EXECUTE IMMEDIATE str_sql;
        str_sql := 'CREATE OR REPLACE SYNONYM COMPLAINTS_USER.'
         || rec.sequence_name
         || ' FOR COMPLAINTS.'
         || rec.sequence_name;
        EXECUTE IMMEDIATE str_sql;
    END LOOP;

END;
