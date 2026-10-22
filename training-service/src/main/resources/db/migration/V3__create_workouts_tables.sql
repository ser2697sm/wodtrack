-- V3: entrenos (training-service)
-- Un entreno (workouts) pertenece a un atleta y a un día.
-- Cada entreno tiene una o varias líneas (workout_entries): un ejercicio y el valor conseguido.
-- El valor se interpreta según exercises.type: WEIGHT -> kg, TIME -> segundos, REPS -> repeticiones.

CREATE TABLE workouts (
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    athlete_id    BIGINT        NOT NULL,
    workout_date  DATE          NOT NULL,
    notes         VARCHAR(500),
    created_at    TIMESTAMPTZ   NOT NULL DEFAULT now(),

    CONSTRAINT fk_workouts_athlete FOREIGN KEY (athlete_id) REFERENCES athletes (id)
);

-- Consulta principal: entrenos de un atleta en un rango de fechas
CREATE INDEX idx_workouts_athlete_date ON workouts (athlete_id, workout_date);

CREATE TABLE workout_entries (
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    workout_id   BIGINT         NOT NULL,
    exercise_id  BIGINT         NOT NULL,
    value        NUMERIC(8, 2)  NOT NULL,

    CONSTRAINT fk_workout_entries_workout  FOREIGN KEY (workout_id)  REFERENCES workouts (id) ON DELETE CASCADE,
    CONSTRAINT fk_workout_entries_exercise FOREIGN KEY (exercise_id) REFERENCES exercises (id),
    CONSTRAINT ck_workout_entries_value_positive CHECK (value > 0)
);

CREATE INDEX idx_workout_entries_workout ON workout_entries (workout_id);
