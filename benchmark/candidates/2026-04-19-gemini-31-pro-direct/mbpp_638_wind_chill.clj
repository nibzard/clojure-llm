(defn wind_chill
  "Write a function to calculate the wind chill index rounded to the nearest integer given the wind velocity in km/h and a temperature in celsius."
  [v t]
  (let [v-pow (Math/pow v 0.16)
        chill (+ 13.12 
                 (* 0.6215 t) 
                 (* -11.37 v-pow) 
                 (* 0.3965 t v-pow))]
    (Math/round chill)))