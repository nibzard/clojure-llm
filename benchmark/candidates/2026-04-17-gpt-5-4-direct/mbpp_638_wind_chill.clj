(defn wind_chill
  "	Write a function to calculate the wind chill index rounded to the next integer given the wind velocity in km/h and a temperature in celsius."
  [v t]
  (int
   (Math/ceil
    (+ 13.12
       (* 0.6215 t)
       (* (- 0.3965 t 11.37) (Math/pow v 0.16))))))