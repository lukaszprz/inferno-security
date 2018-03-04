--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.6
-- Dumped by pg_dump version 10.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

--
-- Name: inferno-dictionary-ispell; Type: TEXT SEARCH DICTIONARY; Schema: public; Owner: postgres
--

CREATE TEXT SEARCH DICTIONARY "inferno-dictionary-ispell" (
    TEMPLATE = pg_catalog.ispell,
    afffile = 'pl_pl', dictfile = 'pl_pl' );


ALTER TEXT SEARCH DICTIONARY "inferno-dictionary-ispell" OWNER TO postgres;

--
-- Name: inferno-dictionary-simple; Type: TEXT SEARCH DICTIONARY; Schema: public; Owner: postgres
--

CREATE TEXT SEARCH DICTIONARY "inferno-dictionary-simple" (
    TEMPLATE = pg_catalog.simple );


ALTER TEXT SEARCH DICTIONARY "inferno-dictionary-simple" OWNER TO postgres;

--
-- Name: inferno-search-configuration-simple; Type: TEXT SEARCH CONFIGURATION; Schema: public; Owner: postgres
--

CREATE TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple" (
    PARSER = pg_catalog."default" );

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR asciiword WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR word WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR numword WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR email WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR url WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR host WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR sfloat WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR version WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR hword_numpart WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR hword_part WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR hword_asciipart WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR numhword WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR asciihword WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR hword WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR url_path WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR file WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR "float" WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR "int" WITH simple;

ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple"
    ADD MAPPING FOR uint WITH simple;


ALTER TEXT SEARCH CONFIGURATION "inferno-search-configuration-simple" OWNER TO postgres;

--
-- Name: polish; Type: TEXT SEARCH CONFIGURATION; Schema: public; Owner: postgres
--

CREATE TEXT SEARCH CONFIGURATION polish (
    PARSER = pg_catalog."default" );


ALTER TEXT SEARCH CONFIGURATION polish OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: ZIPcodes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "ZIPcodes" (
    town text NOT NULL,
    "ZIPcode" text NOT NULL
);


ALTER TABLE "ZIPcodes" OWNER TO postgres;

--
-- Name: group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "group" (
    name text NOT NULL,
    description text
);


ALTER TABLE "group" OWNER TO postgres;

--
-- Name: memberlist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE memberlist (
    person integer NOT NULL,
    "group" text NOT NULL
);


ALTER TABLE memberlist OWNER TO postgres;

--
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE person (
    id integer NOT NULL,
    christian_name text,
    name text,
    street text,
    town text,
    "ZIPcode" text,
    country text,
    category text,
    description text
);


ALTER TABLE person OWNER TO postgres;

--
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE person_id_seq OWNER TO postgres;

--
-- Name: person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE person_id_seq OWNED BY person.id;


--
-- Name: pfm_attribute; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pfm_attribute (
    attribute text NOT NULL,
    typeofattrib text,
    typeofget text,
    sqlselect text,
    nr integer,
    form text NOT NULL,
    valuelist text,
    "default" text
);


ALTER TABLE pfm_attribute OWNER TO postgres;

--
-- Name: pfm_form; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pfm_form (
    name text NOT NULL,
    tablename text,
    showform boolean DEFAULT true,
    view boolean DEFAULT false,
    sqlselect text,
    sqlfrom text,
    groupby text,
    help text,
    pkey text,
    sqlorderby text,
    sqllimit text
);


ALTER TABLE pfm_form OWNER TO postgres;

--
-- Name: pfm_link; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pfm_link (
    linkname text NOT NULL,
    sqlwhere text,
    orderby text,
    displayattrib text,
    fromform text NOT NULL,
    toform text
);


ALTER TABLE pfm_link OWNER TO postgres;

--
-- Name: pfm_report; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pfm_report (
    name text NOT NULL,
    description text,
    sqlselect text
);


ALTER TABLE pfm_report OWNER TO postgres;

--
-- Name: pfm_section; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pfm_section (
    report text NOT NULL,
    level integer NOT NULL,
    fieldlist text,
    layout text,
    summary text,
    CONSTRAINT level_min_1 CHECK ((level >= 1))
);


ALTER TABLE pfm_section OWNER TO postgres;

--
-- Name: pfm_value; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pfm_value (
    value text NOT NULL,
    description text,
    valuelist text NOT NULL
);


ALTER TABLE pfm_value OWNER TO postgres;

--
-- Name: pfm_value_list; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pfm_value_list (
    name text NOT NULL
);


ALTER TABLE pfm_value_list OWNER TO postgres;

--
-- Name: pfm_version; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pfm_version (
    seqnr integer NOT NULL,
    version text,
    date text,
    comment text
);


ALTER TABLE pfm_version OWNER TO postgres;

--
-- Name: pfm_version_seqnr_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pfm_version_seqnr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pfm_version_seqnr_seq OWNER TO postgres;

--
-- Name: pfm_version_seqnr_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pfm_version_seqnr_seq OWNED BY pfm_version.seqnr;


--
-- Name: person id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person ALTER COLUMN id SET DEFAULT nextval('person_id_seq'::regclass);


--
-- Name: pfm_version seqnr; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pfm_version ALTER COLUMN seqnr SET DEFAULT nextval('pfm_version_seqnr_seq'::regclass);


--
-- Data for Name: ZIPcodes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "ZIPcodes" (town, "ZIPcode") FROM stdin;
Aaigem	9420
Aalbeke	8511
Aalst	9300
Aalst (Limb.)	3800
Aalter	9880
Aarschot	3200
Aarsele	8700
Aartrijke	8211
Aartselaar	2630
Abée	4557
Abolens	4280
Achel	3930
Achêne	5590
Achet	5362
Acosse	4219
Acoz	6280
Adegem	9991
Adinkerke	8660
Affligem	1790
Afsnee	9051
Agimont	5544
Aineffe	4317
Aische-en-Refail	5310
Aiseau	6250
Aiseau-Presles	6250
Aisemont	5070
Alken	3570
Alle	5550
Alleur	4432
Alsemberg	1652
Alveringem	8690
Amay	4540
Amberloup	6680
Amblève	4770
Ambly	6953
Ambresin	4219
Amel	4770
Amonines	6997
Amougies	7750
Ampsin	4540
Andenne	5300
Anderlecht	1070
Anderlues	6150
Andrimont	4821
Angleur	4031
Angre	7387
Angreau	7387
Anhée	5537
Anlier	6721
Anloy	6890
Annevoie-Rouillon	5537
Ans	4430
Anseremme	5500
Anseroeul	7750
Anthée	5520
Antheit	4520
Anthisnes	4160
Antoing	7640
Antwerpen	2018
Antwerpen	2020
Antwerpen	2030
Antwerpen	2040
Antwerpen	2050
Antwerpen	2060
Anvaing	7910
Anzegem	8570
Appels	9200
Appelterre-Eichem	9400
Arbre (Ht.)	7811
Arbre (Nam.)	5170
Arbrefontaine	4990
Arc-Ainières	7910
Archennes	1390
Arc-Wattripont	7910
Ardooie	8850
Arendonk	2370
Argenteau	4601
Arlon	6700
Arquennes	7181
Arsimont	5060
Arville	6870
As	3665
Aspelare	9404
Asper	9890
Asquillies	7040
Ass. Commiss. Communau. française	1007
Ass. Réun. Com. Communau. Commune	1005
Asse	1730
Assebroek	8310
Assenede	9960
Assenois	6860
Assent	3460
Assesse	5330
Astene	9800
Ath	7800
Athis	7387
Athus	6791
Attenhoven	3404
Attenrode	3384
Attert	6717
Attre	7941
Aubange	6790
Aubechies	7972
Aubel	4880
Aublain	5660
Auby-sur-Semois	6880
Auderghem	1160
Audregnies	7382
Aulnois	7040
Autelbas	6706
Autre-Eglise	1367
Autreppe	7387
Auvelais	5060
Ave-et-Auffe	5580
Avekapelle	8630
Avelgem	8580
Avennes	4260
Averbode	3271
Avernas-le-Bauduin	4280
Avin	4280
Awans	4340
Awenne	6870
Awirs	4400
Aye	6900
Ayeneux	4630
Aywaille	4920
B.S.D. (Belg. Strijdkr. Duitsland)	4090
Baaigem	9890
Baal	3128
Baardegem	9310
Baarle-Hertog	2387
Baasrode	9200
Bachte-Maria-Leerne	9800
Baelen (Lg.)	4837
Bagimont	5550
Baileux	6464
Bailièvre	6460
Baillamont	5555
Bailleul	7730
Baillonville	5377
Baisieux	7380
Baisy-Thy	1470
Balâtre	5190
Balegem	9860
Balen	2490
Bambrugge	9420
Bande	6951
Barbençon	6500
Barchon	4671
Baronville	5570
Barry	7534
Barvaux-Condroz	5370
Barvaux-sur-Ourthe	6940
Basècles	7971
Bas-Oha	4520
Basse-Bodeux	4983
Bassenge	4690
Bassevelde	9968
Bassilly	7830
Bastogne	6600
Bas-Warneton	7784
Batsheers	3870
Battice	4651
Battignies	7130
Baudour	7331
Bauffe	7870
Baugnies	7604
Baulers	1401
Bavegem	9520
Bavikhove	8531
Bazel	9150
Beaufays	4052
Beaumont	6500
Beauraing	5570
Beausaint	6980
Beauvechain	1320
Beauvoorde	8630
Beauwelz	6594
Beclers	7532
Beek	3960
Beerlegem	9630
Beernem	8730
Beerse	2340
Beersel	1650
Beerst	8600
Beert	1673
Beervelde	9080
Beerzel	2580
Beez	5000
Beffe	6987
Begijnendijk	3130
Beho	6672
Beigem	1852
Bekegem	8480
Bekkerzeel	1730
Bekkevoort	3460
Belgische Senaat	1009
Belgrade	5001
Bellaire	4610
Bellecourt	7170
Bellefontaine (Lux.)	6730
Bellefontaine (Nam.)	5555
Bellegem	8510
Bellem	9881
Bellevaux	6834
Bellevaux-Ligneuville	4960
Bellingen	1674
Beloeil	7970
Belsele (Sint-Niklaas)	9111
Ben-Ahin	4500
Bende	6941
Berbroek	3540
Berchem (Antwerpen)	2600
Berchem (O.-Vl.)	9690
Berchem-Sainte-Agathe	1082
Berendrecht	2040
Berg (Bt.)	1910
Berg (Limb.)	3700
Bergilers	4360
Beringen	3580
Berlaar	2590
Berlare	9290
Berlingen	3830
Berloz	4257
Berneau	4607
Bernissart	7320
Bersillies-l'Abbaye	6560
Bertem	3060
Bertogne	6687
Bertrée	4280
Bertrix	6880
Berzée	5651
Beselare	8980
Betekom	3130
Bettincourt	4300
Beuzet	5030
Bevel	2560
Bever	1547
Bevercé	4960
Bevere	9700
Beveren (Leie)	8791
Beveren (Roeselare)	8800
Beveren-aan-den-Ijzer	8691
Beveren-Waas	9120
Beverlo	3581
Beverst	3740
Beyne-Heusay	4610
Bienne-lez-Happart	6543
Bierbeek	3360
Biercée	6533
Bierges	1301
Bierghes	1430
Bierset	4460
Bierwart	5380
Biesme	5640
Biesmerée	5640
Biesme-sous-Thuin	6531
Bievene	1547
Bievre	5555
Biez	1390
Bihain	6690
Bikschote	8920
Bilstain	4831
Bilzen	3740
Binche	7130
Binderveld	3850
Binkom	3211
Bioul	5537
Bissegem	8501
Bizet	7783
Blaasveld	2830
Blaimont	5542
Blandain	7522
Blanden	3052
Blankenberge	8370
Blaregnies	7040
Blaton	7321
Blaugies	7370
Blégny	4670
Bléharies	7620
Blehen	4280
Bleid	6760
Bleret	4300
Blicquy	7903
Bocholt	3950
Boechout	2530
Boekhout	3890
Boekhoute	9961
Boëlhe	4250
Boezinge	8904
Bogaarden	1670
Bohan	5550
Boignée	5140
Boirs	4690
Bois-de-Lessines	7866
Bois-de-Villers	5170
Bois-d'Haine	7170
Bois-et-Borsu	4560
Bolinne	5310
Bolland	4653
Bomal (Bt.)	1367
Bomal-sur-Ourthe	6941
Bombaye	4607
Bommershoven (Haren)	3840
Boncelles	4100
Boneffe	5310
Bonheiden	2820
Boninne	5021
Bonlez	1325
Bonnert	6700
Bonneville	5300
Bon-Secours	7603
Bonsin	5377
Booischot	2221
Booitshoeke	8630
Boom	2850
Boorsem	3631
Boortmeerbeek	3190
Borchtlombeek	1761
Borgerhout (Antwerpen)	2140
Borgloon	3840
Borlez	4317
Borlo	3891
Borlon	6941
Bornem	2880
Bornival	1404
Borsbeek (Antw.)	2150
Borsbeke	9552
Bossière	5032
Bossuit	8583
Bossut-Gottechain	1390
Bost	3300
Bothey	5032
Bottelare	9820
Bouffioulx	6200
Bouge	5004
Bougnies	7040
Bouillon	6830
Bourlers	6464
Bourseigne-Neuve	5575
Bourseigne-Vieille	5575
Boussoit	7110
Boussu	7300
Boussu-en-Fagne	5660
Boussu-lez-Walcourt	6440
Bousval	1470
Boutersem	3370
Bouvignes-sur-Meuse	5500
Bouvignies	7803
Bouwel	2288
Bovekerke	8680
Bovelingen	3870
Bovenistier	4300
Bovesse	5081
Bovigny	6671
Bra	4990
Braffe	7604
Braibant	5590
Braine-l'Alleud	1420
Braine-le-Château	1440
Braine-le-Comte	7090
Braives	4260
Brakel	9660
Branchon	5310
Bras	6800
Brasmenil	7604
Brasschaat	2930
Bray	7130
Brecht	2960
Bredene	8450
Bree	3960
Breendonk	2870
Bressoux	4020
Brielen	8900
Broechem	2520
Broekom	3840
Brucargo	1931
Brugelette	7940
Brugge	8000
Brûly	5660
Brûly-de-Pesche	5660
Brunehaut	7620
Brussegem	1785
Brussel	1000
Brussel (Anderlecht)	1070
Brussel (Elsene)	1050
Brussel (Etterbeek)	1040
Brussel (Evere)	1140
Brussel (Ganshoren)	1083
Brussel (Haren)	1130
Brussel (Jette)	1090
Brussel (Koekelberg)	1081
Brussel (Laken)	1020
Brussel (Neder-Over-Heembeek)	1120
Brussel (Oudergem)	1160
Brussel (Schaarbeek)	1030
Brussel (Sint-Agatha-Berchem)	1082
Brussel (Sint-Gillis)	1060
Brussel (Sint-Jans-Molenbeek)	1080
Brussel (Sint-Joost-ten-Node)	1210
Brussel (Sint-Lambrechts-Woluwe)	1200
Brussel (Sint-Pieters-Woluwe)	1150
Brussel (Ukkel)	1180
Brussel (Vorst)	1190
Brussel (Watermaal-Bosvoorde)	1170
Brussel X-Luchthaven Remailing	1934
Brusselse Hoofdstedelijke Raad	1005
Brustem	3800
Bruxelles	1000
Bruxelles (Anderlecht)	1070
Bruxelles (Auderghem)	1160
Bruxelles (Berchem-Sainte-Agathe)	1082
Bruxelles (Etterbeek)	1040
Bruxelles (Evere)	1140
Bruxelles (Forest)	1190
Bruxelles (Ganshoren)	1083
Bruxelles (Haeren)	1130
Bruxelles (Ixelles)	1050
Bruxelles (Jette)	1090
Bruxelles (Koekelberg)	1081
Bruxelles (Laeken)	1020
Bruxelles (Molenbeek-Saint-Jean)	1080
Bruxelles (Neder-Over-Heembeek)	1120
Bruxelles (Saint-Gilles)	1060
Bruxelles (Saint-Josse-ten-Noode)	1210
Bruxelles (Schaerbeek)	1030
Bruxelles (Uccle)	1180
Bruxelles (Watermael-Boitsfort)	1170
Bruxelles (Woluwe-Saint-Lambert)	1200
Bruxelles (Woluwe-Saint-Pierre)	1150
Bruxelles X-Aeroport Remailing	1934
Bruyelle	7641
Brye	6222
Budingen	3440
Buggenhout	9255
Buissenal	7911
Buissonville	5580
Buizingen	1501
Buken	1910
Bullange	4760
Büllingen	4760
Bulskamp	8630
Bunsbeek	3380
Burcht	2070
Burdinne	4210
Bure	6927
Burg-Reuland	4790
Burst	9420
Bury	7602
Bütgenbach	4750
Butgenbach	4750
Buvingen	3891
Buvrinnes	7133
Buzenol	6743
Buzet	6230
Callenelle	7604
Calonne	7642
Cambron-Casteau	7940
Cambron-Saint-Vincent	7870
Cargovil	1804
Carlsbourg	6850
Carnières	7141
Casteau (Soignies)	7061
Castillon	5650
Celles (Ht.)	7760
Celles (Lg.)	4317
Celles (Nam.)	5561
Cérexhe-Heuseux	4632
Cerfontaine	5630
Céroux-Mousty	1341
Chaineux	4650
Chairière	5550
Chambre des Représentants	1008
Champion	5020
Champlon	6971
Chanly	6921
Chantemelle	6742
Chapelle-à-Oie	7903
Chapelle-à-Wattines	7903
Chapelle-lez-Herlaimont	7160
Chapon-Seraing	4537
Charleroi	6000
Charneux	4654
Chassepierre	6824
Chastre	1450
Chastrès	5650
Chastre-Villeroux-Blanmont	1450
Châtelet	6200
Châtelineau	6200
Châtillon	6747
Chaudfontaine	4050
Chaumont-Gistoux	1325
Chaussée-Notre-Dame-Louvignies	7063
Chênée	4032
Cherain	6673
Cheratte	4602
Chercq	7521
Chevetogne	5590
Chevron	4987
Chièvres	7950
Chimay	6460
Chiny	6810
Chokier	4400
Christelijke Sociale Organisaties	1031
Ciergnon	5560
Ciney	5590
Ciplet	4260
Ciply	7024
Cité Administrative de l'Etat	1010
Clabecq	1480
Clavier	4560
Clermont (Lg.)	4890
Clermont (Nam.)	5650
Clermont-sous-Huy	4480
Cognelée	5022
Colfontaine	7340
Comblain-au-Pont	4170
Comblain-Fairon	4180
Comblain-la-Tour	4180
Comines	7780
Comines-Warneton	7780
Conneux	5590
Conseil Region Bruxelles-Capitale	1005
Corbais	1435
Corbion	6838
Cordes	7910
Corenne	5620
Cornesse	4860
Cornimont	5555
Corroy-le-Château	5032
Corroy-le-Grand	1325
Corswarem	4257
Cortil-Noirmont	1450
Cortil-Wodon	5380
Couillet	6010
Courcelles	6180
Courrière	5336
Cour-sur-Heure	6120
Court-Saint-Etienne	1490
Couthuin	4218
Coutisse	5300
Couture-Saint-Germain	1380
Couvin	5660
Cras-Avernas	4280
Crehen	4280
Crisnée	4367
Croix-lez-Rouveroy	7120
Crombach	4784
Crupet	5332
Cuesmes	7033
Cugnon	6880
Cul-des-Sarts	5660
Custinne	5562
D.I.V.	1045
Dadizele	8890
Dailly	5660
Daknam	9160
Dalhem	4607
Damme	8340
Dampicourt	6767
Dampremy	6020
Darion	4253
Daussois	5630
Daussoulx	5020
Dave	5100
Daverdisse	6929
De Haan	8420
De Klinge	9170
De Moeren	8630
De Panne	8660
De Pinte	9840
Deerlijk	8540
Deftinge	9570
Deinze	9800
Denderbelle	9280
Denderhoutem	9450
Denderleeuw	9470
Dendermonde	9200
Denderwindeke	9400
Denée	5537
Dentergem	8720
Dergneau	7912
Dessel	2480
Desselgem	8792
Destelbergen	9070
Desteldonk	9042
Deurle	9831
Deurne (Antwerpen)	2100
Deurne (Bt.)	3290
Deux-Acren	7864
Dhuy	5310
Diegem	1831
Diepenbeek	3590
Diest	3290
Diets-Heur	3700
Dikkebus	8900
Dikkele	9630
Dikkelvenne	9890
Diksmuide	8600
Dilbeek	1700
Dilsen-Stokkem	3650
Dinant	5500
Dion	5570
Dion-Valmont	1325
Dison	4820
Dochamps	6960
Doel	9130
Dohan	6836
Doische	5680
Dolembreux	4140
Donceel	4357
Dongelberg	1370
Donk	3540
Donstiennes	6536
Dorinne	5530
Dormaal	3440
Dottenijs	7711
Dottignies	7711
Dour	7370
Dourbes	5670
Dranouter	8951
Dréhance	5500
Driekapellen	8600
Drieslinter	3350
Drogenbos	1620
Drongen	9031
Dudzele	8380
Duffel	2570
Duisburg	3080
Duras	3803
Durbuy	6940
Durnal	5530
Dworp	1653
E.U.-Commissie	1049
E.U.-Raad	1048
Eben-Emael	4690
Ebly	6860
Ecaussinnes	7190
Ecaussinnes-d'Enghien	7190
Ecaussinnes-Lalaing	7191
Edegem	2650
Edelare	9700
Edingen	7850
Eeklo	9900
Eernegem	8480
Egem	8740
Eggewaartskapelle	8630
Eghezée	5310
Ehein	4120
Eigenbilzen	3740
Eindhout	2430
Eine	9700
Eisden	3630
Eke	9810
Ekeren (Antwerpen)	2180
Eksaarde	9160
Eksel	3941
Elen	3650
Elene	9620
Elewijt	1982
Eliksem	3400
Elingen	1671
Ellemelle	4590
Ellezelles	7890
Ellignies-lez-Frasnes	7910
Ellignies-Sainte-Anne	7972
Ellikom	3670
Elouges	7370
Elsegem	9790
Elsenborn	4750
Elsene	1050
Elst	9660
Elverdinge	8906
Elversele	9140
Emblem	2520
Embourg	4053
Emelgem	8870
Emines	5080
Emptinne	5363
Ename	9700
Engelmanshoven	3800
Enghien	7850
Engis	4480
Enines	1350
Ensival	4800
Epinois	7134
Eppegem	1980
Eprave	5580
Erbaut	7050
Erbisoeul	7050
Ere	7500
Erembodegem (Aalst)	9320
Erezée	6997
Ermeton-sur-Biert	5644
Ernage	5030
Erneuville	6972
Ernonheid	4920
Erondegem	9420
Erpe	9420
Erpe-Mere	9420
Erpent	5101
Erpion	6441
Erps-Kwerps	3071
Erquelinnes	6560
Erquennes	7387
Ertvelde	9940
Erwetegem	9620
Escanaffles	7760
Esen	8600
Esneux	4130
Espierres	8587
Espierres-Helchin	8587
Esplechin	7502
Esquelmes	7743
Essen	2910
Essene	1790
Estaimbourg	7730
Estaimpuis	7730
Estinnes	7120
Estinnes-au-Mont	7120
Estinnes-au-Val	7120
Etalle	6740
Ethe	6760
Etikhove	9680
Ettelgem	8460
Etterbeek	1040
Eugies (Frameries)	7080
Eupen	4700
Europees Parlement	1047
Evegnée	4631
Evelette	5350
Everbeek	9660
Everberg	3078
Evere	1140
Evergem	9940
Evregnies	7730
Evrehailles	5530
Eynatten	4731
Ezemaal	3400
F.B.A. (Forces Belges en Allemagne)	4090
Fagnolle	5600
Faimes	4317
Falaën	5522
Falisolle	5060
Fallais	4260
Falmagne	5500
Falmignoul	5500
Familleureux	7181
Farciennes	6240
Faulx-les-Tombes	5340
Fauroeulx	7120
Fauvillers	6637
Faymonville	4950
Fays-les-Veneurs	6856
Fayt-le-Franc	7387
Fayt-lez-Manage	7170
Felenne	5570
Feluy	7181
Feneur	4607
Fernelmont	5380
Ferrières	4190
Feschaux	5570
Fexhe-le-Haut-Clocher	4347
Fexhe-Slins	4458
Filot	4181
Finnevaux	5560
Fize-Fontaine	4530
Fize-le-Marsal	4367
Flamierge	6686
Flavion	5620
Flawinne	5020
Flémalle	4400
Flémalle-Grande	4400
Flémalle-Haute	4400
Flénu	7012
Fléron	4620
Fleurus	6220
Flobecq	7880
Flône	4540
Florée	5334
Floreffe	5150
Florennes	5620
Florenville	6820
Floriffoux	5150
Flostoy	5370
Focant	5572
Folx-les-Caves	1350
Fontaine-l'Evêque	6140
Fontaine-Valmont	6567
Fontenelle	5650
Fontenoille	6820
Fontenoy	7643
Fooz	4340
Forchies-la-Marche	6141
Forest	1190
Forest (Ht.)	7910
Forêt	4870
Forge-Philippe	6596
Forges	6464
Forrières	6953
Forville	5380
Fosse (Lg.)	4980
Fosses-la-Ville	5070
Fouleng	7830
Fourbechies	6440
Fouron-le-Comte	3798
Fourons	3790
Fouron-Saint-Martin	3790
Fouron-Saint-Pierre	3792
Foy-Notre-Dame	5504
Fraipont	4870
Fraire	5650
Fraiture	4557
Frameries	7080
Framont	6853
Franchimont	5600
Francorchamps	4970
Franc-Waret	5380
Franière	5150
Frasnes (Nam.)	5660
Frasnes-lez-Anvaing	7910
Frasnes-lez-Buissenal	7911
Frasnes-lez-Gosselies	6210
Freloux	4347
Freux	6800
Froidchapelle	6440
Froidfontaine	5576
Froidmont	7504
Fronville	6990
Froyennes	7503
Fumal	4260
Furfooz	5500
Furnaux	5641
Gaasbeek	1750
Gages	7943
Gallaix	7906
Galmaarden	1570
Ganshoren	1083
Gaurain-Ramecroix (Tournai)	7530
Gavere	9890
Gedinne	5575
Geel	2440
Geer	4250
Geest-Gérompont-Petit-Rosière	1367
Geetbets	3450
Gelbressée	5024
Gelinden	3800
Gellik	3620
Gelrode	3200
Geluveld	8980
Geluwe	8940
Gembes	6929
Gembloux	5030
Gemmenich	4851
Genappe	1470
Genk	3600
Genly	7040
Genoelselderen	3770
Gent	9000
Gentbrugge	9050
Gentinnes	1450
Genval	1332
Geraardsbergen	9500
Gerdingen	3960
Gerin	5524
Gérompont	1367
Gérouville	6769
Gerpinnes	6280
Gestel	2590
Gesves	5340
Ghislenghien	7822
Ghlin	7011
Ghoy	7863
Gibecq	7823
Gierle	2275
Gijverinkhove	8691
Gijzegem	9308
Gijzelbrechtegem	8570
Gijzenzele	9860
Gilly (Charleroi)	6060
Gimnée	5680
Gingelom	3890
Gistel	8470
Gits	8830
Givry	7041
Glabais	1473
Glabbeek-Zuurbemde	3380
Glain	4000
Gleixhe	4400
Glimes	1315
Glons	4690
Gochenée	5680
Godarville	7160
Godinne	5530
Godveerdegem	9620
Goé	4834
Goeferdinge	9500
Goegnies-Chaussée	7040
Goesnes	5353
Goetsenhoven	3300
Gomzé-Andoumont	4140
Gondregnies	7830
Gonrieux	5660
Gontrode	9090
Gooik	1755
Gorsem	3803
Gors-Opleeuw	3840
Gosselies	6041
Gotem	3840
Gottem	9800
Gottignies	7070
Gougnies	6280
Gourdinne	5651
Goutroux	6030
Gouvy	6670
Gouy-lez-Piéton	6181
Gozée	6534
Grâce-Berleur	4460
Grâce-Hollogne	4460
Graide	5555
Grammene	9800
Grand-Axhe	4300
Grandglise	7973
Grand-Hallet	4280
Grand-Halleux	6698
Grandhan	6940
Grand-Leez	5031
Grand-Manil	5030
Grandmenil	6960
Grandmetz	7900
Grand-Rechain	4650
Grand-Reng	6560
Grandrieu	6470
Grand-Rosière-Hottomont	1367
Grandville	4360
Grandvoir	6840
Grapfontaine	6840
Graty	7830
Graux	5640
Grazen	3450
Grembergen	9200
Grez-Doiceau	1390
Grimbergen	1850
Grimminge	9506
Grivegnée	4030
Grobbendonk	2280
Groot-Bijgaarden	1702
Groot-Gelmen	3800
Groot-Loon	3840
Grosage	7950
Gros-Fays	5555
Grote-Brogel	3990
Grotenberge	9620
Grote-Spouwen	3740
Gruitrode	3670
Grune	6952
Grupont	6927
Guignies	7620
Guigoven	3723
Guirsch	6704
Gullegem	8560
Gutschoven	3870
Haacht	3150
Haaltert	9450
Haasdonk	9120
Haasrode	3053
Habay	6720
Habay-la-Neuve	6720
Habay-la-Vieille	6723
Habergy	6782
Haccourt	4684
Hachy	6720
Hacquegnies	7911
Haren (Bruxelles)	1130
Haillot	5351
Haine-Saint-Paul	7100
Haine-Saint-Pierre	7100
Hainin	7350
Hakendover	3300
Halanzy	6792
Halen	3545
Hallaar	2220
Halle	1500
Halle (Kempen)	2980
Halle-Booienhoven	3440
Halleux	6986
Halma	6922
Halmaal	3800
Haltinne	5340
Ham	3945
Hamipré	6840
Hamme (Bt.)	1785
Hamme (O.-Vl.)	9220
Hamme-Mille	1320
Hamoir	4180
Hamois	5360
Hamont	3930
Hamont-Achel	3930
Hampteau	6990
Ham-sur-Heure	6120
Ham-sur-Heure-Nalinnes	6120
Ham-sur-Sambre	5190
Handzame	8610
Haneffe	4357
Hannêche	4210
Hannut	4280
Hanret	5310
Hansbeke	9850
Han-sur-Lesse	5580
Hantes-Wihéries	6560
Hanzinelle	5621
Hanzinne	5621
Harchies	7321
Harelbeke	8530
Haren (Borgloon)	3840
Haren (Brussel)	1130
Haren (Tongeren)	3700
Hargimont	6900
Harmignies	7022
Harnoncourt	6767
Harre	6960
Harsin	6950
Harveng	7022
Harzé	4920
Hasselt	3500
Hastière	5540
Hastière-Lavaux	5540
Hastière-par-Delà	5541
Hatrival	6870
Haulchin	7120
Hauset	4730
Haut-Fays	6929
Haut-Ittre	1461
Haut-le-Wastia	5537
Hautrage	7334
Havay	7041
Havelange	5370
Haversin	5590
Havinnes	7531
Havré	7021
Hechtel	3940
Hechtel-Eksel	3940
Heer	5543
Heers	3870
Hees	3740
Heestert	8551
Heffen	2801
Heikruis	1670
Heindonk	2830
Heinsch	6700
Heist-aan-Zee	8301
Heist-op-den-Berg	2220
Hekelgem	1790
Heks	3870
Helchin	8587
Helchteren	3530
Heldergem	9450
Hélécine	1357
Helen-Bos	3440
Helkijn	8587
Hellebecq	7830
Hemelveerdegem	9571
Hemiksem	2620
Hemptinne (Fernelmont)	5380
Hemptinne-lez-Florennes	5620
Hendrieken	3840
Henis	3700
Hennuyères	7090
Henri-Chapelle	4841
Henripont	7090
Hensies	7350
Heppen	3971
Heppenbach	4771
Heppignies	6220
Herbeumont	6887
Herchies	7050
Herderen	3770
Herdersem	9310
Herent	3020
Herentals	2200
Herenthout	2270
Herfelingen	1540
Hergenrath	4728
Hérinnes-lez-Pecq	7742
Herk-de-Stad	3540
Hermalle-sous-Argenteau	4681
Hermalle-sous-Huy	4480
Hermée	4680
Hermeton-sur-Meuse	5540
Herne	1540
Héron	4217
Herquegies	7911
Herseaux	7712
Herselt	2230
Herstal	4040
Herstappe	3717
Hertain	7522
Herten	3831
Hertsberge	8020
Herve	4650
Herzele	9550
Heule	8501
Heure (Nam.)	5377
Heure-le-Romain	4682
Heurne	9700
Heusden (Limb.)	3550
Heusden (O.-Vl.)	9070
Heusden-Zolder	3550
Heusy	4802
Heuvelland	8950
Hever	3191
Heverlee	3001
Hévillers	1435
Heyd	6941
Hillegem	9550
Hingene	2880
Hingeon	5380
Hives	6984
Hoboken (Antwerpen)	2660
Hodeige	4351
Hodister	6987
Hody	4162
Hoegaarden	3320
Hoeilaart	1560
Hoeke	8340
Hoelbeek	3746
Hoeleden	3471
Hoepertingen	3840
Hoeselt	3730
Hoevenen	2940
Hofstade (Bt.)	1981
Hofstade (O.-Vl.)	9308
Hogne	5377
Hognoul	4342
Hollain	7620
Hollange	6637
Hollebeke	8902
Hollogne-aux-Pierres	4460
Hollogne-sur-Geer	4250
Holsbeek	3220
Hombeek	2811
Hombourg	4852
Hompré	6640
Hondelange	6780
Honnay	5570
Honnelles	7387
Hooglede	8830
Hoogstade	8690
Hoogstraten	2320
Horebeke	9667
Horion-Hozémont	4460
Hornu	7301
Horpmaal	3870
Horrues	7060
Hotton	6990
Houdemont	6724
Houdeng-Aimeries	7110
Houdeng-Goegnies (La Louvière)	7110
Houdremont	5575
Houffalize	6660
Hour	5563
Housse	4671
Houtaing	7812
Houtain-le-Val	1476
Houtain-Saint-Siméon	4682
Houtave	8377
Houtem (W.-Vl.)	8630
Houthalen	3530
Houthalen-Helchteren	3530
Houthem (Comines)	7781
Houthulst	8650
Houtvenne	2235
Houwaart	3390
Houx	5530
Houyet	5560
Hove	2540
Hoves (Ht.)	7830
Howardries	7624
Huccorgne	4520
Huise	9750
Huissignies	7950
Huizingen	1654
Huldenberg	3040
Hulshout	2235
Hulsonniaux	5560
Hulste	8531
Humain	6900
Humbeek	1851
Hundelgem	9630
Huppaye	1367
Huy	4500
Hyon	7022
Ichtegem	8480
Iddergem	9472
Idegem	9506
Ieper	8900
Impe	9340
Incourt	1315
Ingelmunster	8770
Ingooigem	8570
International Press Center	1041
Irchonwelz	7801
Isières	7822
Isnes	5032
Itegem	2222
Itterbeek	1701
Ittre	1460
Ivoz-Ramet	4400
Ixelles	1050
Izegem	8870
Izel	6810
Izenberge	8691
Izier	6941
Jabbeke	8490
Jalhay	4845
Jallet	5354
Jamagne	5600
Jambes (Namur)	5100
Jamiolle	5600
Jamioulx	6120
Jamoigne	6810
Jandrain-Jandrenouille	1350
Jauche	1350
Jauchelette	1370
Javingue	5570
Jehay	4540
Jehonville	6880
Jemappes	7012
Jemelle	5580
Jemeppe-sur-Meuse	4101
Jemeppe-sur-Sambre	5190
Jeneffe (Lg.)	4357
Jeneffe (Nam.)	5370
Jesseren (Kolmont)	3840
Jette	1090
Jeuk	3890
Jodoigne	1370
Jodoigne-Souveraine	1370
Jollain-Merlin	7620
Joncret	6280
Julémont	4650
Jumet (Charleroi)	6040
Jupille-sur-Meuse	4020
Juprelle	4450
Jurbise	7050
Juseret	6642
Kaaskerke	8600
Kachtem	8870
Kaggevinne	3293
Kain	7540
Kalken	9270
Kallo (Beveren-Waas)	9120
Kallo (Kieldrecht)	9130
Kalmthout	2920
Kamer van Volksvertegenwoordigers	1008
Kampenhout	1910
Kanegem	8700
Kanne	3770
Kapellen (Antw.)	2950
Kapellen (Bt.)	3381
Kapelle-op-den-Bos	1880
Kaprijke	9970
Kaster	8572
Kasterlee	2460
Kaulille	3950
Keerbergen	3140
Keiem	8600
Kelmis	4720
Kemexhe	4367
Kemmel	8956
Kemzeke	9190
Kerkhove	8581
Kerkom	3370
Kerkom-bij-Sint-Truiden	3800
Kerksken	9451
Kermt (Hasselt)	3510
Kerniel	3840
Kersbeek-Miskom	3472
Kessel	2560
Kessel-Lo (Leuven)	3010
Kessenich	3640
Kester	1755
Kettenis	4701
Keumiée	5060
Kieldrecht (Beveren)	9130
Kinrooi	3640
Kleine-Brogel	3990
Kleine-Spouwen	3740
Klein-Gelmen	3870
Klemskerke	8420
Klerken	8650
Kluisbergen	9690
Kluizen	9940
Knesselare	9910
Knokke	8300
Knokke-Heist	8300
Kobbegem	1730
Koekelare	8680
Koekelberg	1081
Koersel	3582
Koksijde	8670
Kolmont (Borgloon)	3840
Kolmont (Tongeren)	3700
Komen	7780
Komen-Waasten	7780
Koningshooikt	2500
Koninksem	3700
Kontich	2550
Kooigem	8510
Koolkerke	8000
Koolskamp	8851
Korbeek-Dijle	3060
Korbeek-Lo	3360
Kortemark	8610
Kortenaken	3470
Kortenberg	3070
Kortessem	3720
Kortijs	3890
Kortrijk	8500
Kortrijk-Dutsel	3220
Kozen	3850
Kraainem	1950
Krombeke	8972
Kruibeke	9150
Kruishoutem	9770
Kumtich	3300
Kuringen	3511
Kuttekoven	3840
Kuurne	8520
Kwaadmechelen	3945
Kwaremont	9690
La Bouverie	7080
La Bruyère	5080
La Calamine	4720
La Glanerie	7611
La Gleize	4987
La Hestre	7170
La Hulpe	1310
La Louvière	7100
La Reid	4910
La Roche-en-Ardenne	6980
Laakdal	2430
Laar	3400
Laarne	9270
Labuissière	6567
Lacuisine	6821
Ladeuze	7950
Laeken (Bruxelles)	1020
Laforêt	5550
Lahamaide	7890
Laken (Brussel)	1020
Lamain	7522
Lambermont	4800
Lambusart	6220
Lamine	4350
Lamontzée	4210
Lamorteau	6767
Lampernisse	8600
Lanaken	3620
Lanaye	4600
Landegem	9850
Landelies	6111
Landen	3400
Landenne	5300
Landskouter	9860
Laneffe	5651
Langdorp	3201
Langemark	8920
Langemark-Poelkapelle	8920
Lanklaar	3650
Lanquesaint	7800
Lantin	4450
Lantremange	4300
Laplaigne	7622
Lapscheure	8340
Lasne	1380
Lasne-Chapelle-Saint-Lambert	1380
Lathuy	1370
Latinne	4261
Latour	6761
Lauw	3700
Lauwe	8930
Lavacherie	6681
Lavaux-Sainte-Anne	5580
Lavoir	4217
Le Mesnil	5670
Le Roeulx	7070
Le Roux	5070
Lebbeke	9280
l'Ecluse	1320
Lede	9340
Ledeberg (Gent)	9050
Ledegem	8880
Leefdaal	3061
Leerbeek	1755
Leernes	6142
Leers-et-Fosteau	6530
Leers-Nord	7730
Leest	2811
Leeuwergem	9620
Leffinge	8432
Léglise	6860
Leignon	5590
Leisele	8691
Leke	8600
Lembeek	1502
Lembeke	9971
Lemberge	9820
Lendelede	8860
Lennik	1750
Lens	7870
Lens-Saint-Remy	4280
Lens-Saint-Servais	4250
Lens-sur-Geer	4360
Leopoldsburg	3970
Les Avins	4560
Les Bons Villers	6210
Les Bulles	6811
Les Hayons	6830
Les Waleffes	4317
l'Escaillère	6464
Lesdain	7621
Lessines	7860
Lessive	5580
Lesterny	6953
Lesve	5170
Lettelingen	7850
Letterhoutem	9521
Leugnies	6500
Leupegem	9700
Leut	3630
Leuven	3000
Leuze (Nam.)	5310
Leuze-en-Hainaut	7900
Leval-Chaudeville	6500
Leval-Trahegnies	7134
Liberchies	6238
Libin	6890
Libramont-Chevigny	6800
Lichtaart	2460
Lichtervelde	8810
Liedekerke	1770
Lieferinge	9400
Liège	4000
Liège	4020
Liège	4030
Lier	2500
Lierde	9570
Lierneux	4990
Liernu	5310
Liers	4042
Liezele	2870
Ligne	7812
Ligney	4254
Ligny	5140
Lille	2275
Lillo	2040
Lillois-Witterzée	1428
Limal	1300
Limbourg	4830
Limelette	1342
Limerlé	6670
Limont	4357
Lincent	4287
Linden	3210
Linkebeek	1630
Linkhout	3560
Linsmeau	1357
Lint	2547
Linter	3350
Lippelo	2890
Lisogne	5501
Lissewege	8380
Lives-sur-Meuse	5101
Lixhe	4600
Lo	8647
Lobbes	6540
Lochristi	9080
Lodelinsart	6042
Loenhout	2990
Loker	8958
Lokeren	9160
Loksbergen	3545
Lombardsijde	8434
Lombise	7870
Lommel	3920
Lommersweiler	4783
Lompret	6463
Lomprez	6924
Loncin	4431
Londerzeel	1840
Longchamps (Lux.)	6688
Longchamps (Nam.)	5310
Longlier	6840
Longueville	1325
Longvilly	6600
Lontzen	4710
Lonzée	5030
Loonbeek	3040
Loppem	8210
Lorcé	4987
Lo-Reninge	8647
Lot	1651
Lotenhulle	9880
Louette-Saint-Denis	5575
Louette-Saint-Pierre	5575
Loupoigne	1471
Louvain-la-Neuve	1348
Louveigné	4141
Lovendegem	9920
Lovenjoel	3360
Loverval	6280
Loyers	5101
Lubbeek	3210
Luingne	7700
Lummen	3560
Lustin	5170
Luttre	6238
Maarkedal	9680
Maarke-Kerkem	9680
Maaseik	3680
Maasmechelen	3630
Mabompré	6663
Machelen (Bt.)	1830
Machelen (O.-Vl.)	9870
Macon	6591
Macquenoise	6593
Maffe	5374
Maffle	7810
Magnée	4623
Maillen	5330
Mainvault	7812
Maisières	7020
Maissin	6852
Maizeret	5300
Mal	3700
Maldegem	9990
Malderen	1840
Malempré	6960
Malèves-Sainte-Marie-Wastines	1360
Malle	2390
Malmedy	4960
Malonne	5020
Malvoisin	5575
Manage	7170
Manderfeld	4760
Manhay	6960
Mannekensvere	8433
Maransart	1380
Marbais (Bt.)	1495
Marbaix (Ht.)	6120
Marbehan	6724
Marche-en-Famenne	6900
Marche-les-Dames	5024
Marche-lez-Ecaussinnes	7190
Marchienne-au-Pont	6030
Marchin	4570
Marchipont	7387
Marchovelette	5380
Marcinelle	6001
Marcourt	6987
Marcq	7850
Marenne	6990
Mariakerke (Gent)	9030
Mariekerke (Bornem)	2880
Mariembourg	5660
Marilles	1350
Mark	7850
Marke (Kortrijk)	8510
Markegem	8720
Marneffe	4210
Marquain	7522
Martelange	6630
Martenslinde	3742
Martouzin-Neuville	5573
Masbourg	6953
Masnuy-Saint-Jean (Jurbise)	7050
Masnuy-Saint-Pierre	7050
Massemen	9230
Massenhoven	2240
Matagne-la-Grande	5680
Matagne-la-Petite	5680
Mater	9700
Maubray	7640
Maulde	7534
Maurage	7110
Mazée	5670
Mazenzele	1745
Mazy	5032
Méan	5372
Mechelen	2800
Mechelen-aan-de-Maas	3630
Mechelen-Bovelingen	3870
Meeffe	4219
Meensel-Kiezegem	3391
Meer	2321
Meerbeek	3078
Meerbeke	9402
Meerdonk	9170
Meerhout	2450
Meerle	2328
Meeswijk	3630
Meetkerke	8377
Meeuwen	3670
Meeuwen-Gruitrode	3670
Mehaigne	5310
Meigem	9800
Meilegem	9630
Meise	1860
Meix-Devant-Virton	6769
Meix-le-Tige	6747
Melden	9700
Meldert (Bt.)	3320
Meldert (Limb.)	3560
Meldert (O.-Vl.)	9310
Melen	4633
Mélin	1370
Melkwezer	3350
Melle	9090
Mellery	1495
Melles	7540
Mellet	6211
Mellier	6860
Melsbroek	1820
Melsele	9120
Melsen	9820
Membach	4837
Membre	5550
Membruggen	3770
Mendonk	9042
Menen	8930
Merbes-le-Château	6567
Merbes-Sainte-Marie	6567
Merchtem	1785
Merdorp	4280
Mere	9420
Merelbeke	9820
Merendree	9850
Merkem	8650
Merksem (Antwerpen)	2170
Merksplas	2330
Merlemont	5600
Mesen	8957
Meslin-l'Evêque	7822
Mesnil-Eglise	5560
Mesnil-Saint-Blaise	5560
Mespelare	9200
Messancy	6780
Messelbroek	3272
Messines	8957
Mesvin	7022
Mettekoven	3870
Mettet	5640
Meulebeke	8760
Meux	5081
Mévergnies-lez-Lens	7942
Meyerode	4770
Michelbeke	9660
Micheroux	4630
Middelburg	9992
Middelkerke	8430
Miécret	5376
Mielen-Boven-Aalst	3891
Mignault	7070
Millen	3770
Milmort	4041
Minderhout	2322
Mirwart	6870
Modave	4577
Moelingen	3790
Moen	8552
Moerbeke	9500
Moerbeke-Waas	9180
Moere	8470
Moerkerke	8340
Moerzeke	9220
Moeskroen	7700
Moha	4520
Mohiville	5361
Moignelée	5060
Moircy	6800
Mol	2400
Molenbaix	7760
Molenbeek-Saint-Jean	1080
Molenbeek-Wersbeek	3461
Molenbeersel	3640
Molenstede	3294
Mollem	1730
Momalle	4350
Momignies	6590
Monceau-en-Ardenne	5555
Monceau-Imbrechies	6592
Monceau-sur-Sambre	6031
Mons	7000
Mons-lez-Liège	4400
Monstreux	1400
Mont (Lux.)	6661
Mont (Nam.)	5530
Montbliart	6470
Mont-de-l'Enclus	7750
Montegnée	4420
Montenaken	3890
Mont-Gauthier	5580
Montignies-lez-Lens	7870
Montignies-Saint-Christophe	6560
Montignies-sur-Roc	7387
Montignies-sur-Sambre	6061
Montigny-le-Tilleul	6110
Montleban	6674
Montroeul-au-Bois	7911
Montroeul-sur-Haine	7350
Mont-Saint-André	1367
Mont-Saint-Aubert	7542
Mont-Sainte-Aldegonde	7141
Mont-Sainte-Geneviève	6540
Mont-Saint-Guibert	1435
Mont-sur-Marchienne	6032
Montzen	4850
Moorsel	9310
Moorsele	8560
Moorslede	8890
Moortsele	9860
Mopertingen	3740
Moregem	9790
Moresnet	4850
Morhet	6640
Morialmé	5621
Morkhoven	2200
Morlanwelz	7140
Morlanwelz-Mariemont	7140
Mormont	6997
Mornimont	5190
Mortier	4670
Mortroux	4607
Mortsel	2640
Morville	5620
Mouland	3790
Moulbaix	7812
Mourcourt	7543
Mouscron	7700
Moustier (Ht.)	7911
Moustier-sur-Sambre	5190
Mouzaive	5550
Moxhe	4280
Mozet	5340
Muizen (Limb.)	3891
Muizen (Mechelen)	2812
Mullem	9700
Munkzwalm	9630
Muno	6820
Munsterbilzen	3740
Munte	9820
Musson	6750
Mussy-la-Ville	6750
My	4190
Naast	7062
Nadrin	6660
Nafraiture	5550
Nalinnes	6120
Namêche	5300
Namur	5000
Nandrin	4550
Naninne	5100
Naomé	5555
Nassogne	6950
Natoye	5360
NAVO - NATO	1110
Nazareth	9810
Néchin	7730
Nederboelare	9500
Nederbrakel	9660
Nederename	9700
Nederhasselt	9400
Nederokkerzeel	1910
Neder-Over-Heembeek (Bru.)	1120
Nederzwalm-Hermelgem	9636
Neerglabbeek	3670
Neerharen	3620
Neerhespen	3350
Neerheylissem	1357
Neerijse	3040
Neerlanden	3404
Neerlinter	3350
Neeroeteren	3680
Neerpelt	3910
Neerrepen	3700
Neervelp	3370
Neerwaasten	7784
Neerwinden	3400
Neigem	9403
Nerem	3700
Nessonvaux	4870
Nethen	1390
Nettinne	5377
Neufchâteau	6840
Neufchâteau (Lg.)	4608
Neufmaison	7332
Neufvilles	7063
Neu-Moresnet	4721
Neupré	4120
Neuville (Philippeville)	5600
Neuville-en-Condroz	4121
Nevele	9850
Niel	2845
Niel-bij-As	3668
Niel-bij-Sint-Truiden	3890
Nieuwenhove	9506
Nieuwenrode	1880
Nieuwerkerken (Aalst)	9320
Nieuwerkerken (Limb.)	3850
Nieuwkapelle	8600
Nieuwkerke	8950
Nieuwkerken-Waas	9100
Nieuwmunster	8377
Nieuwpoort	8620
Nieuwrode	3221
Nijlen	2560
Nil-Saint-Vincent-Saint-Martin	1457
Nimy	7020
Ninove	9400
Nismes	5670
Nivelles	1400
Niverlée	5680
Nives	6640
Nobressart	6717
Nodebais	1320
Noduwez	1350
Noirchain	7080
Noirefontaine	6831
Noiseux	5377
Nokere	9771
Nollevaux	6851
Noorderwijk	2200
Noordschote	8647
Nossegem	1930
Nothomb	6717
Nouvelles	7022
Noville (Lg.)	4347
Noville (Lux.)	6600
Noville-les-Bois	5380
Noville-sur-Méhaigne	5310
Nukerke	9681
Obaix	6230
Obigies	7743
Obourg	7034
Ochamps	6890
Ocquier	4560
Odeigne	6960
Odeur	4367
Oedelem	8730
Oekene	8800
Oelegem	2520
Oeren	8690
Oeselgem	8720
Oetingen	1755
Oeudeghien	7911
Oevel	2260
Offagne	6850
Ogy	7862
Ohain	1380
Ohey	5350
Oignies-en-Thiérache	5670
Oisquercq	1480
Oizy	5555
Okegem	9400
Olen	2250
Oleye	4300
Ollignies	7866
Olloy-sur-Viroin	5670
Olmen	2491
Olne	4877
Olsene	9870
Omal	4252
Ombret	4540
Omezée	5600
On	6900
Onhaye	5520
Onkerzele	9500
Onnezies	7387
Onoz	5190
Onze-Lieve-Vrouw-Lombeek	1760
Onze-Lieve-Vrouw-Waver	2861
Ooigem	8710
Ooike (Wortegem-Petegem)	9790
Oombergen (Zottegem)	9620
Oorbeek	3300
Oordegem	9340
Oostakker	9041
Oostduinkerke	8670
Oosteeklo	9968
Oostende	8400
Oosterzele	9860
Oostham	3945
Oostkamp	8020
Oostkerke (Damme)	8340
Oostkerke (Diksmuide)	8600
Oostmalle	2390
Oostnieuwkerke	8840
Oostrozebeke	8780
Oostvleteren	8640
Oostwinkel	9931
Opbrakel	9660
Opdorp	9255
Opglabbeek	3660
Opgrimbie	3630
Ophain-Bois-Seigneur-Isaac	1421
Ophasselt	9500
Opheers	3870
Opheylissem	1357
Ophoven	3640
Opitter	3960
Oplinter	3300
Opoeteren	3680
Opont	6852
Opprebais	1315
Oppuurs	2890
Opvelp	3360
Opwijk	1745
Orbais	1360
Orchimont	5550
Orcq	7501
Ordingen	3800
Oret	5640
Oreye	4360
Organisations Sociales Chrétiennes	1031
Orgeo	6880
Ormeignies	7802
Orp-Jauche	1350
Orp-le-Grand	1350
Orroir	7750
Orsmaal-Gussenhoven	3350
Ortho	6983
Ostiches	7804
OTAN - NATO	1110
Otegem	8553
Oteppe	4210
Othée	4340
Otrange	4360
Ottenburg	3040
Ottergem	9420
Ottignies	1340
Ottignies-Louvain-la-Neuve	1340
Oudegem	9200
Oudekapelle	8600
Oudenaarde	9700
Oudenaken	1600
Oudenburg	8460
Oudergem	1160
Oud-Heverlee	3050
Oud-Turnhout	2360
Ouffet	4590
Ougrée	4102
Oupeye	4680
Outer	9406
Outgaarden	3321
Outrelouxhe	4577
Outrijve	8582
Ouwegem	9750
Overboelare	9500
Overhespen	3350
Overijse	3090
Overmere	9290
Overpelt	3900
Overrepen (Kolmont)	3700
Overwinden	3400
Paal	3583
Paifve	4452
Pailhe	4560
Paliseul	6850
Pamel	1760
Papignies	7861
Parike	9661
Parlement de la Communauté française	1012
Parlement Européen	1047
Passendale	8980
Patignies	5575
Paturages	7340
Paulatem	9630
Pecq	7740
Peer	3990
Peissant	7120
Pellaines	4287
Pellenberg	3212
Pepingen	1670
Pepinster	4860
Perk	1820
Péronnes-lez-Antoing	7640
Péronnes-lez-Binche	7134
Péruwelz	7600
Pervijze	8600
Perwez	1360
Perwez-Haillot	5352
Pesche	5660
Pessoux	5590
Petegem-aan-de-Leie	9800
Petegem-aan-de-Schelde	9790
Petigny	5660
Petite-Chapelle	5660
Petit-Enghien	7850
Petit-Fays	5555
Petit-Hallet	4280
Petit-Rechain	4800
Petit-Roeulx-lez-Braine	7090
Petit-Roeulx-lez-Nivelles	7181
Petit-Thier	6692
Peutie	1800
Philippeville	5600
Piéton	7160
Piétrain	1370
Piètrebais	1315
Pipaix	7904
Piringen (Haren)	3700
Pironchamps	6240
Pittem	8740
Plainevaux	4122
Plancenoit	1380
Ploegsteert	7782
Plombières	4850
Poederlee	2275
Poeke	9880
Poelkapelle	8920
Poesele	9850
Pollare	9401
Polleur	4910
Pollinkhove	8647
Pommeroeul	7322
Pondrôme	5574
Pont-à-Celles	6230
Pont-de-Loup	6250
Pontillas	5380
Poperinge	8970
Poppel	2382
Popuelles	7760
Porcheresse (Lux.)	6929
Porcheresse (Nam.)	5370
Postcheque	1100
Pottes	7760
Poucet	4280
Poulseur	4171
Poupehan	6830
Pousset	4350
Presgaux	5660
Presles	6250
Profondeville	5170
Promo-Control	1414
Proven	8972
Pry	5650
Pulderbos	2242
Pulle	2243
Purnode	5530
Pussemange	5550
Putte	2580
Puurs	2870
Quaregnon	7390
Quartes	7540
Quenast	1430
Queue-du-Bois	4610
Quevaucamps	7972
Quévy	7040
Quévy-le-Grand	7040
Quévy-le-Petit	7040
Quiévrain	7380
R.T.L. - T.V.I.	1201
Raad Vlaamse Gemeenschapscommissie	1006
Rachecourt	6792
Racour	4287
Raeren	4730
Ragnies	6532
Rahier	4987
Ramegnies	7971
Ramegnies-Chin	7520
Ramelot	4557
Ramillies	1367
Ramsdonk	1880
Ramsel	2230
Ramskapelle (Knokke-Heist)	8301
Ramskapelle (Nieuwpoort)	8620
Rance	6470
Ransart	6043
Ransberg	3470
Ranst	2520
Ravels	2380
Rebaix	7804
Rebecq	1430
Rebecq-Rognon	1430
Recht	4780
Recogne	6800
Redu	6890
Reet	2840
Rekem	3621
Rekkem	8930
Relegem	1731
Remagne	6800
Remersdaal	3791
Remicourt	4350
Renaix	9600
Rendeux	6987
Reninge	8647
Reningelst	8970
Renlies	6500
Reppel	3950
Ressaix	7134
Ressegem	9551
Resteigne	6927
Retie	2470
Retinne	4621
Reuland	4790
Rèves	6210
Rhisnes	5080
Rhode-Saint-Genese	1640
Richelle	4600
Riemst	3770
Rienne	5575
Rièzes	6464
Rijkel	3840
Rijkevorsel	2310
Rijkhoven	3740
Rijksadministratief Centrum	1010
Rijmenam	2820
Riksingen	3700
Rillaar	3202
Rivière	5170
Rixensart	1330
Robechies	6460
Robelmont	6769
Robertville	4950
Roborst	9630
Rochefort	5580
Rochehaut	6830
Rocherath	4761
Roclenge-sur-Geer	4690
Rocourt	4000
Roesbrugge-Haringe	8972
Roeselare	8800
Rognée	5651
Roisin	7387
Roksem	8460
Rollegem	8510
Rollegem-Kapelle	8880
Roloux	4347
Roly	5600
Romedenne	5600
Romerée	5680
Romershoven	3730
Romsée	4624
Rongy	7623
Ronquières	7090
Ronse	9600
Ronsele	9932
Roosbeek	3370
Roosdaal	1760
Rosée	5620
Roselies	6250
Rosières	1331
Rosmeer	3740
Rosoux-Crenwick	4257
Rossignol	6730
Rotem	3650
Rotheux-Rimière	4120
Rotselaar	3110
Roucourt	7601
Rouveroy (Ht.)	7120
Rouvreux	4140
Rouvroy	6767
Roux	6044
Roux-Miroir	1315
Roy	6900
Rozebeke	9630
RTBF	1044
Ruddervoorde	8020
Ruette	6760
Ruien	9690
Ruisbroek (Antw.)	2870
Ruisbroek (Bt.)	1601
Ruiselede	8755
Rukkelingen-Loon	3870
Rulles	6724
Rumbeke	8800
Rumes	7610
Rumillies	7540
Rummen	3454
Rumsdorp	3400
Rumst	2840
Runkelen	3803
Rupelmonde	9150
Russeignies	7750
Rutten	3700
's Gravenvoeren	3798
's Gravenwezel	2970
's Herenelderen	3700
S.H.A.P.E. België	7010
S.H.A.P.E. Belgique	7010
Saint-Amand	6221
Saint-André	4606
Saint-Aubin	5620
Saint-Denis (Ht.)	7034
Saint-Denis-Bovesse	5081
Sainte-Cécile	6820
Sainte-Marie-Chevigny	6800
Sainte-Marie-sur-Semois	6740
Sainte-Ode	6680
Saintes	1480
Saint-Georges-sur-Meuse	4470
Saint-Gérard	5640
Saint-Germain	5310
Saint-Géry	1450
Saint-Ghislain	7330
Saint-Gilles	1060
Saint-Hubert	6870
Saint-Jean-Geest	1370
Saint-Josse-ten-Noode	1210
Saint-Léger (Ht.)	7730
Saint-Léger (Lux.)	6747
Saint-Marc	5003
Saint-Mard	6762
Saint-Martin	5190
Saint-Maur	7500
Saint-Médard	6887
Saint-Nicolas (Lg.)	4420
Saint-Pierre	6800
Saint-Remy (Ht.)	6460
Saint-Remy (Lg.)	4672
Saint-Remy-Geest	1370
Saint-Sauveur	7912
Saint-Servais	5002
Saint-Séverin	4550
Saint-Symphorien	7030
Saint-Vaast	7100
Saint-Vincent	6730
Saint-Vith	4780
Saive	4671
Salles	6460
Samart	5600
Sambreville	5060
Samrée	6982
Sankt Vith	4780
Sars-la-Bruyère	7080
Sars-la-Buissière	6542
Sart-Bernard	5330
Sart-Custinne	5575
Sart-Dames-Avelines	1495
Sart-en-Fagne	5600
Sart-Eustache	5070
Sart-lez-Spa	4845
Sart-Saint-Laurent	5070
Sautin	6470
Sautour	5600
Sauvenière	5030
Schaarbeek	1030
Schaerbeek	1030
Schaffen	3290
Schalkhoven	3732
Schaltin	5364
Schelderode	9820
Scheldewindeke	9860
Schelle	2627
Schellebelle	9260
Schendelbeke	9506
Schepdaal	1703
Scherpenheuvel	3270
Scherpenheuvel-Zichem	3270
Schilde	2970
Schoenberg	4782
Schönberg	4782
Schoonaarde	9200
Schore	8433
Schorisse	9688
Schoten	2900
Schriek	2223
Schuiferskapelle	8700
Schulen	3540
Sclayn	5300
Scy	5361
Seilles	5300
Sélange	6781
Seloignes	6596
Semmerzake	9890
Senat de Belgique	1009
Seneffe	7180
Sensenruth	6832
Seny	4557
Senzeille	5630
Septon	6940
Seraing	4100
Seraing-le-Château	4537
Serinchamps	5590
Serskamp	9260
Serville	5521
Sibret	6640
Signeulx	6750
Sijsele	8340
Silenrieux	5630
Silly	7830
Sinaai-Waas	9112
Sinsin	5377
Sint-Agatha-Berchem	1082
Sint-Agatha-Rode	3040
Sint-Amands	2890
Sint-Amandsberg (Gent)	9040
Sint-Andries	8200
Sint-Antelinks	9550
Sint-Baafs-Vijve	8710
Sint-Blasius-Boekel	9630
Sint-Denijs	8554
Sint-Denijs-Boekel	9630
Sint-Denijs-Westrem	9051
Sint-Eloois-Vijve	8793
Sint-Eloois-Winkel	8880
Sint-Genesius-Rode	1640
Sint-Gillis	1060
Sint-Gillis-bij-Dendermonde	9200
Sint-Gillis-Waas	9170
Sint-Goriks-Oudenhove	9620
Sint-Huibrechts-Hern	3730
Sint-Huibrechts-Lille	3910
Sint-Jacobs-Kapelle	8600
Sint-Jan	8900
Sint-Jan-in-Eremo	9982
Sint-Jans-Molenbeek	1080
Sint-Job-in-'t-Goor	2960
Sint-Joost-ten-Node	1210
Sint-Joris (Beernem)	8730
Sint-Joris (Nieuwpoort)	8620
Sint-Joris-Weert	3051
Sint-Joris-Winge	3390
Sint-Katelijne-Waver	2860
Sint-Katherina-Lombeek	1742
Sint-Kornelis-Horebeke	9667
Sint-Kruis (Brugge)	8310
Sint-Kruis-Winkel	9042
Sint-Kwintens-Lennik	1750
Sint-Lambrechts-Herk	3500
Sint-Lambrechts-Woluwe	1200
Sint-Laureins	9980
Sint-Laureins-Berchem	1600
Sint-Lenaarts	2960
Sint-Lievens-Esse	9550
Sint-Lievens-Houtem	9520
Sint-Margriete	9981
Sint-Margriete-Houtem (Tienen)	3300
Sint-Maria-Horebeke	9667
Sint-Maria-Latem	9630
Sint-Maria-Lierde	9570
Sint-Maria-Oudenhove (Zottegem)	9620
Sint-Martens-Bodegem	1700
Sint-Martens-Latem	9830
Sint-Martens-Leerne	9800
Sint-Martens-Lennik	1750
Sint-Martens-Lierde	9572
Sint-Martens-Voeren	3790
Sint-Michiels	8200
Sint-Niklaas	9100
Sint-Pauwels	9170
Sint-Pieters-Kapelle (Bt.)	1541
Sint-Pieters-Kapelle (W.-Vl.)	8433
Sint-Pieters-Leeuw	1600
Sint-Pieters-Rode	3220
Sint-Pieters-Voeren	3792
Sint-Pieters-Woluwe	1150
Sint-Rijkers	8690
Sint-Stevens-Woluwe	1932
Sint-Truiden	3800
Sint-Ulriks-Kapelle	1700
Sippenaeken	4851
Sirault	7332
Sivry	6470
Sivry-Rance	6470
Sleidinge	9940
Slijpe	8433
Slins	4450
Sluizen	3700
Smeerebbe-Vloerzegem	9506
Smetlede	9340
Smuid	6890
Snaaskerke	8470
Snellegem	8490
SOC	1105
Soheit-Tinlot	4557
Sohier	6920
Soignies	7060
Soiron	4861
Solre-Saint-Géry	6500
Solre-sur-Sambre	6560
Sombreffe	5140
Somme-Leuze	5377
Sommethonne	6769
Sommière	5523
Somzée	5651
Sorée	5340
Sorinne-la-Longue	5333
Sorinnes	5503
Sosoye	5537
Sougné-Remouchamps	4920
Soulme	5680
Soumagne	4630
Soumoy	5630
Sourbrodt	4950
Souvret	6182
Sovet	5590
Soy	6997
Soye (Nam.)	5150
Spa	4900
Spalbeek	3510
Spermalie	8433
Spiennes	7032
Spiere	8587
Spiere-Helkijn	8587
Spontin	5530
Spouwen	3740
Sprimont	4140
Spy	5190
Stabroek	2940
Staden	8840
Stalhille	8490
Stambruges	7973
Stave	5646
Stavele	8691
Stavelot	4970
Steendorp	9140
Steenhuffel	1840
Steenhuize-Wijnhuize	9550
Steenkerke (W.-Vl.)	8630
Steenkerque (Ht.)	7090
Steenokkerzeel	1820
Stekene	9190
Stembert	4801
Stene	8400
Sterrebeek	1933
Stevoort	3512
Stokkem	3650
Stokrooie	3511
Stoumont	4987
Straimont	6887
Strée (Ht.)	6511
Strée-lez-Huy	4577
Strépy-Bracquegnies	7110
Strijpen	9620
Strijtem	1760
Strombeek-Bever	1853
Stuivekenskerke	8600
Suarlée	5020
Sugny	5550
Surice	5600
Suxy	6812
Tailles	6661
Taintignies	7618
Tamines	5060
Tarcienne	5651
Tavier	4163
Taviers (Nam.)	5310
Tavigny	6662
Tellin	6927
Templeuve	7520
Temploux	5020
Temse	9140
Tenneville	6970
Teralfene	1790
Terhagen	2840
Termes	6813
Ternat	1740
Tertre	7333
Tervuren	3080
Terwagne	4560
Tessenderlo	3980
Testelt	3272
Teuven	3793
Theux	4910
Thiaumont	6717
Thieu	7070
Thieulain	7901
Thieusies	7061
Thiméon	6230
Thimister	4890
Thimister-Clermont	4890
Thimougies	7533
Thines	1402
Thirimont	6500
Thisnes	4280
Thommen	4791
Thon	5300
Thorembais-les-Béguines	1360
Thorembais-Saint-Trond	1360
Thoricourt	7830
Thuillies	6536
Thuin	6530
Thulin	7350
Thumaide	7971
Thy-le-Bauduin	5621
Thy-le-Château	5651
Thynes	5502
Thys	4367
Tiegem	8573
Tielen	2460
Tielrode	9140
Tielt	8700
Tielt (Bt.)	3390
Tielt-Winge	3390
Tienen	3300
Tignée	4630
Tihange	4500
Tildonk	3150
Tilff	4130
Tillet	6680
Tilleur	4420
Tillier	5380
Tilly	1495
Tinlot	4557
Tintange	6637
Tintigny	6730
Tisselt	2830
Toernich	6700
Tohogne	6941
Tollembeek	1570
Tongeren	3700
Tongerlo (Antw.)	2260
Tongerlo (Limb.)	3960
Tongre-Notre-Dame	7951
Tongre-Saint-Martin	7950
Tongrinne	5140
Tontelange	6717
Torgny	6767
Torhout	8820
Tourinne (Lg.)	4263
Tourinnes-la-Grosse	1320
Tourinnes-Saint-Lambert	1457
Tournai	7500
Tournay	6840
Tourpes	7904
Transinne	6890
Trazegnies	6183
Treignes	5670
Trembleur	4670
Tremelo	3120
Trivières	7100
Trognée	4280
Trois-Ponts	4980
Trooz	4870
Tubize	1480
Turnhout	2300
U.E.-Commission	1049
U.E.-Conseil	1048
Uccle	1180
Ucimont	6833
Uikhoven	3631
Uitbergen	9290
Uitkerke	8370
Ukkel	1180
Ulbeek	3832
Upigny	5310
Ursel	9910
Vaalbeek	3054
Val-Meer	3770
Vance	6741
Varendonk	2431
Varsenare	8490
Vaucelles	5680
Vaulx (Tournai)	7536
Vaulx-lez-Chimay	6462
Vaux-Chavanne	6960
Vaux-et-Borset	4530
Vaux-lez-Rosières	6640
Vaux-sous-Chèvremont	4051
Vaux-sur-Sure	6640
Vechmaal	3870
Vedrin	5020
Veerle	2431
Velaines	7760
Velaine-sur-Sambre	5060
Veldegem	8210
Veldwezelt	3620
Vellereille-les-Brayeux	7120
Vellereille-le-Sec	7120
Velm	3806
Velroux	4460
Veltem-Beisem	3020
Velzeke-Ruddershove	9620
Vencimont	5575
Ver.Verg.Gemeensch.Gemeensch.Comm.	1005
Vergnies	6440
Verlaine	4537
Verlée	5370
Verrebroek	9130
Vertrijk	3370
Verviers	4800
Vesqueville	6870
Veulen	3870
Veurne	8630
Vezin	5300
Vezon	7538
Viane	9500
Vichte	8570
Vielsalm	6690
Viemme	4317
Viersel	2240
Vierset-Barse	4577
Vierves-sur-Viroin	5670
Viesville	6230
Vieux-Genappe	1472
Vieuxville	4190
Vieux-Waleffe	4530
Villance	6890
Ville-en-Hesbaye	4260
Ville-Pommeroeul	7322
Villerot	7334
Villers-aux-Tours	4161
Villers-Deux-Eglises	5630
Villers-Devant-Orval	6823
Villers-en-Fagne	5600
Villers-la-Bonne-Eau	6600
Villers-la-Loue	6769
Villers-la-Tour	6460
Villers-la-Ville	1495
Villers-le-Bouillet	4530
Villers-le-Gambon	5600
Villers-le-Peuplier	4280
Villers-le-Temple	4550
Villers-l'Evêque	4340
Villers-lez-Heest	5080
Villers-Notre-Dame	7812
Villers-Perwin	6210
Villers-Poterie	6280
Villers-Saint-Amand	7812
Villers-Sainte-Gertrude	6941
Villers-Saint-Ghislain	7031
Villers-Saint-Siméon	4453
Villers-sur-Lesse	5580
Villers-sur-Semois	6740
Ville-sur-Haine (Le Roeulx)	7070
Vilvoorde	1800
Vinalmont	4520
Vinderhoute	9921
Vinkem	8630
Vinkt	9800
Virelles	6461
Virginal-Samme	1460
Viroinval	5670
Virton	6760
Visé	4600
Vissenaken	3300
Vitrival	5070
Vivegnis	4683
Vivy	6833
Vlaamse Raad - Vlaams Parlement	1011
Vladslo	8600
Vlamertinge	8908
Vlekkem	9420
Vleteren	8640
Vlezenbeek	1602
Vliermaal	3724
Vliermaalroot	3721
Vlierzele	9520
Vlijtingen	3770
Vlimmeren	2340
Vlissegem	8421
Vloesberg	7880
Vodecée	5600
Vodelée	5680
Voeren	3790
Vogenée	5650
Volkegem	9700
Vollezele	1570
Vonêche	5570
Voorde	9400
Voormezele	8902
Voort	3840
Voroux-Goreux	4347
Voroux-lez-Liers	4451
Vorselaar	2290
Vorsen	3890
Vorst	1190
Vorst (Kempen)	2430
Vosselaar	2350
Vosselare	9850
Vossem	3080
Vottem	4041
Vrasene	9120
Vremde	2531
Vreren	3700
Vresse-sur-Semois	5550
Vroenhoven	3770
VRT	1043
VTM	1818
Vucht	3630
Vurste	9890
Vyle-et-Tharoul	4570
Waanrode	3473
Waarbeke	9506
Waardamme	8020
Waarloos	2550
Waarmaarde	8581
Waarschoot	9950
Waasmont	3401
Waasmunster	9250
Waasten	7784
Wachtebeke	9185
Wadelincourt	7971
Wagnelée	6223
Waha	6900
Waillet	5377
Waimes	4950
Wakken	8720
Walcourt	5650
Walem	2800
Walhain	1457
Walhain-Saint-Paul	1457
Walhorn	4711
Walsbets	3401
Walshoutem	3401
Waltwilder	3740
Wambeek	1741
Wancennes	5570
Wandre	4020
Wanfercée-Baulet	6224
Wange	3400
Wangenies	6220
Wanlin	5564
Wanne	4980
Wannebecq	7861
Wannegem-Lede	9772
Wansin	4280
Wanze	4520
Wanzele	9340
Warchin	7548
Warcoing	7740
Wardin	6600
Waregem	8790
Waremme	4300
Waret-la-Chaussée	5310
Waret-l'Evêque	4217
Warisoulx	5080
Warnant	5537
Warnant-Dreye	4530
Warneton	7784
Warquignies	7340
Warsage	4608
Warzée	4590
Wasmes	7340
Wasmes-Audemez-Briffoeil	7604
Wasmuel	7390
Wasseiges	4219
Waterland-Oudeman	9988
Waterloo	1410
Watermaal-Bosvoorde	1170
Watermael-Boitsfort	1170
Watervliet	9988
Watou	8978
Wattripont	7910
Waudrez	7131
Waulsort	5540
Wauthier-Braine	1440
Wavre	1300
Wavreille	5580
Wayaux	6210
Ways	1474
Webbekom	3290
Wechelderzande	2275
Weelde	2381
Weerde	1982
Weert	2880
Wegnez	4860
Weillen	5523
Weismes	4950
Welden	9700
Welkenraedt	4840
Welle	9473
Wellen	3830
Wellin	6920
Wemmel	1780
Wenduine	8420
Wépion	5100
Werbomont	4190
Werchter	3118
Wéris	6940
Werken	8610
Werm	3730
Wervik	8940
Wespelaar	3150
Westende	8434
Westerlo	2260
Westkapelle	8300
Westkerke	8460
Westmalle	2390
Westmeerbeek	2235
Westouter	8954
Westrem	9230
Westrozebeke	8840
Westvleteren	8640
Wetteren	9230
Wevelgem	8560
Wezemaal	3111
Wezembeek-Oppem	1970
Wezeren	3401
Wez-Velvain	7620
Wibrin	6666
Wichelen	9260
Widooie (Haren)	3700
Wiekevorst	2222
Wielsbeke	8710
Wierde	5100
Wiers	7608
Wiesme	5571
Wieze	9280
Wihéries	7370
Wihogne	4452
Wijchmaal	3990
Wijer	3850
Wijgmaal (Brabant)	3018
Wijnegem	2110
Wijshagen	3670
Wijtschate	8953
Wilderen	3803
Willaupuis	7904
Willebringen	3370
Willebroek	2830
Willemeau	7506
Willerzie	5575
Wilrijk (Antwerpen)	2610
Wilsele	3012
Wilskerke	8431
Wimmertingen	3501
Winenne	5570
Wingene	8750
Winksele	3020
Wintershoven	3722
Witry	6860
Wodecq	7890
Woesten	8640
Wolkrange	6780
Woluwe-Saint-Lambert	1200
Woluwe-Saint-Pierre	1150
Wolvertem	1861
Wommelgem	2160
Wommersom	3350
Wonck	4690
Wondelgem	9032
Wontergem	9800
Wortegem	9790
Wortegem-Petegem	9790
Wortel	2323
Woubrechtegem	9550
Woumen	8600
Wulpen	8670
Wulvergem	8952
Wulveringem	8630
Wuustwezel	2990
Xhendelesse	4652
Xhendremael	4432
Xhoris	4190
Yernée-Fraineux	4550
Yves-Gomezée	5650
Yvoir	5530
Zaffelare	9080
Zandbergen	9506
Zande	8680
Zandhoven	2240
Zandvliet	2040
Zandvoorde (Oostende)	8400
Zandvoorde (Zonnebeke)	8980
Zarlardinge	9500
Zarren	8610
Zaventem	1930
Zedelgem	8210
Zeebrugge (Brugge)	8380
Zegelsem	9660
Zele	9240
Zelem	3545
Zellik	1731
Zelzate	9060
Zemst	1980
Zepperen	3800
Zerkegem	8490
Zétrud-Lumay	1370
Zevekote	8470
Zeveneken	9080
Zeveren	9800
Zevergem	9840
Zichem	3271
Zichen-Zussen-Bolder	3770
Zillebeke	8902
Zingem	9750
Zoerle-Parwijs	2260
Zoersel	2980
Zolder	3550
Zomergem	9930
Zonhoven	3520
Zonnebeke	8980
Zonnegem	9520
Zottegem	9620
Zoutenaaie	8630
Zoutleeuw	3440
Zuidschote	8904
Zuienkerke	8377
Zulte	9870
Zulzeke	9690
Zutendaal	3690
Zwalm	9630
Zwevegem	8550
Zwevezele	8750
Zwijnaarde	9052
Zwijndrecht	2070
Antwerpen	2000
\.


--
-- Data for Name: group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "group" (name, description) FROM stdin;
tennis	Acquaintances from the tennis club
pfm	Persons involved in the pfm project
work	Acquaintances from work
cycling	Cycling companions
family	Members of the family
\.


--
-- Data for Name: memberlist; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY memberlist (person, "group") FROM stdin;
1	pfm
1	work
3	work
3	tennis
2	tennis
2	pfm
10	cycling
10	tennis
6	family
8	family
10	family
8	work
6	tennis
12	work
\.


--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY person (id, christian_name, name, street, town, "ZIPcode", country, category, description) FROM stdin;
1	Adriaan	Brouwers	De Coninckstraat 23	Zwevezele	8750	Belgium	acquaintance	\N
3	Nelly	Verdonck	Azalealaan 33	Lochristi	9080	Belgium	acquaintance	\N
4		PC winkeltje	Wapenstilstandlaan 67	Wommelgem	2160	Belgium	company	PC hardware
2	Albert	Van de Perre	Schanslaan 45	Berchem	2600	Belgium	acquaintance	Colleague from work
5		TEVE-electro	Wilgenlaan 98	Berchem	2600	Belgium	company	TV and HIFi shop
6	Hugo	Van Riel	Kerkstraat 56	Ranst	2520	Belgium	service	Medical Doctor
7	Marcel	Willekens	Schriek 34	Ranst	2520	Belgium	service	Dentist
9	Erik	Van Meensel	Kesselheide 34	Nijlen	2560	Belgium	acquaintance	
10	Norbert	Van Horebeke	Hoogstraat 3	Zottegem	9620	Belgium	acquaintance	
8	Nancy	Lemmens	Copernicuslaan 198	Gent	9000	Belgium	acquaintance	
12	Adri	Van Geluwe	Jan Breydelstraat 21	Aalbeke	8511	Belgium	acquaintance	
\.


--
-- Data for Name: pfm_attribute; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pfm_attribute (attribute, typeofattrib, typeofget, sqlselect, nr, form, valuelist, "default") FROM stdin;
linkname	taQuoted	tgDirect		1	pfm_link	none	\N
value	taQuoted	tgDirect	\N	2	pfm_value	none	\N
sqlwhere	taQuoted	tgDirect		4	pfm_link	none	\N
orderby	taQuoted	tgDirect		5	pfm_link	none	\N
displayattrib	taQuoted	tgDirect		6	pfm_link	none	\N
description	taQuoted	tgDirect		3	pfm_value	none	\N
name	taQuoted	tgDirect		1	pfm_report	none	\N
description	taQuoted	tgDirect		2	pfm_report	none	\N
report	taQuoted	tgLink	select name, description from pfm_report order by name	1	pfm_section	none	\N
nr	taNotQuoted	tgDirect		7	pfm_attribute	none	\N
name	taQuoted	tgDirect		1	pfm_form	none	\N
attribute	taQuoted	tgDirect		2	pfm_attribute	none	\N
sqlselect	taQuoted	tgDirect		5	pfm_attribute	none	\N
form	taQuoted	tgLink	SELECT name FROM pfm_form ORDER BY name	1	pfm_attribute	none	\N
fromform	taQuoted	tgLink	SELECT name FROM pfm_form ORDER BY name	2	pfm_link	none	\N
toform	taQuoted	tgLink	SELECT name FROM pfm_form ORDER BY name	3	pfm_link	none	\N
valuelist	taQuoted	tgLink	SELECT name FROM pfm_value_list ORDER BY name	1	pfm_value	none	\N
name	taQuoted	tgDirect		1	pfm_value_list	none	\N
christian_name	taQuoted	tgDirect		2	person	none	\N
name	taQuoted	tgDirect		3	person	none	\N
street	taQuoted	tgDirect		4	person	none	\N
town	taQuoted	tgDirect		5	person	none	\N
category	taQuoted	tgList		8	person	categories	\N
name	taQuoted	tgDirect		1	group	none	\N
description	taQuoted	tgDirect		2	group	none	\N
description	taQuoted	tgDirect		9	person	none	\N
person	taNotQuoted	tgLink	SELECT id, name, christian_name FROM person ORDER BY name, christian_name	1	memberlist	none	\N
christian_name	taQuoted	tgReadOnly		2	memberlist	none	\N
name	taQuoted	tgReadOnly		3	memberlist	none	\N
group	taQuoted	tgLink	SELECT name, description FROM "group" ORDER BY name	4	memberlist	none	\N
ZIPcode	taQuoted	tgDirect		1	ZIPcodes	none	\N
town	taQuoted	tgDirect		2	ZIPcodes	none	\N
ZIPcode	taQuoted	tgLink	SELECT "ZIPcode", town FROM "ZIPcodes" ORDER BY town	6	person	none	\N
sqlselect	taQuoted	tgDirect		3	pfm_report	none	\N
tablename	taQuoted	tgDirect		2	pfm_form	none	\N
sqlselect	taQuoted	tgDirect	\N	4	pfm_form	none	\N
sqlfrom	taQuoted	tgDirect	\N	5	pfm_form	none	\N
groupby	taQuoted	tgDirect		6	pfm_form	none	\N
pkey	taQuoted	tgDirect		3	pfm_form	none	
default	taQuoted	tgDirect		8	pfm_attribute	none	
typeofattrib	taQuoted	tgList		3	pfm_attribute	typeofattribute	taQuoted
typeofget	taQuoted	tgList	\N	4	pfm_attribute	typeofget	tgDirect
valuelist	taQuoted	tgLink	SELECT name FROM pfm_value_list ORDER BY name	6	pfm_attribute	none	none
id	taNotQuoted	tgDirect		1	person	none	=SELECT nextval('person_id_seq')
country	taQuoted	tgDirect		7	person	none	Belgium
help	taQuoted	tgDirect		11	pfm_form	none	\N
showform	taQuoted	tgList		9	pfm_form	boolean	t
view	taQuoted	tgList		10	pfm_form	boolean	f
sqlorderby	taQuoted	tgDirect		7	pfm_form	none	
sqllimit	taQuoted	tgDirect		8	pfm_form	none	
fieldlist	taQuoted	tgDirect		5	pfm_section	none	\N
level	taNotQuoted	tgDirect		3	pfm_section	none	1
layout	taQuoted	tgList		4	pfm_section	layout	table
summary	taQuoted	tgDirect		6	pfm_section	none	
sqlselect	taQuoted	tgReadOnly		2	pfm_section	none	
\.


--
-- Data for Name: pfm_form; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pfm_form (name, tablename, showform, view, sqlselect, sqlfrom, groupby, help, pkey, sqlorderby, sqllimit) FROM stdin;
group	group	t	f	name, description	"group"			name	\N	\N
memberlist	memberlist	t	f	memberlist."group", memberlist.person,\np.christian_name, p.name	memberlist LEFT OUTER JOIN person p ON (p.id = memberlist.person)			person group	\N	\N
ZIPcodes	ZIPcodes	t	f	"ZIPcode", town	"ZIPcodes"			ZIPcode town	\N	\N
pfm_value	pfm_value	f	f	value, description, valuelist	pfm_value	\N	The table "pfm_value" contains all the values of the lists defined in\npfm_value_list.\n\nIt has the following attributes:\n\n    - valuelist : the name of the valuelist to which this value belongs\n\n    - value : a character string;\n\n    - description : a description of the value.\n	valuelist value	valuelist, value	\N
pfm_value_list	pfm_value_list	f	f	name	pfm_value_list	\N	The table "pfm_value_list" contains all the value lists of all the forms.\n\nIts only attribute is\n\n    - name : a name uniquely identifying the value list.\n	name	name	\N
pfm_form	pfm_form	f	f	name, tablename, sqlselect, sqlfrom, groupby, showform, "view", help, pkey, sqlorderby, sqllimit	pfm_form	\N	A form allows the user to administer the data of just one table. This\ntable is henceforth referred to as "the form's main table".\n\nHowever, a form also has an SQL SELECT statement, which generates the\ndata that are displayed on it.\n\nIn the simplest case the SQL SELECT statement is just:\n\n    SELECT <attributes of main table> FROM <main table>\n\nIn that case, the data which can be administered and the data which\nare displayed on the form are the same.\n\nIn more complex cases, the <main table> can be JOINED with other\ntables, which makes it possible to display data of other related\ntables as well. These data cannot be modified by means of the form.\n\nThe table "pfm_form" has the following attributes:\n\n    - name : the name of the form (usually equal to the name of\n      the form's table);\n\n    - tablename : the name of the form's main table;\n\n    - pkey : the primary key of the form's main table, which may\n      consist of more than one attribute. In that case pkey is a SPACE\n      separated list of the attributes of the primary key;\n\n      Note: If pkey is empty, the form is read-only, since pfm is\n            unable to uniquely identify a record. You can use the\n            'oid' as primary key, but according to the PostgreSQL\n            documentation that is not recommended, unless you set a\n            UNIQUE constraint on the 'oid'.\n\n    - sqlselect : the attribute list of the form's SQL SELECT\n      statement, not including the word 'SELECT';\n\n    - sqlfrom : the FROM clause of the form's SQL SELECT statement,\n      not including the word 'FROM';\n\n    - groupby : an optional 'GROUP BY' clause, not including the words\n      'GROUP BY';\n\n    - sqlorderby : an optional 'ORDER BY' clause, not including the\n      words 'ORDER BY';\n\n    - sqllimit : an optional 'LIMIT' clause, only specifying the limit\n      value as a positive integer;\n\n      Notes:\n\n          - This enables the designer of the form to avoid excessive\n            memory usage by limiting the number of records loaded in\n            the form's internal buffer. This may be useful for\n            handling large tables.\n\n          - If sqllimit is a positive integer, a\n\n                   LIMIT sqllimit OFFSET 0\n\n            is added to the form's SELECT when opening the form.\n\n            This means that only 'sqllimit' records are loaded into\n            the form's internal buffer. When the user moves beyond the\n            last record in the internal buffer, the internal buffer is\n            first cleared and then reloaded with the next 'sqllimit'\n            records by re-executing the form's SELECT but now with\n            another OFFSET in the LIMIT clause.\n\n          - If sqllimit is an empty string, no LIMIT clause is\n            appended to the form's SELECT.\n\n          - Always specify an 'sqlorderby' if you specify an\n            'sqllimit'.  See PostgreSQL documentation of LIMIT-clause\n            in SELECT statement for more details.\n\n    - showform : a boolean indicating whether the form is shown\n      in "normal mode" (showform = 'true') or in "design mode"\n      (showform = 'false'). Typically, showform is set 'true' for user\n      defined forms and 'false' for the predefined pfm_* forms.\n\n    - view : a boolean indicating whether or not the\n      "tablename" is a view;\n\n    - help : a text which is displayed when the user presses\n      the [Help] key on the form.\n\nThe form's main table is defined by tablename. Only the data of\nthat table can be administered by using the form.\n\nAll the data generated by the form's SQL SELECT statement can be\ndisplayed on the form. The SQL SELECT statement is defined by:\n\n    - the sqlselect, sqlfrom, groupby, sqlorderby and sqllimit\n      attributes of pfm_form; and\n\n    - the optional WHERE and ORDER BY clauses provided by the user\n      when opening the form.\n\nNote: The WHERE clause provided by the user when opening the form, becomes\n      a HAVING clause, if there is a GROUP BY clause.\n\nThe following rules should be observed when filling out sqlselect and\nsqlfrom:\n\n    1. The form's main table must appear in 'sqlfrom', and must not be\n       aliased. Similarly, the main table's attributes appearing in\n       'sqlselect' must not be aliased. The other tables appearing in\n       the 'sqlfrom' may be aliased.\n\n    2. The fields appearing in 'sqlselect' must have a unique, simple\n       name without the need to precede them with a tablename. So,\n       calculated fields must be given a name by aliasing and\n       attributes of tables other than the main table may need to be\n       aliased in order to have a unique, simple name.\n\n    3. The 'sqlfrom' is either just the name of the form's main table,\n       or it is a JOIN clause in which the 'LEFT' table is the form's\n       main table. Several join clauses can be nested in order to\n       involve more than 2 tables. See examples below.\n\n\nExample 1: the SQL SELECT for the person form of the addressbook database\n\n\ntablename:\n    person\n\npkey:\n    id\n\nsqlselect:\n    id, christian_name, name, street, town, "ZIPcode",\n    country, category, description\n\nsqlfrom:\n    person\n\ngroupby:\n    -\n\n\nExample 2: the SQL SELECT for the memberlist form of the addressbook database\n\n\ntablename:\n    memberlist\n\npkey:\n    group person\n\nsqlselect:\n    memberlist."group", memberlist.person, p.christian_name, p.name\n\nsqlfrom:\n    memberlist LEFT OUTER JOIN person p ON (p.id = memberlist.person)\n\ngroupby:\n    -	name	showform DESC, name	\N
pfm_attribute	pfm_attribute	f	f	attribute, typeofattrib, typeofget, sqlselect, nr, form, valuelist, "default"	pfm_attribute	\N	The table "pfm_attribute" defines all the properties of form attributes.\n\nIt has the following attributes:\n\n    - form : the "name" of the form to which the attribute\n      belongs;\n\n    - attribute : the name of the attribute; this must be equal\n      to the name of the corresponding attribute of the form's SQL\n      SELECT statement;\n\n    - typeofattrib : the type of attribute:\n\n        o taQuoted: the value provided by the user is put\n             between single quotes when it is transferred to SQL\n             UPDATE or INSERT statements;\n            \n        o taNotQuoted: the value provided by the user is not\n             quoted when it is transferred to SQL UPDATE or INSERT\n             statements.\n\n          Hint: In general, all attribute values must be quoted, exept\n                the values or expressions for numeric attributes.\n\n    - typeofget: defines how the user provides a value for the\n      attribute; possible values are:\n\n          o tgDirect: the user types the value directly;\n\n          o tgExpression: the user types an expression which is first\n            evaluated before it is passed to SQL UPDATE or INSERT;\n\n            Note: Even with tgDirect it is possible to enter an\n                  expression as new value for an attribute, but then\n                  the expression is evaluated by postgresql whereas\n                  with tgExpression, the expression is first evaluated\n                  by Tcl before the SQL statement is sent to\n                  postgresql.\n\n          o tgList: the user selects a value by means of a list box\n            containing a list of values defined in table "pfm_value";\n\n          o tgLink: the user selects a value by means of a list box\n            containing a list of values which is the result from a\n            query on another table.\n\n          o tgReadOnly: this attribute cannot be modified by\n            the user.\n\n            Note: All calculated attributes and all attributes from\n                  tables other than the form's main table should be\n                  declared 'read-only'. If this rule is not observed,\n                  the Add and Update operations on this form will fail.\n\n    - sqlselect: the SQL SELECT statement which is used to fill the\n      list box with possible values for the attribute (only meaningful\n      if typeofget = tgLink).\n\n      Note :\n\n         o The sqlselect may return more than 1 attribute. If so, all\n           the attributes are displayed in the list-box, but only the\n           first one is used for updating the attribute.\n\n    - valuelist : the "name" of the value list defined in table\n      "pfm_value_list" (only meaningful if typeofget = tgList);\n\n    - nr: a number which determines the order in which attributes are\n      displayed on the form;\n\n    - default: a default value for this attribute which is used when\n      adding a record. If the first character is an '=' sign, the\n      following characters should be an SQL SELECT statement which\n      returns just one value.\n\n      Example:\n\n      default: =SELECT nextval('seq_person_id')\n\n      In this example the default value is the next value of the\n      sequenece 'seq_person_id'.\n	form attribute	form, nr	\N
pfm_link	pfm_link	f	f	linkname, sqlwhere, orderby, displayattrib, fromform, toform	pfm_link	\N	A link is a navigation tool which allows you to follow a "one-to-many"\nor "many-to-one" relationship from one form to another.\n\nEvery link is stored as a record in the pfm_link table, which has the\nfollowing attributes:\n\n    - linkname : the name of the link, which is displayed on\n      a link button on the "fromform";\n\n    - fromform : the name of the form from which the link\n      originates;\n\n    - toform : the name of the form to which the link leads;\n\n    - sqlwhere : the "WHERE"-clause which is used to open the\n      "toform" and in which the value of an attribute of the\n      "fromform" may be represented by $(attrib-x), where\n      'attrib-x' is the name of the attribute;\n\n    - orderby : an 'order by' clause which determines the order of the\n      records in the 'toform';\n\n    - displayattrib : a space separated list of\n      attributes of the 'fromform', the value of which is displayed on\n      the 'toform' to remind the user from which record the link\n      originated.\n\nNote: Postgres Forms does not provide any checks to safeguard\n      the referential integrity of the data base in case of updates or\n      deletions. However, postgreSQL provides these functions as\n      'foreign key' table constraints (see postgreSQL documentation).	fromform linkname	fromform, linkname	\N
pfm_report	pfm_report	f	f	name, description, sqlselect	pfm_report	\N	The table pfm_report defines all the reports for the current data\nbase.\n\npfm_report has the following attributes:\n\n    - name: the name of the report. This is the name that\n      appears in the selection list of the "Run Report" function.\n\n    - description: free text describing the purpose of the\n      report in more detail.\n\n    - sqlselect: an SQL SELECT statement that generates the\n      data for the report.\n\nThe sqlselect may contain one or more parameters for which a\nvalue is requested at "Run report" time. A parameter in the sqlwhere\nmust be formatted as $(parameter_name).\n\nExample:\n\nsqlselect: \n\n    SELECT g.name AS "group", g.description, p.id, p.name,\n           p.christian_name, p.street, p."ZIPcode", p.town, p.country\n    FROM "group" g\n       LEFT JOIN memberlist m ON g.name = m."group"\n       LEFT JOIN person p ON m.person = p.id\n    WHERE "group" = '$(group)'\n    ORDER BY g.name, p.name, p.christian_name\n\nWhen the report is run, the user is prompted to enter a value for the\nparameter "group". Then the report data are generated by executing the\nsqlselect statement in which $(group) is replaced with the value\nentered by the user.\n	name	name	\N
pfm_section	pfm_section	f	f	pfm_section.report, r.sqlselect, pfm_section."level", pfm_section.fieldlist, pfm_section.layout, pfm_section.summary	pfm_section LEFT OUTER JOIN pfm_report r ON (pfm_section.report = r.name)	\N	The data returned by the report's SQL SELECT statement may be\nconsidered as a table with a column for each 'field' specified after\nthe word 'SELECT' and with a row for each record.\n\nBy specifying an 'ORDER BY' clause in the report's SQL SELECT\nstatement, it is possible to group rows with the same values for some\nfields together.\n\nThe report generator has an "economy" algorithm which avoids printing\nthe same data repeatedly.\n\nTo control this you have to distribute the fields (columns) of the\ntable over n sections such that section 1 contains the fields that are\nchanging least frequently (when moving from one row to the next),\nsection 2 contains the fields that are changing more frequently, and\nsection n contains the fields that are changing at every row.\n\nWhen the data of the first row of the table are printed, the data of\nsection 1 are printed first. Then, on the following line, indented by\none tab stop, the data of section 2 are printed. Then, on the\nfollowing line, indented by 2 tab stops, data of section 2 are\nprinted, etc.\n\n[section 1] <--- row 1\n\n    [section 2] <--- row 1\n\n        [section 3]  <--- row 1\n\nThen, when the next rows are being printed, data of the lower numbered\nsections are only printed if they are different from the data of the\nlast printed section of the same number:\n\n[section 1]\n\n    [section 2]\n\n        [section 3]  <--- row 1\n        [section 3]  <--- row 2\n        [section 3]  <--- row 3\n\n    [section 2]\n\n        [section 3]  <--- row 4\n        [section 3]  <--- row 5\n\n[section 1]\n\n    [section 2]\n\n        [section 3]  <--- row 6\n        [section 3]  <--- row 7\n\nThe report generator also enables you to print a summary at every\npoint where a higher numbered section is about to be followed by a\nlower numbered section:\n\n[section 1]\n\n    [section 2]\n\n        [section 3]  <--- row 1\n        [section 3]  <--- row 2\n        [section 3]  <--- row 3\n\n        [summary 3]\n\n    [section 2]\n\n        [section 3]  <--- row 4\n        [section 3]  <--- row 5\n\n        [summary 3]\n\n    [summary 2]\n\n[section 1]\n\n    [section 2]\n\n        [section 3]  <--- row 6\n        [section 3]  <--- row 7\n\n        [summary 3]\n\n    [summary 2]\n\n[summary 1]\n\nA summary i is printed just before a lower numbered section j (j < i).\nIts data can be calculated:\n\n    - by applying one of the aggregate funtions: COUNT, SUM, AVG,\n      STDDEV, MIN, MAX;\n\n    - on the fields of the sections j (j >= i), between the last\n      printed lower numbered section k (k < i), till the next (not\n      yet printed) lower numbered section k (k < i).\n\nIn particular, summary 1 is printed at the end of the report, is\ncalculated from all the sections of the report and may be calculated\nfrom all the fields.\n\nA record in pfm_section defines a section and a summary of a report.\n\nThe table pfm_section has the following attributes:\n\n    - report: the name of the report to which the section belongs\n\n    - level: a number 1, 2, 3, 4, ... . The first level must be\n      '1'. The next levels must be numbered consecutively. In the most\n      simple report, there is only a section with level 1.\n\n    - layout: can be "row", "column" or "table".\n\n    - fieldlist: a space separated list of field specifiers,\n      one for each field to be printed in the sections of this level\n      (see below for details).\n\n    - summary: a space separated list of summary field\n      specifiers (see below for details).\n\nThe fieldlist is a SPACE separated list of field specifiers\n\n    field_spec_1 field_spec_2 ... field_spec_N\n\nwhere each field specifier is formatted as follows:\n\n    {field_i label_i alignment_i max_length_i}\n\nwhere :\n\n    - field_i is the name of one of the columns returned by the\n      report's SQL SELECT statement;\n\n    - label_i is a string which has to be used as label for printing\n      the i-th field of this section; if it consists of more than 1\n      word, it must be delimited by double quotes (" .... ");\n\n    - alignment_i is optional; if present, it is either l or r,\n      indicating whether this field should be left or right aligned.\n\n    - max_length_i is optional: if present, it is the maximum number\n      of characters per line for printing the data of this field;\n      lines longer than max_length_i will be wrapped by inserting\n      one or more line breaks before printing.\n\n      Notes :\n\n          o The alignment is optional. If it is left out, left\n            alignment is assumed by default.\n\n          o The alignment only influences the table layout. Column and\n            row layouts are unaffected by the alignment indicator.\n\n          o Multi-line fields, i.e. fields containing more than one\n            line of text are only formatted properly in a column or\n            table layout.\n\n          o For a table layout, pfm automatically calculates the column\n            width that is required to display all data. So, normally\n            you don't have to worry about column widths. However,\n            sometimes, the data of a few records, make the columns\n            excessively wide. That is where you might consider using\n            "max_length_i" in the field specifier. If the data do not\n            exceed that maximum, it won't have any effect.\n\n          o Although 'alignment' and 'max_length' are both optional,\n            you have to specify 'alignment' if you want to specify\n            max_length.\n\nFor every section, the layout can be defined as:\n\n    - row: the section's field labels and field values are\n      printed in one row in a format: label_1 : value_1; label_2 :\n      value_2; ... etc.\n\n    - column: the section's field labels are printed in a first\n      column, the section's field values are printed in a second column.\n\n    - table: the section's values are printed in a table with a\n      column per field and a row per record, the section's field\n      labels are used as column headers for the table.\n\nThe summary must be formatted as a space separated list of summary\nspecifiers:\n\n    summary_spec_1 summary_sepc_2 .... summary_sepc_N\n\nwhere each summary_spec is formatted as follows:\n\n    {field_i aggregate_i format_i}\n\nwhere:\n\n    - field_i is the name of a field defined in the fieldlist of\n      either this section, or another, higher numbered section;\n\n    - aggregate_i is one of the aggregate functions: COUNT, SUM, AVG,\n      STDDEV, MIN, MAX (see below for details); and\n\n    - format_i is an optional 'ANSI C sprintf' formatting string (see\n      below for details). If it is left out, the number is printed\n      with maximum precision.\n\n\nAggregate functions:\n\nIn general, the aggregate functions, use the same "economy" algorithm\nthat is used for printing section data.\n\nWhen all the fields of a section, which is not the highest numbered\nsection of the report, have the same values for a number of\nconsecutive rows, this section's data are only printed once for these\nrows.\n\nSimilarly, these rows are only counted once by the aggregate functions\napplied to a field of this section.\n\nThe aggregate functions that can be used in a summary are:\n\n    - COUNT: Counts the number of rows. In this case, the field_i that\n           is specified only determines which section is counted.\n\n    - SUM: Calculates the sum of all the values of the specified\n           field.\n\n    - AVG: Calculates the average of the values of the specified\n           field.\n\n    - STDDEV: Calculates the sample standard deviation for the values of the\n           specified field:\n\n           SQRT (SUM( (value_i - AVG(value))**2 ) / (N - 1))\n\n\t   where :\n\n               - value_1, value_2, ... value_N are the values of the\n                 considered field;\n\n               - AVG(value) is the average of the considered values;\n\n               - N is the number of values.\n\n    - MIN: Calculates the minimum of the values of the specified\n           field.\n\n    - MAX: Calculated the maximum of the values of the specified\n           field.\n\n\n'ANSI C sprintf' formatting string:\n\nHere is a short overview of the 'ANSI C sprintf' formatting string. In\ngeneral its form is:\n\n     %'MinWidth'.'Precision''Conversion'\n\nwhere:\n\n    - 'MinWidth' is an integer defining the minimum width (as number\n      of characters) for the number to be printed. If the number does\n      not need so much space, spaces are inserted in front of the\n      number, unless MinWidth is negative. In that case, spaces are\n      appended at the end. If the number needs more space than\n      MinWidth, more space is used.\n\n    - 'Precision' is an integer defining how many digits to print\n      after the decimal point, or, in the case of g or G conversion,\n      the total number of digits to appear, including those on both\n      sides of the decimal point\n\n    - 'Conversion' is one of:\n\n          o d : convert integer to signed decimal string. In this case,\n                there is no need to define a 'Precision'.\n\n                Example: %1d\n\n                         prints an integer and uses as many characters\n                         as required.\n\n          o f : convert floating point number to fixed point\n                notation. In this case, 'Precision' defines the number\n                of digits to print after the decimal point. If there\n                are not enough digits available, trailing zeroes are\n                appended.\n\n                Example: %1.2f\n\n                         prints a floating point number wiht 2 digits\n                         after the decimal point and uses as many\n                         characters as required.\n\n          o e or E : Convert floating-point number to scientific\n                notation in the form x.yyye±zz, where the number of\n                y's is determined by the 'Precision' (default: 6). If\n                the precision is 0 then no decimal point is output. If\n                the E form is used then E is printed instead of e.\n\n                Example: %1.5E\n\n                         prints a floating point number in the form\n                         x.yyyyy E±zz \n\n          o g or G : If the exponent is less than -4 or greater than\n                or equal to the precision, then convert floating-point\n                number as for %e or %E. Otherwise convert as for\n                %f. Trailing zeroes and a trailing decimal point are\n                omitted. In this case the 'Precision' specifies the\n                total number of digits to appear, including those on\n                both sides of the decimal point\n\n                Example: %1.4G\n\n                          prints 2345.0 as 2345\n                          prints 234567.0 as 2.346E+05\n                          prints 0.003456 as 0.003456\n                          prints 0.00003456 as 3.456E-05	report level	report, "level"	\N
person	person	t	f	id, christian_name, name, street, town, "ZIPcode", country, category, description	person		When adding another person, a new id will be created automatically.\n\nWhen entering the ZIPcode, you can\n\n    - either select it from the list, by clicking on the ZIP-code\n      button;\n\n    - or type directly by clicking on the 'Expand' button, at the right\n      of the ZIPcode.\n\nUp to now, only the ZIP codes for Belgium have been inserted.	id	\N	\N
\.


--
-- Data for Name: pfm_link; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pfm_link (linkname, sqlwhere, orderby, displayattrib, fromform, toform) FROM stdin;
Report	name='$(report)'		level	pfm_section	pfm_report
Sections	report='$(name)'	level	name	pfm_report	pfm_section
Attributes	form='$(name)'	nr	name	pfm_form	pfm_attribute
incoming links	toform='$(name)'	fromform	name	pfm_form	pfm_link
outgoing links	fromform='$(name)'	toform	name	pfm_form	pfm_link
Where used?	valuelist='$(name)'		name	pfm_value_list	pfm_attribute
Values	valuelist='$(name)'	value	name	pfm_value_list	pfm_value
Value list	name='$(valuelist)'		attribute	pfm_attribute	pfm_value_list
from Form	name='$(fromform)'		linkname	pfm_link	pfm_form
to Form	name='$(toform)'		linkname	pfm_link	pfm_form
Valuelist	name='$(valuelist)'		value	pfm_value	pfm_value_list
Person	id = $(person)		"group"	memberlist	person
Groups	person = $(id)	"group"	christian_name name	person	memberlist
Members	"group" = '$(name)'	name, christian_name	name	group	memberlist
Group	name = '$(group)'		christian_name name group	memberlist	group
Form	name='$(form)'		attribute	pfm_attribute	pfm_form
\.


--
-- Data for Name: pfm_report; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pfm_report (name, description, sqlselect) FROM stdin;
DefinedForms	Report showing the form definitions of this database	SELECT f.name, f.tablename, f.sqlselect AS "SELECT", f.sqlfrom AS "FROM",\n       f.groupby AS "GROUP BY", f.sqlorderby AS "ORDER BY",\n       f.sqllimit AS "LIMIT", f.pkey AS "PRIM. KEY", f.showform, f."view",\n       a.attribute, a.typeofattrib, a.typeofget, a.sqlselect, a.nr,\n       a.valuelist, a."default"\nFROM pfm_form f LEFT OUTER JOIN pfm_attribute a ON (f.name = a.form)\nORDER BY f.showform DESC, f.name, a.nr
DefinedLinks	Report showing the link definitions of this database	SELECT linkname, sqlwhere, orderby, displayattrib, fromform , toform\nFROM pfm_link\nORDER BY fromform, linkname
FormHelp	Report showing the on-line help for forms defined in this database	SELECT name, help\nFROM pfm_form\nWHERE help <> ''\nORDER BY showform DESC, name
DefinedReports	Report showing the report definitions of this database	SELECT r.name, r.description, r.sqlselect,\n       s."level", s.fieldlist, s.layout, s.summary\nFROM pfm_report r LEFT OUTER JOIN pfm_section s ON (r.name = s.report)\nORDER BY r.name
Persons and groups	Lists all persons and the groups to which they belong	SELECT p.id, p.christian_name, p.name, p.street, p.town, p."ZIPcode",\n       p.country, p.category, p.description, g.name AS "group"\nFROM person p\n    LEFT JOIN memberlist m ON p.id = m.person\n    LEFT JOIN "group" g ON m."group" = g.name\nORDER BY p.name, p.christian_name, g.name
Groups and persons	Lists all groups and their members	SELECT g.name AS "group", g.description, p.id, p.name, p.christian_name,\n       p.street, p."ZIPcode", p.town, p.country\nFROM "group" g\n   LEFT JOIN memberlist m ON g.name = m."group"\n   LEFT JOIN person p ON m.person = p.id\nORDER BY g.name, p.name, p.christian_name
Group	List all the members of a certain group	SELECT g.name AS "group", g.description, p.id, p.name,\n       p.christian_name, p.street, p."ZIPcode", p.town, p.country\nFROM "group" g\n   LEFT JOIN memberlist m ON g.name = m."group"\n   LEFT JOIN person p ON m.person = p.id\nWHERE "group" = '$(group)'\nORDER BY g.name, p.name, p.christian_name
\.


--
-- Data for Name: pfm_section; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pfm_section (report, level, fieldlist, layout, summary) FROM stdin;
Groups and persons	1	{group group l} {description description l}	row	{group COUNT}
Groups and persons	2	{id id r} {christian_name "Chr. name" l} {name name l} {street street l} {ZIPcode ZIP l} {town town l} {country country l}	table	{id COUNT}
Group	1	{id id r} {christian_name "chr. name" l} {name name l} {street street l} {ZIPcode ZIP l} {town town l} {country country l}	table	{id COUNT}
Persons and groups	2	{group groups l}	table	{group COUNT}
Persons and groups	1	{id id r} {name name l} {christian_name "Chr. name" l} {street street l} {ZIPcode ZIP l} {town town l} {country country l}	table	{id COUNT}
DefinedForms	1	{name Form l} {tablename Table l} {SELECT SELECT l} {FROM FROM l} {"GROUP BY" "GROUP BY" l} {"ORDER BY" "ORDER BY" l} {LIMIT LIMIT l} {"PRIM. KEY" "PRIM. KEY" l} {showform showform l} {view view l}	column	
DefinedLinks	2	{linkname linkname l} {toform toform l} {sqlwhere sqlwhere l 30} {orderby orderby l 30} {displayattrib displayattrib l}	table	{linkname COUNT}
DefinedLinks	1	{fromform "Links from form" l}	row	{fromform COUNT}
DefinedReports	1	{name Report} {description Description} {sqlselect SQL}	column	
DefinedReports	2	{level Section r} {fieldlist fieldlist l 40} {layout layout l} {summary summary l 30}	table	
FormHelp	1	{name Form}	row	
FormHelp	2	{help "On line help" l 80}	table	
DefinedForms	2	{nr nr r} {attribute attribute l} {typeofattrib typeofattrib l} {typeofget typeofget l} {sqlselect sqlselect l 30} {valuelist valuelist l} {default default l 30}	table	
\.


--
-- Data for Name: pfm_value; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pfm_value (value, description, valuelist) FROM stdin;
taQuoted	Value must be enclosed in ' ' for SQL.	typeofattribute
taNotQuoted	Value must not be enclosed in ' ' for SQL.	typeofattribute
tgDirect	Value directly typed by user.	typeofget
tgExpression	Value may be given as an expression.	typeofget
tgList	Value comes from a valuelist.	typeofget
tgLink	Value comes from 'sqlselect'.	typeofget
t	TRUE	boolean
f	FALSE	boolean
column	A column for the labels, a second column for the corresponding values	layout
table	A table with the labels as table header	layout
row	Labels and values on 1 row	layout
tgReadOnly	User cannot change the value of this attribute	typeofget
acquaintance	A personal acquaintance	categories
company	A company	categories
service	A service, or a person providing a service	categories
\.


--
-- Data for Name: pfm_value_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pfm_value_list (name) FROM stdin;
typeofattribute
typeofget
boolean
layout
none
categories
\.


--
-- Data for Name: pfm_version; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pfm_version (seqnr, version, date, comment) FROM stdin;
1	1.5.0	2017-11-07	install_addressbook.sql
\.


--
-- Data for Name: unit_prefixes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY unit_prefixes  FROM stdin;
\.


--
-- Data for Name: unit_units; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY unit_units  FROM stdin;
\.


--
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('person_id_seq', 12, true);


--
-- Name: pfm_version_seqnr_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pfm_version_seqnr_seq', 1, true);


--
-- Name: ZIPcodes ZIPcodes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "ZIPcodes"
    ADD CONSTRAINT "ZIPcodes_pkey" PRIMARY KEY ("ZIPcode", town);


--
-- Name: group group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "group"
    ADD CONSTRAINT group_pkey PRIMARY KEY (name);


--
-- Name: memberlist memberlist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY memberlist
    ADD CONSTRAINT memberlist_pkey PRIMARY KEY (person, "group");


--
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- Name: pfm_attribute pfm_attribute_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pfm_attribute
    ADD CONSTRAINT pfm_attribute_pkey PRIMARY KEY (form, attribute);


--
-- Name: pfm_form pfm_form_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pfm_form
    ADD CONSTRAINT pfm_form_pkey PRIMARY KEY (name);


--
-- Name: pfm_link pfm_link_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pfm_link
    ADD CONSTRAINT pfm_link_pkey PRIMARY KEY (fromform, linkname);


--
-- Name: pfm_report pfm_report_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pfm_report
    ADD CONSTRAINT pfm_report_pkey PRIMARY KEY (name);


--
-- Name: pfm_section pfm_section_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pfm_section
    ADD CONSTRAINT pfm_section_pkey PRIMARY KEY (report, level);


--
-- Name: pfm_value_list pfm_value_list_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pfm_value_list
    ADD CONSTRAINT pfm_value_list_pkey PRIMARY KEY (name);


--
-- Name: pfm_value pfm_value_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pfm_value
    ADD CONSTRAINT pfm_value_pkey PRIMARY KEY (valuelist, value);


--
-- Name: pfm_version pfm_version_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pfm_version
    ADD CONSTRAINT pfm_version_pkey PRIMARY KEY (seqnr);


--
-- Name: pfm_attribute ref_form; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pfm_attribute
    ADD CONSTRAINT ref_form FOREIGN KEY (form) REFERENCES pfm_form(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: pfm_link ref_fromform; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pfm_link
    ADD CONSTRAINT ref_fromform FOREIGN KEY (fromform) REFERENCES pfm_form(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: memberlist ref_group; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY memberlist
    ADD CONSTRAINT ref_group FOREIGN KEY ("group") REFERENCES "group"(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: pfm_value ref_list; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pfm_value
    ADD CONSTRAINT ref_list FOREIGN KEY (valuelist) REFERENCES pfm_value_list(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: memberlist ref_person; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY memberlist
    ADD CONSTRAINT ref_person FOREIGN KEY (person) REFERENCES person(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: pfm_section ref_sections; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pfm_section
    ADD CONSTRAINT ref_sections FOREIGN KEY (report) REFERENCES pfm_report(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: pfm_link ref_toform; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pfm_link
    ADD CONSTRAINT ref_toform FOREIGN KEY (toform) REFERENCES pfm_form(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: pfm_attribute ref_value_list; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pfm_attribute
    ADD CONSTRAINT ref_value_list FOREIGN KEY (valuelist) REFERENCES pfm_value_list(name) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

