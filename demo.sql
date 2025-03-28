PGDMP  /                    }            postgres    15.8    17.4 &    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    5    postgres    DATABASE     t   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';
    DROP DATABASE postgres;
                     postgres    false            �           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                        postgres    false    3828            �           0    0    DATABASE postgres    ACL     2   GRANT ALL ON DATABASE postgres TO dashboard_user;
                        postgres    false    3828            �           0    0    postgres    DATABASE PROPERTIES     >   ALTER DATABASE postgres SET "app.settings.jwt_exp" TO '3600';
                          postgres    false                       1259    29291    departamento    TABLE     �   CREATE TABLE public.departamento (
    id bigint NOT NULL,
    codigo character varying NOT NULL,
    nombre character varying NOT NULL
);
     DROP TABLE public.departamento;
       public         heap r       postgres    false            �           0    0    TABLE departamento    ACL     =  GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,TRUNCATE,UPDATE ON TABLE public.departamento TO anon;
GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,TRUNCATE,UPDATE ON TABLE public.departamento TO authenticated;
GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,TRUNCATE,UPDATE ON TABLE public.departamento TO service_role;
          public               postgres    false    278                       1259    29294    departamento_id_seq    SEQUENCE     �   ALTER TABLE public.departamento ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.departamento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    278            �           0    0    SEQUENCE departamento_id_seq    ACL     �   GRANT ALL ON SEQUENCE public.departamento_id_seq TO anon;
GRANT ALL ON SEQUENCE public.departamento_id_seq TO authenticated;
GRANT ALL ON SEQUENCE public.departamento_id_seq TO service_role;
          public               postgres    false    279                       1259    30423    distrito    TABLE     �   CREATE TABLE public.distrito (
    id bigint NOT NULL,
    "idMunicipio" bigint NOT NULL,
    codigo character varying NOT NULL,
    nombre character varying NOT NULL
);
    DROP TABLE public.distrito;
       public         heap r       postgres    false            �           0    0    TABLE distrito    ACL     1  GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,TRUNCATE,UPDATE ON TABLE public.distrito TO anon;
GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,TRUNCATE,UPDATE ON TABLE public.distrito TO authenticated;
GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,TRUNCATE,UPDATE ON TABLE public.distrito TO service_role;
          public               postgres    false    282                       1259    30426    distrito_id_seq    SEQUENCE     �   ALTER TABLE public.distrito ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.distrito_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    282            �           0    0    SEQUENCE distrito_id_seq    ACL     �   GRANT ALL ON SEQUENCE public.distrito_id_seq TO anon;
GRANT ALL ON SEQUENCE public.distrito_id_seq TO authenticated;
GRANT ALL ON SEQUENCE public.distrito_id_seq TO service_role;
          public               postgres    false    283                       1259    30407 	   municipio    TABLE     �   CREATE TABLE public.municipio (
    id bigint NOT NULL,
    "idDepartamento" bigint NOT NULL,
    codigo character varying NOT NULL,
    nombre character varying NOT NULL
);
    DROP TABLE public.municipio;
       public         heap r       postgres    false            �           0    0    TABLE municipio    ACL     4  GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,TRUNCATE,UPDATE ON TABLE public.municipio TO anon;
GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,TRUNCATE,UPDATE ON TABLE public.municipio TO authenticated;
GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,TRUNCATE,UPDATE ON TABLE public.municipio TO service_role;
          public               postgres    false    280                       1259    30410    municipio_id_seq    SEQUENCE     �   ALTER TABLE public.municipio ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.municipio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    280            �           0    0    SEQUENCE municipio_id_seq    ACL     �   GRANT ALL ON SEQUENCE public.municipio_id_seq TO anon;
GRANT ALL ON SEQUENCE public.municipio_id_seq TO authenticated;
GRANT ALL ON SEQUENCE public.municipio_id_seq TO service_role;
          public               postgres    false    281            �          0    29291    departamento 
   TABLE DATA           :   COPY public.departamento (id, codigo, nombre) FROM stdin;
    public               postgres    false    278   ,       �          0    30423    distrito 
   TABLE DATA           E   COPY public.distrito (id, "idMunicipio", codigo, nombre) FROM stdin;
    public               postgres    false    282   �,       �          0    30407 	   municipio 
   TABLE DATA           I   COPY public.municipio (id, "idDepartamento", codigo, nombre) FROM stdin;
    public               postgres    false    280   A-       �           0    0    departamento_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.departamento_id_seq', 45, true);
          public               postgres    false    279            �           0    0    distrito_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.distrito_id_seq', 80, true);
          public               postgres    false    283                        0    0    municipio_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.municipio_id_seq', 5, true);
          public               postgres    false    281            ?           2606    30448 $   departamento departamento_codigo_key 
   CONSTRAINT     a   ALTER TABLE ONLY public.departamento
    ADD CONSTRAINT departamento_codigo_key UNIQUE (codigo);
 N   ALTER TABLE ONLY public.departamento DROP CONSTRAINT departamento_codigo_key;
       public                 postgres    false    278            A           2606    30450 $   departamento departamento_nombre_key 
   CONSTRAINT     a   ALTER TABLE ONLY public.departamento
    ADD CONSTRAINT departamento_nombre_key UNIQUE (nombre);
 N   ALTER TABLE ONLY public.departamento DROP CONSTRAINT departamento_nombre_key;
       public                 postgres    false    278            C           2606    29301    departamento departamento_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.departamento
    ADD CONSTRAINT departamento_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.departamento DROP CONSTRAINT departamento_pkey;
       public                 postgres    false    278            K           2606    30431    distrito distrito_codigo_key 
   CONSTRAINT     Y   ALTER TABLE ONLY public.distrito
    ADD CONSTRAINT distrito_codigo_key UNIQUE (codigo);
 F   ALTER TABLE ONLY public.distrito DROP CONSTRAINT distrito_codigo_key;
       public                 postgres    false    282            M           2606    30437    distrito distrito_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.distrito
    ADD CONSTRAINT distrito_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.distrito DROP CONSTRAINT distrito_pkey;
       public                 postgres    false    282            E           2606    30444    municipio municipio_codigo_key 
   CONSTRAINT     [   ALTER TABLE ONLY public.municipio
    ADD CONSTRAINT municipio_codigo_key UNIQUE (codigo);
 H   ALTER TABLE ONLY public.municipio DROP CONSTRAINT municipio_codigo_key;
       public                 postgres    false    280            G           2606    30446    municipio municipio_nombre_key 
   CONSTRAINT     [   ALTER TABLE ONLY public.municipio
    ADD CONSTRAINT municipio_nombre_key UNIQUE (nombre);
 H   ALTER TABLE ONLY public.municipio DROP CONSTRAINT municipio_nombre_key;
       public                 postgres    false    280            I           2606    30417    municipio municipio_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.municipio
    ADD CONSTRAINT municipio_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.municipio DROP CONSTRAINT municipio_pkey;
       public                 postgres    false    280            O           2606    30438 "   distrito distrito_idMunicipio_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.distrito
    ADD CONSTRAINT "distrito_idMunicipio_fkey" FOREIGN KEY ("idMunicipio") REFERENCES public.municipio(id);
 N   ALTER TABLE ONLY public.distrito DROP CONSTRAINT "distrito_idMunicipio_fkey";
       public               postgres    false    3657    280    282            N           2606    30418 '   municipio municipio_idDepartamento_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.municipio
    ADD CONSTRAINT "municipio_idDepartamento_fkey" FOREIGN KEY ("idDepartamento") REFERENCES public.departamento(id);
 S   ALTER TABLE ONLY public.municipio DROP CONSTRAINT "municipio_idDepartamento_fkey";
       public               postgres    false    278    280    3651            �           0    29291    departamento    ROW SECURITY     :   ALTER TABLE public.departamento ENABLE ROW LEVEL SECURITY;          public               postgres    false    278            �           0    30423    distrito    ROW SECURITY     6   ALTER TABLE public.distrito ENABLE ROW LEVEL SECURITY;          public               postgres    false    282            �           0    30407 	   municipio    ROW SECURITY     7   ALTER TABLE public.municipio ENABLE ROW LEVEL SECURITY;          public               postgres    false    280            �   �   x��A
�0E�N�	DL+�,Q�&.�s3��
ab�Ă��,^L����+�ݰ����H�u�*ɓف��H�2{T5�>K����Q2F3���%l�K��U��d`6O���*�֬{t�2G�+\��E��	���a�t��@�3�      �   o   x�3�4�t�
�t��*-I-H-,M�2�4�v��.M��,�/��27*v��N�+ITp.*�R��L�H,H�27����so��27��8q��(8'��q��qqq �q�      �   /   x�3�4�t��t.-NN,�I�S��/*I�2�#��q��qqq wjK     