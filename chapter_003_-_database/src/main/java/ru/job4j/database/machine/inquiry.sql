select t1.brandD brand, t1.body_type body, t1.engine_fuel_type fuel, t1.gearbox_type gearbox
from (((select machine.brand          brandD,
               machine.engine_vin     mev,
               machine.body_vin       mbv,
               machine.gearbox_number mgn,
               body.vin               body_vin,
               type                   body_type
        from machine
                 left join body on machine.body_vin = body.vin) b
    join
    (select machine.brand          brandE,
            machine.engine_vin     mev,
            machine.body_vin       mbv,
            machine.gearbox_number mgn,
            engine.vin             engine_vin,
            fuel_type              engine_fuel_type
     from machine
              left join engine on machine.engine_vin = engine.vin) e on b.brandD = e.brandE) be
    join
    (select machine.brand          brandG,
            machine.engine_vin     mev,
            machine.body_vin       mbv,
            machine.gearbox_number mgn,
            gearbox.number         gearbox_number,
            gearbox.type           gearbox_type
     from machine
              left join gearbox on machine.gearbox_number = gearbox.number) g on be.brandD = g.brandG
         ) t1
;

select body.type details, body.vin unique_number
from machine
         full join
     body on machine.body_vin = body.vin
where machine.body_vin is null
UNION
select engine.fuel_type details, engine.vin unique_number
from machine
         full join
     engine on machine.engine_vin = engine.vin
where machine.engine_vin is null
UNION
select gearbox.type details, gearbox.number unique_number
from machine
         full join
     gearbox on machine.gearbox_number = gearbox.number
where machine.gearbox_number is null
;

