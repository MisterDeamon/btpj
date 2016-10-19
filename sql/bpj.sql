
table gp_agc_std_bale
blue_bale_id  bale_id blae_code agency_code pay_union_code

table gp_agc_bale
bale_id blue_id bale_name bale_code bale_name_en depict status sale_date_fst sale_date_last

table gp_agc_agency_blue
blue_bale_id blue_id parent_id std_bale_id card_id status is_templet fcd

select * from gp_agc_std_bale where bale_code = '7B28'
select * from gp_agc_bale where bale_id = 41266
select * from gp_agc_agency_blue where blue_id = 49084;




SELECT b.blue_bale_id,c.bale_id,c.bale_code,a.agency_code,a.pay_union_code
FROM gp_age_agency_blue a 
LEFT JOIN gp_agc_bale b ON  a.blue_id = b.blue_id 
LEFT JOIN gp_agc_std_bale c ON b.std_bale_id = c.bale_id
WHERE a.agency_code = 'B0106200' AND c.bale_code = '7B28';

select * from  gp_age_agency_blue a where a.agency_code = 'B0106200';