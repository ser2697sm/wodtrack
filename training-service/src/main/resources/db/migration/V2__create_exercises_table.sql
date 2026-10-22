-- V2: catálogo de ejercicios (training-service)
-- type indica cómo se mide el ejercicio y, por tanto, qué es una mejor marca:
--   WEIGHT -> kg, cuanto más mejor (sentadilla, peso muerto...)
--   TIME   -> segundos, cuanto menos mejor (WODs como Fran, Grace...)
--   REPS   -> repeticiones, cuanto más mejor (dominadas máximas...)

CREATE TABLE exercises (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(100)  NOT NULL,
    type        VARCHAR(20)   NOT NULL,
    created_at  TIMESTAMPTZ   NOT NULL DEFAULT now(),

    CONSTRAINT uk_exercises_name UNIQUE (name),
    CONSTRAINT ck_exercises_type CHECK (type IN ('WEIGHT', 'TIME', 'REPS'))
);

-- Ejercicios iniciales para poder probar la API sin crear datos a mano
INSERT INTO exercises (name, type) VALUES
    ('Back Squat',   'WEIGHT'),
    ('Front Squat',  'WEIGHT'),
    ('Deadlift',     'WEIGHT'),
    ('Clean & Jerk', 'WEIGHT'),
    ('Snatch',       'WEIGHT'),
    ('Fran',         'TIME'),
    ('Grace',        'TIME'),
    ('Pull-ups',     'REPS');
