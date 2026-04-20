(defn wind_chill
  "	Write a function to calculate the wind chill index rounded to the next integer given the wind velocity in km/h and a temperature in celsius."
  [v t]
  (Math/ceil
   (- (+ (* 13.12 1.0)
         (* 0.6215 t)
         (* -11.37 (Math/pow v 0.16)))
      (* 0.3965 t (Math/pow v 0.16)))))