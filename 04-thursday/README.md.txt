Roddede med lidt tests med @BeforeClass og @AfterClass annotationer og fik det til at virke nogenlunde...
Lavede de annotationer, da jeg ellers ville have problemer med min testdata, da min persistence unit ikke stod som drop and create. 
Stod den til drop and create ville jeg ikke have noget data i mine endpoints når jeg tilgik dem.
Endte med at fjerne tests da det var lidt halvhjerte - måske jeg skulle have beholdt dem??